import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ChatClient extends UnicastRemoteObject implements ChatInterface {
    private String name;
    private ChatInterface server;

    protected ChatClient(String name) throws RemoteException {
        this.name = name;
    }

    public void startChat() {
        try {
            server = (ChatInterface) Naming.lookup("//localhost/ChatServer");
            server.registerClient(this);

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("Enter message (type 'exit' to quit): ");
                String message = scanner.nextLine();

                if (message.equalsIgnoreCase("exit")) {
                    System.exit(0);
                }

                ChatMessage chatMessage = new ChatMessage();
                chatMessage.setSender(name);
                chatMessage.setContent("[" + name + "]: " + message);

                server.sendMessage(chatMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMessage(ChatMessage message) throws RemoteException {
        System.out.println(message.getContent());
    }

    @Override
    public void registerClient(ChatInterface client) throws RemoteException {
        // Cette méthode n'est pas utilisée dans le client
    }

    public static void main(String[] args) {
        try {
            String name = args[0];
            ChatClient client = new ChatClient(name);
            client.startChat();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
