

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatInterface extends Remote {
   public void sendMessage(String message) throws RemoteException;
    public void registerClient(ChatInterface client) throws RemoteException;
}