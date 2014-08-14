package texasHoldemPoker.Model;

public class PokerCard {
	
	private Card card;
	
	private Suit suit;
	
	public PokerCard(Card card, Suit suit) {	
		this.setCard(card);
		this.setSuit(suit);
	}
	
	public String toString() {
		return this.getCard().toString() + " " + this.suit.toString();		
	}
	
	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Suit getSuit() {
		return suit;
	}

	public void setSuit(Suit suit) {
		this.suit = suit;
	}
}
