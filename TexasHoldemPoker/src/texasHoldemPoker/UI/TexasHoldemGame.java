package texasHoldemPoker.UI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.ArrayList;

import texasHoldemPoker.Common.FileHelper;
import texasHoldemPoker.Model.Player;
import texasHoldemPoker.Model.PokerCard;
import texasHoldemPoker.Model.PokerGame;
import texasHoldemPoker.Model.PokerPlayer;
import texasHoldemPoker.Model.PokerPlayerDecision;
import texasHoldemPoker.UI.CustomControls.ImagePanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

public class TexasHoldemGame extends JFrame{
	
	private PokerGame game;
		
	/**
	 * Create the application.
	 */
	public TexasHoldemGame() throws Exception {
		initialize();
	}
	/*
	 * Atrributes
	 * */
	private JLabel lblPlayer1;
	private JLabel lblPlayer2;
	private JLabel lblPlayer3;
	private JLabel lblPlayer4;
	private JLabel lblDealer;
	private JPanel imgPlayer1FirstCard;
	private JPanel imgPlayer1SecondCard;
	private JPanel imgPlayer2FirstCard;		
	private JPanel imgPlayer2SecondCard;		
	private JPanel imgPlayer3FirstCard;		
	private JPanel imgPlayer3SecondCard;
	private JPanel imgPlayer4FirstCard;
	private JPanel imgPlayer4SecondCard;
	private JPanel imgFlopFirstCard;
	private JPanel imgFlopSecondCard;
	private JPanel imgFlopCardThirdCard;
	private JPanel imgTurnCard;		
	private JPanel imgRiverCard;

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setTitle("Poker");
		this.setBounds(100, 100, 1023, 526);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		lblPlayer1 = new JLabel("");
		lblPlayer1.setBounds(10, 166, 102, 14);
		this.getContentPane().add(lblPlayer1);
		
		lblPlayer2 = new JLabel("");
		lblPlayer2.setBounds(260, 11, 102, 14);
		this.getContentPane().add(lblPlayer2);
		
		lblPlayer3 = new JLabel("");
		lblPlayer3.setBounds(639, 11, 105, 14);
		this.getContentPane().add(lblPlayer3);
		
		lblPlayer4 = new JLabel("");
		lblPlayer4.setBounds(804, 166, 102, 14);
		this.getContentPane().add(lblPlayer4);
		
		lblDealer = new JLabel("Dealer");
		lblDealer.setBounds(492, 323, 46, 14);
		this.getContentPane().add(lblDealer);
		
		imgPlayer1FirstCard = new JPanel();
		imgPlayer1FirstCard.setBounds(10, 191, 86, 121);
		this.getContentPane().add(imgPlayer1FirstCard);
		GroupLayout gl_imgPlayer1FirstCard = new GroupLayout(imgPlayer1FirstCard);
		gl_imgPlayer1FirstCard.setHorizontalGroup(
			gl_imgPlayer1FirstCard.createParallelGroup(Alignment.LEADING)
				.addGap(0, 67, Short.MAX_VALUE)
		);
		gl_imgPlayer1FirstCard.setVerticalGroup(
			gl_imgPlayer1FirstCard.createParallelGroup(Alignment.LEADING)
				.addGap(0, 97, Short.MAX_VALUE)
		);
		imgPlayer1FirstCard.setLayout(gl_imgPlayer1FirstCard);

		imgPlayer1SecondCard = new JPanel();
		imgPlayer1SecondCard.setBounds(105, 191, 86, 121);
		this.getContentPane().add(imgPlayer1SecondCard);
		imgPlayer1SecondCard.setLayout(new BorderLayout(0, 0));
		
		imgPlayer2FirstCard = new JPanel();
		imgPlayer2FirstCard.setBounds(260, 36, 86, 121);
		this.getContentPane().add(imgPlayer2FirstCard);
		GroupLayout gl_imgPlayer2FirstCard = new GroupLayout(imgPlayer2FirstCard);
		gl_imgPlayer2FirstCard.setHorizontalGroup(
			gl_imgPlayer2FirstCard.createParallelGroup(Alignment.LEADING)
				.addGap(0, 46, Short.MAX_VALUE)
		);
		gl_imgPlayer2FirstCard.setVerticalGroup(
			gl_imgPlayer2FirstCard.createParallelGroup(Alignment.LEADING)
				.addGap(0, 69, Short.MAX_VALUE)
		);
		imgPlayer2FirstCard.setLayout(gl_imgPlayer2FirstCard);
		
		imgPlayer2SecondCard = new JPanel();
		imgPlayer2SecondCard.setBounds(356, 36, 86, 121);
		this.getContentPane().add(imgPlayer2SecondCard);
		GroupLayout gl_imgPlayer2SecondCard = new GroupLayout(imgPlayer2SecondCard);
		gl_imgPlayer2SecondCard.setHorizontalGroup(
			gl_imgPlayer2SecondCard.createParallelGroup(Alignment.LEADING)
				.addGap(0, 46, Short.MAX_VALUE)
		);
		gl_imgPlayer2SecondCard.setVerticalGroup(
			gl_imgPlayer2SecondCard.createParallelGroup(Alignment.LEADING)
				.addGap(0, 69, Short.MAX_VALUE)
		);
		imgPlayer2SecondCard.setLayout(gl_imgPlayer2SecondCard);
		
		imgPlayer3FirstCard = new JPanel();
		imgPlayer3FirstCard.setBounds(639, 36, 86, 121);
		this.getContentPane().add(imgPlayer3FirstCard);
		GroupLayout gl_imgPlayer3FirstCard = new GroupLayout(imgPlayer3FirstCard);
		gl_imgPlayer3FirstCard.setHorizontalGroup(
			gl_imgPlayer3FirstCard.createParallelGroup(Alignment.LEADING)
				.addGap(0, 46, Short.MAX_VALUE)
		);
		gl_imgPlayer3FirstCard.setVerticalGroup(
			gl_imgPlayer3FirstCard.createParallelGroup(Alignment.LEADING)
				.addGap(0, 69, Short.MAX_VALUE)
		);
		imgPlayer3FirstCard.setLayout(gl_imgPlayer3FirstCard);
		
		imgPlayer3SecondCard = new JPanel();
		imgPlayer3SecondCard.setBounds(735, 36, 86, 121);
		this.getContentPane().add(imgPlayer3SecondCard);
		GroupLayout gl_imgPlayer3SecondCard = new GroupLayout(imgPlayer3SecondCard);
		gl_imgPlayer3SecondCard.setHorizontalGroup(
			gl_imgPlayer3SecondCard.createParallelGroup(Alignment.LEADING)
				.addGap(0, 46, Short.MAX_VALUE)
		);
		gl_imgPlayer3SecondCard.setVerticalGroup(
			gl_imgPlayer3SecondCard.createParallelGroup(Alignment.LEADING)
				.addGap(0, 69, Short.MAX_VALUE)
		);
		imgPlayer3SecondCard.setLayout(gl_imgPlayer3SecondCard);
		
		imgPlayer4FirstCard = new JPanel();
		imgPlayer4FirstCard.setBounds(804, 191, 86, 121);
		this.getContentPane().add(imgPlayer4FirstCard);
		GroupLayout gl_imgPlayer4FirstCard = new GroupLayout(imgPlayer4FirstCard);
		gl_imgPlayer4FirstCard.setHorizontalGroup(
			gl_imgPlayer4FirstCard.createParallelGroup(Alignment.LEADING)
				.addGap(0, 46, Short.MAX_VALUE)
		);
		gl_imgPlayer4FirstCard.setVerticalGroup(
			gl_imgPlayer4FirstCard.createParallelGroup(Alignment.LEADING)
				.addGap(0, 69, Short.MAX_VALUE)
		);
		imgPlayer4FirstCard.setLayout(gl_imgPlayer4FirstCard);
		
		imgPlayer4SecondCard = new JPanel();
		imgPlayer4SecondCard.setBounds(900, 191, 86, 121);
		this.getContentPane().add(imgPlayer4SecondCard);
		GroupLayout gl_imgPlayer4SecondCard = new GroupLayout(imgPlayer4SecondCard);
		gl_imgPlayer4SecondCard.setHorizontalGroup(
			gl_imgPlayer4SecondCard.createParallelGroup(Alignment.LEADING)
				.addGap(0, 46, Short.MAX_VALUE)
		);
		gl_imgPlayer4SecondCard.setVerticalGroup(
			gl_imgPlayer4SecondCard.createParallelGroup(Alignment.LEADING)
				.addGap(0, 69, Short.MAX_VALUE)
		);
		imgPlayer4SecondCard.setLayout(gl_imgPlayer4SecondCard);
		
		imgFlopFirstCard = new JPanel();
		imgFlopFirstCard.setBounds(260, 191, 86, 121);
		this.getContentPane().add(imgFlopFirstCard);
		GroupLayout gl_imgFlopFirstCard = new GroupLayout(imgFlopFirstCard);
		gl_imgFlopFirstCard.setHorizontalGroup(
			gl_imgFlopFirstCard.createParallelGroup(Alignment.LEADING)
				.addGap(0, 46, Short.MAX_VALUE)
		);
		gl_imgFlopFirstCard.setVerticalGroup(
			gl_imgFlopFirstCard.createParallelGroup(Alignment.LEADING)
				.addGap(0, 69, Short.MAX_VALUE)
		);
		imgFlopFirstCard.setLayout(gl_imgFlopFirstCard);
		
		imgFlopSecondCard = new JPanel();
		imgFlopSecondCard.setBounds(356, 191, 86, 121);
		this.getContentPane().add(imgFlopSecondCard);
		GroupLayout gl_imgFlopSecondCard = new GroupLayout(imgFlopSecondCard);
		gl_imgFlopSecondCard.setHorizontalGroup(
			gl_imgFlopSecondCard.createParallelGroup(Alignment.LEADING)
				.addGap(0, 46, Short.MAX_VALUE)
		);
		gl_imgFlopSecondCard.setVerticalGroup(
			gl_imgFlopSecondCard.createParallelGroup(Alignment.LEADING)
				.addGap(0, 69, Short.MAX_VALUE)
		);
		imgFlopSecondCard.setLayout(gl_imgFlopSecondCard);
		
		imgFlopCardThirdCard = new JPanel();
		imgFlopCardThirdCard.setBounds(452, 191, 86, 121);
		this.getContentPane().add(imgFlopCardThirdCard);
		GroupLayout gl_imgFlopCardThirdCard = new GroupLayout(imgFlopCardThirdCard);
		gl_imgFlopCardThirdCard.setHorizontalGroup(
			gl_imgFlopCardThirdCard.createParallelGroup(Alignment.LEADING)
				.addGap(0, 46, Short.MAX_VALUE)
		);
		gl_imgFlopCardThirdCard.setVerticalGroup(
			gl_imgFlopCardThirdCard.createParallelGroup(Alignment.LEADING)
				.addGap(0, 69, Short.MAX_VALUE)
		);
		imgFlopCardThirdCard.setLayout(gl_imgFlopCardThirdCard);
		
		imgTurnCard = new JPanel();
		imgTurnCard.setBounds(548, 191, 86, 121);
		this.getContentPane().add(imgTurnCard);
		GroupLayout gl_imgTurnCard = new GroupLayout(imgTurnCard);
		gl_imgTurnCard.setHorizontalGroup(
			gl_imgTurnCard.createParallelGroup(Alignment.LEADING)
				.addGap(0, 46, Short.MAX_VALUE)
		);
		gl_imgTurnCard.setVerticalGroup(
			gl_imgTurnCard.createParallelGroup(Alignment.LEADING)
				.addGap(0, 69, Short.MAX_VALUE)
		);
		imgTurnCard.setLayout(gl_imgTurnCard);
		
		imgRiverCard = new JPanel();
		imgRiverCard.setBounds(643, 191, 86, 121);
		this.getContentPane().add(imgRiverCard);
		GroupLayout gl_imgRiverCard = new GroupLayout(imgRiverCard);
		gl_imgRiverCard.setHorizontalGroup(
			gl_imgRiverCard.createParallelGroup(Alignment.LEADING)
				.addGap(0, 46, Short.MAX_VALUE)
		);
		gl_imgRiverCard.setVerticalGroup(
			gl_imgRiverCard.createParallelGroup(Alignment.LEADING)
				.addGap(0, 69, Short.MAX_VALUE)
		);
		imgRiverCard.setLayout(gl_imgRiverCard);
		
		this.hideAllImages();
	}
	
	public void initializeGame(ArrayList<Player> players) throws Exception {
		this.game = new PokerGame(10, 0);
		
		this.initializePlayers(players);
		
		int bigBlind = this.game.getBigBlind();	
		
		this.game.dealCards();
		this.game.dealCards();
		
		this.showPlayerInfo(this.game.getPlayers());
		
		boolean finishGame = false;
		int gameState = 0;
		
		while(gameState < 3 && !finishGame)
		{
			boolean allPlayersHasTheSameBet = false;
			int playerPlayCounter = 1;
			do
			{				
				PokerPlayer currentPlayer = this.game.getPlayer();
				
				bigBlind = this.game.getBigBlind();
				
				ArrayList<PokerCard> tableCards = this.game.getTableCards();
				
				//Wait for UI Response
				TexasHoldemPlayerDecision playerDecideForm = new TexasHoldemPlayerDecision(tableCards, currentPlayer, bigBlind);
				playerDecideForm.setVisible(true);
				
				PokerPlayerDecision playerDecision = playerDecideForm.getPlayerDecision();
				
				if (playerDecision == PokerPlayerDecision.Raise) {
					int bet = playerDecideForm.getRaiseAmount();
					this.game.playTurn(playerDecision, bet);
				}
				else {
					this.game.playTurn(playerDecision);					
				}
				
				playerDecideForm.dispose();
				
				
				if(this.game.getPlayers().size() == 1) 
				{
					finishGame = true;
				}
				
				if (this.game.allPlayersHasSameBet() && playerPlayCounter % this.game.getPlayers().size() == 0) {
					allPlayersHasTheSameBet = true;
				}
				
				this.game.nextTurn();
				playerPlayCounter++;
			}				
			while(!finishGame && !allPlayersHasTheSameBet);
			
			if (!finishGame)
			{
				switch(gameState)
				{
					case 0:
						this.game.flop();
						showFlopCard();
						break;
					case 1:
						this.game.turn();
						showTurnCards();
						break;
					case 2:
						this.game.river();
						showRiverCard();
						break;
					default:
						break;
				}
				
				gameState++;
			}
		}
		
		game.finishGame();		
	}
	
	private void showFlopCard() {
		ArrayList<PokerCard> cards = this.game.getTableCards();
		
		PokerCard firstFlopCard = cards.get(0);
		PokerCard secondTurnCard = cards.get(1);
		PokerCard thirdTurnCard = cards.get(2);

		this.showCard(imgFlopFirstCard, firstFlopCard);
		this.showCard(imgFlopSecondCard, secondTurnCard);
		this.showCard(imgFlopCardThirdCard, thirdTurnCard);
	}
	
	private void showTurnCards() {
		ArrayList<PokerCard> cards = this.game.getTableCards();
		PokerCard turnCard = cards.get(3);
		
		this.showCard(imgTurnCard, turnCard);
	}	
	
	private void showRiverCard() {
		ArrayList<PokerCard> cards = this.game.getTableCards();
		PokerCard riverCard = cards.get(3);
		
		this.showCard(imgRiverCard, riverCard);
	}
	
	private void showPlayerInfo(ArrayList<PokerPlayer> pokerPlayers) {			
		for (int i = 0; i < pokerPlayers.size(); i++) {
			PokerPlayer player = pokerPlayers.get(i);
			
			if (i == 0) {
				this.setPlayerInfo(player, lblPlayer1, imgPlayer1FirstCard, imgPlayer1SecondCard);
			}
			
			if (i == 1) {
				this.setPlayerInfo(player, lblPlayer2, imgPlayer2FirstCard, imgPlayer2SecondCard);
			}
			
			if (i == 2) {
				this.setPlayerInfo(player, lblPlayer3, imgPlayer3FirstCard, imgPlayer3SecondCard);
			}
			
			if (i == 3) {
				this.setPlayerInfo(player, lblPlayer4, imgPlayer4FirstCard, imgPlayer4SecondCard);
			}
		}
	}
	
	private void hideAllImages() {
		imgPlayer1FirstCard.setVisible(false);
		imgPlayer1SecondCard.setVisible(false);
		imgPlayer2FirstCard.setVisible(false);
		imgPlayer2SecondCard.setVisible(false);
		imgPlayer3FirstCard.setVisible(false);
		imgPlayer3SecondCard.setVisible(false);
		imgPlayer4FirstCard.setVisible(false);
		imgPlayer4SecondCard.setVisible(false);
		
		imgFlopFirstCard.setVisible(false);
		imgFlopSecondCard.setVisible(false);
		imgFlopCardThirdCard.setVisible(false);
		imgTurnCard.setVisible(false);
		imgRiverCard.setVisible(false);
	}
	
	private void initializePlayers(ArrayList<Player> players) {	
		ArrayList<PokerPlayer> pokerPlayers = getPokerPlayers(players);
	
		for(PokerPlayer pokerPlayer : pokerPlayers) {
			this.game.addPlayer(pokerPlayer);
		}
	}
	
	private void setPlayerInfo(PokerPlayer player, JLabel lblPlayerName, JPanel firstCardPanel, JPanel secondCardPanel) {
		if (player != null) {
			String playerName = player.getName();
			lblPlayerName.setText(playerName);
			
			ArrayList<PokerCard> hand = player.getHand();
			
			PokerCard firstCard = hand.get(0);
			PokerCard secondCard = hand.get(1);
			
			this.showCard(firstCardPanel, firstCard);
			this.showCard(secondCardPanel, secondCard);
		}
	}
	
	private ArrayList<PokerPlayer> getPokerPlayers(ArrayList<Player> players) {
		ArrayList<PokerPlayer> pokerPlayers = new ArrayList<PokerPlayer>();
		
		for(Player player : players) {
			PokerPlayer pokerPlayer = new PokerPlayer(player.getName());			
			pokerPlayer.setBalance(player.getSalary());
			pokerPlayers.add(pokerPlayer);
		}
		
		return pokerPlayers;
	}
	
	private void showCard(JPanel cardPanel, PokerCard card) {
		String cardFilename = FileHelper.getImageCard(card);		
		cardPanel.add(new ImagePanel(cardFilename));		
		cardPanel.setVisible(true);
	}
}
