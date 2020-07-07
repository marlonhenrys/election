import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

@SuppressWarnings("serial")
public class CandidateServant extends UnicastRemoteObject implements Candidate {

    private String name;
    private Vector<Integer> votes;

    public CandidateServant(String name) throws RemoteException {
        this.name = name;
        this.votes = new Vector<Integer>();
    }

    @Override
    public String getName() throws RemoteException {
        return name;
    }

    @Override
    public Vector<Integer> getVotes() throws RemoteException {
        return votes;
    }

    @Override
    public void setVote(Integer voter) throws RemoteException {
        votes.add(voter);
    }

}