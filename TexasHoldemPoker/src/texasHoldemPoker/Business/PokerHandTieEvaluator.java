package texasHoldemPoker.Business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import texasHoldemPoker.Common.PokerCardComparator;
import texasHoldemPoker.Common.PokerHelper;
import texasHoldemPoker.Common.Catalogs.PokerRankingCatalog;
import texasHoldemPoker.Model.PokerCard;
import texasHoldemPoker.Model.PokerHandComparer;

public class PokerHandTieEvaluator {
	
	public static ArrayList<PokerCard> getBestHandWithTie(ArrayList<PokerCard> cards, ArrayList<PokerCard> newCards, int rank) {
		PokerHandComparer handComparer = resolveTieHand(cards, newCards, rank);
		
		if (handComparer == PokerHandComparer.FirstHandIsBetter) {
			return cards;
		}
		else if (handComparer == PokerHandComparer.SecondHandIsBetter) {
			return newCards;
		}
		else {
			return cards;
		}
	}
	
	public static PokerHandComparer resolveTieHand(ArrayList<PokerCard> cards, ArrayList<PokerCard> newCards, int rank) {
		
		Map<Integer, String> pokerHelper = PokerHelper.getPokerRank();		
		String rankDescription = pokerHelper.get(rank);
		
		if (rankDescription == PokerRankingCatalog.CARTA_ALTA) {
			return highCard(cards, newCards);
		}
		
		if (rankDescription == PokerRankingCatalog.PAREJA) {
			return pair(cards, newCards);
		}
		
		if (rankDescription == PokerRankingCatalog.DOBLE_PAREJA) {
			return doublePair(cards, newCards);
		}
		
		if (rankDescription == PokerRankingCatalog.TRIO) {
			return threeOfAKind(cards, newCards);
		}
		
		if (rankDescription == PokerRankingCatalog.ESCALERA) {
			return straight(cards, newCards);
		}
		
		if (rankDescription == PokerRankingCatalog.COLOR) {
			return color(cards, newCards);
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
		
		if (rankDescription == PokerRankingCatalog.ESCALERA_REAL) {
			return straight(cards, newCards);
		}
		
		//to avoid errors with the compiler;
		return null;
	}
	
	private static PokerHandComparer highCard(ArrayList<PokerCard> cards, ArrayList<PokerCard> newCards) {
		return compareByHighCard(cards, newCards);
	}
	
	private static PokerHandComparer pair(ArrayList<PokerCard> cards, ArrayList<PokerCard> newCards) {
		int pairCardValue = getCardValueRepetition(cards, 2);
		int pairNewCardValue = getCardValueRepetition(newCards, 2);
		
		if (pairCardValue > pairNewCardValue) {
			return PokerHandComparer.FirstHandIsBetter;
		}
		else if (pairCardValue == pairNewCardValue) {
			return compareCardByCard(cards, newCards);				
		}
		else {
			return PokerHandComparer.SecondHandIsBetter;
		}
	}
	
	private static PokerHandComparer doublePair(ArrayList<PokerCard> cards, ArrayList<PokerCard> newCards) {
		int pairCardValue = getCardValueRepetitionDoublePair(cards, 2);
		int pairNewCardValue = getCardValueRepetitionDoublePair(newCards, 2);
		
		if (pairCardValue > pairNewCardValue) {
			return PokerHandComparer.FirstHandIsBetter;
		}
		else if (pairCardValue == pairNewCardValue) {
			return compareCardByCard(cards, newCards);
		}
		else {
			return PokerHandComparer.SecondHandIsBetter;					
		}
	}
	
	private static PokerHandComparer threeOfAKind(ArrayList<PokerCard> cards, ArrayList<PokerCard> newCards) {
		
	  int threeOfAKindCardValue = getCardValueRepetition(cards, 3);
	  int threeOfAKindNewcardValue = getCardValueRepetition(newCards, 3);
	  
	  if (threeOfAKindCardValue > threeOfAKindNewcardValue) {
		  return PokerHandComparer.FirstHandIsBetter;
	  }
	  else if (threeOfAKindCardValue == threeOfAKindNewcardValue){
			return compareCardByCard(cards, newCards);
	  }
	  else {
		  return PokerHandComparer.SecondHandIsBetter;
	  }
	}
	
	//use ordinal instead getCardValue
	private static PokerHandComparer straight(ArrayList<PokerCard> cards, ArrayList<PokerCard> newCards) {
		int cardIndex = 0;		
		
		Collections.sort(cards, new PokerCardComparator());
		Collections.sort(newCards, new PokerCardComparator());

		while (cardIndex < 5) {
			int cardValue = cards.get(cardIndex).getCard().ordinal();
			int newCardValue = newCards.get(cardIndex).getCard().ordinal();
			
			if (cardValue > newCardValue) {
				return PokerHandComparer.FirstHandIsBetter;
			}
			else if (cardValue == newCardValue) {
				cardIndex++;
			}
			else {
				return PokerHandComparer.SecondHandIsBetter;
			}
		}
		
		return PokerHandComparer.BothAreEqual;
	}
	
	private static PokerHandComparer poker(ArrayList<PokerCard> cards, ArrayList<PokerCard> newCards) {
		return compareByHighCard(cards, newCards);
	}
	
	private static PokerHandComparer color(ArrayList<PokerCard> cards, ArrayList<PokerCard> newCards) {
		return compareCardByCard(cards, newCards);
	}
	
	private static PokerHandComparer compareByHighCard(ArrayList<PokerCard> cards, ArrayList<PokerCard> newCards) {
		int cardsStraight = getCardSum(cards);
		int newCardsStraight = getCardSum(newCards);
		
		if (cardsStraight > newCardsStraight) {
			return PokerHandComparer.FirstHandIsBetter;
		}
		else if(cardsStraight == newCardsStraight) {
			return PokerHandComparer.BothAreEqual;
		}
		else {
			return PokerHandComparer.SecondHandIsBetter;
		}
	}
	
	private static PokerHandComparer compareCardByCard(ArrayList<PokerCard> cards, ArrayList<PokerCard> newCards) {
		int cardIndex = 0;
		
		Collections.sort(cards, new PokerCardComparator());
		Collections.sort(newCards, new PokerCardComparator());
		
		while (cardIndex < 5) {
			int cardValue = cards.get(cardIndex).getCardValue();
			int newCardValue = newCards.get(cardIndex).getCardValue();
			
			if (cardValue > newCardValue) {
				return PokerHandComparer.FirstHandIsBetter;
			}
			else if (cardValue == newCardValue) {
				cardIndex++;
			}
			else {
				return PokerHandComparer.SecondHandIsBetter;
			}
		}
		
		return PokerHandComparer.BothAreEqual;
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
	
	private static int getCardValueRepetitionDoublePair(ArrayList<PokerCard> cards, int repeated) {
		
		HashMap<Integer, Integer> repetitions = new HashMap<Integer, Integer>();
		
		for (int i = 0; i < cards.size(); ++i) {
		    int item = cards.get(i).getCardValue();
	
		    if (repetitions.containsKey(item))
		        repetitions.put(item, repetitions.get(item) + 1);
		    else
		        repetitions.put(item, 1);
		}
		
		int sum = 0;
		
		 for (Map.Entry<Integer, Integer> e : repetitions.entrySet()) {
			 if (e.getValue() == repeated) {
				 sum += e.getKey();
			 }
		 }
		 
		 return sum;
	}
}
