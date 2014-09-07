package texasHoldemPoker.Test;

import static org.junit.Assert.*;

import org.junit.Test;

import texasHoldemPoker.Model.*;

public class PokerGameTest {

	@Test
	public void gameBetweenTwoPlayers() {
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
	}

}
