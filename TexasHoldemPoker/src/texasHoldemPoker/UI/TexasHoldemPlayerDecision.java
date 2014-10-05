package texasHoldemPoker.UI;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import texasHoldemPoker.Model.Player;
import texasHoldemPoker.Model.PokerCard;
import texasHoldemPoker.Model.PokerPlayer;
import texasHoldemPoker.Model.PokerPlayerDecision;
import texasHoldemPoker.Persistence.Sql.Dao.IPlayerDAO;
import texasHoldemPoker.Persistence.Sql.Dao.PlayerDAO;
import texasHoldemPoker.UI.CustomControls.ImagePanel;
import texasHoldemPoker.Common.FileHelper;
import texasHoldemPoker.Common.Validators;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class TexasHoldemPlayerDecision extends JDialog {

	private JFrame frmPlayerDecision;
	private PokerPlayer pokerPlayer;
	private PokerPlayerDecision playerDecision;
	private ArrayList<PokerCard> tableCards;
	private JTextField txtBet;
	private	JLabel lblCardsInTable;	
	private	JPanel firstFlopCard;			
	private	JPanel secondFlopCard;			
	private	JPanel thirdFlopCard;			
	private	JPanel turnCard;			
	private	JPanel riverCard;			
	private	JSlider slBet;
	private	JLabel lblBet;
	private	JButton btnLeave;
	private	JButton btnCheck;
	private	JButton btnBet;
	private	JLabel lblTurno;
	private	JLabel lblPlayerName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TexasHoldemPlayerDecision window = new TexasHoldemPlayerDecision();
					window.frmPlayerDecision.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public TexasHoldemPlayerDecision() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	
		this.setModal(true);
		this.setTitle("Decision del jugador");
		this.setBounds(100, 100, 492, 308);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		lblCardsInTable = new JLabel("Cartas en la mesa :");
		
		firstFlopCard = new JPanel();
		
		secondFlopCard = new JPanel();
		
		thirdFlopCard = new JPanel();
		
		turnCard = new JPanel();
		
		riverCard = new JPanel();
		
		slBet = new JSlider();
		slBet.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
		        int currentValue = ((JSlider)event.getSource()).getValue();
		        updateMethod(currentValue);				
			}
		});
		
		lblBet = new JLabel("Apuesta:");
		
		btnLeave = new JButton("Retirarse");
		btnLeave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerLeave();
			}
		});
		
		btnCheck = new JButton("Pasar");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerChecks();
			}
		});
		
		btnBet = new JButton("Apostar");
		btnBet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerBet();				
			}
		});
		
		txtBet = new JTextField();
		txtBet.setColumns(10);
		
		lblTurno = new JLabel("Turno:");
		
		lblPlayerName = new JLabel("");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCardsInTable, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(firstFlopCard, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(secondFlopCard, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(thirdFlopCard, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(turnCard, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(riverCard, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblBet, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(slBet, GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
									.addGap(24)
									.addComponent(txtBet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnLeave)
									.addGap(10)
									.addComponent(btnCheck, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnBet))))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblTurno)
							.addGap(18)
							.addComponent(lblPlayerName)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addComponent(lblCardsInTable)
					.addGap(5)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(firstFlopCard, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
						.addComponent(secondFlopCard, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
						.addComponent(thirdFlopCard, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
						.addComponent(turnCard, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
						.addComponent(riverCard, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTurno)
						.addComponent(lblPlayerName))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(slBet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addComponent(lblBet, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtBet, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnLeave)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnCheck)
							.addComponent(btnBet))))
		);
		getContentPane().setLayout(groupLayout);
	}

	public PokerPlayer getPokerPlayer() {
		return pokerPlayer;
	}

	public void setPokerPlayer(PokerPlayer pokerPlayer) {
		this.pokerPlayer = pokerPlayer;
	}

	public PokerPlayerDecision getPlayerDecision() {
		return playerDecision;
	}

	public void setPlayerDecision(PokerPlayerDecision playerDecision) {
		this.playerDecision = playerDecision;
	}

	public ArrayList<PokerCard> getTableCards() {
		return tableCards;
	}

	public void setTableCards(ArrayList<PokerCard> tableCards) {
		this.tableCards = tableCards;
	}
	
	private void playerChecks() {
		this.playerDecision = PokerPlayerDecision.Call;
	}
	
	private void playerLeave() {
		this.playerDecision = PokerPlayerDecision.Leave;		
	}
	
	private void playerBet() {
		this.playerDecision = PokerPlayerDecision.Raise;
	}
	
	private void updateMethod(int value) {
		this.txtBet.setText(String.valueOf(value));
	}
	
	private void setPlayerInfo(PokerPlayer player, int bigBlind) {
		lblPlayerName.setText(player.getName());
		
		slBet.setMinimum(bigBlind);
		slBet.setMaximum(player.getBalance());
	}
}
