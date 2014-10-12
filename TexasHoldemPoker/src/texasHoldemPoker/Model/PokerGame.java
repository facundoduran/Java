package texasHoldemPoker.Model;

import java.util.*;

public class PokerGame {
		
	private Deck deck;
	private ArrayList<PokerPlayer> players;
	private ArrayList<PokerCard> communitaryCards;
	private int pot;
	private int turnPosition;
	private int bigBlind;
	private int smallBlind;

	public PokerGame(int bigBlind) {
		this.deck = new Deck();
		this.setTableCards(new ArrayList<PokerCard>());
		this.players = new ArrayList<PokerPlayer>();
		this.setBigBlind(bigBlind);
		this.smallBlind = this.getBigBlind() /2 ;
		this.pot = this.getBigBlind() + smallBlind;	
	}
	
	public void startGame(int turnPosition) {
		this.setBigBlindPos(turnPosition);
		PokerPlayer player = this.getPlayer();
		player.setHasBigBlind(true);		
		long amount = this.getBigBlind() - player.getBet();
		player.call(amount);
		this.pot = this.getBigBlind() + smallBlind;
	}
	
	public void addPlayer(PokerPlayer player) {
		this.players.add(player);
	}
		
	public void dealCards() {
		for (int i = 0; i < 2; i++) {
			for(PokerPlayer pokerPlayer : players) {
				PokerCard card = deck.shuffleCard();
				pokerPlayer.addCard(card);
			}
		}
	}
	
	public PokerPlayer getPlayer() {
		PokerPlayer playerTurn = this.getPlayingPlayers().get(getBigBlindPos());
		return playerTurn;
	}
	
	public void playTurn(PokerPlayerDecision decision) {
		PokerPlayer playerTurn = this.getPlayer();
		
		if(playerTurn !=null) {		
			if (decision == PokerPlayerDecision.Leave) {
				playerTurn.leave();
			}
			
			if (decision == PokerPlayerDecision.Call) {
				if (this.getBigBlind() != playerTurn.getBet()) {
					long amount = this.getBigBlind() - playerTurn.getBet();
					playerTurn.call(amount);
					this.pot += amount;	
				}
			}
			
			if (decision == PokerPlayerDecision.AllIn) {
				playerTurn.allIn();
			}
			
			this.nextTurn(decision);
		}
	}
	
	public void playTurn(PokerPlayerDecision decision, int bet) {
		PokerPlayer playerTurn = this.getPlayer();
		
		if(playerTurn !=null) {
			
			if (decision == PokerPlayerDecision.Raise) {
				
				if (playerTurn.getBalance() <= bet) {
					playerTurn.allIn();
				}
				else {
					playerTurn.raise(bet);
					this.pot += bet;
					this.bigBlind += bet;
				}				
			}
		}
		
		nextTurn(decision);
	}
		
	public void flop() {
		for(int i = 0; i < 3; i++) {
			this.addCardInTable();
		}
	}
	
	public void turn() {
		this.addCardInTable();
	}
	
	public void river() {
		this.addCardInTable();
	}
	
	public void finishGame() {
		//evaluate hands

		//give prizes - determine if exists tie
		
		//remove players with balance zero
		for(PokerPlayer pokerPlayer : players) {
			if (pokerPlayer.getBalance() == 0) {
				players.remove(pokerPlayer);
			}
		}
		
		//return class with winner and blind position
	}
	
	public boolean allPlayersHasSameBet() {
		ArrayList<PokerPlayer> playerPlaying = new ArrayList<PokerPlayer>();
		
		for(PokerPlayer pokerPlayer : playerPlaying) {
			if (pokerPlayer.getBet() != this.getBigBlind()) {
				return false;
			}
		}
		
		return true;
	}
	
	public ArrayList<PokerPlayer> getPlayers() {
		return players;
	}
	
	public ArrayList<PokerPlayer> getPlayingPlayers() {
		ArrayList<PokerPlayer> playerPlaying = new ArrayList<PokerPlayer>();
		
		for(PokerPlayer pokerPlayer : players) {
			if (pokerPlayer.getDecision() != PokerPlayerDecision.Leave){
				playerPlaying.add(pokerPlayer);
			}
		}
		
		return playerPlaying;
	}

	public void setPlayers(ArrayList<PokerPlayer> players) {
		this.players = players;
	}

	public int getPot() {
		return pot;
	}

	public void setPot(int pot) {
		this.pot = pot;
	}	
	
	public int getBigBlind() {
		return bigBlind;
	}

	public void setBigBlind(int bigBlind) {
		this.bigBlind = bigBlind;
	}

	public int getBigBlindPos() {
		if (turnPosition >= this.players.size()) {
			return 0;
		}
		
		return turnPosition;
	}

	public void setBigBlindPos(int bigBlindPos) {
		if (bigBlindPos > this.getPlayingPlayers().size()) {
			this.turnPosition = 0;
		}
		else {
			this.turnPosition = bigBlindPos;
		}
	}

	public ArrayList<PokerCard> getTableCards() {
		return communitaryCards;
	}

	public void setTableCards(ArrayList<PokerCard> table) {
		this.communitaryCards = table;
	}

	private void addCardInTable() {
		PokerCard card = deck.shuffleCard();
		getTableCards().add(card);
	}
	
	private void nextTurn(PokerPlayerDecision decision) {		
		if (getBigBlindPos() +1 >= this.getPlayingPlayers().size()) {
			this.setBigBlindPos(0) ;
		}
		else {
			if (decision != PokerPlayerDecision.Leave) {
				setBigBlindPos(getBigBlindPos() + 1);
			}		
		}
	}
}
