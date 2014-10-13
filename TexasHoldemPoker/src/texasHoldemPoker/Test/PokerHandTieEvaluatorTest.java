package texasHoldemPoker.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Test;

import texasHoldemPoker.Business.PokerHandTieEvaluator;
import texasHoldemPoker.Common.PokerHelper;
import texasHoldemPoker.Common.Catalogs.PokerRankingCatalog;
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
		
		Map<String, Integer> rankDescriptionMap = PokerHelper.getPokerRankDescriptionByIndex();
		
		int rankIndex = rankDescriptionMap.get(PokerRankingCatalog.TRIO);
		
		ArrayList<PokerCard> bestHand = PokerHandTieEvaluator.getBestHandWithTie(cards, newCards, rankIndex);
		
		assertEquals(bestHand, cards);
	}
	
	@Test
	public void poker_Test() {
		ArrayList<PokerCard> cards = new ArrayList<PokerCard>();
		cards.add(new PokerCard(Card.As, Suit.Corazones));
		cards.add(new PokerCard(Card.As, Suit.Diamantes));
		cards.add(new PokerCard(Card.As, Suit.Picas));
		cards.add(new PokerCard(Card.As, Suit.Corazones));
		cards.add(new PokerCard(Card.Tres, Suit.Corazones));
		
		ArrayList<PokerCard> newCards = new ArrayList<PokerCard>();
		newCards.add(new PokerCard(Card.As, Suit.Corazones));
		newCards.add(new PokerCard(Card.As, Suit.Diamantes));
		newCards.add(new PokerCard(Card.As, Suit.Picas));
		newCards.add(new PokerCard(Card.As, Suit.Corazones));
		newCards.add(new PokerCard(Card.Jota, Suit.Corazones));		
		
		Map<String, Integer> rankDescriptionMap = PokerHelper.getPokerRankDescriptionByIndex();
		
		int rankIndex = rankDescriptionMap.get(PokerRankingCatalog.POKER);
		
		ArrayList<PokerCard> bestHand = PokerHandTieEvaluator.getBestHandWithTie(cards, newCards, rankIndex);
		
		assertEquals(bestHand, newCards);
	}
	
	@Test
	public void straight_Test() {
		ArrayList<PokerCard> cards = new ArrayList<PokerCard>();
		cards.add(new PokerCard(Card.As, Suit.Corazones));
		cards.add(new PokerCard(Card.Dos, Suit.Diamantes));
		cards.add(new PokerCard(Card.Tres, Suit.Picas));
		cards.add(new PokerCard(Card.Cuatro, Suit.Corazones));
		cards.add(new PokerCard(Card.Cinco, Suit.Corazones));
		
		ArrayList<PokerCard> newCards = new ArrayList<PokerCard>();
		newCards.add(new PokerCard(Card.Dos, Suit.Corazones));
		newCards.add(new PokerCard(Card.Tres, Suit.Diamantes));
		newCards.add(new PokerCard(Card.Cuatro, Suit.Picas));
		newCards.add(new PokerCard(Card.Cinco, Suit.Diamantes));
		newCards.add(new PokerCard(Card.Seis, Suit.Corazones));		
		
		Map<String, Integer> rankDescriptionMap = PokerHelper.getPokerRankDescriptionByIndex();
		
		int rankIndex = rankDescriptionMap.get(PokerRankingCatalog.ESCALERA);
		
		ArrayList<PokerCard> bestHand = PokerHandTieEvaluator.getBestHandWithTie(cards, newCards, rankIndex);
		
		assertEquals(bestHand, newCards);
	}
	
	@Test
	public void color_Test() {
		ArrayList<PokerCard> cards = new ArrayList<PokerCard>();
		cards.add(new PokerCard(Card.Cinco, Suit.Picas));
		cards.add(new PokerCard(Card.Jota, Suit.Picas));
		cards.add(new PokerCard(Card.As, Suit.Picas));
		cards.add(new PokerCard(Card.Dos, Suit.Picas));
		cards.add(new PokerCard(Card.Siete, Suit.Picas));
		
		ArrayList<PokerCard> newCards = new ArrayList<PokerCard>();
		newCards.add(new PokerCard(Card.Tres, Suit.Picas));
		newCards.add(new PokerCard(Card.Ocho, Suit.Picas));
		newCards.add(new PokerCard(Card.As, Suit.Picas));
		newCards.add(new PokerCard(Card.Dos, Suit.Picas));
		newCards.add(new PokerCard(Card.Siete, Suit.Picas));		
		
		Map<String, Integer> rankDescriptionMap = PokerHelper.getPokerRankDescriptionByIndex();
		
		int rankIndex = rankDescriptionMap.get(PokerRankingCatalog.COLOR);
		
		ArrayList<PokerCard> bestHand = PokerHandTieEvaluator.getBestHandWithTie(cards, newCards, rankIndex);
		
		assertEquals(bestHand, cards);
	}
	
	@Test
	public void straightWeakness_Test() {
		ArrayList<PokerCard> cards = new ArrayList<PokerCard>();
		cards.add(new PokerCard(Card.As, Suit.Corazones));
		cards.add(new PokerCard(Card.Dos, Suit.Diamantes));
		cards.add(new PokerCard(Card.Tres, Suit.Picas));
		cards.add(new PokerCard(Card.Cuatro, Suit.Corazones));
		cards.add(new PokerCard(Card.Cinco, Suit.Corazones));
		
		ArrayList<PokerCard> newCards = new ArrayList<PokerCard>();
		newCards.add(new PokerCard(Card.Diez, Suit.Corazones));
		newCards.add(new PokerCard(Card.Jota, Suit.Diamantes));
		newCards.add(new PokerCard(Card.Reina, Suit.Picas));
		newCards.add(new PokerCard(Card.Rey, Suit.Corazones));
		newCards.add(new PokerCard(Card.As, Suit.Picas));		
		
		Map<String, Integer> rankDescriptionMap = PokerHelper.getPokerRankDescriptionByIndex();
		
		int rankIndex = rankDescriptionMap.get(PokerRankingCatalog.ESCALERA);
		
		ArrayList<PokerCard> bestHand = PokerHandTieEvaluator.getBestHandWithTie(cards, newCards, rankIndex);
		
		assertEquals(bestHand, newCards);
	}
	
	@Test
	public void strongestStraight_Test() {
		ArrayList<PokerCard> cards = new ArrayList<PokerCard>();
		cards.add(new PokerCard(Card.Diez, Suit.Corazones));
		cards.add(new PokerCard(Card.Jota, Suit.Diamantes));
		cards.add(new PokerCard(Card.Reina, Suit.Picas));
		cards.add(new PokerCard(Card.Rey, Suit.Corazones));
		cards.add(new PokerCard(Card.Nueve, Suit.Picas));	
		
		ArrayList<PokerCard> newCards = new ArrayList<PokerCard>();
		newCards.add(new PokerCard(Card.Diez, Suit.Corazones));
		newCards.add(new PokerCard(Card.Jota, Suit.Diamantes));
		newCards.add(new PokerCard(Card.Reina, Suit.Picas));
		newCards.add(new PokerCard(Card.Rey, Suit.Corazones));
		newCards.add(new PokerCard(Card.As, Suit.Picas));		
		
		Map<String, Integer> rankDescriptionMap = PokerHelper.getPokerRankDescriptionByIndex();
		
		int rankIndex = rankDescriptionMap.get(PokerRankingCatalog.ESCALERA);
		
		ArrayList<PokerCard> bestHand = PokerHandTieEvaluator.getBestHandWithTie(cards, newCards, rankIndex);
		
		assertEquals(bestHand, newCards);		
	}
}
