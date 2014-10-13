package texasHoldemPoker.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

import texasHoldemPoker.Business.HandEvaluator;
import texasHoldemPoker.Business.TieEvaluator;
import texasHoldemPoker.Common.PokerCardComparator;
import texasHoldemPoker.Model.*;

public class TieEvaluatorTest {
	
	@Test
	public void checkKickers_OneWinner_Test() {
	
		PokerPlayer firstPlayer = new PokerPlayer("firstPlayer");
		firstPlayer.setBalance(2000);
		PokerPlayer secondPlayer = new PokerPlayer("secondPlayer");
		secondPlayer.setBalance(3000);
		PokerPlayer thirdPlayer = new PokerPlayer("thirdPlayer");
		thirdPlayer.setBalance(5000);
		PokerPlayer fourthPlayer = new PokerPlayer("fourthPlayer");
		fourthPlayer.setBalance(9000);
		
		firstPlayer.addCard(new PokerCard(Card.As, Suit.Trebol));
		firstPlayer.addCard(new PokerCard(Card.Rey, Suit.Corazones));
		
		secondPlayer.addCard(new PokerCard(Card.As, Suit.Corazones));
		secondPlayer.addCard(new PokerCard(Card.Nueve, Suit.Corazones));
		
		thirdPlayer.addCard(new PokerCard(Card.As, Suit.Picas));
		thirdPlayer.addCard(new PokerCard(Card.Reina, Suit.Diamantes));
		
		fourthPlayer.addCard(new PokerCard(Card.Tres, Suit.Trebol));
		fourthPlayer.addCard(new PokerCard(Card.Siete, Suit.Corazones));
		
		ArrayList<PokerCard> communitaryCards = new ArrayList<PokerCard>();
		communitaryCards.add(new PokerCard(Card.As, Suit.Diamantes));
		communitaryCards.add(new PokerCard(Card.Rey, Suit.Picas));
		communitaryCards.add(new PokerCard(Card.Tres, Suit.Diamantes));
		communitaryCards.add(new PokerCard(Card.Siete, Suit.Diamantes));
		communitaryCards.add(new PokerCard(Card.Nueve, Suit.Trebol));
		
		PokerHandEvaluation firstPlayerEvaluation = HandEvaluator.getBestHand(firstPlayer, communitaryCards);
		PokerHandEvaluation secondPlayerEvaluation = HandEvaluator.getBestHand(secondPlayer, communitaryCards);
		PokerHandEvaluation thirdPlayerEvaluation = HandEvaluator.getBestHand(thirdPlayer, communitaryCards);
		PokerHandEvaluation fourthPlayerEvaluation = HandEvaluator.getBestHand(fourthPlayer, communitaryCards);
		
		int rank = firstPlayerEvaluation.getRank();
		
		assertEquals(secondPlayerEvaluation.getRank(), rank);
		assertNotEquals(thirdPlayerEvaluation.getRank(), rank);
		assertEquals(fourthPlayerEvaluation.getRank(), rank);
		
		//tie beetween first, second and fourth- check the kickers
		ArrayList<PokerHandEvaluation> potentialWinners = new ArrayList<PokerHandEvaluation>();
		potentialWinners.add(firstPlayerEvaluation);
		potentialWinners.add(secondPlayerEvaluation);
		potentialWinners.add(fourthPlayerEvaluation);
		
		ArrayList<PokerHandEvaluation> winners = TieEvaluator.resolveWinnersByKicker(potentialWinners);
		
		assertEquals(winners.size(), 1);
	}
	
	@Test
	public void checkKickers_TwoWinners_Test() {
		
		PokerPlayer firstPlayer = new PokerPlayer("firstPlayer");
		firstPlayer.setBalance(2000);
		PokerPlayer secondPlayer = new PokerPlayer("secondPlayer");
		secondPlayer.setBalance(3000);
		PokerPlayer thirdPlayer = new PokerPlayer("thirdPlayer");
		thirdPlayer.setBalance(5000);
		PokerPlayer fourthPlayer = new PokerPlayer("fourthPlayer");
		fourthPlayer.setBalance(9000);
		
		firstPlayer.addCard(new PokerCard(Card.As, Suit.Trebol));
		firstPlayer.addCard(new PokerCard(Card.Rey, Suit.Corazones));
		
		secondPlayer.addCard(new PokerCard(Card.As, Suit.Corazones));
		secondPlayer.addCard(new PokerCard(Card.Rey, Suit.Trebol));
		
		thirdPlayer.addCard(new PokerCard(Card.As, Suit.Picas));
		thirdPlayer.addCard(new PokerCard(Card.Reina, Suit.Diamantes));
		
		fourthPlayer.addCard(new PokerCard(Card.Tres, Suit.Trebol));
		fourthPlayer.addCard(new PokerCard(Card.Siete, Suit.Corazones));
		
		ArrayList<PokerCard> communitaryCards = new ArrayList<PokerCard>();
		communitaryCards.add(new PokerCard(Card.As, Suit.Diamantes));
		communitaryCards.add(new PokerCard(Card.Rey, Suit.Picas));
		communitaryCards.add(new PokerCard(Card.Tres, Suit.Diamantes));
		communitaryCards.add(new PokerCard(Card.Siete, Suit.Diamantes));
		communitaryCards.add(new PokerCard(Card.Nueve, Suit.Trebol));
		
		PokerHandEvaluation firstPlayerEvaluation = HandEvaluator.getBestHand(firstPlayer, communitaryCards);
		PokerHandEvaluation secondPlayerEvaluation = HandEvaluator.getBestHand(secondPlayer, communitaryCards);
		PokerHandEvaluation thirdPlayerEvaluation = HandEvaluator.getBestHand(thirdPlayer, communitaryCards);
		PokerHandEvaluation fourthPlayerEvaluation = HandEvaluator.getBestHand(fourthPlayer, communitaryCards);
		
		int rank = firstPlayerEvaluation.getRank();
		
		assertEquals(secondPlayerEvaluation.getRank(), rank);
		assertNotEquals(thirdPlayerEvaluation.getRank(), rank);
		assertEquals(fourthPlayerEvaluation.getRank(), rank);
		
		//tie beetween first, second and fourth- check the kickers
		ArrayList<PokerHandEvaluation> potentialWinners = new ArrayList<PokerHandEvaluation>();
		potentialWinners.add(firstPlayerEvaluation);
		potentialWinners.add(secondPlayerEvaluation);
		potentialWinners.add(fourthPlayerEvaluation);
		
		ArrayList<PokerHandEvaluation> winners = TieEvaluator.resolveWinnersByKicker(potentialWinners);
		
		assertEquals(winners.size(), 2);
	}
	
	@Test
	public void checkKickers_TwoWinners_DividePot_Test() {
		
		PokerPlayer firstPlayer = new PokerPlayer("firstPlayer");
		firstPlayer.setBalance(2000);
		PokerPlayer secondPlayer = new PokerPlayer("secondPlayer");
		secondPlayer.setBalance(3000);
		PokerPlayer thirdPlayer = new PokerPlayer("thirdPlayer");
		thirdPlayer.setBalance(5000);
		PokerPlayer fourthPlayer = new PokerPlayer("fourthPlayer");
		fourthPlayer.setBalance(9000);
		
		firstPlayer.addCard(new PokerCard(Card.Jota, Suit.Diamantes));
		firstPlayer.addCard(new PokerCard(Card.Jota, Suit.Picas));
		
		secondPlayer.addCard(new PokerCard(Card.Reina, Suit.Diamantes));
		secondPlayer.addCard(new PokerCard(Card.Reina, Suit.Trebol));
		
		thirdPlayer.addCard(new PokerCard(Card.As, Suit.Picas));
		thirdPlayer.addCard(new PokerCard(Card.Rey, Suit.Diamantes));
		
		fourthPlayer.addCard(new PokerCard(Card.As, Suit.Trebol));
		fourthPlayer.addCard(new PokerCard(Card.Siete, Suit.Corazones));
		
		ArrayList<PokerCard> communitaryCards = new ArrayList<PokerCard>();
		
		communitaryCards.add(new PokerCard(Card.Cinco, Suit.Corazones));
		communitaryCards.add(new PokerCard(Card.Cinco, Suit.Picas));
		communitaryCards.add(new PokerCard(Card.Cinco, Suit.Trebol));
		communitaryCards.add(new PokerCard(Card.Dos, Suit.Diamantes));
		communitaryCards.add(new PokerCard(Card.Cinco, Suit.Diamantes));
		
		/*
		communitaryCards.add(new PokerCard(Card.Cinco, Suit.Corazones));
		communitaryCards.add(new PokerCard(Card.Cinco, Suit.Picas));
		communitaryCards.add(new PokerCard(Card.Cinco, Suit.Trebol));
		communitaryCards.add(new PokerCard(Card.As, Suit.Diamantes));
		communitaryCards.add(new PokerCard(Card.Cinco, Suit.Diamantes));
		*/
		PokerHandEvaluation firstPlayerEvaluation = HandEvaluator.getBestHand(firstPlayer, communitaryCards);
		PokerHandEvaluation secondPlayerEvaluation = HandEvaluator.getBestHand(secondPlayer, communitaryCards);
		PokerHandEvaluation thirdPlayerEvaluation = HandEvaluator.getBestHand(thirdPlayer, communitaryCards);
		PokerHandEvaluation fourthPlayerEvaluation = HandEvaluator.getBestHand(fourthPlayer, communitaryCards);		
		
		int rank = firstPlayerEvaluation.getRank();
		
		assertEquals(secondPlayerEvaluation.getRank(), rank);
		
		//tie beetween first, second and fourth- check the kickers
		ArrayList<PokerHandEvaluation> potentialWinners = new ArrayList<PokerHandEvaluation>();
		potentialWinners.add(firstPlayerEvaluation);
		potentialWinners.add(secondPlayerEvaluation);
		potentialWinners.add(thirdPlayerEvaluation);
		potentialWinners.add(fourthPlayerEvaluation);
		
		int pokerCardIndex = 0;
		
		ArrayList<PokerHandEvaluation> winners = new ArrayList<PokerHandEvaluation>();
		
		while(pokerCardIndex < 5) {			
			int maxCardValue = 0;
			for(PokerHandEvaluation evaluation : potentialWinners) {				
				PokerCard card = evaluation.getBestHand().get(pokerCardIndex);
				
				if (card.getCardValue() > maxCardValue) {
					maxCardValue = card.getCardValue();
					winners = new ArrayList<PokerHandEvaluation>();
					winners.add(evaluation);
				}
				else {
					winners.add(evaluation);
				}
			}
			
			if (winners.size() > 1) {
				pokerCardIndex++;
			}			
		}		
		
		assertEquals(winners.size(), 2);

		
		//ArrayList<PokerHandEvaluation> winners = TieEvaluator.resolveWinnersByKicker(potentialWinners);		
		//assertEquals(winners.size(), 1);
	}
}
