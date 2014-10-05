package texasHoldemPoker.Model;

import java.util.*;

public class PokerGame {
		
	private Deck deck;
	private ArrayList<PokerPlayer> players;
	private ArrayList<PokerCard> table;
	private long pot;
	private int bigBlindPos;
	private int bigBlind;
	private int smallBlind;

	public PokerGame(int bigBlind, int bigBlingPos) {
		this.deck = new Deck();
		this.table = new ArrayList<PokerCard>();
		this.players = new ArrayList<PokerPlayer>();
		this.setBigBlind(bigBlind);
		this.smallBlind = this.getBigBlind() /2 ;
		this.pot = this.getBigBlind() + smallBlind;	
		this.setBigBlindPos(bigBlingPos);
	}
	
	public void addPlayer(PokerPlayer player) {
		this.players.add(player);
	}
		
	public void dealCards() {
		for(PokerPlayer pokerPlayer : players) {
			PokerCard card = deck.shuffleCard();
			pokerPlayer.addCard(card);
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
	
	public void playTurn(PokerPlayer playerTurn, PokerPlayerDecision decision) {
		this.playTurn(playerTurn, decision, 0);
	}
	
	public void playTurn(PokerPlayer playerTurn, PokerPlayerDecision decision, int bet) {
		if (this.players.contains(playerTurn)) {
			long amount = this.getBigBlind() - playerTurn.getBet();
			
			switch(decision) {
			
			case Call:
				playerTurn.call(amount);
				break;
			case Raise:
				playerTurn.raise(bet);
				break;
			case AllIn:
				playerTurn.allIn();
				break;
			case Leave:
				this.players.remove(playerTurn);
				this.nextTurn();
				break;
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
				//players.remove(pokerPlayer);
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

	public long getPot() {
		return pot;
	}

	public void setPot(long pot) {
		this.pot = pot;
	}	
	
	public int getBigBlind() {
		return bigBlind;
	}

	public void setBigBlind(int bigBlind) {
		this.bigBlind = bigBlind;
	}

	public int getBigBlindPos() {
		return bigBlindPos;
	}

	public void setBigBlindPos(int bigBlindPos) {
		this.bigBlindPos = bigBlindPos;
	}

	private void addCardInTable() {
		PokerCard card = deck.shuffleCard();
		table.add(card);
	}
}
