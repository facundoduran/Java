package texasHoldemPoker.Business;

import java.util.ArrayList;

import texasHoldemPoker.Common.Catalogs.PokerRankingCatalog;
import texasHoldemPoker.Model.PokerCard;
import texasHoldemPoker.Model.PokerHandEvaluation;

public class TieEvaluator {
	
	public static ArrayList<PokerHandEvaluation> getWinners(ArrayList<PokerHandEvaluation> potentialWinners) {
		//we have a tie and we need the rank of the first player
		PokerHandEvaluation firstPlayer = potentialWinners.get(0);
		String rankDescription = firstPlayer.getRankDescription();		
		
		ArrayList<PokerHandEvaluation> winners = new ArrayList<PokerHandEvaluation>();
		
		if (rankDescription == PokerRankingCatalog.CARTA_ALTA) {
			winners = highCardResolve(potentialWinners);
		}
		
		if (rankDescription == PokerRankingCatalog.PAREJA) {
			winners = pairResolve(winners);
		}
		
		if (rankDescription == PokerRankingCatalog.DOBLE_PAREJA) {
			winners = doublePairResolve(winners);
		}
		
		if (rankDescription == PokerRankingCatalog.TRIO) {
			winners = threeOfAKindColorResolve(winners);
		}
		
		if (rankDescription == PokerRankingCatalog.ESCALERA) {
			winners = straightResolve(winners);
		}
		
		if (rankDescription == PokerRankingCatalog.COLOR) {
			winners = flushResolve(winners);
		}
		
		if (rankDescription == PokerRankingCatalog.POKER) {
			winners = fourOfAkindResolve(winners);
		}
		
		if (rankDescription == PokerRankingCatalog.FULL_HOUSE) {
			winners = fullHouseResolve(winners);
		}
		
		if (rankDescription == PokerRankingCatalog.ESCALERA_DE_COLOR) {
			winners = straightFlushResolve(winners);
		}	
		
		return winners;
	}
	
	public static ArrayList<PokerHandEvaluation> highCardResolve(ArrayList<PokerHandEvaluation> potentialWinners) {
		return resolveWinnersByKicker(potentialWinners);
	}
	
	public static ArrayList<PokerHandEvaluation> pairResolve(ArrayList<PokerHandEvaluation> potentialWinners) {
		return resolveWinnersByKicker(potentialWinners);
	}
	
	public static ArrayList<PokerHandEvaluation> doublePairResolve(ArrayList<PokerHandEvaluation> potentialWinners) {
		return resolveWinnersByKicker(potentialWinners);
	}
	
	public static ArrayList<PokerHandEvaluation> threeOfAKindColorResolve(ArrayList<PokerHandEvaluation> potentialWinners) {
		return resolveWinnersByKicker(potentialWinners);
	}
	
	public static ArrayList<PokerHandEvaluation> straightResolve(ArrayList<PokerHandEvaluation> potentialWinners) {
		return null;
	}
	
	public static ArrayList<PokerHandEvaluation> flushResolve(ArrayList<PokerHandEvaluation> potentialWinners) {
		return null;
	}
	
	public static ArrayList<PokerHandEvaluation> fourOfAkindResolve(ArrayList<PokerHandEvaluation> potentialWinners) {
		return resolveWinnersByKicker(potentialWinners);
	}
	
	public static ArrayList<PokerHandEvaluation> fullHouseResolve(ArrayList<PokerHandEvaluation> potentialWinners) {
		return null;
	}
	
	public static ArrayList<PokerHandEvaluation> straightFlushResolve(ArrayList<PokerHandEvaluation> potentialWinners) {
		return null;
	}
	
	//must be private, is public for test purposals
	public static ArrayList<PokerHandEvaluation> resolveWinnersByKicker(ArrayList<PokerHandEvaluation> potentialWinners) {
		
		ArrayList<PokerHandEvaluation> firstKickerEvaluation = new ArrayList<PokerHandEvaluation>();	
		ArrayList<PokerHandEvaluation> secondKickerEvaluation = new ArrayList<PokerHandEvaluation>();	
		
		int maxFirstKicker = 0;
		
		for(PokerHandEvaluation evaluation : potentialWinners) {
			PokerCard firstKicker = evaluation.getPlayer().getHand().get(0);
			
			if (firstKicker.getCardValue() > maxFirstKicker) {
				firstKickerEvaluation = new ArrayList<PokerHandEvaluation>();
				firstKickerEvaluation.add(evaluation);
				maxFirstKicker = firstKicker.getCardValue();
			}
			else if (firstKicker.getCardValue() == maxFirstKicker) {
				firstKickerEvaluation.add(evaluation);
			}
		}
			
		if(firstKickerEvaluation.size() > 1) {			
			int maxSecondKicker = 0;

			for(PokerHandEvaluation evaluation : firstKickerEvaluation) {
				PokerCard secondKicker = evaluation.getPlayer().getHand().get(1);	
				
				if (secondKicker.getCardValue() > maxSecondKicker) {
					secondKickerEvaluation = new ArrayList<PokerHandEvaluation>();
					secondKickerEvaluation.add(evaluation);
					maxSecondKicker = secondKicker.getCardValue();
				}
				else if (secondKicker.getCardValue() == maxSecondKicker) {
					secondKickerEvaluation.add(evaluation);
				}
			}
			
			//one or more winners
			return secondKickerEvaluation;
		}
		else {
			//only one winner
			return firstKickerEvaluation;
		}
	}
}
