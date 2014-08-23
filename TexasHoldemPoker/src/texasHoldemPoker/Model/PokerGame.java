package texasHoldemPoker.Model;

import java.util.*;

public class PokerGame {
	
	private ArrayList<PokerPlayer> players;
	private long pot;

	public PokerGame(ArrayList<PokerPlayer> players) {
		this.players = players;
		this.pot = 0;
	}	 
	
	public void finishGame() {
		//evaluate hands

		//give prizes - determine if exists tie
		
		//remove players with balance zero
		for(PokerPlayer pokerPlayer : players) {
			if (pokerPlayer.getBalance() == 0) {
				players.remove(pokerPlayer);
			}
		}
	}
	
	public ArrayList<PokerPlayer> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<PokerPlayer> players) {
		this.players = players;
	}

	public long getPot() {
		return pot;
	}

	public void setPot(long pot) {
		this.pot = pot;
	}	
}
