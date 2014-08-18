package texasHoldemPoker.Business;

import texasHoldemPoker.Model.*;
import texasHoldemPoker.Common.Combinatory;
import texasHoldemPoker.Common.PokerHelper;

import java.util.ArrayList;
import java.util.Map;

public class HandEvaluator {
	
	public static int getBestHand(ArrayList<PokerCard> cards)
	{		
		ArrayList<ArrayList<Integer>> combinations = Combinatory.getCombinations(5, cards.size());
		
		int maxRank = 0;
		
		for(int i = 0; i < combinations.size(); i++)
		{
			ArrayList<PokerCard> hand = new ArrayList<PokerCard>();
			
			ArrayList<Integer> combination = combinations.get(i);
			for(int j = 0; j < combination.size(); j++)
			{
				int index = combination.get(j);
				PokerCard cardInTable = cards.get(index);
				hand.add(cardInTable);
			}
			
			int index = evaluateHand(hand);
			
			if (index > maxRank)
			{
				maxRank = index;
			}
		}		
		
		return maxRank;
	}
	
	public static int evaluateHand(ArrayList<PokerCard> cards)
	{
		int index = HandEvaluator.calcIndex(cards);	

		Map<Integer, Integer> pokerHelper = PokerHelper.getPokerIndexRank();
		
		return pokerHelper.get(index);
	}	
	
	private static int calcIndex(ArrayList<PokerCard> cards)
	{
		int i, s;
		long o;
		long v = 0;

		for (i = 0 ; i < 5; i++)
		{		
			o = 0;
			
			int cardValue = cards.get(i).getCardValue();			
			o = (long)Math.pow(2, cardValue *4);
			v += o * (( v / o & 15 ) +1);
		}
		
		PokerCard firstCard = cards.get(0);
		PokerCard secondCard = cards.get(1);
		PokerCard thirdCard = cards.get(2);
		PokerCard fourthCard = cards.get(3);
		PokerCard fifthCard = cards.get(4);
		
		if ((v %= 15) != 5) 
		{
			return (int)v - 1;
		}
		else 
		{	
			s = 1 << firstCard.getCardValue() | 1 << secondCard.getCardValue() | 1 << thirdCard.getCardValue() | 1 << fourthCard.getCardValue() | 1 << fifthCard.getCardValue();
		}
	  
		//check if exist straight
		boolean existStraight = ((s / (s & - s ) == 31) || (s == 0x403c));
		
		v -= existStraight ? 3 : 1;
	  
		//check if exist or not flush
		boolean existFlush = 
						firstCard.getSuit() == secondCard.getSuit() && 
						secondCard.getSuit() == thirdCard.getSuit() &&
						thirdCard.getSuit() == fourthCard.getSuit() &&
						fourthCard.getSuit() == fifthCard.getSuit();
		
		int flush = existFlush ? 1 : 0;
		
		return (int)v - ( flush * ((s == 0x7c00) ? -5 : 1));		
	}	
}
