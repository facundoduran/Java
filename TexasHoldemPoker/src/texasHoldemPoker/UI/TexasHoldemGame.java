package texasHoldemPoker.UI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.util.ArrayList;

import texasHoldemPoker.Common.FileHelper;
import texasHoldemPoker.Model.Player;
import texasHoldemPoker.Model.PokerCard;
import texasHoldemPoker.Model.PokerGame;
import texasHoldemPoker.Model.PokerHandEvaluation;
import texasHoldemPoker.Model.PokerPlayer;
import texasHoldemPoker.Model.PokerPlayerDecision;
import texasHoldemPoker.Persistence.Sql.Dao.IPlayerDAO;
import texasHoldemPoker.Persistence.Sql.Dao.PlayerDAO;
import texasHoldemPoker.UI.CustomControls.ImagePanel;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.EventQueue;
import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class TexasHoldemGame extends JFrame{
	
	private IPlayerDAO playerDAO;
	private PokerGame game;	
	private ArrayList<String> players;
	private int bigBlind;
	private int bigBlindPos;
		
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
								
					ArrayList<String> players = new ArrayList<String>();
					players.add("Facundo");
					players.add("Julieta");
					players.add("Juan");
					
					TexasHoldemGame window = new TexasHoldemGame(players, 4);

					window.setVisible(true);
					window.initializeGame();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public TexasHoldemGame(ArrayList<String> players, int bigBlind) throws Exception {
		initialize();
		this.bigBlindPos = 2;
		this.players = players;
		this.bigBlind = bigBlind;
		this.playerDAO = new PlayerDAO();
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnGame = new JMenu("Juego");
		menuBar.add(mnGame);
		
		JMenuItem mnItemNewGame = new JMenuItem("Nuevo Juego");
		mnItemNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				newGame();
			}
		});
		mnGame.add(mnItemNewGame);
		
		JMenuItem mnItemExit = new JMenuItem("Salir");
		mnItemExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitGame();
			}
		});
		mnGame.add(mnItemExit);
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
	private JPanel pnlWinner;
	private JLabel lblWinnerInfo;
	private JLabel lblPotInfo;
	private JLabel lblWinner;
	private JLabel lblPot;
	private JMenuBar menuBar;
	private JLabel lblFirstPlayerHandResult;
	private JLabel lblFirstPlayerHandResultInfo;
	private JLabel lblSecondPlayerHandResult;
	private JLabel lblSecondPlayerHandResultInfo;
	private JLabel lblThirdPlayerHandResult;
	private JLabel lblThirdPlayerHandResultInfo;
	private JLabel lblFourthPlayerHandResult;
	private JLabel lblFourthPlayerHandResultInfo;

	private void initialize() {
		this.setTitle("Poker");
		this.setBounds(100, 100, 1023, 526);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		lblPlayer1 = new JLabel("");
		lblPlayer1.setBounds(10, 166, 102, 14);
		this.getContentPane().add(lblPlayer1);
		
		lblPlayer2 = new JLabel("");
		lblPlayer2.setBounds(148, 11, 102, 14);
		this.getContentPane().add(lblPlayer2);
		
		lblPlayer3 = new JLabel("");
		lblPlayer3.setBounds(527, 11, 105, 14);
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
		imgPlayer2FirstCard.setBounds(260, 11, 86, 121);
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
		imgPlayer2SecondCard.setBounds(356, 11, 86, 121);
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
		imgPlayer3FirstCard.setBounds(639, 11, 86, 121);
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
		imgPlayer3SecondCard.setBounds(735, 11, 86, 121);
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
		
		pnlWinner = new JPanel();
		pnlWinner.setBounds(260, 361, 465, 76);
		pnlWinner.setVisible(false);
		getContentPane().add(pnlWinner);
		
		lblWinner = new JLabel("Ganador:");
		
		lblPot = new JLabel("Pozo:");
		
		lblWinnerInfo = new JLabel("");
		
		lblPotInfo = new JLabel("");
		GroupLayout gl_pnlWinner = new GroupLayout(pnlWinner);
		gl_pnlWinner.setHorizontalGroup(
			gl_pnlWinner.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlWinner.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlWinner.createParallelGroup(Alignment.LEADING)
						.addComponent(lblWinner, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPot))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_pnlWinner.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlWinner.createSequentialGroup()
							.addComponent(lblPotInfo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addContainerGap(29, Short.MAX_VALUE))
						.addGroup(gl_pnlWinner.createSequentialGroup()
							.addComponent(lblWinnerInfo, GroupLayout.PREFERRED_SIZE, 356, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_pnlWinner.setVerticalGroup(
			gl_pnlWinner.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlWinner.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlWinner.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblWinner)
						.addComponent(lblWinnerInfo, GroupLayout.PREFERRED_SIZE, 9, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnlWinner.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblPotInfo, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPot))
					.addContainerGap(31, Short.MAX_VALUE))
		);
		pnlWinner.setLayout(gl_pnlWinner);
		
		lblFirstPlayerHandResult = new JLabel("Mano:");
		lblFirstPlayerHandResult.setBounds(10, 323, 38, 14);
		getContentPane().add(lblFirstPlayerHandResult);
		
		lblFirstPlayerHandResultInfo = new JLabel("");
		lblFirstPlayerHandResultInfo.setBounds(58, 323, 133, 14);
		getContentPane().add(lblFirstPlayerHandResultInfo);
		
		lblSecondPlayerHandResult = new JLabel("Mano:");
		lblSecondPlayerHandResult.setBounds(260, 141, 38, 14);
		getContentPane().add(lblSecondPlayerHandResult);
		
		lblSecondPlayerHandResultInfo = new JLabel("");
		lblSecondPlayerHandResultInfo.setBounds(308, 141, 134, 14);
		getContentPane().add(lblSecondPlayerHandResultInfo);
		
		lblThirdPlayerHandResult = new JLabel("Mano:");
		lblThirdPlayerHandResult.setBounds(639, 141, 38, 14);
		getContentPane().add(lblThirdPlayerHandResult);
		
		lblThirdPlayerHandResultInfo = new JLabel("");
		lblThirdPlayerHandResultInfo.setBounds(687, 141, 134, 14);
		getContentPane().add(lblThirdPlayerHandResultInfo);
		
		lblFourthPlayerHandResult = new JLabel("Mano:");
		lblFourthPlayerHandResult.setBounds(804, 323, 38, 14);
		getContentPane().add(lblFourthPlayerHandResult);
		
		lblFourthPlayerHandResultInfo = new JLabel("");
		lblFourthPlayerHandResultInfo.setBounds(852, 323, 134, 14);
		getContentPane().add(lblFourthPlayerHandResultInfo);
		
		this.showAllImages(false);
	}
	
	public void initializeGame() throws Exception {
		
		this.game = new PokerGame(bigBlind);
		
		this.hideShowdownControls();
		
		this.initializePlayers();
		
		bigBlindPos = bigBlindPos % this.game.getPlayers().size();
		
		this.game.startGame(bigBlindPos);
		
		int bigBlind = this.game.getBigBlind();	
		
		this.game.dealCards();
		
		this.showPlayerInfo(this.game.getPlayers(), false);
		
		boolean allInBreak = false;
		boolean finishGame = false;
		int gameState = 0;
		
		while(gameState < 3 && !finishGame)
		{
			boolean continuePlaying = false;
			int playerPlayCounter = 1;
			do
			{				
				PokerPlayer currentPlayer = this.game.getPlayer();
				
				bigBlind = this.game.getBigBlind();
				
				ArrayList<PokerCard> tableCards = this.game.getCommunitaryCards();
				
				int pot = this.game.getPot();
												
				if (!currentPlayer.madeAllIn() && !allInBreak) {
					//Wait for UI Response
					TexasHoldemPlayerDecision playerDecideForm = new TexasHoldemPlayerDecision(tableCards, currentPlayer, bigBlind, pot);
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
				}
				else {
					this.game.nextTurn(PokerPlayerDecision.AllIn);
				}
				
				//check if all players leave
				if(this.game.getPlayingPlayers().size() == 1) 
				{
					finishGame = true;
				}
				
				//check if all players raise
				if (this.game.allPlayersHasSameBet() && playerPlayCounter % this.game.getPlayingPlayers().size() == 0) {
					continuePlaying = true;
				}
				
				//check if all players made all in
				if (!this.game.existMoreThanOnePlayerWithoutAllInOrLeave()) {
					continuePlaying = true;
				}
				
				playerPlayCounter++;
			}				
			while(!finishGame && !continuePlaying);
			
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
		
		ArrayList<PokerHandEvaluation> winners = game.finishGame();
		
		ArrayList<PokerHandEvaluation> finalEvaluation = game.getPlayersEvaluation();
		
		this.showWinnersInfo(winners);
		
		this.showPlayerEvaluation(finalEvaluation);
		
		this.showPlayerInfo(game.getPlayers(), true);
		
		this.pnlWinner.setVisible(true);
		
		this.bigBlindPos++;
	}

	private void showFlopCard() {
		ArrayList<PokerCard> cards = this.game.getCommunitaryCards();
		
		PokerCard firstFlopCard = cards.get(0);
		PokerCard secondTurnCard = cards.get(1);
		PokerCard thirdTurnCard = cards.get(2);

		this.showCard(imgFlopFirstCard, firstFlopCard);
		this.showCard(imgFlopSecondCard, secondTurnCard);
		this.showCard(imgFlopCardThirdCard, thirdTurnCard);
	}
	
	private void showTurnCards() {
		ArrayList<PokerCard> cards = this.game.getCommunitaryCards();
		PokerCard turnCard = cards.get(3);
		
		this.showCard(imgTurnCard, turnCard);
	}	
	
	private void showRiverCard() {
		ArrayList<PokerCard> cards = this.game.getCommunitaryCards();
		PokerCard riverCard = cards.get(4);
		
		this.showCard(imgRiverCard, riverCard);
	}
	
	private void showPlayerInfo(ArrayList<PokerPlayer> pokerPlayers, boolean showCards) {			
		for (int i = 0; i < pokerPlayers.size(); i++) {
			PokerPlayer player = pokerPlayers.get(i);
			
			if (i == 0) {
				this.setPlayerInfo(player, lblPlayer1, imgPlayer1FirstCard, imgPlayer1SecondCard, showCards);
			}
			
			if (i == 1) {
				this.setPlayerInfo(player, lblPlayer2, imgPlayer2FirstCard, imgPlayer2SecondCard, showCards);
			}
			
			if (i == 2) {
				this.setPlayerInfo(player, lblPlayer3, imgPlayer3FirstCard, imgPlayer3SecondCard, showCards);
			}
			
			if (i == 3) {
				this.setPlayerInfo(player, lblPlayer4, imgPlayer4FirstCard, imgPlayer4SecondCard, showCards);
			}
		}
	}
	
	private void showAllImages(boolean show) {
		imgPlayer1FirstCard.setVisible(show);
		imgPlayer1SecondCard.setVisible(show);
		imgPlayer2FirstCard.setVisible(show);
		imgPlayer2SecondCard.setVisible(show);
		imgPlayer3FirstCard.setVisible(show);
		imgPlayer3SecondCard.setVisible(show);
		imgPlayer4FirstCard.setVisible(show);
		imgPlayer4SecondCard.setVisible(show);
		
		imgFlopFirstCard.setVisible(show);
		imgFlopSecondCard.setVisible(show);
		imgFlopCardThirdCard.setVisible(show);
		imgTurnCard.setVisible(show);
		imgRiverCard.setVisible(show);
	}
	
	private void initializePlayers() {	
		//Search the player in the database using player names
		ArrayList<Player> players = playerDAO.getPlayersInList(this.players);
	
		for (Player player : players) {
			String playerName = player.getName();
			int balance = player.getSalary();
			
			PokerPlayer pokerPlayer = new PokerPlayer(playerName);
			pokerPlayer.setBalance(balance);
			
			this.game.addPlayer(pokerPlayer);
		}
	}
	
	private void setPlayerInfo(PokerPlayer player, JLabel lblPlayerName, JPanel firstCardPanel, JPanel secondCardPanel, boolean showCards) {
		if (player != null) {
			String playerName = player.getName();
			lblPlayerName.setText(playerName);
						
			firstCardPanel.removeAll();
			secondCardPanel.removeAll();
			
			if (showCards) {				
				ArrayList<PokerCard> hand = player.getHand();
				
				PokerCard firstCard = hand.get(0);
				PokerCard secondCard = hand.get(1);
				
				this.showCard(firstCardPanel, firstCard);
				this.showCard(secondCardPanel, secondCard);
			}
			else{
				String hiddenCard = FileHelper.getImagePath("b1fv");
				firstCardPanel.add(new ImagePanel(hiddenCard));
				secondCardPanel.add(new ImagePanel(hiddenCard));
				firstCardPanel.setVisible(true);
				secondCardPanel.setVisible(true);
			}		
		}
	}
	
	private void newGame() {
		this.pnlWinner.setVisible(false);
		this.clearControls();
		this.showAllImages(false);
		this.hideShowdownControls();
		
		try {
			this.initializeGame();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void exitGame() {
		int result = JOptionPane.showConfirmDialog(new JFrame(), 
				   "Esta seguro de salir del juego? ", "Informacion!", JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION) {
				    System.exit(0);
				} 
	}
	
	private void showCard(JPanel cardPanel, PokerCard card) {
		cardPanel.removeAll();
		String cardFilename = FileHelper.getImageCard(card);
		cardPanel.setVisible(false);
		cardPanel.add(new ImagePanel(cardFilename));
		cardPanel.setVisible(true);
	}
	
	private void clearControls() {
		imgPlayer1FirstCard.removeAll();
		imgPlayer1SecondCard.removeAll();
		imgPlayer2FirstCard.removeAll();
		imgPlayer2SecondCard.removeAll();
		imgPlayer3FirstCard.removeAll();
		imgPlayer3SecondCard.removeAll();
		imgPlayer4FirstCard.removeAll();
		imgPlayer4SecondCard.removeAll();	
		
		imgFlopFirstCard.removeAll();
		imgFlopSecondCard.removeAll();
		imgFlopCardThirdCard.removeAll();
		imgTurnCard.removeAll();
		imgRiverCard.removeAll();
	}
	
	private void showPlayerEvaluation(ArrayList<PokerHandEvaluation> playersEvaluation){
		this.showdownPlayerControls(playersEvaluation, true);
	}
	
	private void updatePlayer(PokerPlayer pokerPlayer) {
		String playerName = pokerPlayer.getName();
		int salary = pokerPlayer.getBalance();
		playerDAO.updateSalary(playerName, salary);
	}
	
	private void showWinnersInfo(ArrayList<PokerHandEvaluation> winners) {
		
		String winnerNames = "";
		
		for (PokerHandEvaluation winner : winners) {
			PokerPlayer player = winner.getPlayer();
			winnerNames += player.getName() + " ";
			this.updatePlayer(player);			
		}
		
		String pot = Integer.toString(this.game.getPot());
		
		lblWinnerInfo.setText(winnerNames);
		lblPotInfo.setText(pot);
	}
	
	private void showdownPlayerControls(ArrayList<PokerHandEvaluation> playerEvaluation, boolean show) {
		
		for (int i = 0; i < playerEvaluation.size(); i++) {
			String rankDescription = playerEvaluation.get(i).getRankDescription();
			
			if (i == 0) {
				lblFirstPlayerHandResult.setVisible(show);
				lblFirstPlayerHandResultInfo.setVisible(show);
				lblFirstPlayerHandResultInfo.setText(rankDescription);
			}
			
			if (i == 1) {
				lblSecondPlayerHandResult.setVisible(show);
				lblSecondPlayerHandResultInfo.setVisible(show);
				lblSecondPlayerHandResultInfo.setText(rankDescription);
			}
			
			if (i == 2) {
				lblThirdPlayerHandResult.setVisible(show);
				lblThirdPlayerHandResultInfo.setVisible(show);
				lblThirdPlayerHandResultInfo.setText(rankDescription);				
			}
			
			if (i == 3) {
				lblFourthPlayerHandResult.setVisible(show);
				lblFourthPlayerHandResultInfo.setVisible(show);
				lblFourthPlayerHandResultInfo.setText(rankDescription);				
			}
		}
		lblWinner.setVisible(show);
		lblWinnerInfo.setVisible(show);
		pnlWinner.setVisible(show);
		
		lblPot.setVisible(show);
		lblPotInfo.setVisible(show);
	}
	
	private void hideShowdownControls() {
		lblFirstPlayerHandResult.setVisible(false);
		lblFirstPlayerHandResultInfo.setVisible(false);
		lblSecondPlayerHandResult.setVisible(false);
		lblSecondPlayerHandResultInfo.setVisible(false);
		lblThirdPlayerHandResult.setVisible(false);
		lblThirdPlayerHandResultInfo.setVisible(false);
		lblFourthPlayerHandResult.setVisible(false);
		lblFourthPlayerHandResultInfo.setVisible(false);
		lblWinner.setVisible(false);
		lblWinnerInfo.setVisible(false);
		pnlWinner.setVisible(false);
		
		lblPot.setVisible(false);
		lblPotInfo.setVisible(false);	
	}
}
