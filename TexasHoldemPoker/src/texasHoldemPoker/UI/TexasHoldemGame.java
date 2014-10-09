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
	private JPanel imgTurnFirstCard;
	private JPanel imgTurnSecondCard;
	private JPanel imgTurnThirdCard;
	private JPanel imgFlopCard;		
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
		
		imgTurnFirstCard = new JPanel();
		imgTurnFirstCard.setBounds(260, 191, 86, 121);
		this.getContentPane().add(imgTurnFirstCard);
		GroupLayout gl_imgTurnFirstCard = new GroupLayout(imgTurnFirstCard);
		gl_imgTurnFirstCard.setHorizontalGroup(
			gl_imgTurnFirstCard.createParallelGroup(Alignment.LEADING)
				.addGap(0, 46, Short.MAX_VALUE)
		);
		gl_imgTurnFirstCard.setVerticalGroup(
			gl_imgTurnFirstCard.createParallelGroup(Alignment.LEADING)
				.addGap(0, 69, Short.MAX_VALUE)
		);
		imgTurnFirstCard.setLayout(gl_imgTurnFirstCard);
		
		imgTurnSecondCard = new JPanel();
		imgTurnSecondCard.setBounds(356, 191, 86, 121);
		this.getContentPane().add(imgTurnSecondCard);
		GroupLayout gl_imgTurnSecondCard = new GroupLayout(imgTurnSecondCard);
		gl_imgTurnSecondCard.setHorizontalGroup(
			gl_imgTurnSecondCard.createParallelGroup(Alignment.LEADING)
				.addGap(0, 46, Short.MAX_VALUE)
		);
		gl_imgTurnSecondCard.setVerticalGroup(
			gl_imgTurnSecondCard.createParallelGroup(Alignment.LEADING)
				.addGap(0, 69, Short.MAX_VALUE)
		);
		imgTurnSecondCard.setLayout(gl_imgTurnSecondCard);
		
		imgTurnThirdCard = new JPanel();
		imgTurnThirdCard.setBounds(452, 191, 86, 121);
		this.getContentPane().add(imgTurnThirdCard);
		GroupLayout gl_imgTurnThirdCard = new GroupLayout(imgTurnThirdCard);
		gl_imgTurnThirdCard.setHorizontalGroup(
			gl_imgTurnThirdCard.createParallelGroup(Alignment.LEADING)
				.addGap(0, 46, Short.MAX_VALUE)
		);
		gl_imgTurnThirdCard.setVerticalGroup(
			gl_imgTurnThirdCard.createParallelGroup(Alignment.LEADING)
				.addGap(0, 69, Short.MAX_VALUE)
		);
		imgTurnThirdCard.setLayout(gl_imgTurnThirdCard);
		
		imgFlopCard = new JPanel();
		imgFlopCard.setBounds(548, 191, 86, 121);
		this.getContentPane().add(imgFlopCard);
		GroupLayout gl_imgFlopCard = new GroupLayout(imgFlopCard);
		gl_imgFlopCard.setHorizontalGroup(
			gl_imgFlopCard.createParallelGroup(Alignment.LEADING)
				.addGap(0, 46, Short.MAX_VALUE)
		);
		gl_imgFlopCard.setVerticalGroup(
			gl_imgFlopCard.createParallelGroup(Alignment.LEADING)
				.addGap(0, 69, Short.MAX_VALUE)
		);
		imgFlopCard.setLayout(gl_imgFlopCard);
		
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
				
				this.game.nextTurn();
			}				
			while(!this.game.allPlayersHasSameBet() || !finishGame);
			
			if (!finishGame)
			{
				switch(gameState)
				{
					case 0:
						this.game.turn();
						showTurnCards();
						break;
					case 1:
						this.game.flop();
						showFlopCard();
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
	
	private void showTurnCards() {
		
	}
	
	private void showFlopCard() {
		
	}
	
	private void showRiverCard() {
		
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
			
			String firstCardFilename = FileHelper.getImageCard(firstCard);
			String secondCardFilename = FileHelper.getImageCard(secondCard);
			
			firstCardPanel.add(new ImagePanel(firstCardFilename));
			secondCardPanel.add(new ImagePanel(secondCardFilename));
			
			firstCardPanel.setVisible(true);
			secondCardPanel.setVisible(true);
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
}
