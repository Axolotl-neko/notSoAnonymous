import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.awt.event.MouseEvent;

public class ChatUUI extends JFrame implements MouseListener {
    private JTextArea chatArea;
    private JTextField inputField;
    JLabel retourl;

 public ChatUUI() {
    


    //declaration des pannels du border layout

JPanel topP = new JPanel();
JPanel eastP = new JPanel();
JPanel westP = new JPanel();
JPanel bottomP = new JPanel();
JPanel centreP = new JPanel();
JPanel containerp = new JPanel();

//coloring the pannels 
topP.setBackground(new Color (87, 135, 61));
eastP.setBackground(new Color (87, 135, 61));
westP.setBackground(new Color (87, 135, 61));
bottomP.setBackground(new Color (87, 135, 61));
centreP.setBackground(new Color (25,25,25));
containerp.setBackground(new Color (87, 135, 61));


//seting size

topP.setPreferredSize(new Dimension(400,150));
eastP.setPreferredSize(new Dimension(94,100));
westP.setPreferredSize(new Dimension(94,100));
bottomP.setPreferredSize(new Dimension(105,150));
containerp.setPreferredSize(new Dimension(400,40));

//creation button retour


ImageIcon retour = new ImageIcon(new ImageIcon("—Pngtree—vector back icon_4267356.png").getImage().getScaledInstance(40,40, Image.SCALE_DEFAULT));
retourl = new JLabel();
retourl.setIcon(retour);
retourl.addMouseListener(this);


containerp.add(retourl);

 //creation du logo + text 


 ImageIcon imageIcon = new ImageIcon(new ImageIcon("pngegg.png").getImage().getScaledInstance(150,150, Image.SCALE_DEFAULT));
 JLabel anon = new JLabel();
 anon.setText("one rule , don't make it boring"); 
 anon.setIcon(imageIcon);
 anon.setHorizontalTextPosition (JLabel.RIGHT);//set text left center or right of image icon
 anon.setVerticalTextPosition (JLabel.CENTER);//set TOP center or bottom
 anon.setForeground( Color.BLACK);
 anon.setFont( new Font ("Hacker",Font.PLAIN,20));

 anon.setVerticalAlignment(JLabel.BOTTOM);

 anon.setHorizontalAlignment(JLabel.CENTER);

 topP.add(anon);

        chatArea = new JTextArea();
        //chatArea.setEditable(false);
        chatArea.setPreferredSize(new Dimension(400,390));
        chatArea.setBackground(new Color (25,25,25));
        chatArea.setFont( new Font ("Hacker",Font.PLAIN,20));
        chatArea.setForeground(new Color (32,194,14));
        JScrollPane scrollPane = new JScrollPane(chatArea);

        inputField = new JTextField();
        inputField.setPreferredSize(new Dimension(412,60));
        inputField.setBackground(new Color (25,25,25));
        inputField.setFont( new Font ("Hacker",Font.PLAIN,20));
        inputField.setForeground(new Color (32,194,14));
        centreP.add(scrollPane);
        bottomP.add(inputField);
        bottomP.add(containerp);
 
        this.add(topP,BorderLayout.NORTH);
        this.add(eastP,BorderLayout.EAST);
        this.add(westP,BorderLayout.WEST);
        this.add(centreP,BorderLayout.CENTER);
        this.add(bottomP,BorderLayout.SOUTH);

        setTitle("Chat App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 700);
        setLocationRelativeTo(null);

        
        //inputField.addActionListener(this);

        this.setResizable(false);
        this.setVisible(true);

       
        }




@Override
public void mouseClicked(MouseEvent e) {

    if(e.getSource()== retourl){
        this.dispose();
       new Launcher();
    }
}

@Override
public void mousePressed(MouseEvent e) {
   
}

@Override
public void mouseReleased(MouseEvent e) {
    
}

@Override
public void mouseEntered(MouseEvent e) {

   
}

@Override
public void mouseExited(MouseEvent e) {
   
  
}

    }