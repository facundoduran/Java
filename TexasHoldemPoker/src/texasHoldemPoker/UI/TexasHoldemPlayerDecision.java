package texasHoldemPoker.UI;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import texasHoldemPoker.Model.PokerCard;
import texasHoldemPoker.Model.PokerPlayer;
import texasHoldemPoker.Model.PokerPlayerDecision;
import texasHoldemPoker.UI.CustomControls.ImagePanel;
import texasHoldemPoker.Common.FileHelper;
import texasHoldemPoker.Common.Validators;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JRadioButton;

public class TexasHoldemPlayerDecision extends JDialog {

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
	private JPanel firstPlayerCard;
	private JPanel secondPlayerCard;
	
	private int raiseAmount;
	private int bigBlind;
	private int pot;
	private PokerPlayerDecision playerDecision;
	private PokerPlayer pokerPlayer;
	private ArrayList<PokerCard> communitaryCards;
	private JLabel lblBigBlindValue;
	private JLabel lblPotInfo;
	private JLabel lblPot;
	private JLabel lblChipsInfo;
	private JLabel lblChips;
	
	/*
	 * Create the application.
	 */
	public TexasHoldemPlayerDecision(ArrayList<PokerCard> tableCards, PokerPlayer pokerPlayer, int bigBlind, int pot) {
		this.communitaryCards = tableCards;
		this.pokerPlayer = pokerPlayer;
		this.bigBlind = bigBlind;
		this.pot = pot;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	
		this.setModal(true);
		this.setResizable(false);
		this.setTitle("Decision del jugador");
		this.setBounds(100, 100, 492, 363);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		lblCardsInTable = new JLabel("Cartas comunitarias :");
		
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
		
		btnBet = new JButton("Subir");
		btnBet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerBet();				
			}
		});
		
		txtBet = new JTextField();
		txtBet.setColumns(10);
		
		lblTurno = new JLabel("Turno:");
		
		lblPlayerName = new JLabel("");
		
		firstPlayerCard = new JPanel();
		
		secondPlayerCard = new JPanel();
		
		JLabel lblBigBlind = new JLabel("Apuesta minima:");
		
		lblBigBlindValue = new JLabel("");
		
		lblPotInfo = new JLabel("Pozo:");
		
		lblPot = new JLabel("");
		
		JRadioButton rdbtnHasBigBlind = new JRadioButton("Ciega Grande");
		
		boolean playerHasBigBlind = this.pokerPlayer.hasBigBlind();
		
		rdbtnHasBigBlind.setEnabled(false);
		
		rdbtnHasBigBlind.setSelected(playerHasBigBlind);
		
		lblChipsInfo = new JLabel("Fichas:");
		
		lblChips = new JLabel("");
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(firstPlayerCard, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(secondPlayerCard, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblTurno)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblPlayerName)))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblBet, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnLeave)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnCheck, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnBet, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(slBet, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblChipsInfo)
											.addGap(15)
											.addComponent(lblChips, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblBigBlind)
												.addComponent(lblPotInfo))
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblPot)
												.addComponent(lblBigBlindValue))))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(rdbtnHasBigBlind)
										.addComponent(txtBet, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblCardsInTable, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(firstFlopCard, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(secondFlopCard, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)))
							.addGap(6)
							.addComponent(thirdFlopCard, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(turnCard, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(riverCard, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblCardsInTable)
							.addGap(5)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(firstFlopCard, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
								.addComponent(secondFlopCard, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
								.addComponent(thirdFlopCard, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
								.addComponent(turnCard, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
								.addComponent(riverCard, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(13)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblTurno)
										.addComponent(lblPlayerName)))
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblBigBlind)
										.addComponent(lblBigBlindValue))))
							.addGap(11)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(13)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblChipsInfo)
										.addComponent(lblChips, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblBet, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(slBet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtBet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnLeave)
										.addComponent(btnCheck)
										.addComponent(btnBet)))
								.addComponent(secondPlayerCard, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
								.addComponent(firstPlayerCard, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(182)
							.addComponent(lblPotInfo))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(182)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(rdbtnHasBigBlind)
								.addComponent(lblPot))))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		showCards();
		showPlayerCard();
		setPlayerInfo();
	}

	public PokerPlayerDecision getPlayerDecision() {
		return playerDecision;
	}

	public void setPlayerDecision(PokerPlayerDecision playerDecision) {
		this.playerDecision = playerDecision;
	}

	public ArrayList<PokerCard> getTableCards() {
		return communitaryCards;
	}

	public void setTableCards(ArrayList<PokerCard> tableCards) {
		this.communitaryCards = tableCards;
	}
	
	private void playerChecks() {
		this.playerDecision = PokerPlayerDecision.Call;
		this.setVisible(false);
	}
	
	private void playerLeave() {
		this.playerDecision = PokerPlayerDecision.Leave;	
		this.setVisible(false);
	}
	
	private void playerBet() {
		String raiseAmount = this.txtBet.getText();
		
		if (Validators.isNumeric(raiseAmount)) 
		{
			int amount = Integer.parseInt(raiseAmount);
			
			if (amount == this.slBet.getMaximum()) {
				this.playerDecision = PokerPlayerDecision.AllIn;
				this.setRaiseAmount(amount);
				this.setVisible(false);
			}
			else if (amount >= slBet.getMinimum()) {
				this.playerDecision = PokerPlayerDecision.Raise;
				this.setRaiseAmount(amount);
				this.setVisible(false);	
			}			 
			else {
				JOptionPane.showMessageDialog(new JFrame(), "El valor ingresado es menor al minimo permitido ", "Error",
					JOptionPane.ERROR_MESSAGE);
			}	
		}
		else {
			JOptionPane.showMessageDialog(new JFrame(), "El valor ingresado no es un numero ", "Error",
		        JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public int getRaiseAmount() {
		return raiseAmount;
	}

	public void setRaiseAmount(int raiseAmount) {
		this.raiseAmount = raiseAmount;
	}

	private void updateMethod(int value) {
		this.txtBet.setText(String.valueOf(value));
		
		if (value == this.slBet.getMaximum()) {
			this.btnBet.setText("All-In");
		}
		else {
			this.btnBet.setText("Subir");
		}
	}
	
	private void setPlayerInfo() {
		lblPlayerName.setText(this.pokerPlayer.getName());
		
		lblPot.setText(Integer.toString(this.pot));
		lblChips.setText(Integer.toString(pokerPlayer.getBalance()));
		
		if (this.pokerPlayer.getBalance() < this.bigBlind) {
			this.btnBet.setText("All-In");
			this.btnCheck.setEnabled(false);
		}
		
		String title = this.getTitle() + " ";
		
		if (communitaryCards.size() <= 1) {
			//Pre flop
			this.setTitle(title + "Pre-Flop");
		}
		else if (communitaryCards.size() >= 1 && communitaryCards.size() <= 3) {
			//Flop
			this.setTitle(title + "Flop");
		}
		else if (communitaryCards.size() == 4) {
			//Turn
			this.setTitle(title + "Turn");
		}
		else if (communitaryCards.size() == 5) {
			//River
			this.setTitle(title + "River");
		}
		
		slBet.setMinimum(this.bigBlind);
		slBet.setMaximum(this.pokerPlayer.getBalance());
		
		lblBigBlindValue.setText(Integer.toString(this.bigBlind));
		
		slBet.setValue(this.bigBlind);
	}
	
	private void showPlayerCard() {
		ArrayList<PokerCard> hand = this.pokerPlayer.getHand();
		
		PokerCard firstCard = hand.get(0);
		PokerCard secondCard = hand.get(1);
		
		String firstCardFilename = FileHelper.getImageCard(firstCard);
		String secondCardFilename = FileHelper.getImageCard(secondCard);
		
		firstPlayerCard.add(new ImagePanel(firstCardFilename));
		secondPlayerCard.add(new ImagePanel(secondCardFilename));
	}
	
	private void showCards() {
		for(int i = 1; i <= communitaryCards.size(); i++) {
			
			PokerCard card = communitaryCards.get(i-1);
			String cardFilename = FileHelper.getImageCard(card);
			
			if (i == 1) {
				firstFlopCard.add(new ImagePanel(cardFilename));
			}
			
			if (i == 2) {
				secondFlopCard.add(new ImagePanel(cardFilename));
			}
			
			if (i == 3) {
				thirdFlopCard.add(new ImagePanel(cardFilename));
			}
			
			if (i == 4) {
				turnCard.add(new ImagePanel(cardFilename));	
			}
			
			if (i == 5) {
				riverCard.add(new ImagePanel(cardFilename));
			}
		}		
	}
}
