package texasHoldemPoker.UI;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;

import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JSlider;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import texasHoldemPoker.Model.Player;
import texasHoldemPoker.Model.PokerGame;
import texasHoldemPoker.Model.PokerPlayer;
import texasHoldemPoker.Model.PokerPlayerDecision;

import javax.swing.JTextField;

public class TexasHoldemGame {

	private JFrame frmPoker;
	
	private PokerGame game;
	
	private Boolean playerPlays; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TexasHoldemGame window = new TexasHoldemGame();
					window.frmPoker.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TexasHoldemGame() throws Exception {
		initialize();
		this.initializeGame();
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
	private JLabel lblTurno;
	private JLabel lblCurrentPlayer;		
	private JLabel lblActions;	
	private JButton btnLeave;
	private JButton btnCheck;
	private JButton btnBet;
	private JSlider slBet;
	private JTextField txtBet;

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPoker = new JFrame();
		frmPoker.setTitle("Poker");
		frmPoker.setBounds(100, 100, 677, 459);
		frmPoker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPoker.getContentPane().setLayout(null);
		
		lblPlayer1 = new JLabel("New label");
		lblPlayer1.setBounds(10, 166, 102, 14);
		frmPoker.getContentPane().add(lblPlayer1);
		
		lblPlayer2 = new JLabel("New label");
		lblPlayer2.setBounds(154, 11, 102, 14);
		frmPoker.getContentPane().add(lblPlayer2);
		
		lblPlayer3 = new JLabel("New label");
		lblPlayer3.setBounds(393, 11, 105, 14);
		frmPoker.getContentPane().add(lblPlayer3);
		
		lblPlayer4 = new JLabel("New label");
		lblPlayer4.setBounds(538, 166, 102, 14);
		frmPoker.getContentPane().add(lblPlayer4);
		
		lblDealer = new JLabel("Dealer");
		lblDealer.setBounds(302, 290, 46, 14);
		frmPoker.getContentPane().add(lblDealer);
		
		imgPlayer1FirstCard = new JPanel();
		imgPlayer1FirstCard.setBounds(10, 191, 46, 69);
		frmPoker.getContentPane().add(imgPlayer1FirstCard);

		imgPlayer1SecondCard = new JPanel();
		imgPlayer1SecondCard.setBounds(66, 191, 46, 69);
		frmPoker.getContentPane().add(imgPlayer1SecondCard);
		
		imgPlayer2FirstCard = new JPanel();
		imgPlayer2FirstCard.setBounds(154, 36, 46, 69);
		frmPoker.getContentPane().add(imgPlayer2FirstCard);
		
		imgPlayer2SecondCard = new JPanel();
		imgPlayer2SecondCard.setBounds(210, 36, 46, 69);
		frmPoker.getContentPane().add(imgPlayer2SecondCard);
		
		imgPlayer3FirstCard = new JPanel();
		imgPlayer3FirstCard.setBounds(393, 36, 46, 69);
		frmPoker.getContentPane().add(imgPlayer3FirstCard);
		
		imgPlayer3SecondCard = new JPanel();
		imgPlayer3SecondCard.setBounds(452, 36, 46, 69);
		frmPoker.getContentPane().add(imgPlayer3SecondCard);
		
		imgPlayer4FirstCard = new JPanel();
		imgPlayer4FirstCard.setBounds(538, 191, 46, 69);
		frmPoker.getContentPane().add(imgPlayer4FirstCard);
		
		imgPlayer4SecondCard = new JPanel();
		imgPlayer4SecondCard.setBounds(594, 191, 46, 69);
		frmPoker.getContentPane().add(imgPlayer4SecondCard);
		
		imgTurnFirstCard = new JPanel();
		imgTurnFirstCard.setBounds(190, 191, 46, 69);
		frmPoker.getContentPane().add(imgTurnFirstCard);
		
		imgTurnSecondCard = new JPanel();
		imgTurnSecondCard.setBounds(246, 191, 46, 69);
		frmPoker.getContentPane().add(imgTurnSecondCard);
		
		imgTurnThirdCard = new JPanel();
		imgTurnThirdCard.setBounds(302, 191, 46, 69);
		frmPoker.getContentPane().add(imgTurnThirdCard);
		
		imgFlopCard = new JPanel();
		imgFlopCard.setBounds(358, 191, 46, 69);
		frmPoker.getContentPane().add(imgFlopCard);
		
		imgRiverCard = new JPanel();
		imgRiverCard.setBounds(414, 191, 46, 69);
		frmPoker.getContentPane().add(imgRiverCard);
		
		lblTurno = new JLabel("Turno del jugador:");
		lblTurno.setBounds(22, 316, 110, 14);
		frmPoker.getContentPane().add(lblTurno);
		
		lblCurrentPlayer = new JLabel("New label");
		lblCurrentPlayer.setBounds(142, 316, 58, 14);
		frmPoker.getContentPane().add(lblCurrentPlayer);
		
		lblActions = new JLabel("Acciones:");
		lblActions.setBounds(22, 337, 58, 14);
		frmPoker.getContentPane().add(lblActions);
		
		btnLeave = new JButton("Retirarse");
		btnLeave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				playerLeave();
			}
		});
		btnLeave.setBounds(128, 353, 89, 23);
		frmPoker.getContentPane().add(btnLeave);
		
		btnCheck = new JButton("Pasar");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerChecks();
			}
		});
		btnCheck.setBounds(221, 353, 78, 23);
		frmPoker.getContentPane().add(btnCheck);
		
		btnBet = new JButton("Apostar");
		btnBet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerBet();				
			}
		});
		btnBet.setBounds(302, 353, 76, 23);
		frmPoker.getContentPane().add(btnBet);
		
		slBet = new JSlider();
		slBet.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
		        int currentValue = ((JSlider)event.getSource()).getValue();
		        updateMethod(currentValue);
			}
		});
		
		slBet.setBounds(128, 387, 181, 23);
		frmPoker.getContentPane().add(slBet);
		
		txtBet = new JTextField();
		txtBet.setBounds(319, 390, 76, 20);
		frmPoker.getContentPane().add(txtBet);
		txtBet.setColumns(10);
	}
	
	public void initializeGame() throws Exception {
		this.game = new PokerGame(10, 0);
		
		//build players array
		PokerPlayer player1 = new PokerPlayer("Facundo");
		PokerPlayer player2 = new PokerPlayer("Jose");
		
		//Add players
		game.addPlayer(player1);
		game.addPlayer(player2);
		
		int bigBlind = this.game.getBigBlind();
		//for each player call to setPlayerInfo
		for(PokerPlayer pokerPlayer : this.game.getPlayers())
		{
			this.setPlayerInfo(pokerPlayer, bigBlind);
		}		
		
		this.game.dealCards();
		
		boolean finishGame = false;
		int gameState = 0;
		
		while(gameState < 3 && !finishGame)
		{
			do
			{
				this.playerPlays = false;
				PokerPlayer currentPlayer = this.game.getPlayer();
				this.setPlayerInfo(currentPlayer, this.game.getBigBlind());
				
				//Wait for UI Response

				
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
	
	private void playerChecks() {

	}
	
	private void playerLeave() {

	}
	
	private void playerBet() {

	}
	
	private void showTurnCards() {
		
	}
	
	private void showFlopCard() {
		
	}
	
	private void showRiverCard() {
		
	}
	
	private void setPlayerInfo(PokerPlayer player, int bigBlind) {
		lblCurrentPlayer.setText(player.getName());
		
		slBet.setMinimum(bigBlind);
		slBet.setMaximum(player.getBalance());
	}
	
	private void updateMethod(int value) {
		this.txtBet.setText(String.valueOf(value));
	}
	
	private void addPlayers(ArrayList<Player> players) {
		for(Player player : players) {
			PokerPlayer pokerPlayer = new PokerPlayer(player.getName());			
			pokerPlayer.setBalance(player.getSalary());
			
			this.game.addPlayer(pokerPlayer);
		}
	}
}
