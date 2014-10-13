package texasHoldemPoker.Business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import texasHoldemPoker.Common.PokerHelper;
import texasHoldemPoker.Common.Catalogs.PokerRankingCatalog;
import texasHoldemPoker.Model.PokerCard;

public class PokerHandTieEvaluator {
	
	public static ArrayList<PokerCard> resolveTieHand(ArrayList<PokerCard> cards, ArrayList<PokerCard> newCards, int rank) {
		Map<Integer, String> pokerHelper = PokerHelper.getPokerRank();
		
		String rankDescription = pokerHelper.get(rank);
		/*
			pokerRank.put(1, PokerRankingCatalog.CARTA_ALTA);
			pokerRank.put(2, PokerRankingCatalog.PAREJA);
			pokerRank.put(3, PokerRankingCatalog.DOBLE_PAREJA);
			pokerRank.put(4, PokerRankingCatalog.TRIO);
			pokerRank.put(5, PokerRankingCatalog.ESCALERA);
			pokerRank.put(6, PokerRankingCatalog.COLOR);
			pokerRank.put(7, PokerRankingCatalog.FULL_HOUSE);
			pokerRank.put(8, PokerRankingCatalog.POKER);
			pokerRank.put(9, PokerRankingCatalog.ESCALERA_DE_COLOR);
		  */
		if (rankDescription == PokerRankingCatalog.CARTA_ALTA) {
			return highCard(cards, newCards);
		}
		
		if (rankDescription == PokerRankingCatalog.PAREJA) {
			return pair(cards, newCards);
		}
		
		if (rankDescription == PokerRankingCatalog.TRIO) {
			return threeOfAKind(cards, newCards);
		}
		
		if (rankDescription == PokerRankingCatalog.ESCALERA) {
			return straight(cards, newCards);
		}
		
		if (rankDescription == PokerRankingCatalog.FULL_HOUSE) {
			return threeOfAKind(cards, newCards);
		}
		
		if (rankDescription == PokerRankingCatalog.POKER) {
			return poker(cards, newCards);
		}
		
		if (rankDescription == PokerRankingCatalog.ESCALERA_DE_COLOR) {
			return straight(cards, newCards);
		}
		
		return null;
	}
	
	private static ArrayList<PokerCard> highCard(ArrayList<PokerCard> cards, ArrayList<PokerCard> newCards) {
		return compareByHandCard(cards, newCards);
	}
	
	private static ArrayList<PokerCard> pair(ArrayList<PokerCard> cards, ArrayList<PokerCard> newCards) {
		int pairCardValue = getCardValueRepetition(cards, 2);
		int pairNewCardValue = getCardValueRepetition(newCards, 2);
		
		if (pairCardValue > pairNewCardValue) {
			return cards;
		}
		else {
			return newCards;					
		}
	}
	
	private static ArrayList<PokerCard> threeOfAKind(ArrayList<PokerCard> cards, ArrayList<PokerCard> newCards) {
		
	  int threeOfAKindCardValue = getCardValueRepetition(cards, 3);
	  int threeOfAKindNewcardValue = getCardValueRepetition(newCards, 3);
	  
	  if (threeOfAKindCardValue > threeOfAKindNewcardValue) {
		  return cards;
	  }
	  else {
		  return newCards;
	  }
	}
	
	private static ArrayList<PokerCard> straight(ArrayList<PokerCard> cards, ArrayList<PokerCard> newCards) {
		return compareByHandCard(cards, newCards);
	}
	
	private static ArrayList<PokerCard> poker(ArrayList<PokerCard> cards, ArrayList<PokerCard> newCards) {
		return compareByHandCard(cards, newCards);
	}
	
	private static ArrayList<PokerCard> compareByHandCard(ArrayList<PokerCard> cards, ArrayList<PokerCard> newCards) {
		int cardsStraight = getCardSum(cards);
		int newCardsStraight = getCardSum(newCards);
		
		if (cardsStraight > newCardsStraight) {
			return cards;
		}
		else {
			return newCards;
		}
	}
	
	private static int getCardSum(ArrayList<PokerCard> cards) {
		int cardsSum = 0;

		for (PokerCard pokerCard : cards) {
			cardsSum += pokerCard.getCardValue();
		}
		
		return cardsSum;
	}
	
	private static int getCardValueRepetition(ArrayList<PokerCard> cards, int repeated) {
		
		HashMap<Integer, Integer> repetitions = new HashMap<Integer, Integer>();
		
		for (int i = 0; i < cards.size(); ++i) {
		    int item = cards.get(i).getCardValue();
	
		    if (repetitions.containsKey(item))
		        repetitions.put(item, repetitions.get(item) + 1);
		    else
		        repetitions.put(item, 1);
		}
		
		 for (Map.Entry<Integer, Integer> e : repetitions.entrySet()) {
			 if (e.getValue() == repeated) {
				 return e.getKey();
			 }
		 }
		 
		 return 0;
	}
}
