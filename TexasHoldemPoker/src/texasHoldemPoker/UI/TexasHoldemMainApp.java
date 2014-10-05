package texasHoldemPoker.UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.BorderLayout;

public class TexasHoldemMainApp extends JFrame {

	private JFrame frmTexasHoldemPoker;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TexasHoldemMainApp window = new TexasHoldemMainApp();
					window.frmTexasHoldemPoker.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TexasHoldemMainApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTexasHoldemPoker = new JFrame();
		frmTexasHoldemPoker.setTitle("Texas Holdem Poker Main");
		frmTexasHoldemPoker.setBounds(100, 100, 513, 330);
		frmTexasHoldemPoker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmTexasHoldemPoker.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Poker");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmJugar = new JMenuItem("Jugar");
		mntmJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showSelectPlayerForm();
			}
		});
		mnNewMenu.add(mntmJugar);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Salir");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnSalaryHistoryMain = new JMenu("Historial de cargas");
		menuBar.add(mnSalaryHistoryMain);
		
		JMenuItem mntmSalaryHistory = new JMenuItem("Consultar Historial de cargas");
		mntmSalaryHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPlayerSearchForm();
			}
		});
		mnSalaryHistoryMain.add(mntmSalaryHistory);
		
		JMenuItem mntmAddPlayer = new JMenuItem("Agregar Jugador");
		mntmAddPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showCreatePlayerForm();
			}
		});
		
		JMenuItem mntmAddCredit = new JMenuItem("Cargar Saldo");
		mntmAddCredit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showPlayerSearchForm();
			}
		});
		mnSalaryHistoryMain.add(mntmAddCredit);
		mnSalaryHistoryMain.add(mntmAddPlayer);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPlayerDecision();
			}
		});
		frmTexasHoldemPoker.getContentPane().add(btnNewButton, BorderLayout.WEST);
	}
	
	private void showSelectPlayerForm() {
		TexasHoldemSelectPlayers selectPlayerForm = new TexasHoldemSelectPlayers();
		selectPlayerForm.setVisible(true);
	}
	
	private void showCreatePlayerForm() {
		TexasHoldemPokerCreatePlayer createPlayerForm = new TexasHoldemPokerCreatePlayer(this, true);
		createPlayerForm.setVisible(true);
	}
	
	private void showPlayerSearchForm() {
		TexasHoldemPlayer playerSearch = new TexasHoldemPlayer();
		playerSearch.setVisible(true);
	}
	
	private void showPlayerDecision() {
		TexasHoldemPlayerDecision playerDecisionForm = new TexasHoldemPlayerDecision();
		playerDecisionForm.setVisible(true);
	}
}
