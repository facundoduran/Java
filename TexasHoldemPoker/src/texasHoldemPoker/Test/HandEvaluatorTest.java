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
		ArrayList<PokerCard> cards = new ArrayList<PokerCard>();
		cards.add(new PokerCard(Card.As, Suit.Corazones));
		cards.add(new PokerCard(Card.Dos, Suit.Picas));
		cards.add(new PokerCard(Card.Jota, Suit.Corazones));
		cards.add(new PokerCard(Card.Diez, Suit.Diamantes));
		cards.add(new PokerCard(Card.Ocho, Suit.Trebol));

		String rankScale = this.GetRank(cards);
		
		assertEquals(rankScale, PokerRankingCatalog.CARTA_ALTA);	
	}
	
	@Test
	public void Pair_Test() {
		ArrayList<PokerCard> cards = new ArrayList<PokerCard>();
		cards.add(new PokerCard(Card.As, Suit.Corazones));
		cards.add(new PokerCard(Card.As, Suit.Picas));
		cards.add(new PokerCard(Card.Jota, Suit.Corazones));
		cards.add(new PokerCard(Card.Diez, Suit.Diamantes));
		cards.add(new PokerCard(Card.Ocho, Suit.Trebol));

		String rankScale = this.GetRank(cards);
		
		assertEquals(rankScale, PokerRankingCatalog.PAREJA);		
	}

	@Test	
	public void TwoPair_Test() 
	{
		ArrayList<PokerCard> cards = new ArrayList<PokerCard>();
		cards.add(new PokerCard(Card.As, Suit.Corazones));
		cards.add(new PokerCard(Card.As, Suit.Picas));
		cards.add(new PokerCard(Card.Diez, Suit.Corazones));
		cards.add(new PokerCard(Card.Diez, Suit.Diamantes));
		cards.add(new PokerCard(Card.Ocho, Suit.Trebol));
		
		String rankScale = this.GetRank(cards);
		
		assertEquals(rankScale, PokerRankingCatalog.DOBLE_PAREJA);	
	}
	
	@Test	
	public void ThreeOfAKind_Test() {
		ArrayList<PokerCard> cards = new ArrayList<PokerCard>();
		cards.add(new PokerCard(Card.As, Suit.Corazones));
		cards.add(new PokerCard(Card.As, Suit.Picas));
		cards.add(new PokerCard(Card.Siete, Suit.Corazones));
		cards.add(new PokerCard(Card.As, Suit.Diamantes));
		cards.add(new PokerCard(Card.Cinco, Suit.Trebol));
		
		String rankScale = this.GetRank(cards);
		
		assertEquals(rankScale, PokerRankingCatalog.TRIO);	
	}	
	
	@Test	
	public void Straight_Test() {
		ArrayList<PokerCard> cards = new ArrayList<PokerCard>();
		cards.add(new PokerCard(Card.As, Suit.Corazones));
		cards.add(new PokerCard(Card.Dos, Suit.Picas));
		cards.add(new PokerCard(Card.Tres, Suit.Corazones));
		cards.add(new PokerCard(Card.Cuatro, Suit.Diamantes));
		cards.add(new PokerCard(Card.Cinco, Suit.Trebol));
		
		String rankScale = this.GetRank(cards);
		
		assertEquals(rankScale, PokerRankingCatalog.ESCALERA);	
	}	
	
	@Test	
	public void Flush_Test() {
		ArrayList<PokerCard> cards = new ArrayList<PokerCard>();
		cards.add(new PokerCard(Card.As, Suit.Corazones));
		cards.add(new PokerCard(Card.Tres, Suit.Corazones));
		cards.add(new PokerCard(Card.Jota, Suit.Corazones));
		cards.add(new PokerCard(Card.Diez, Suit.Corazones));
		cards.add(new PokerCard(Card.Ocho, Suit.Corazones));
		
		String rankScale = this.GetRank(cards);
		
		assertEquals(rankScale, PokerRankingCatalog.COLOR);	
	}		
	
	@Test	
	public void FullHouse_Test() {
		ArrayList<PokerCard> cards = new ArrayList<PokerCard>();
		cards.add(new PokerCard(Card.As, Suit.Corazones));
		cards.add(new PokerCard(Card.As, Suit.Picas));
		cards.add(new PokerCard(Card.Cinco, Suit.Corazones));
		cards.add(new PokerCard(Card.As, Suit.Diamantes));
		cards.add(new PokerCard(Card.Cinco, Suit.Trebol));
		
		String rankScale = this.GetRank(cards);
		
		assertEquals(rankScale, PokerRankingCatalog.FULL_HOUSE);	
	}	
	
	@Test	
	public void FourOfAKind_Test() {
		ArrayList<PokerCard> cards = new ArrayList<PokerCard>();
		cards.add(new PokerCard(Card.As, Suit.Corazones));
		cards.add(new PokerCard(Card.As, Suit.Picas));
		cards.add(new PokerCard(Card.As, Suit.Trebol));
		cards.add(new PokerCard(Card.As, Suit.Diamantes));
		cards.add(new PokerCard(Card.Cinco, Suit.Trebol));
		
		String rankScale = this.GetRank(cards);
		
		assertEquals(rankScale, PokerRankingCatalog.POKER);	
	}		

	@Test
	public void StraightFlush_Test() {
		ArrayList<PokerCard> cards = new ArrayList<PokerCard>();
		cards.add(new PokerCard(Card.Seis, Suit.Corazones));
		cards.add(new PokerCard(Card.Dos, Suit.Corazones));
		cards.add(new PokerCard(Card.Tres, Suit.Corazones));
		cards.add(new PokerCard(Card.Cuatro, Suit.Corazones));
		cards.add(new PokerCard(Card.Cinco, Suit.Corazones));
		
		String rankScale = this.GetRank(cards);
		
		assertEquals(rankScale, PokerRankingCatalog.ESCALERA_DE_COLOR);	
	}		
	
	@Test
	public void RoyalFlush_Test() {
		ArrayList<PokerCard> cards = new ArrayList<PokerCard>();
		cards.add(new PokerCard(Card.Jota, Suit.Corazones));
		cards.add(new PokerCard(Card.Reina, Suit.Corazones));
		cards.add(new PokerCard(Card.Rey, Suit.Corazones));
		cards.add(new PokerCard(Card.Diez, Suit.Corazones));
		cards.add(new PokerCard(Card.As, Suit.Corazones));
		
		String rankScale = this.GetRank(cards);
		
		assertEquals(rankScale, PokerRankingCatalog.ESCALERA_REAL);	
	}			

	private String GetRank(ArrayList<PokerCard> cards) {
		int rankValue = HandEvaluator.evaluateHand(cards);
		
		Map<Integer, String> ranks = PokerHelper.getPokerRank();
		
		return ranks.get(rankValue);
	}	
}
