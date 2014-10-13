package texasHoldemPoker.Common;

import texasHoldemPoker.Common.Catalogs.*;

import java.util.Map;
import java.util.HashMap;

public class PokerHelper {

	public static Map<Integer, Integer> getPokerIndexRank()
	{
		Map<Integer, Integer> pokerIndexRank = new HashMap<Integer, Integer>();
		
		pokerIndexRank.put(0, 8);
		pokerIndexRank.put(1, 9);
		pokerIndexRank.put(2, 5);
		pokerIndexRank.put(3, 6);
		pokerIndexRank.put(4, 1);
		pokerIndexRank.put(5, 2);
		pokerIndexRank.put(6, 3);
		pokerIndexRank.put(7, 10);
		pokerIndexRank.put(8, 4);
		pokerIndexRank.put(9, 7);
		
		return pokerIndexRank;
	}
	
	public static Map<Integer, String> getPokerRank()
	{
		Map<Integer, String> pokerRank = new HashMap<Integer, String>();
		
		pokerRank.put(1, PokerRankingCatalog.CARTA_ALTA);
		pokerRank.put(2, PokerRankingCatalog.PAREJA);
		pokerRank.put(3, PokerRankingCatalog.DOBLE_PAREJA);
		pokerRank.put(4, PokerRankingCatalog.TRIO);
		pokerRank.put(5, PokerRankingCatalog.ESCALERA);
		pokerRank.put(6, PokerRankingCatalog.COLOR);
		pokerRank.put(7, PokerRankingCatalog.FULL_HOUSE);
		pokerRank.put(8, PokerRankingCatalog.POKER);
		pokerRank.put(9, PokerRankingCatalog.ESCALERA_DE_COLOR);
		pokerRank.put(10, PokerRankingCatalog.ESCALERA_REAL);
		
		return pokerRank;
	}
	
	public static Map<String, Integer> getPokerRankDescriptionByIndex()
	{
		Map<String, Integer> pokerRank = new HashMap<String, Integer>();
		
		pokerRank.put(PokerRankingCatalog.CARTA_ALTA, 1);
		pokerRank.put(PokerRankingCatalog.PAREJA, 2);
		pokerRank.put(PokerRankingCatalog.DOBLE_PAREJA, 3);
		pokerRank.put(PokerRankingCatalog.TRIO, 4);
		pokerRank.put(PokerRankingCatalog.ESCALERA, 5);
		pokerRank.put(PokerRankingCatalog.COLOR, 6);
		pokerRank.put(PokerRankingCatalog.FULL_HOUSE, 7);
		pokerRank.put(PokerRankingCatalog.POKER, 8);
		pokerRank.put(PokerRankingCatalog.ESCALERA_DE_COLOR, 9);
		pokerRank.put(PokerRankingCatalog.ESCALERA_REAL, 10);
		
		return pokerRank;
	}
}
