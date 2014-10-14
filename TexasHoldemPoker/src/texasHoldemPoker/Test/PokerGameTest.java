package texasHoldemPoker.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import texasHoldemPoker.Model.*;

public class PokerGameTest {

	@Test
	public void allPlayersHasTheSameBet() {
		PokerPlayer firstPlayer = new PokerPlayer("firstPlayer");
		firstPlayer.setBalance(2000);
		PokerPlayer secondPlayer = new PokerPlayer("secondPlayer");
		secondPlayer.setBalance(3000);
		PokerPlayer thirdPlayer = new PokerPlayer("thirdPlayer");
		thirdPlayer.setBalance(5000);
		PokerPlayer fourthPlayer = new PokerPlayer("fourthPlayer");
		fourthPlayer.setBalance(9000);
		
		ArrayList<PokerPlayer> players = new ArrayList<PokerPlayer>();
		
		firstPlayer.setBet(1000);
		secondPlayer.setBet(1000);
		thirdPlayer.setBet(1000);
		fourthPlayer.setBet(1000);
		
		players.add(firstPlayer);
		players.add(secondPlayer);
		players.add(thirdPlayer);
		players.add(fourthPlayer);
		
		boolean allPlayersHasTheSameBet = allPlayersHasSameBet(players, 1000);
		
		assertEquals(allPlayersHasTheSameBet, true);
	}
	
	@Test
	public void AllPlayersHasTheSameBetAndSomePlayerMadeAllIn() {
		PokerPlayer firstPlayer = new PokerPlayer("firstPlayer");
		firstPlayer.setBalance(2000);
		PokerPlayer secondPlayer = new PokerPlayer("secondPlayer");
		secondPlayer.setBalance(3000);
		PokerPlayer thirdPlayer = new PokerPlayer("thirdPlayer");
		thirdPlayer.setBalance(5000);
		PokerPlayer fourthPlayer = new PokerPlayer("fourthPlayer");
		fourthPlayer.setBalance(9000);
		
		ArrayList<PokerPlayer> players = new ArrayList<PokerPlayer>();
		
		firstPlayer.allIn();
		secondPlayer.raise(3000);
		thirdPlayer.call(3000);
		fourthPlayer.leave();
		
		players.add(firstPlayer);
		players.add(secondPlayer);
		players.add(thirdPlayer);
		//players.add(fourthPlayer);
		
		boolean allPlayersHasTheSameBet = allPlayersHasSameBet(players, 3000);
		
		assertEquals(allPlayersHasTheSameBet, true);
	}
	
	@Test
	public void gameBetweenTwoPlayers() {
		/*
		PokerPlayer player1 = new PokerPlayer("player1");
		
		PokerPlayer player2 = new PokerPlayer("player2");

		//initial big blind 2
		int blindPos = 1;
		PokerGame game = new PokerGame(2, blindPos);
		
		player1.setBalance(50);
		player2.setBalance(50);
		
		game.addPlayer(player1);
		game.addPlayer(player2);
		
		game.playTurn(player2, PokerPlayerDecision.Raise, 50);
		
		game.dealCards();
		
		
		while (!game.allPlayersHasSameBet()) {
			game.nextTurn();
			PokerPlayer turnPlayer = game.getPlayer();
			game.playTurn(turnPlayer, PokerPlayerDecision.Call);
		}
		
		game.turn();
		
		while (!game.allPlayersHasSameBet()) {
			game.nextTurn();
			PokerPlayer turnPlayer = game.getPlayer();
			game.playTurn(turnPlayer, PokerPlayerDecision.Call);	
		}
		
		game.river();
		
		while (!game.allPlayersHasSameBet()) {
			PokerPlayer turnPlayer = game.getPlayer();
			game.playTurn(turnPlayer, PokerPlayerDecision.Call);
		}
		
		game.flop();
		
		game.finishGame();
		
		*/
	}
	
	public boolean allPlayersHasSameBet(ArrayList<PokerPlayer> playingPlayers, int bigBlind) {
		
		for(PokerPlayer pokerPlayer : playingPlayers) {
			if (!pokerPlayer.madeAllIn() && pokerPlayer.getBet() != bigBlind) {
				return false;
			}
		}
		
		return true;
	}
}
