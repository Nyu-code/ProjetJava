
public class Main {

   public static void main(String[] args) {
    
      String host = "127.0.0.1";
      int port = 1906;
      
      Serveur Serveur = new Serveur(host, port);
      Serveur.open();
      
      System.out.println("Serveur lancé");
      
      Thread t = new Thread(new Client(host, port));
      t.start();
     
//      Serveur.close();

      }
   }