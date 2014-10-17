package texasHoldemPoker.Model;

import java.util.ArrayList;
import texasHoldemPoker.Model.PokerCard;

public class PokerPlayer extends Player{
	
	private ArrayList<PokerCard> hand;
	
	private int bet;
	
	private int balance;
	
	private PokerPlayerMovement move;
	
	private PokerBlind blind;
			
	public PokerPlayer(String name) {
		super(name);
		this.hand = new ArrayList<PokerCard>();
		this.setBet(0);
		this.setBalance(0);
		this.setMove(PokerPlayerMovement.Wait);
		this.setBlind(PokerBlind.None);
	}
	
	public void addCard(PokerCard card) {
		this.hand.add(card);
	}
	
	public void call() {
		this.setMove(PokerPlayerMovement.Call);
	}
	public void call(long amount) {
		this.call();
		this.makeBet(amount);
	}
	
	public void raise(long amount) {
		if (this.getBalance() - amount > 0) {
			this.makeBet(amount);
			this.setMove(PokerPlayerMovement.Raise);
		}
		else {			
			this.allIn();
		}
	}
	
	public void leave() {
		this.setMove(PokerPlayerMovement.Fold);
	}
	
	public void allIn() {
		this.setMove(PokerPlayerMovement.AllIn);
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
		
		if (balance == 0) {
			this.setMove(PokerPlayerMovement.Out);
		}
	}

	public PokerPlayerMovement getMove() {
		return move;
	}

	public void setMove(PokerPlayerMovement decision) {
		this.move = decision;
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
	
	private void makeBet(long amount) {
		this.bet += amount;
		this.balance -= amount;
	}
}
