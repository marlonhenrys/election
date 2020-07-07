import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface Election extends Remote {

    Vector<String> candidates() throws RemoteException;

    Boolean vote(String candidate, Integer voter) throws RemoteException;

    Candidate result(String candidate) throws RemoteException;
}
