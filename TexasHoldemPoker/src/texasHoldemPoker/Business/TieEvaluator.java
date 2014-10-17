package texasHoldemPoker.Business;

import java.util.ArrayList;

import texasHoldemPoker.Model.PokerCard;
import texasHoldemPoker.Model.PokerHandComparer;
import texasHoldemPoker.Model.PokerHandEvaluation;

public class TieEvaluator {
	
	public static ArrayList<PokerHandEvaluation> getWinners(ArrayList<PokerHandEvaluation> potentialWinners) {
		//we have a tie and we need the rank of the first player (the rank is the same for all players TIE)
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
	
	public static ArrayList<PokerHandEvaluation> highCardResolve(ArrayList<PokerHandEvaluation> potentialWinners) {
		return null;
	}
	
	public static ArrayList<PokerHandEvaluation> pairResolve(ArrayList<PokerHandEvaluation> potentialWinners) {
		return null;
	}
	
	public static ArrayList<PokerHandEvaluation> doublePairResolve(ArrayList<PokerHandEvaluation> potentialWinners) {
		return null;
	}
	
	public static ArrayList<PokerHandEvaluation> threeOfAKindColorResolve(ArrayList<PokerHandEvaluation> potentialWinners) {
		return null;
	}
	
	public static ArrayList<PokerHandEvaluation> straightResolve(ArrayList<PokerHandEvaluation> potentialWinners) {
		return null;
	}
	
	public static ArrayList<PokerHandEvaluation> flushResolve(ArrayList<PokerHandEvaluation> potentialWinners) {
		return null;
	}
	
	public static ArrayList<PokerHandEvaluation> fourOfAkindResolve(ArrayList<PokerHandEvaluation> potentialWinners) {
		return null;
	}
	
	public static ArrayList<PokerHandEvaluation> fullHouseResolve(ArrayList<PokerHandEvaluation> potentialWinners) {
		return null;
	}
	
	public static ArrayList<PokerHandEvaluation> straightFlushResolve(ArrayList<PokerHandEvaluation> potentialWinners) {
		return null;
	}
}
