import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ElectionServer {
    public static void main(String args[]) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            Election election = new ElectionServant();
            Election stub = (Election) UnicastRemoteObject.exportObject(election, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("Election", stub);
            System.out.println("Election Server running...");
        } catch (Exception e) {
            System.err.println("ElectionServer: main method " + e.getMessage());
            e.printStackTrace();
        }
    }
}
