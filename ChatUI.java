import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class ChatUI extends JFrame implements ActionListener, ChatInterface {
    private JTextArea chatArea;
    private JTextField inputField;
    private ChatInterface server;
    private String name;

    public ChatUI(String name) {
        this.name = name;

        setTitle("Chat App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);

        inputField = new JTextField(30);
        inputField.addActionListener(this);

        add(scrollPane, BorderLayout.CENTER);
        add(inputField, BorderLayout.SOUTH);
        setVisible(true);

        try {
            server = (ChatInterface) Naming.lookup("//localhost/ChatServer");
            server.registerClient(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e) {
        String message = inputField.getText();
        inputField.setText("");

        try {
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setSender(name);
            chatMessage.setContent(message);
            server.sendMessage(chatMessage);
            appendToChat("You: " + message); // Afficher le message envoyé par l'utilisateur
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

    public void sendMessage(ChatMessage message) throws RemoteException {
        String sender = message.getSender();
        String content = message.getContent();
        appendToChat(sender + ": " + content); // Afficher les messages reçus des autres utilisateurs
    }

    public void registerClient(ChatInterface client) throws RemoteException {
        // Logique pour enregistrer le client sur le serveur (si nécessaire)
    }

    private void appendToChat(String message) {
        chatArea.append(message + "\n");
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java ChatUI <username>");
            return;
        }
        String name = args[0];
        SwingUtilities.invokeLater(() -> new ChatUI(name));
    }
}
