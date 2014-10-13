package texasHoldemPoker.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

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
		
		HashMap<Integer, Integer> repetitions = new HashMap<Integer, Integer>();
	
		for (int i = 0; i < cards.size(); ++i) {
		    int item = cards.get(i).getCardValue();
	
		    if (repetitions.containsKey(item))
		        repetitions.put(item, repetitions.get(item) + 1);
		    else
		        repetitions.put(item, 1);
		}
		
		 for (Map.Entry<Integer, Integer> e : repetitions.entrySet()) {
			 if (e.getValue() == 3) {
				 System.out.println(e.getKey() * 3);
				 System.out.println(e.getValue());
			 }
		 }
		 
	}

}
