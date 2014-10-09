package texasHoldemPoker.UI;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
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
	private PokerPlayerDecision playerDecision;
	private PokerPlayer pokerPlayer;
	private ArrayList<PokerCard> tableCards;
	private JLabel lblBigBlindValue;
	
	/*
	 * Create the application.
	 */
	public TexasHoldemPlayerDecision(ArrayList<PokerCard> tableCards, PokerPlayer pokerPlayer, int bigBlind) {
		this.tableCards = tableCards;
		this.pokerPlayer = pokerPlayer;
		this.bigBlind = bigBlind;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	
		this.setModal(true);
		this.setTitle("Decision del jugador");
		this.setBounds(100, 100, 492, 363);
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
		
		firstPlayerCard = new JPanel();
		
		secondPlayerCard = new JPanel();
		
		JLabel lblBigBlind = new JLabel("Apuesta minima:");
		
		lblBigBlindValue = new JLabel("");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCardsInTable, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(firstFlopCard, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(secondFlopCard, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(thirdFlopCard, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(turnCard, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(riverCard, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))))
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
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblBigBlind)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblBigBlindValue))
								.addComponent(lblBet, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(slBet, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtBet, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnLeave)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnCheck, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnBet)))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
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
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblBet, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(slBet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtBet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnLeave)
								.addComponent(btnCheck)
								.addComponent(btnBet)))
						.addComponent(secondPlayerCard, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
						.addComponent(firstPlayerCard, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))
					.addGap(56))
		);
		getContentPane().setLayout(groupLayout);
		showCards();
		showPlayerCard();
		setPlayerInfo(pokerPlayer, this.bigBlind);
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
		this.setVisible(false);
	}
	
	private void playerLeave() {
		this.playerDecision = PokerPlayerDecision.Leave;	
		this.setVisible(false);
	}
	
	private void playerBet() {
		this.playerDecision = PokerPlayerDecision.Raise;
		String raiseAmount = this.txtBet.getText();
		
		if (Validators.isNumeric(raiseAmount)) 
		{
			int amount = Integer.parseInt(raiseAmount);
			this.setRaiseAmount(amount);
			this.setVisible(false);		
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
	}
	
	private void setPlayerInfo(PokerPlayer player, int bigBlind) {
		lblPlayerName.setText(player.getName());
		
		slBet.setMinimum(bigBlind);
		slBet.setMaximum(player.getBalance());
		
		lblBigBlindValue.setText(Integer.toString(bigBlind));
		
		slBet.setValue(bigBlind);
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
		for(int i = 1; i <= tableCards.size(); i++) {
			
			PokerCard card = tableCards.get(i-1);
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
