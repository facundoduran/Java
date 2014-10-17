package texasHoldemPoker.Model;

import java.util.ArrayList;
import texasHoldemPoker.Model.PokerCard;

public class PokerPlayer extends Player{
	
	private ArrayList<PokerCard> hand;
	
	private int bet;
	
	private int balance;
	
	private PokerPlayerDecision decision;
	
	private PokerBlind blind;
			
	public PokerPlayer(String name) {
		super(name);
		this.hand = new ArrayList<PokerCard>();
		this.setBet(0);
		this.setBalance(0);
		this.setDecision(PokerPlayerDecision.Play);
		this.setBlind(PokerBlind.None);
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
	
	public void leave() {
		this.setDecision(PokerPlayerDecision.Leave);
	}
	
	public void allIn() {
		this.setDecision(PokerPlayerDecision.AllIn);
		this.bet += balance;
		this.balance = 0;		
	}
	
	public boolean madeAllIn() {
		return this.getBalance() == 0;
	}

	public ArrayList<PokerCard> getHand() {
		return hand;
	}

	public void setHand(ArrayList<PokerCard> hand) {
		this.hand = hand;
	}

	public int getBet() {
		return bet;
	}

	public void setBet(int bet) {
		this.bet = bet;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public PokerPlayerDecision getDecision() {
		return decision;
	}

	public void setDecision(PokerPlayerDecision decision) {
		this.decision = decision;
	}
	
	private void clearHand() {
		this.hand = new ArrayList<PokerCard>();
	}

	public boolean hasBigBlind() {
		return this.blind == PokerBlind.BigBlind;
	}
	
	public boolean hasSmallBlind() {
		return this.blind == PokerBlind.SmallBlind;
	}
	
	public boolean hasDealer() {
		return this.blind == PokerBlind.Dealer;
	}
	
	public boolean hasNotBlind() {
		return this.blind == PokerBlind.None;
	}

	public PokerBlind getBlind() {
		return blind;
	}

	public void setBlind(PokerBlind blind) {
		this.blind = blind;
	}	
}
