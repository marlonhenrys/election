import java.rmi.RemoteException;

import javax.swing.JFrame;

public class ElectionClient {
    public static void main(String[] args) throws RemoteException {
        ClientGUI cGUI = new ClientGUI();
        cGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cGUI.setSize(400, 400);
        cGUI.setResizable(false);
        cGUI.setVisible(true);
        cGUI.setLocationRelativeTo(null);
    }
}
