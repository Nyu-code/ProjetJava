
public class Main {

   public static void main(String[] args) {
    
      String host = "127.0.0.1";
      int port = 1906;
      
      Serveur ts = new Serveur(host, port);
      ts.open();
      
      System.out.println("Serveur lancé");
      
      Thread t = new Thread(new Client(host, port));
      t.start();
     
     
      }
   }