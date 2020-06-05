

import java.io.BufferedInputStream;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.io.FileOutputStream;

public class ClientProcessor implements Runnable{

   private Socket socket;
   private PrintWriter writer = null;
   private BufferedInputStream reader = null;
   
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
            //Permet de récupérer la fin d'un endpoint du socket client
            InetSocketAddress remote = (InetSocketAddress)socket.getRemoteSocketAddress();
            
            //On affiche quelques infos, pour le débuggage
            String debug = "";
            debug = "Thread : " + Thread.currentThread().getName() + "\n ";
            debug += "Demande de l'adresse : " + remote.getAddress().getHostAddress() +"\n";
            debug += " Sur le port : " + remote.getPort() + ".\n";
            System.out.println("\n"+debug);
            
            //On traite la demande du client en fonction de la commande reçu
            String toSend = "";
            
            switch(réponse){
            
               case "0":
                  Map m = new Map();
                  System.out.println(m);
                  break;
                  
               case "1":
                  toSend = "";
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