import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.rmi.NotBoundException;


public class ChatClient extends UnicastRemoteObject implements ChatInterface  {
    private String name;
    private ChatInterface server;

    protected ChatClient(String name) throws RemoteException {
        this.name = name;
    }

    public void startChat() {
        try {


            ChatInterface SServer = (ChatInterface) Naming.lookup("rmi://localhost:1098/Oserver");
          
           
            SServer.registerClient(this);

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("Enter message (type 'exit' to quit): ");
                String message = scanner.nextLine();

                if (message.equalsIgnoreCase("exit")) {
                    System.exit(0);
                }

                sendMessage("[" + name + "]: " + message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    @Override
    public void sendMessage(String message) throws RemoteException {
        System.out.println(message);
    }

    @Override
    public void registerClient(ChatInterface client) {
        // This method is not used in the client
    }

    public static void main(String[] args) throws NotBoundException ,RemoteException{
        try {
            String name = args[0];
            ChatClient client = new ChatClient(name);
            client.startChat();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}