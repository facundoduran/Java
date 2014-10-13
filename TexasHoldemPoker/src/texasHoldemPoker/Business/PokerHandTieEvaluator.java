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
		if (rankDescription == PokerRankingCatalog.TRIO) {
			return threeOfAKind(cards, newCards);
		}
		
		if (rankDescription == PokerRankingCatalog.ESCALERA) {
			return straight(cards, newCards);
		}
		
		if (rankDescription == PokerRankingCatalog.FULL_HOUSE) {
			return threeOfAKind(cards, newCards);
		}
		
		if (rankDescription == PokerRankingCatalog.ESCALERA_DE_COLOR) {
			return straight(cards, newCards);
		}
		
		return null;
	}
	
	private static ArrayList<PokerCard> threeOfAKind(ArrayList<PokerCard> cards, ArrayList<PokerCard> newCards) {
		
	  HashMap<PokerCard, Integer> repetitions = new HashMap<PokerCard, Integer>();

	  for (int i = 0; i < cards.size(); ++i) {
	      PokerCard item = cards.get(i);

	      if (repetitions.containsKey(item))
	          repetitions.put(item, repetitions.get(item) + 1);
	      else
	          repetitions.put(item, 1);
	  }
	  
	  return null;

	}
	
	private static ArrayList<PokerCard> straight(ArrayList<PokerCard> cards, ArrayList<PokerCard> newCards) {
		int cardsStraight = 0;
		int newCardsStraight = 0;
		
		for (PokerCard pokerCard : cards) {
			cardsStraight += pokerCard.getCardValue();
		}
		
		for (PokerCard pokerCard : newCards) {
			newCardsStraight += pokerCard.getCardValue();
		}
		
		if (cardsStraight > newCardsStraight) {
			return cards;
		}
		else {
			return newCards;
		}
	}
}
