package texasHoldemPoker.Model;

import java.util.ArrayList;
import texasHoldemPoker.Model.PokerCard;

public class PokerPlayer extends Player{
	
	private ArrayList<PokerCard> hand;
	
	private long bet;
	
	private long balance;
	
	public PokerPlayer(String name) {
		super(name);
		this.hand = new ArrayList<PokerCard>();
		this.setBalance(0);
	}	
	
	public void addCard(PokerCard card) {
		this.hand.add(card);
	}
	
	public void call(long amount) {
		this.raise(amount);
	}
	
	public void raise(long amount) {
		if (this.getBalance() - amount > 0) {
			this.bet += amount;
			this.balance -= amount;
		}
		else {			
			this.allIn();
		}
	}
	
	public void allIn() {
		this.bet += balance;
		this.balance = 0;		
	}

	public ArrayList<PokerCard> getHand() {
		return hand;
	}

	public void setHand(ArrayList<PokerCard> hand) {
		this.hand = hand;
	}

	public long getBet() {
		return bet;
	}

	public void setBet(long bet) {
		this.bet = bet;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}
}