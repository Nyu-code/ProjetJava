

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ClientProcessor extends JFrame implements Runnable{
	
// le coeur du serveur;
	private PersonnageJoueur p;
	private PersonnageNonJoueur pnj;
	private Socket socket;
	private PrintWriter writer = null;
	private BufferedInputStream reader = null;
	private String [] Boutons = {"Nouvelle Partie", "Charger une partie", "Quitter le jeu"};
	
	
	public ClientProcessor(Socket Sock){
      socket = Sock;
   }
   
   //Le traitement lancé dans un thread séparé
   public void run(){
      System.err.println("Lancement du traitement de la connexion cliente");

      boolean closeConnexion = false;
      
      //tant que la connexion est active, on traite les demandes
      // isClosed() permet de retourner un boolean qui nous dit si le socket est fermé ou pas.
      while(!socket.isClosed()){
         
         try {
        	 
            writer = new PrintWriter(socket.getOutputStream());
            reader = new BufferedInputStream(socket.getInputStream());
            
            //On attend la demande du client
            String réponse = read();
            System.out.println("En attente de la demande du client");
            
            //On traite la demande du client en fonction de la commande reçu
            String toSend = "";
            
            switch(réponse){
            
               case "0":
                  Map m = new Map();
                  System.out.println(m);
                  break;
                  
               case "1":
                  Charger();
                  break;
                  
               case "2":
                  toSend = "Le jeu se ferme";
                  closeConnexion = true;
                  break;
                  
               default : 
                  toSend = "Commande inconnu !";
                  break;
            }
            
            //On envoie la réponse au client
            writer.write(toSend);
            writer.flush();
            
            if(closeConnexion){
               System.out.println("Le jeu se ferme !");
               writer = null;
               reader = null;
               socket.close();
               break;
            }
         }catch(SocketException e){
            System.err.println("LA CONNEXION A ETE INTERROMPUE ! ");
            break;
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }
	public void Combat(PersonnageJoueur p1, PersonnageNonJoueur pnj) {
		//Tant que le joueur est en combat la boucle tourne
		verifTour(p1, pnj);
		while (p1.enCombat) {
			
		if (p1.isTon_tour()) {
			p1.affichePA();
			p1.faireAction();
			if (verifMort(p1)) { //Si le joueur est mort on lui demande ce qu'il veut faire
				p1.setEnCombat();
				demande_joueur();
			}
			else if (pnj.getHp() <= 0) { //Si le monstre est battu le joueur récupère les exp du monstre
				p1.setEnCombat();
				p1.setExp(p1.getExp()+pnj.getExp());
			}
			p1.setTon_tour(false); // à la fin du tour du joueur on lui enlève la priorité
		}
		
		else { // tour du pnj
			pnj.attaque_joueur(p1);
			
			if (verifMort(p1)) { //Si le joueur est mort durant l'attaque du pnj on lui demande ce qu'il veut faire
				p1.setEnCombat();
				demande_joueur();
			}
			p1.setTon_tour(true); // au tour du joueur
		}
	}
}
	public void verifTour(PersonnageJoueur p1, PersonnageNonJoueur pnj) { //permet de vérifier qui commence au début du tour
		if (p1.getInit() > pnj.getInit()) { //Si l'initiative du joueur > init du pnj alrs p1 joue avant pnj
			p1.setTon_tour(true);
		}
		else {
			pnj.setTon_tour(true);
		}
	}
   public void Charger() {
		JFileChooser select = new JFileChooser(""); // à déterminer l'endroit où sera les parties sauvegarder
		select.addChoosableFileFilter(new FiltreExtensionFichier()); //On filtre le fichier selectionner par des .ser uniquement
		int res = select.showDialog(this, "Charger");
		String namefile = "";
		//On vérifie la commande envoyé par le joueur
		if (res == JFileChooser.APPROVE_OPTION) {
			File f	= select.getSelectedFile();
			System.out.println("Nom du fichier selectionné : " + f.getName());
			namefile = f.getName();
		}
		//On stop tout si le joueur ne souhaite plus charger;
		else {
			System.out.println("Chargement de partie annulé");
			return;
		}//On désérialise le fichier et on les mets dans chaque object pour relancer la partie avec ces données
		try(FileInputStream fis = new FileInputStream(namefile);
				ObjectInputStream ois = new ObjectInputStream(fis)){
			PersonnageJoueur p1 = (PersonnageJoueur) ois.readObject();
			Map m = (Map) ois.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//On relance le jeu avec les données récupérer
		//A COMPLETER
		
   }
   public boolean verifMort(PersonnageJoueur p1) {
		if (p1.getBlessure() == "inconscient") { //le combat s'arrête si le joueur est mort
			return true;
		}
		else {
			return false;
		}
	}
   public void demande_joueur() {// permet de demander au joueur après la mort ce qu'il veut faire
	   ImageIcon icon = new ImageIcon("mort.png");
	int choix = JOptionPane.showOptionDialog(this, "Vous êtes mort au combat",
			   "état d'urgence", JOptionPane.DEFAULT_OPTION,
			   JOptionPane.WARNING_MESSAGE, icon , Boutons ,Boutons[0]);
	if (choix == 0) {
		// redemarre le jeu avec une nouvelle partie
		//A COMPLETER
	}
	else if (choix == 1) {
		Charger();
	}
	else { //On arrete la connexion du client avec le serveur si il souhaite quitter le jeu
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
   }
   
   //La méthode que nous utilisons pour lire les réponses
   private String read() throws IOException{      
      String réponse = "";
      int stream;
      byte[] b = new byte[4096];
      stream = reader.read(b);
      réponse = new String(b, 0, stream);
      return réponse;
   }
   
}