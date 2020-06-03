

import java.io.BufferedInputStream;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Client extends JFrame implements Runnable{
	
   private Socket connexion = null;
   private PrintWriter writer = null;
   private BufferedInputStream reader = null;
   
   //Notre liste de commandes. Le serveur nous répondra différemment selon la commande utilisée.
   private static int count = 0;
   private String name = "Utilisateur-";   
   
   public Client(String host, int port){
      name += ++count;
      try {
         connexion = new Socket(host, port);
      } catch (UnknownHostException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   
   public void run(){
         try {

            
            writer = new PrintWriter(connexion.getOutputStream(), true);
            reader = new BufferedInputStream(connexion.getInputStream());
            //On envoie la commande au serveur
            
            String commande = getCommand();
            System.out.println(commande);
            writer.write(commande);
            //flush() permet d'être sûr que le message est envoyé
            writer.flush();
//            
            System.out.println("Commande " + commande + " envoyée au serveur");
//            
            //On attend la réponse
            String response = read();
            System.out.println("\t * " + name + " : Réponse reçue " + response);
//            
           
         } catch (IOException e1) {
            e1.printStackTrace();
         }
         
         try {
            Thread.currentThread().sleep(1000);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
         writer.write("CLOSE");
         writer.flush();
         writer.close();
      }

   private String getCommand(){
	   ImageIcon icon = new ImageIcon("icon64.png");
	   String Boutons[]= {"Nouvelle partie","Charger une partie","Quitter le jeu"};
	   int choix = JOptionPane.showOptionDialog(this,"Bienvenue sur EHLPTMMMORPGSVR",
			   "EHLPTMMMORPGSVR", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, icon , Boutons ,Boutons[0]);
	   String réponse = "";
	   if (choix == 0) {
		   réponse = "0";
	   }
	   else if (choix == 1) {
		   réponse = "1";
	   }
	   else if (choix == 2) {
		   réponse = "2";
	   }
	   else  {
		   System.out.println("Pas de réponse de la part de l'utilisateur");
	   }
      return réponse;
   }

//Méthode pour lire les réponses du serveur
   private String read() throws IOException{
      String response = "";
      int stream;
      byte[] b = new byte[4096];
      stream = reader.read(b);
      response = new String(b, 0, stream);      
      return response;
   }   
}