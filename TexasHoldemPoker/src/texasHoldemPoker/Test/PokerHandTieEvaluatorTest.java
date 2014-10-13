package texasHoldemPoker.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import texasHoldemPoker.Business.PokerHandTieEvaluator;
import texasHoldemPoker.Model.Card;
import texasHoldemPoker.Model.PokerCard;
import texasHoldemPoker.Model.Suit;

public class PokerHandTieEvaluatorTest {

	@Test
	public void threeOfAKind_Test() {
		ArrayList<PokerCard> cards = new ArrayList<PokerCard>();
		cards.add(new PokerCard(Card.As, Suit.Corazones));
		cards.add(new PokerCard(Card.As, Suit.Diamantes));
		cards.add(new PokerCard(Card.As, Suit.Picas));
		cards.add(new PokerCard(Card.Dos, Suit.Corazones));
		cards.add(new PokerCard(Card.Tres, Suit.Corazones));
		
		ArrayList<PokerCard> newCards = new ArrayList<PokerCard>();
		newCards.add(new PokerCard(Card.Dos, Suit.Corazones));
		newCards.add(new PokerCard(Card.Dos, Suit.Diamantes));
		newCards.add(new PokerCard(Card.Dos, Suit.Picas));
		newCards.add(new PokerCard(Card.Cinco, Suit.Corazones));
		newCards.add(new PokerCard(Card.Tres, Suit.Corazones));		
		
		ArrayList<PokerCard> bestHand = PokerHandTieEvaluator.resolveTieHand(cards, newCards, 4);
		
		assertEquals(bestHand, cards);
	}

}
