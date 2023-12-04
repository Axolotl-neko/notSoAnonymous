  
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.*;

public class Registre{
  
  public static void main(String[] args) {
        try {
            ChatInterface Oserver = new ChatServer();
            Registry registre = LocateRegistry.createRegistry(1098);
            registre.rebind("Oserver", Oserver);
            System.out.println("ChatServer is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
   

    
   
   
   
    }
}