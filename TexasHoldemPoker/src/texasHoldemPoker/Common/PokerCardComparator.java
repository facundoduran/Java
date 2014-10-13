package texasHoldemPoker.Common;

import java.util.Comparator;

import texasHoldemPoker.Model.PokerCard;

public class PokerCardComparator implements Comparator<PokerCard> {

	@Override
	public int compare(PokerCard firstPokerCard, PokerCard secondPokerCard) {
		// TODO Auto-generated method stub
		return firstPokerCard.getCardValue() > secondPokerCard.getCardValue() ? 1 : -1; 
	}

}
