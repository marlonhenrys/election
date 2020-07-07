import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JOptionPane;

public class VoteRegister extends Thread {

	private String candidate;
	private Integer clientId;
	private String electionServer;

	public VoteRegister(String electionServer, Integer clientId, String candidate) {
		this.electionServer = electionServer;
		this.clientId = clientId;
		this.candidate = candidate;
	}

	@Override
	public void run() {
		while (true) {

			try {
				Registry registry = LocateRegistry.getRegistry(electionServer);
				Election election = (Election) registry.lookup("Election");

				boolean success = election.vote(candidate, clientId);

				if (success) {
					JOptionPane.showConfirmDialog(null, "Voto registrado para " + candidate + "!", "Sucesso",
							JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
				} else {
					JOptionPane.showConfirmDialog(null, "Este cliente ja possui um voto registrado.", "Erro",
							JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				}

				break;

			} catch (RemoteException | NotBoundException e) {
				try {
					sleep(5000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}