

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Serveur{

   //On initialise des valeurs par défaut
   private int port = 1906;
   private String host = "127.0.0.1";
   private ServerSocket server = null;
   private boolean isRunning = true;
   
   public Serveur(){
      try {
         server = new ServerSocket(port, 100 , InetAddress.getByName(host));
         
      } catch (UnknownHostException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   public Serveur(String pHost, int pPort){
      host = pHost;
      port = pPort;
      try {
         server = new ServerSocket(port, 9, InetAddress.getByName(host));
         
      } catch (UnknownHostException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   
   //On lance notre serveur
   public void open(){
      
      //Toujours dans un thread à part vu qu'il est dans une boucle infinie
      Thread t = new Thread(new Runnable(){
         public void run(){
            while(isRunning == true){
               
               try {
                  //On attend une connexion d'un client
                  Socket client = server.accept();
                  
                  //Une fois reçue, on la traite dans un thread séparé
                  System.out.println("Connexion cliente reçue.");
                  
                  //On lance le run() de ClientProcessor en mettant le client en paramètre
                  //Client Processor agit comme un centre de serveur qui lui traitera les demande du client
                  Thread t = new Thread(new ClientProcessor(client));
                  t.start();
                  
               } catch (IOException e) {
                  e.printStackTrace();
               }
            }
            
            try {
               server.close();
            } catch (IOException e) {
               e.printStackTrace();
               server = null;
            }
         }
      });
      
      t.start();
   }
   
   public void close(){
      isRunning = false;
   }
	public static void main(String[] args) {
		   String host = "127.0.0.1";
		   int port = 1906;
		   
		   Serveur s = new Serveur(host,port);
		   s.open();
		   
		  System.out.println("Serveur initialisé.");
	}
}