package texasHoldemPoker.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import texasHoldemPoker.Common.PokerCardComparator;
import texasHoldemPoker.Common.PokerHelper;

public class PokerHandEvaluation {
	private ArrayList<PokerCard> bestHand;
	private PokerPlayer player;
	private int rank;
	
	public PokerHandEvaluation(PokerPlayer player, ArrayList<PokerCard> besthand, int rank) {
		this.setPlayer(player);
		this.setBestHand(besthand);
		this.setRank(rank);
	}
	
	public PokerHandEvaluation(PokerPlayer player, ArrayList<PokerCard> besthand) {
		this.setPlayer(player);
		this.setBestHand(besthand);
	}
	
	public String getRankDescription() {
		Map<Integer, String> ranks = PokerHelper.getPokerRank();
		return ranks.get(this.rank);
	}
	
	public ArrayList<PokerCard> getBestHand() {
		
		Collections.sort(bestHand, new PokerCardComparator());
		return bestHand;
	}
	
	public void setBestHand(ArrayList<PokerCard> bestHand) {
		this.bestHand = bestHand;
	}
	
	public PokerPlayer getPlayer() {
		return player;
	}
	
	public void setPlayer(PokerPlayer player) {
		this.player = player;
	}
	
	public int getRank() {
		return rank;
	}
	
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public PokerCard getMaxCard() {
		//TO DO 
		return null;
	}
}
