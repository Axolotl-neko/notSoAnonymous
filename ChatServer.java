import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ChatServer extends UnicastRemoteObject implements ChatInterface
 {

    private static List<ChatInterface> clients = new ArrayList<>();


    protected ChatServer() throws RemoteException {
    }

    @Override
    public void registerClient(ChatInterface client) throws RemoteException {
        clients.add(client);
        System.out.println("New client registered: " + client);
    }

    @Override

    public void sendMessage(String message) throws RemoteException {
        System.out.println("Received message: " + message);
        broadcastMessage(message);
    }




    private void broadcastMessage(String message) {
        // Broadcast the message to all connected clients
        for (ChatInterface client : clients) {
            try {
                client.sendMessage(message);
            } catch (RemoteException e) {
                // Handle RemoteException if a client is unreachable
                System.err.println("Error sending message to a client:");
                e.printStackTrace();
            }
        }
    }


}