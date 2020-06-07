

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Client extends JFrame implements Runnable{
	
   private Socket client = null;
   private DataOutputStream out = null;
   private DataInputStream in = null;
   private static int count = 0;
   private String name = "Utilisateur-";   
   private boolean start = false;
   public Client(String host, int port){
      name += ++count;
      try {
         client = new Socket(host, port);
      } catch (UnknownHostException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   
   public void run(){
         try {
            
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            DataInputStream in = new DataInputStream(client.getInputStream());
            //On envoie la commande au serveur
            if (!start) {
            String commande = getCommand();
            System.out.println(commande);
            out.writeUTF(commande);
            out.flush();
            
//           
            if (commande == "0") {
            	commande = "Nouvelle partie";
            }
            else if (commande == "1") {
            	commande = "Charger une partie";
            }
            else {
            	commande = "Quitter le jeu";
            }
            //On informe au client que la commande à bien été envoyé
            System.out.println("Commande " + commande + " envoyée au serveur");
            start = true;
            }
            
            //On attend la réponse
            String réponse = read();
            System.out.println("\t * " + name + " : Réponse reçue \n" + réponse);
//          
           
         } catch (IOException e1) {
            e1.printStackTrace();
         }
         
         try {
            Thread.currentThread().sleep(1000);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
         try {
			out.writeUTF("CLOSE");
	        out.flush();
	        out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		   System.out.println("Pas de réponse de la part de l'utilisateur / CONNEXION INTERROMPU");
		   try {
			out.writeUTF("CLOSE");
			out.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		   //Si l'utilisateur ne compte pas envoyé de réponse ou quoi que ce soit on ferme la connexion
		   try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		   
	   }
      return réponse;
   }

//Méthode pour lire les réponses du serveur
   private String read() throws IOException{
	   DataInputStream in = new DataInputStream(client.getInputStream());
	   String rep_serv = in.readUTF();
	   return rep_serv;
   }
	public static void main(String[] args) {
		   Thread t = new Thread(new Client("127.0.0.1", 1906));
		   t.start();
	}
}