import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface Candidate extends Remote {

    String getName() throws RemoteException;

    Vector<Integer> getVotes() throws RemoteException;

    void setVote(Integer voter) throws RemoteException;
}
