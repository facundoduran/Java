package texasHoldemPoker.UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		
		JMenu mnNewMenu_1 = new JMenu("Historial de cargas");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Consultar Historial de cargas");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_1);
		
		JMenuItem mntmAgregarJugador = new JMenuItem("Agregar Jugador");
		mntmAgregarJugador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showCreatePlayerForm();
			}
		});
		mnNewMenu_1.add(mntmAgregarJugador);
	}
	
	private void showCreatePlayerForm() {
		TexasHoldemPokerCreatePlayer createPlayerForm = new TexasHoldemPokerCreatePlayer(this, true);
	}

}
