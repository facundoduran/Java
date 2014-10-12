package texasHoldemPoker.Model;

import java.util.*;

public class PokerGame {
		
	private Deck deck;
	private ArrayList<PokerPlayer> players;
	private ArrayList<PokerCard> communitaryCards;
	private int pot;
	private int bigBlindPos;
	private int bigBlind;
	private int smallBlind;

	public PokerGame(int bigBlind, int bigBlingPos) {
		this.deck = new Deck();
		this.setTableCards(new ArrayList<PokerCard>());
		this.players = new ArrayList<PokerPlayer>();
		this.setBigBlind(bigBlind);
		this.smallBlind = this.getBigBlind() /2 ;
		this.pot = this.getBigBlind() + smallBlind;	
		this.setBigBlindPos(bigBlingPos);
	}
	
	public void startGame(int bigBlind) {
		this.deck = new Deck();
		this.setBigBlind(bigBlind);
		this.smallBlind = this.getBigBlind() /2 ;
		this.pot = this.getBigBlind() + smallBlind;	
		this.clearPlayerHands();
		this.clearTableCards();
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
	
	public void nextTurn() {		
		if (getBigBlindPos() == this.players.size()) {
			this.setBigBlindPos(0) ;
		}
		else {
			setBigBlindPos(getBigBlindPos() + 1);
		}
	}
	
	public PokerPlayer getPlayer() {
		PokerPlayer playerTurn = this.players.get(getBigBlindPos());
		return playerTurn;
	}
	
	public void playTurn(PokerPlayerDecision decision) {
		PokerPlayer playerTurn = this.getPlayer();
		
		if(playerTurn !=null) {		
			if (decision == PokerPlayerDecision.Leave) {
				playerTurn.leave();
			}
			
			if (decision == PokerPlayerDecision.Call) {
				long amount = this.getBigBlind() - playerTurn.getBet();
				playerTurn.call(amount);
			}
			
			if (decision == PokerPlayerDecision.AllIn) {
				playerTurn.allIn();
			}
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
				}				
			}
		}
		
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
	
		for(PokerPlayer pokerPlayer : players) {
			if (pokerPlayer.getBet() != this.getBigBlind()) {
				return false;
			}
		}
		
		return true;
	}
	
	public ArrayList<PokerPlayer> getPlayers() {
		return players;
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
		if (bigBlindPos == this.players.size()) {
			return 0;
		}
		
		return bigBlindPos;
	}

	public void setBigBlindPos(int bigBlindPos) {
		if (bigBlindPos > this.players.size()) {
			this.bigBlindPos = 0;
		}
		else {
			this.bigBlindPos = bigBlindPos;
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
	
	private void clearPlayerHands() {
		for(PokerPlayer pokerPlayer : players) {
			pokerPlayer.newGame();
		}
	}	
	
	private void clearTableCards() {
		this.communitaryCards = new ArrayList<PokerCard>();
	}
}
