import java.rmi.RemoteException;
import java.util.Vector;

public class ElectionServant implements Election {

    private Vector<Candidate> candidates;
    private Vector<Integer> voters;

    public ElectionServant() throws RemoteException {
        candidates = new Vector<Candidate>();
        voters = new Vector<Integer>();

        candidates.add(new CandidateServant("Maria Augusta"));
        candidates.add(new CandidateServant("Hugo Bastos"));
        candidates.add(new CandidateServant("Tadeu Faria"));
    }

    @Override
    public Vector<String> candidates() throws RemoteException {
        Vector<String> cNames = new Vector<String>();

        for (Candidate c : this.candidates)
            cNames.add(c.getName());

        return cNames;
    }

    @Override
    public Boolean vote(String candidate, Integer voter) throws RemoteException {
        if (!voters.contains(voter)) {
            for (Candidate c : this.candidates) {
                if (c.getName().equals(candidate)) {
                    c.setVote(voter);
                    voters.add(voter);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Candidate result(String candidate) throws RemoteException {
        for (Candidate c : this.candidates) {
            if (c.getName().equals(candidate))
                return c;
        }
        return null;
    }
}
