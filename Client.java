

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.InputMismatchException;
import java.util.Scanner;

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
   private Menu menu;
   
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

            String commande = getCommand();
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
            
            //On attend la réponse
            if (commande == "Nouvelle partie") {
                commencer(out, in);
                client.close();
            }
            
//          
            
         } catch (IOException e1) {
            e1.printStackTrace();
         }
         
         try {
            Thread.currentThread().sleep(1000);
         } catch (InterruptedException e) {
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
   public void commencer(DataOutputStream out, DataInputStream in) {
	   try {
		Scanner sc = new Scanner(System.in);
		boolean in_game = true;
		while (in_game) {
			String rep_serv = in.readUTF();
			System.out.println(rep_serv);
			int rep_client = sc.nextInt();
			if (rep_client == 5) {
				in_game = false;
				out.writeInt(rep_client);
				out.flush();
			}
			else if (rep_client == 1) {
				int q = deplacer();
				rep_client = q;
				out.writeInt(rep_client);
				out.flush();
			}
			else if (rep_client == 2) {
				out.writeInt(rep_client);
				out.flush();
			}
			else if (rep_client == 3) {
				out.writeInt(rep_client);
				out.flush();
				rep_serv = in.readUTF();
				System.out.println(rep_serv);
				Scanner sc_utiliserItem = new Scanner(System.in);
				rep_client = sc_utiliserItem.nextInt();
				sc_utiliserItem.close();
				out.writeInt(rep_client);
				out.flush();
			}
		}
	} catch (IOException e) {
		e.printStackTrace();
	}
}
   public int utiliserObjet() {
	   Scanner sc = new Scanner(System.in);
       System.out.println("Choisissez l'item à utiliser : (mettre le chiffre) qui est dans votre inventaire sur l'item désirer");
       boolean choix = false;
       //premiere verification
       while (!choix) {
           try {

               int input = sc.nextInt();
               if (input < 17 && input > 0) {
                   choix = true;
               } else {
                   System.out.println("La saisie n'est pas correcte.");
               }
               return input;
           } catch (InputMismatchException e) {
               System.out.println("Vous n'avez bien saisie un chiffre, recommencez.");
           }
       }
	return 0;
   }
   public int deplacer() {

       //premiere verification
       Scanner sc = new Scanner(System.in);
       System.out.println("Choisissez votre déplacement : gauche(11) , haut (12), droite (13), bas (14)");
       boolean choix = false;
       //premiere verification
       while (!choix) {
           try {

               int input = sc.nextInt();
               if (input < 15 && input > 10) {
                   choix = true;
               } else {
                   System.out.println("La saisie n'est pas correcte.");
               }
               return input;
           } catch (InputMismatchException e) {
               System.out.println("Vous n'avez bien saisie un chiffre, recommencez.");
           }
       }
	return 0;
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