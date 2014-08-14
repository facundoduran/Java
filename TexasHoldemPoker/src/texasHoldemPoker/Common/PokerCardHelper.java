package texasHoldemPoker.Common;

import texasHoldemPoker.Model.*;

public class PokerCardHelper {
	
	private static final String fileExtension = ".png"; 
	
	public static String getPokerCardImageFileName(PokerCard card) {
		int cardValue = card.getCard().ordinal();
		Suit cardSuit = card.getSuit();
		
		String value = Integer.toString(cardValue);
		String suit = cardSuit.toString().substring(0, 1);
		
		return value + suit + fileExtension;				
	}
}
