import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;
import java.util.Vector;

public class ClientGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private Integer clientId;
    private Election election = null;

    ClientGUI() {
        super("Election");
        generateId();
        mainScreen();
    }

    public void generateId() {
        Random generate = new Random();
        clientId = generate.nextInt(generate.nextInt(9876543)) + generate.nextInt(9876543);
    }

    public void mainScreen() {

        if (System.getSecurityManager() == null)
            System.setSecurityManager(new SecurityManager());

        setLayout(new BorderLayout());

        JPanel mContent = new JPanel();
        mContent.setLayout(new FlowLayout());

        JButton showBtn = new JButton("Candidatos");
        JButton voteBtn = new JButton("Votar");
        JButton resultBtn = new JButton("Resultados");
        JButton newClientBtn = new JButton("Gerar novo cliente");

        mContent.add(showBtn);
        mContent.add(voteBtn);
        mContent.add(resultBtn);
        mContent.add(newClientBtn);

        JPanel titleP = new JPanel();
        titleP.setLayout(new FlowLayout());
        JLabel title = new JLabel("Eleições 2020");
        titleP.add(title);
        titleP.setBorder(BorderFactory.createEmptyBorder(10, 0, 100, 0));

        JPanel serverP = new JPanel();
        serverP.setLayout(new FlowLayout());
        JLabel serverText = new JLabel("Servidor: ");
        JTextField serverField = new JTextField("localhost");
        serverField.setEditable(true);
        serverField.setColumns(15);
        serverP.add(serverText);
        serverP.add(serverField);

        add(titleP, BorderLayout.NORTH);
        add(mContent, BorderLayout.CENTER);
        add(serverP, BorderLayout.SOUTH);

        showBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showScreen(serverField.getText());
            }
        });

        voteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voteScreen(serverField.getText());
            }
        });

        resultBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultScreen(serverField.getText());
            }
        });

        newClientBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateId();
                JOptionPane.showConfirmDialog(mContent, "O novo ID deste cliente e: " + clientId, "Aviso",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JOptionPane.showConfirmDialog(mContent, "O ID deste cliente e: " + clientId, "Aviso",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
    }

    public void voteScreen(String electionServer) {
        try {
            Registry registry = LocateRegistry.getRegistry(electionServer);
            election = (Election) registry.lookup("Election");

            JFrame vContent = new JFrame();
            vContent.setLayout(new BorderLayout());
            vContent.setSize(400, 400);
            vContent.setVisible(true);
            vContent.setResizable(false);
            vContent.setLocationRelativeTo(null);
            JPanel voteP = new JPanel();
            voteP.setLayout(new FlowLayout());

            JPanel titleP = new JPanel();
            titleP.setLayout(new FlowLayout());
            JLabel vTitle = new JLabel("[VOTAR] Escolha um candidato:");
            titleP.add(vTitle);
            titleP.setBorder(BorderFactory.createEmptyBorder(10, 0, 100, 0));

            JButton c1Btn = new JButton("Maria Augusta");
            JButton c2Btn = new JButton("Hugo Bastos");
            JButton c3Btn = new JButton("Tadeu Faria");
            JButton backBtn = new JButton("Voltar");

            vContent.add(titleP, BorderLayout.NORTH);
            voteP.add(c1Btn);
            voteP.add(c2Btn);
            voteP.add(c3Btn);
            vContent.add(voteP, BorderLayout.CENTER);
            vContent.add(backBtn, BorderLayout.SOUTH);

            c1Btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    VoteRegister vRegister = new VoteRegister(electionServer, clientId, c1Btn.getText());
                    vRegister.start();
                }
            });
            c2Btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    VoteRegister vRegister = new VoteRegister(electionServer, clientId, c2Btn.getText());
                    vRegister.start();
                }
            });
            c3Btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    VoteRegister vRegister = new VoteRegister(electionServer, clientId, c3Btn.getText());
                    vRegister.start();
                }
            });
            backBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    vContent.setVisible(false);
                }
            });

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(this, e.getMessage(), "Erro", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void resultScreen(String electionServer) {

        JFrame rContent = new JFrame();
        rContent.setLayout(new BorderLayout());
        rContent.setSize(400, 400);
        rContent.setVisible(true);
        rContent.setResizable(false);
        rContent.setLocationRelativeTo(null);
        JPanel resultP = new JPanel();
        resultP.setLayout(new FlowLayout());

        JPanel titleP = new JPanel();
        titleP.setLayout(new FlowLayout());
        JLabel vTitle = new JLabel("[RESULTADOS] Escolha um candidato:");
        titleP.add(vTitle);
        titleP.setBorder(BorderFactory.createEmptyBorder(10, 0, 100, 0));

        JButton c1Btn = new JButton("Maria Augusta");
        JButton c2Btn = new JButton("Hugo Bastos");
        JButton c3Btn = new JButton("Tadeu Faria");
        JButton backBtn = new JButton("Voltar");

        rContent.add(titleP, BorderLayout.NORTH);
        resultP.add(c1Btn);
        resultP.add(c2Btn);
        resultP.add(c3Btn);
        rContent.add(resultP, BorderLayout.CENTER);
        rContent.add(backBtn, BorderLayout.SOUTH);

        c1Btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showResult(electionServer, c1Btn.getText());
            }
        });
        c2Btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showResult(electionServer, c2Btn.getText());
            }
        });
        c3Btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showResult(electionServer, c3Btn.getText());
            }
        });
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rContent.setVisible(false);
            }
        });
    }

    public void showScreen(String electionServer) {
        try {
            Registry registry = LocateRegistry.getRegistry(electionServer);
            election = (Election) registry.lookup("Election");

            JFrame sContent = new JFrame();
            sContent.setLayout(new BorderLayout());
            sContent.setSize(400, 400);
            sContent.setVisible(true);
            sContent.setResizable(false);
            sContent.setLocationRelativeTo(null);
            JPanel showP = new JPanel();
            showP.setLayout(new FlowLayout());

            JPanel titleP = new JPanel();
            titleP.setLayout(new FlowLayout());
            JLabel sTitle = new JLabel("LISTA DE CANDIDATOS:");
            titleP.add(sTitle);
            titleP.setBorder(BorderFactory.createEmptyBorder(10, 0, 100, 0));

            try {
                Vector<String> names = election.candidates();

                for (String name : names)
                    showP.add(new JLabel(name + ";"));

            } catch (RemoteException e) {
                JOptionPane.showConfirmDialog(this, e.getMessage(), "Erro", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.ERROR_MESSAGE);
            }

            JButton backBtn = new JButton("Voltar");

            sContent.add(titleP, BorderLayout.NORTH);
            sContent.add(showP, BorderLayout.CENTER);
            sContent.add(backBtn, BorderLayout.SOUTH);

            backBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sContent.setVisible(false);
                }
            });

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(this, e.getMessage(), "Erro", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void showResult(String electionServer, String candidate) {
        try {
            Registry registry = LocateRegistry.getRegistry(electionServer);
            election = (Election) registry.lookup("Election");

            Candidate c = election.result(candidate);
            String cResult = "O(a) candidato(a) " + c.getName() + " possui " + c.getVotes().size() + " voto(s).";
            JOptionPane.showConfirmDialog(null, cResult, "Resultado", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

        } catch (RemoteException | NotBoundException e) {
            JOptionPane.showConfirmDialog(this, e.getMessage(), "Erro", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
