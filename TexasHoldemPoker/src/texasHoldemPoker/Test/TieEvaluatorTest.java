package texasHoldemPoker.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

import texasHoldemPoker.Business.HandEvaluator;
import texasHoldemPoker.Business.PokerHandTieEvaluator;
import texasHoldemPoker.Business.TieEvaluator;
import texasHoldemPoker.Common.PokerCardComparator;
import texasHoldemPoker.Model.*;

public class TieEvaluatorTest {
	
	@Test
	public void highCard_Test() {
		PokerPlayer firstPlayer = new PokerPlayer("firstPlayer");
		firstPlayer.setBalance(2000);
		PokerPlayer secondPlayer = new PokerPlayer("secondPlayer");
		secondPlayer.setBalance(3000);
		PokerPlayer thirdPlayer = new PokerPlayer("thirdPlayer");
		thirdPlayer.setBalance(5000);
		
		//Double Pair
		firstPlayer.addCard(new PokerCard(Card.Diez, Suit.Trebol));
		firstPlayer.addCard(new PokerCard(Card.Tres, Suit.Corazones));
		
		//Double Pair
		secondPlayer.addCard(new PokerCard(Card.Seis, Suit.Trebol));
		secondPlayer.addCard(new PokerCard(Card.Dos, Suit.Trebol));
		
		//Pair
		thirdPlayer.addCard(new PokerCard(Card.Dos, Suit.Picas));
		thirdPlayer.addCard(new PokerCard(Card.Tres, Suit.Diamantes));
		
		ArrayList<PokerCard> communitaryCards = new ArrayList<PokerCard>();
		communitaryCards.add(new PokerCard(Card.Cinco, Suit.Trebol));
		communitaryCards.add(new PokerCard(Card.Rey, Suit.Corazones));
		communitaryCards.add(new PokerCard(Card.Cuatro, Suit.Picas));
		communitaryCards.add(new PokerCard(Card.Nueve, Suit.Diamantes));
		communitaryCards.add(new PokerCard(Card.Jota, Suit.Picas));
		
		PokerHandEvaluation firstPlayerEvaluation = HandEvaluator.getBestHand(firstPlayer, communitaryCards);
		PokerHandEvaluation secondPlayerEvaluation = HandEvaluator.getBestHand(secondPlayer, communitaryCards);
		PokerHandEvaluation thirdPlayerEvaluation = HandEvaluator.getBestHand(secondPlayer, communitaryCards);
			
		//tie beetween first, second and fourth- check the kickers
		ArrayList<PokerHandEvaluation> potentialWinners = new ArrayList<PokerHandEvaluation>();
		potentialWinners.add(firstPlayerEvaluation);
		potentialWinners.add(secondPlayerEvaluation);
		potentialWinners.add(thirdPlayerEvaluation);
		
		ArrayList<PokerHandEvaluation> winners = this.getWinners(potentialWinners);
		
		assertEquals(winners.get(0).getPlayer().getName(), "firstPlayer");
		
		//assertEquals(, 1);
	}
	
	@Test	
	public void pair_Test() {
		PokerPlayer firstPlayer = new PokerPlayer("firstPlayer");
		firstPlayer.setBalance(2000);
		PokerPlayer secondPlayer = new PokerPlayer("secondPlayer");
		secondPlayer.setBalance(3000);
		PokerPlayer thirdPlayer = new PokerPlayer("thirdPlayer");
		thirdPlayer.setBalance(5000);
		
		//Double Pair
		firstPlayer.addCard(new PokerCard(Card.Cuatro, Suit.Picas));
		firstPlayer.addCard(new PokerCard(Card.Cinco, Suit.Diamantes));
		
		//Double Pair
		secondPlayer.addCard(new PokerCard(Card.Siete, Suit.Corazones));
		secondPlayer.addCard(new PokerCard(Card.Diez, Suit.Corazones));
		
		//Pair
		thirdPlayer.addCard(new PokerCard(Card.Cuatro, Suit.Trebol));
		thirdPlayer.addCard(new PokerCard(Card.Seis, Suit.Diamantes));
		
		ArrayList<PokerCard> communitaryCards = new ArrayList<PokerCard>();
		communitaryCards.add(new PokerCard(Card.Ocho, Suit.Trebol));
		communitaryCards.add(new PokerCard(Card.Jota, Suit.Corazones));
		communitaryCards.add(new PokerCard(Card.Rey, Suit.Corazones));
		communitaryCards.add(new PokerCard(Card.Reina, Suit.Picas));
		communitaryCards.add(new PokerCard(Card.Jota, Suit.Trebol));
		
		PokerHandEvaluation firstPlayerEvaluation = HandEvaluator.getBestHand(firstPlayer, communitaryCards);
		PokerHandEvaluation secondPlayerEvaluation = HandEvaluator.getBestHand(secondPlayer, communitaryCards);
		PokerHandEvaluation thirdPlayerEvaluation = HandEvaluator.getBestHand(secondPlayer, communitaryCards);
		
		int rank = firstPlayerEvaluation.getRank();
		
		//tie beetween first, second and fourth- check the kickers
		ArrayList<PokerHandEvaluation> potentialWinners = new ArrayList<PokerHandEvaluation>();
		potentialWinners.add(firstPlayerEvaluation);
		potentialWinners.add(secondPlayerEvaluation);
		potentialWinners.add(thirdPlayerEvaluation);
		
		ArrayList<PokerHandEvaluation> winners = this.getWinners(potentialWinners);
		
		assertEquals(winners.get(0).getPlayer().getName(), "secondPlayer");
	}
	
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
		
		//Double Pair
		firstPlayer.addCard(new PokerCard(Card.As, Suit.Trebol));
		firstPlayer.addCard(new PokerCard(Card.Nueve, Suit.Corazones));
		
		//Double Pair
		secondPlayer.addCard(new PokerCard(Card.As, Suit.Corazones));
		secondPlayer.addCard(new PokerCard(Card.Rey, Suit.Corazones));
		
		//Pair
		thirdPlayer.addCard(new PokerCard(Card.As, Suit.Picas));
		thirdPlayer.addCard(new PokerCard(Card.Reina, Suit.Diamantes));
		
		//Double Pair
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
		PokerHandEvaluation thirdPlayerEvaluation = HandEvaluator.getBestHand(secondPlayer, communitaryCards);
		PokerHandEvaluation fourthPlayerEvaluation = HandEvaluator.getBestHand(fourthPlayer, communitaryCards);
		
		int rank = firstPlayerEvaluation.getRank();
		
		//tie beetween first, second and fourth- check the kickers
		ArrayList<PokerHandEvaluation> potentialWinners = new ArrayList<PokerHandEvaluation>();
		potentialWinners.add(firstPlayerEvaluation);
		potentialWinners.add(secondPlayerEvaluation);
		potentialWinners.add(fourthPlayerEvaluation);
		
		ArrayList<PokerHandEvaluation> winners = this.getWinners(potentialWinners);
		
		assertEquals(winners.size(), 1);
	}
	
	@Test
	public void doublePairTie_TwoWinners_Test() {
		
		PokerPlayer firstPlayer = new PokerPlayer("firstPlayer");
		firstPlayer.setBalance(2000);
		PokerPlayer secondPlayer = new PokerPlayer("secondPlayer");
		secondPlayer.setBalance(3000);
		PokerPlayer thirdPlayer = new PokerPlayer("thirdPlayer");
		thirdPlayer.setBalance(5000);
		PokerPlayer fourthPlayer = new PokerPlayer("fourthPlayer");
		fourthPlayer.setBalance(9000);
		
		//Double Pair
		firstPlayer.addCard(new PokerCard(Card.As, Suit.Trebol));
		firstPlayer.addCard(new PokerCard(Card.Rey, Suit.Corazones));
		
		//Double Pair
		secondPlayer.addCard(new PokerCard(Card.As, Suit.Corazones));
		secondPlayer.addCard(new PokerCard(Card.Rey, Suit.Trebol));
		
		//Pair
		thirdPlayer.addCard(new PokerCard(Card.As, Suit.Picas));
		thirdPlayer.addCard(new PokerCard(Card.Reina, Suit.Diamantes));
		
		//Double Pair
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
		
		//tie beetween first, second and fourth- check the kickers
		ArrayList<PokerHandEvaluation> potentialWinners = new ArrayList<PokerHandEvaluation>();
		potentialWinners.add(firstPlayerEvaluation);
		potentialWinners.add(secondPlayerEvaluation);
		potentialWinners.add(fourthPlayerEvaluation);
		
		ArrayList<PokerHandEvaluation> winners = this.getWinners(potentialWinners);
		
		assertEquals(winners.size(), 2);
	}
	
	@Test
	public void doublePairTie_ThreeWinners_Test() {
		
		PokerPlayer firstPlayer = new PokerPlayer("firstPlayer");
		firstPlayer.setBalance(2000);
		PokerPlayer secondPlayer = new PokerPlayer("secondPlayer");
		secondPlayer.setBalance(3000);
		PokerPlayer thirdPlayer = new PokerPlayer("thirdPlayer");
		thirdPlayer.setBalance(5000);
		PokerPlayer fourthPlayer = new PokerPlayer("fourthPlayer");
		fourthPlayer.setBalance(9000);
		
		//Double Pair
		firstPlayer.addCard(new PokerCard(Card.As, Suit.Trebol));
		firstPlayer.addCard(new PokerCard(Card.Rey, Suit.Corazones));
		
		//Double Pair
		secondPlayer.addCard(new PokerCard(Card.As, Suit.Corazones));
		secondPlayer.addCard(new PokerCard(Card.Rey, Suit.Trebol));
		
		//Pair
		thirdPlayer.addCard(new PokerCard(Card.As, Suit.Picas));
		thirdPlayer.addCard(new PokerCard(Card.Rey, Suit.Diamantes));
		
		//Double Pair
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
		
		//tie beetween first, second and fourth- check the kickers
		ArrayList<PokerHandEvaluation> potentialWinners = new ArrayList<PokerHandEvaluation>();
		potentialWinners.add(firstPlayerEvaluation);
		potentialWinners.add(secondPlayerEvaluation);
		potentialWinners.add(thirdPlayerEvaluation);
		potentialWinners.add(fourthPlayerEvaluation);
		
		ArrayList<PokerHandEvaluation> winners = this.getWinners(potentialWinners);
		
		assertEquals(winners.size(), 3);
	}
	
	@Test
	public void pokerTie_OneWinner_Test() {

		PokerPlayer firstPlayer = new PokerPlayer("firstPlayer");
		firstPlayer.setBalance(2000);
		PokerPlayer secondPlayer = new PokerPlayer("secondPlayer");
		secondPlayer.setBalance(3000);
		PokerPlayer thirdPlayer = new PokerPlayer("thirdPlayer");
		thirdPlayer.setBalance(5000);
		PokerPlayer fourthPlayer = new PokerPlayer("fourthPlayer");
		fourthPlayer.setBalance(9000);
		
		//Poker 5 5 5 5 J
		firstPlayer.addCard(new PokerCard(Card.Jota, Suit.Diamantes));
		firstPlayer.addCard(new PokerCard(Card.Jota, Suit.Picas));
		
		//Poker 5 5 5 5 Q
		secondPlayer.addCard(new PokerCard(Card.Reina, Suit.Diamantes));
		secondPlayer.addCard(new PokerCard(Card.Reina, Suit.Trebol));
		
		//Poker 5 5 5 5 K
		thirdPlayer.addCard(new PokerCard(Card.Reina, Suit.Picas));
		thirdPlayer.addCard(new PokerCard(Card.Rey, Suit.Diamantes));
		
		//Poker 5 5 5 5 A
		fourthPlayer.addCard(new PokerCard(Card.As, Suit.Trebol));
		fourthPlayer.addCard(new PokerCard(Card.Siete, Suit.Corazones));
		
		ArrayList<PokerCard> communitaryCards = new ArrayList<PokerCard>();
		
		communitaryCards.add(new PokerCard(Card.Cinco, Suit.Corazones));
		communitaryCards.add(new PokerCard(Card.Cinco, Suit.Picas));
		communitaryCards.add(new PokerCard(Card.Cinco, Suit.Trebol));
		communitaryCards.add(new PokerCard(Card.Dos, Suit.Diamantes));
		communitaryCards.add(new PokerCard(Card.Cinco, Suit.Diamantes));
		
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
		
		ArrayList<PokerHandEvaluation> winners = this.getWinners(potentialWinners);
		
		assertEquals(winners.size(), 1);
	}
	
	@Test
	public void pokerTie_TwoWinners_DividePot_Test() {
		
		PokerPlayer firstPlayer = new PokerPlayer("firstPlayer");
		firstPlayer.setBalance(2000);
		PokerPlayer secondPlayer = new PokerPlayer("secondPlayer");
		secondPlayer.setBalance(3000);
		PokerPlayer thirdPlayer = new PokerPlayer("thirdPlayer");
		thirdPlayer.setBalance(5000);
		PokerPlayer fourthPlayer = new PokerPlayer("fourthPlayer");
		fourthPlayer.setBalance(9000);
		
		//Poker 5 5 5 5 J
		firstPlayer.addCard(new PokerCard(Card.Jota, Suit.Diamantes));
		firstPlayer.addCard(new PokerCard(Card.Jota, Suit.Picas));
		
		//Poker 5 5 5 5 Q
		secondPlayer.addCard(new PokerCard(Card.Reina, Suit.Diamantes));
		secondPlayer.addCard(new PokerCard(Card.Reina, Suit.Trebol));
		
		//Poker 5 5 5 5 A
		thirdPlayer.addCard(new PokerCard(Card.As, Suit.Picas));
		thirdPlayer.addCard(new PokerCard(Card.Rey, Suit.Diamantes));
		
		//Poker 5 5 5 5 A
		fourthPlayer.addCard(new PokerCard(Card.As, Suit.Trebol));
		fourthPlayer.addCard(new PokerCard(Card.Siete, Suit.Corazones));
		
		ArrayList<PokerCard> communitaryCards = new ArrayList<PokerCard>();
		
		communitaryCards.add(new PokerCard(Card.Cinco, Suit.Corazones));
		communitaryCards.add(new PokerCard(Card.Cinco, Suit.Picas));
		communitaryCards.add(new PokerCard(Card.Cinco, Suit.Trebol));
		communitaryCards.add(new PokerCard(Card.Nueve, Suit.Diamantes));
		communitaryCards.add(new PokerCard(Card.Cinco, Suit.Diamantes));
		
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
		
		
		ArrayList<PokerHandEvaluation> winners = this.getWinners(potentialWinners);
		
		assertEquals(winners.size(), 2);
	}
	
	@Test
	public void pokerTie_FourWinners_DividePot_Test() {
		
		PokerPlayer firstPlayer = new PokerPlayer("firstPlayer");
		firstPlayer.setBalance(2000);
		PokerPlayer secondPlayer = new PokerPlayer("secondPlayer");
		secondPlayer.setBalance(3000);
		PokerPlayer thirdPlayer = new PokerPlayer("thirdPlayer");
		thirdPlayer.setBalance(5000);
		PokerPlayer fourthPlayer = new PokerPlayer("fourthPlayer");
		fourthPlayer.setBalance(9000);
		
		//Poker 5 5 5 5 A
		firstPlayer.addCard(new PokerCard(Card.Jota, Suit.Diamantes));
		firstPlayer.addCard(new PokerCard(Card.Jota, Suit.Picas));
		
		//Poker 5 5 5 5 A
		secondPlayer.addCard(new PokerCard(Card.Reina, Suit.Diamantes));
		secondPlayer.addCard(new PokerCard(Card.Reina, Suit.Trebol));
		
		//Poker 5 5 5 5 A
		thirdPlayer.addCard(new PokerCard(Card.As, Suit.Picas));
		thirdPlayer.addCard(new PokerCard(Card.Rey, Suit.Diamantes));
		
		//Poker 5 5 5 5 A
		fourthPlayer.addCard(new PokerCard(Card.As, Suit.Trebol));
		fourthPlayer.addCard(new PokerCard(Card.Siete, Suit.Corazones));
		
		ArrayList<PokerCard> communitaryCards = new ArrayList<PokerCard>();
		
		communitaryCards.add(new PokerCard(Card.Cinco, Suit.Corazones));
		communitaryCards.add(new PokerCard(Card.Cinco, Suit.Picas));
		communitaryCards.add(new PokerCard(Card.Cinco, Suit.Trebol));
		communitaryCards.add(new PokerCard(Card.As, Suit.Diamantes));
		communitaryCards.add(new PokerCard(Card.Cinco, Suit.Diamantes));
		
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
		
		ArrayList<PokerHandEvaluation> winners = this.getWinners(potentialWinners);
		
		assertEquals(winners.size(), 4);
	}
	
	@Test
	public void color_OneWinner_Test() {
		PokerPlayer firstPlayer = new PokerPlayer("firstPlayer");
		firstPlayer.setBalance(2000);
		PokerPlayer secondPlayer = new PokerPlayer("secondPlayer");
		secondPlayer.setBalance(3000);
		PokerPlayer thirdPlayer = new PokerPlayer("thirdPlayer");
		thirdPlayer.setBalance(5000);
		PokerPlayer fourthPlayer = new PokerPlayer("fourthPlayer");
		fourthPlayer.setBalance(9000);
		
		//Color
		firstPlayer.addCard(new PokerCard(Card.As, Suit.Diamantes));
		firstPlayer.addCard(new PokerCard(Card.As, Suit.Picas));
		
		//Pair
		secondPlayer.addCard(new PokerCard(Card.Diez, Suit.Trebol));
		secondPlayer.addCard(new PokerCard(Card.Diez, Suit.Corazones));
		
		//Color
		thirdPlayer.addCard(new PokerCard(Card.Siete, Suit.Trebol));
		thirdPlayer.addCard(new PokerCard(Card.Cinco, Suit.Picas));
		
		ArrayList<PokerCard> communitaryCards = new ArrayList<PokerCard>();
		communitaryCards.add(new PokerCard(Card.Seis, Suit.Picas));
		communitaryCards.add(new PokerCard(Card.Tres, Suit.Diamantes));
		communitaryCards.add(new PokerCard(Card.Cuatro, Suit.Picas));
		communitaryCards.add(new PokerCard(Card.Reina, Suit.Picas));
		communitaryCards.add(new PokerCard(Card.Nueve, Suit.Picas));
		
		PokerHandEvaluation firstPlayerEvaluation = HandEvaluator.getBestHand(firstPlayer, communitaryCards);
		PokerHandEvaluation secondPlayerEvaluation = HandEvaluator.getBestHand(secondPlayer, communitaryCards);
		PokerHandEvaluation thirdPlayerEvaluation = HandEvaluator.getBestHand(thirdPlayer, communitaryCards);
		
		ArrayList<PokerHandEvaluation> potentialWinners = new ArrayList<PokerHandEvaluation>();
		potentialWinners.add(firstPlayerEvaluation);
		//potentialWinners.add(secondPlayerEvaluation);
		potentialWinners.add(thirdPlayerEvaluation);
		
		ArrayList<PokerHandEvaluation> winners = this.getWinners(potentialWinners);
		
		assertEquals(winners.size(), 1);		
	}
	
	private ArrayList<PokerHandEvaluation> getWinners(ArrayList<PokerHandEvaluation> potentialWinners) {
		PokerHandEvaluation firstPlayerEvaluation = potentialWinners.get(0);
		
		int rank = firstPlayerEvaluation.getRank();

		ArrayList<PokerHandEvaluation> winners = new ArrayList<PokerHandEvaluation>();
		
		for(int i = 0; i < potentialWinners.size() -1; i++) {
			PokerHandEvaluation firstPotentialWinner = potentialWinners.get(i);
			PokerHandEvaluation secondPotentialWinner = potentialWinners.get(i + 1);
			
			ArrayList<PokerCard> firstPotentialWinnerBestHand = firstPotentialWinner.getBestHand();
			ArrayList<PokerCard> secondPotentialWinnerBestHand = secondPotentialWinner.getBestHand();

			PokerHandComparer handComparer = PokerHandTieEvaluator.resolveTieHand(firstPotentialWinnerBestHand, secondPotentialWinnerBestHand, rank);
			
			if (handComparer == PokerHandComparer.FirstHandIsBetter && !winners.contains(firstPotentialWinner)) {
				winners = new ArrayList<PokerHandEvaluation>();
				winners.add(firstPotentialWinner);
			}
			else if (handComparer == PokerHandComparer.SecondHandIsBetter && !winners.contains(secondPotentialWinner)) {
				winners = new ArrayList<PokerHandEvaluation>();
				winners.add(secondPotentialWinner);
			}
			else if (handComparer  == PokerHandComparer.BothAreEqual) {				
				if (!winners.contains(firstPotentialWinner)) {
					winners.add(firstPotentialWinner);
				}
				
				if (!winners.contains(secondPotentialWinner)) {
					winners.add(secondPotentialWinner);
				}
			}
		}		
		
		return winners;
	}
}
