package texasHoldemPoker.Model;

import java.util.*;

public class Deck {
	private final int CARDS_NUMBER = 52;
	
	private ArrayList<PokerCard> cards;
	
	public Deck(){
		this.cards = new ArrayList<PokerCard>();
		this.initializeDeck();
		this.shuffle();
	}
	
	public PokerCard shuffleCard(){
		int lastIndex = cards.size() -1 ;
		PokerCard card = cards.get(lastIndex);
		this.cards.remove(card);
		return card;
	}
	
	private void initializeDeck(){		
		for(int i =0; i < CARDS_NUMBER; i++){					 
			PokerCard card = getRandomCard(i);
			cards.add(card);			 
		}		
	}
	
	private void shuffle(){
		Random rnd = new Random();
		for(int i =0; i < CARDS_NUMBER; i++){	
			int rndCard = rnd.nextInt(CARDS_NUMBER);
			
			PokerCard cardTemp = this.cards.get(i);
			this.cards.set(i, this.cards.get(rndCard));
			this.cards.set(rndCard, cardTemp);			
		}
	}
	
	private PokerCard getRandomCard(int rndValue){
		 int indexCardValue = rndValue % 13;
		 int indexCardSuit = rndValue / 13;	
		 Card cardValue = Card.values()[indexCardValue];
		 Suit suitValue = Suit.values()[indexCardSuit];	 
		 return new PokerCard(cardValue, suitValue);
	}	
	
}
