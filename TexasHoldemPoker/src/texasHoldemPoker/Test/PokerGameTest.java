package texasHoldemPoker.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import texasHoldemPoker.Business.HandEvaluator;
import texasHoldemPoker.Business.TieEvaluator;
import texasHoldemPoker.Model.*;

public class PokerGameTest {
	
	ArrayList<PokerPlayer> playersPlayingTest;

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
	
	private boolean allPlayersHasSameBet(ArrayList<PokerPlayer> playingPlayers, int bigBlind) {
		
		for(PokerPlayer pokerPlayer : playingPlayers) {
			if (!pokerPlayer.madeAllIn() && pokerPlayer.getBet() != bigBlind) {
				return false;
			}
		}
		
		return true;
	}

	@Test
	public void doublePairTest() {
		
		ArrayList<PokerPlayer> playersPlaying = new ArrayList<PokerPlayer>();
		
		PokerPlayer firstPlayer = new PokerPlayer("firstPlayer");
		firstPlayer.setBalance(100);
		firstPlayer.setBet(50);
		
		PokerPlayer secondPlayer = new PokerPlayer("secondPlayer");
		secondPlayer.setBalance(150);
		secondPlayer.setBet(50);
		
		PokerPlayer thirdPlayer = new PokerPlayer("thirdPlayer");
		thirdPlayer.setBalance(200);
		thirdPlayer.setBet(50);
				
		PokerPlayer fourthPlayer = new PokerPlayer("fourthPlayer");
		fourthPlayer.setBalance(250);
		fourthPlayer.setBet(50);
		
		//Double Pair
		firstPlayer.addCard(new PokerCard(Card.Diez, Suit.Corazones));
		firstPlayer.addCard(new PokerCard(Card.Diez, Suit.Picas));
		
		//Pair
		secondPlayer.addCard(new PokerCard(Card.Reina, Suit.Corazones));
		secondPlayer.addCard(new PokerCard(Card.Jota, Suit.Picas));
		
		//Pair
		thirdPlayer.addCard(new PokerCard(Card.Dos, Suit.Corazones));
		thirdPlayer.addCard(new PokerCard(Card.Siete, Suit.Picas));
		
		//Pair 
		fourthPlayer.addCard(new PokerCard(Card.Seis, Suit.Corazones));
		fourthPlayer.addCard(new PokerCard(Card.Cinco, Suit.Picas));
				
		ArrayList<PokerCard> communitaryCards = new ArrayList<PokerCard>();
		communitaryCards.add(new PokerCard(Card.As, Suit.Diamantes));
		communitaryCards.add(new PokerCard(Card.Rey, Suit.Picas));
		communitaryCards.add(new PokerCard(Card.Tres, Suit.Diamantes));
		communitaryCards.add(new PokerCard(Card.Nueve, Suit.Diamantes));
		communitaryCards.add(new PokerCard(Card.Nueve, Suit.Trebol));
		
		playersPlaying.add(firstPlayer);
		playersPlaying.add(secondPlayer);
		playersPlaying.add(thirdPlayer);
		playersPlaying.add(fourthPlayer);
		
		playersPlayingTest = new ArrayList<PokerPlayer>();
		playersPlayingTest.addAll(playersPlaying);
		
		ArrayList<PokerHandEvaluation> winners = new ArrayList<PokerHandEvaluation>();
		
		//evaluate hands only if exists players playing
		if (playersPlaying.size() > 1) {			
			ArrayList<PokerHandEvaluation> gameResult = new ArrayList<PokerHandEvaluation>();
			
			for(PokerPlayer player : playersPlaying) {
				PokerHandEvaluation handEvaluation = HandEvaluator.getBestHand(player, communitaryCards);
				gameResult.add(handEvaluation);
			}
			
			ArrayList<PokerHandEvaluation> potentialWinners = HandEvaluator.getWinners(gameResult);	
			
			//exist tie, search the winner and give the prize to him
			if (potentialWinners.size() > 1) {
				
				winners = TieEvaluator.getWinners(potentialWinners);			
				
				int totalForEachPlayer = this.getPot() / winners.size();
				
				for (PokerHandEvaluation winner : winners) {
					PokerPlayer player = winner.getPlayer();
					int total = totalForEachPlayer + player.getBalance();
					player.setBalance(total);
				}
			}
			else if (potentialWinners.size() == 1){
				PokerHandEvaluation winner = potentialWinners.get(0);
				int total = this.getPot() + winner.getPlayer().getBalance();
				winner.getPlayer().setBalance(total);
				
				winners.add(winner);
			}
		}
		else if (playersPlaying.size() == 1) {			
			PokerPlayer winner = playersPlaying.get(0);
			int total = this.getPot() + winner.getBalance();
			winner.setBalance(total);
			
			winners.add(new PokerHandEvaluation(winner, winner.getHand()));
		}
		
		assertEquals(winners.size(), 1);
	}
	
	private int getPot() {
		int pot = 0;
		for (PokerPlayer pokerPlayer : playersPlayingTest) {
			pot += pokerPlayer.getBet();
		}
		
		return pot;
	}
}
