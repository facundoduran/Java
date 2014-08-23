package texasHoldemPoker.Model;

import java.util.*;

public class PokerGame {
		
	private Deck deck;
	private ArrayList<PokerPlayer> players;
	private ArrayList<PokerCard> table;
	private long pot;
	private int bigBlind;
	private int smallBlind;

	public PokerGame() {
		this.deck = new Deck();
		this.table = new ArrayList<PokerCard>();
		this.players = new ArrayList<PokerPlayer>();
		this.pot = 0;
		this.smallBlind = bigBlind /2 ;
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
	
	public void newGame() {
		
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
	}
	
	public boolean allPlayersHasSameBet() {
	
		for(PokerPlayer pokerPlayer : players) {
			if (pokerPlayer.getBet() != this.bigBlind) {
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
	
	private void addCardInTable() {
		PokerCard card = deck.shuffleCard();
		table.add(card);
	}
}
