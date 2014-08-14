package texasHoldemPoker.Test;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

import texasHoldemPoker.Business.HandEvaluator;
import texasHoldemPoker.Common.*;
import texasHoldemPoker.Common.Catalogs.*;
import texasHoldemPoker.Model.*;

public class HandEvaluatorTest {
	
	@Test
	public void HighCard_Test() {
		
	}
	
	@Test
	public void Deuce_Test() {
		ArrayList<PokerCard> cards = new ArrayList<PokerCard>();
		cards.add(new PokerCard(Card.As, Suit.Corazones));
		cards.add(new PokerCard(Card.As, Suit.Picas));
		cards.add(new PokerCard(Card.Jota, Suit.Corazones));
		cards.add(new PokerCard(Card.Diez, Suit.Diamantes));
		cards.add(new PokerCard(Card.Ocho, Suit.Trebol));

		int rankValue = HandEvaluator.evaluateHand(cards);
		
		Map<Integer, String> ranks = PokerHelper.getPokerRank();
		
		String rankScale = ranks.get(rankValue);
		
		assertEquals(rankScale, PokerRankingCatalog.PAREJA);		
	}

}
