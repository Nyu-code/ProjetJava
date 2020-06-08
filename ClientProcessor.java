

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ClientProcessor extends JFrame implements Runnable{
	
// le coeur du serveur;
	private PersonnageJoueur p;
	private PersonnageNonJoueur pnj;
	private Socket socket;
	private DataOutputStream out = null;
	private DataInputStream in = null;
	private String [] Boutons = {"Nouvelle Partie", "Charger une partie", "Quitter le jeu"};
	private boolean start = false;
	private static ArrayList<PersonnageJoueur> Liste_Joueurs = new ArrayList<PersonnageJoueur>();
	private int nbJoueurs = 0;
	private static ArrayList<byte[]> Nom_Joueurs = new ArrayList<byte[]>();
	private Map map;
	private Menu menu;

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
        	 
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             DataInputStream in = new DataInputStream(socket.getInputStream());
            
            //On attend la demande du client
            String réponse = read();
            System.out.println("En attente de la demande du client");
            
            //Phase début 
            //On traite la demande du client en fonction de la commande reçu
            String toSend = "";
            
            switch(réponse){
            
               case "0":
            	  start_game(out, in);
               case "1":
//                  toSend = "La partie se charge";
//          		  charger();
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
            if(!start) {
                out.writeUTF(toSend);
                out.flush();
                start = true;
            }
            
            
            
            
            
            
            if(closeConnexion){
               System.out.println("Le jeu se ferme !");
               out = null;
               in = null;
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
	public void combat(PersonnageJoueur p1, PersonnageNonJoueur pnj) {
		//Tant que le joueur est en combat la boucle tourne
		verifTour(p1, pnj);
		while (p1.enCombat) {
			
		if (p1.isTon_tour()) {
			p1.affichePA();
			p1.faireAction(); //le joueur choisira si il veut attaquer ou utiliser une potion
			p1.setBlessure(); // On met à jour les blessures du joueur
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
			p1.setBlessure();
			if (verifMort(p1)) { //Si le joueur est mort durant l'attaque du pnj on lui demande ce qu'il veut faire
				p1.setEnCombat();
				demande_joueur();
			}
			p1.setTon_tour(true); // au tour du joueur
		}
	}
		
}
	public void start_game(DataOutputStream out, DataInputStream in) throws IOException {
		
		Map m = new Map();
		this.map = m;
        PersonnageJoueur p = map.listePersonnage.get(0);
    String msg = map.toString();
    String Personnage ="\n" + p.toString()+"\n"+ p.afficheStats() + "\n" + p.afficheInventaire(); //On envoit toutes les informations au client
    String menuJoueur = "\n Vous pouvez : "+
    "\n 1 - vous déplacer (2PA)" +
    "\n 2 - attaquer (3PA)" +
    "\n 3 - utiliser un objet (variable)" +
    "\n 4 - Ramasser/déposer un objet (2PA)" +
    "\n 5 - finir et garder les PA restants"+
    "\n Votre choix :";
    msg += Personnage;
    msg += menuJoueur;
    out.writeUTF(msg);
    out.flush();
    boolean in_game = true;
    while (in_game) {
        int rep_client = in.readInt();
        switch(rep_client) {
        // les cas où on se déplace
        case 11 :
        	map.deplacer(p, 1);
        	msg = map.toString();
        	msg += Personnage;
        	msg += menuJoueur;
        	out.writeUTF(msg);
        	out.flush();
        case 12 :
        	map.deplacer(p, 2);
        	msg = map.toString();
        	msg += Personnage;
        	msg += menuJoueur;
        	out.writeUTF(msg);
        	out.flush();
        case 13 :
        	map.deplacer(p, 3);
        	msg = map.toString();
        	msg += Personnage;
        	msg += menuJoueur;
        	out.writeUTF(msg);
        	out.flush();
        case 14 :
        	map.deplacer(p, 4);
        	msg = map.toString();
        	msg += Personnage;
        	msg += menuJoueur;
        	out.writeUTF(msg);
        	out.flush();
        	//Cas pour attaquer
        case 2 :
  		  ArrayList<Case> Liste_autour = map.autour(p);
  		  for(int i = 0; i< Liste_autour.size() ; i++) {
  			  if (Liste_autour.get(i).uneCase instanceof PersonnageNonJoueur) {
  				  pnj = (PersonnageNonJoueur) Liste_autour.get(i).uneCase;
          		  combat(p,pnj);
          	    Personnage ="\n" + p.toString()+"\n"+ p.afficheStats() + "\n" + p.afficheInventaire(); //On envoit toutes les informations au client
              	msg = map.toString();
            	msg += Personnage;
            	msg += menuJoueur;
            	out.writeUTF(msg);
            	out.flush(); 
  			  }
  			  else {

  	        	msg = map.toString();
  	        	msg += "\n Il n'y a aucun monstre autour de vous !";
  	        	msg += Personnage;
  	        	msg += menuJoueur;
  	        	out.writeUTF(msg);
  	        	out.flush();
  			  }
  		  }
        case 3 :
        		out.writeUTF("Choisissez l'item à utiliser : (mettre le chiffre) qui est dans votre inventaire sur l'item désirer");
        		out.flush();
        		rep_client = in.readInt();
        		Item item_joueur = p.getInventaire().get(rep_client-1);
        		p.utiliserObjet(item_joueur,rep_client-1);
        		
        	    Personnage ="\n" + p.toString()+"\n"+ p.afficheStats() + "\n" + p.afficheInventaire(); //On envoit toutes les informations au client

	        	msg = map.toString();
	        	msg += "\n L'item : "+ item_joueur + " à été utiliser.";
	        	
	        	msg += Personnage;
	        	msg += menuJoueur;
	        	out.writeUTF(msg);
	        	out.flush();
        case 4 : 
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
	public void sauvegarder() {
        //Demande de confirmation de sauvegarde pour éviter toute abusation et tout fail
        int choix = JOptionPane.showConfirmDialog(this, "Êtes-vous sûr de vouloir sauvegarder ?", 
                "Demande de confirmation pour sauvegarder", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (choix == 1) {
            return;
        }
        //On crée la date à laquelle il sauvegarde le fichier et on l'implente
        //dans le nom du fichier pour pouvoir se repérer lors des chargements de partie
        DateFormat format = new SimpleDateFormat("[yyyy.MM.dd][HH.mm.ss]");
        Date date = new Date();
        String s_date = format.format(date);
        
        //On sauvegarde le personnage et la map en les serialisant
        try (FileOutputStream fos = new FileOutputStream("Liste_Joueurs.ser");
                ObjectOutputStream oos = new ObjectOutputStream(fos)){
        	
        	for(int i = 0; i<Liste_Joueurs.size(); i++) {
        		if (p.getPseudo() == Liste_Joueurs.get(i).getPseudo()) {
        			Liste_Joueurs.set(i, p);
                    oos.writeObject(Liste_Joueurs);
        		}
        		else {
                	Liste_Joueurs.add(p);
                    oos.writeObject(Liste_Joueurs);
        		}
        	}
            
            FileOutputStream fos_2 = new FileOutputStream("Nom_Joueurs.ser");
            ObjectOutputStream oos_2 = new ObjectOutputStream(fos_2);
            
            String Nom_j = p.getPseudo() + s_date;
            Nom_Joueurs.add(Nom_j.getBytes());
            oos_2.writeObject(Nom_Joueurs);
            System.out.println("La sauvegarde à bien été faite !");
            
            oos_2.close();
            
        }catch(FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   @SuppressWarnings("unchecked")
   
   public PersonnageJoueur charger() {
			File f	= new File("Liste_Joueurs.ser");
			File fnom = new File("Nom_Joueurs.ser");
			System.out.println("Nom du fichier selectionné : " + f.getName());
			if(!f.exists()) {
				try {
					f.createNewFile();
					
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
			if (!fnom.exists()) {
				try {
					fnom.createNewFile();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
			//On désérialise le fichier et on les mets dans chaque object pour relancer la partie avec ces données
		try(FileInputStream fis = new FileInputStream(f);
				ObjectInputStream ois = new ObjectInputStream(fis)){
			FileInputStream fis_2 = new FileInputStream(fnom);
			ObjectInputStream ois_2 = new ObjectInputStream(fis_2);
			
			
//			for (int i = 0; i<Liste_Joueurs.size();i++) {
//				PersonnageJoueur p1 = (PersonnageJoueur) ois.readObject();
//				Liste_Joueurs.set(i,p1);
//			}
			Liste_Joueurs = (ArrayList<PersonnageJoueur>) ois.readObject();
			Nom_Joueurs = (ArrayList<byte[]>) ois_2.readObject();
			String choix = "";
			
			for(int i = 0; i<Liste_Joueurs.size(); i++) {
				PersonnageJoueur p = Liste_Joueurs.get(i);
				String pseudo = new String(Nom_Joueurs.get(i));
				p.setPseudo(pseudo);
				choix += i+1+ " : " + p.getPseudo() +"\n";
			}
			out.writeUTF(choix);
			int choix_joueurs = in.readInt();
			PersonnageJoueur p = Liste_Joueurs.get(choix_joueurs - 1);
			return p;
			
			
					
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
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
		charger();
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
	   DataInputStream in = new DataInputStream(socket.getInputStream());
	   String commande_client = in.readUTF();
	   return commande_client;
   }
   
}