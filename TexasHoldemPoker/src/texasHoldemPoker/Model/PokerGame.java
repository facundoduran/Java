package texasHoldemPoker.Model;

import java.util.*;

import texasHoldemPoker.Business.HandEvaluator;
import texasHoldemPoker.Business.TieEvaluator;

public class PokerGame {
		
	private Deck deck;
	private ArrayList<PokerPlayer> players;
	private ArrayList<PokerCard> communitaryCards;
	private int pot;
	private int bottomPosition;
	private int playerIndex;
	private int highestBet;
	private int smallBlind;

	public PokerGame(int bigBlind) {
		this.deck = new Deck();
		this.setTableCards(new ArrayList<PokerCard>());
		this.players = new ArrayList<PokerPlayer>();
		this.setHighestBet(bigBlind);
		this.smallBlind = this.getHighestBet() /2 ;
		this.pot = this.getHighestBet() + smallBlind;	
	}
	
	public void startGame(int bottomPosition) {
		this.setBottomPosition(bottomPosition);
		PokerPlayer dealerPlayer = this.players.get(bottomPosition);
		dealerPlayer.setBlind(PokerBlind.Dealer);
		
		int bigBlindIndex = (bottomPosition + 2) % players.size();
		int smallBlindIndex = (bottomPosition + 1) % players.size();		
		
		PokerPlayer smallBlindPlayer = this.players.get(smallBlindIndex);
		smallBlindPlayer.setBlind(PokerBlind.SmallBlind);
		smallBlindPlayer.call(this.smallBlind);	
		
		PokerPlayer bigBlindPlayer = this.players.get(bigBlindIndex);
		bigBlindPlayer.setBlind(PokerBlind.BigBlind);
		long amount = this.getHighestBet() - bigBlindPlayer.getBet();
		bigBlindPlayer.call(amount);
		
		this.pot = this.getHighestBet() + smallBlind;
		
		int preFlopTurnIndex = (bottomPosition + 3) % players.size();
		this.setPlayerTurnIndex(preFlopTurnIndex);
	}
	
	public void addPlayer(PokerPlayer player) {
		this.players.add(player);
	}
		
	public void dealCards() {
		for (int i = 0; i < 2; i++) {
			for(PokerPlayer pokerPlayer : players) {
				PokerCard card = deck.shuffleCard();
				pokerPlayer.addCard(card);
			}
		}
	}
	
	public PokerPlayer getPlayer() {
		int currentPlayerIndex = this.getPlayerTurnIndex();
		int activePlayersSize = this.getActivePlayers().size();
		ArrayList<PokerPlayer> activePlayers = this.getActivePlayers();
		
		while (currentPlayerIndex < activePlayersSize) {
			PokerPlayer playerTurn = activePlayers.get(currentPlayerIndex);
			if (playerTurn.getMove() == PokerPlayerMovement.Wait) {
				return playerTurn;
			}
			
			currentPlayerIndex++;
		}
		
		currentPlayerIndex = this.getPlayerTurnIndex();
		while(currentPlayerIndex >= 0) {
			PokerPlayer playerTurn = this.getActivePlayers().get(currentPlayerIndex);
			if (playerTurn.getMove() == PokerPlayerMovement.Wait) {
				return playerTurn;
			}
			currentPlayerIndex--;
		}
		
		return null;
		//PokerPlayer playerTurn = this.getActivePlayers().get(currentPlayerIndex);
		//return playerTurn;
	}
	
	public void playTurn(PokerPlayerMovement decision) {
		PokerPlayer playerTurn = this.getPlayer();
		
		if(playerTurn !=null) {		
			if (decision == PokerPlayerMovement.Fold) {
				playerTurn.leave();
			}
			
			if (decision == PokerPlayerMovement.Call) {				
				
				if (this.getHighestBet() != playerTurn.getBet()) {
					long amount = this.getHighestBet() - playerTurn.getBet();
					playerTurn.call(amount);
					this.pot += amount;	
				}
				else {
					playerTurn.call();
				}
			}
			
			if (decision == PokerPlayerMovement.AllIn) {
				this.pot += playerTurn.getBalance();
				this.highestBet = playerTurn.getBalance();
				playerTurn.allIn();				
			}
			
			this.nextTurn(decision);
		}
	}
	
	public void playTurn(PokerPlayerMovement decision, int bet) {
		PokerPlayer playerTurn = this.getPlayer();
		
		if(playerTurn !=null) {
			
			if (decision == PokerPlayerMovement.Raise) {
				
				if (playerTurn.getBalance() <= bet) {
					playerTurn.allIn();
				}
				else {
					playerTurn.raise(bet);
					this.pot += bet;
					this.highestBet += bet;
					this.notifyRaise(playerTurn);
				}				
			}
		}
		
		nextTurn(decision);
	}
		
	public void flop() {
		for(int i = 0; i < 3; i++) {
			this.showCommunitaryCard();
		}
		
		int bottomPosition = this.getBottomPosition();
		int nextPlayerPosition = bottomPosition + 1 % this.getActivePlayers().size();
		this.setPlayerTurnIndex(nextPlayerPosition);
	}
	
	public void turn() {
		this.showCommunitaryCard();
	}
	
	public void river() {
		this.showCommunitaryCard();
	}
	
	public ArrayList<PokerHandEvaluation> getPlayersEvaluation() {
		ArrayList<PokerPlayer> playersPlaying = this.getActivePlayers();

		ArrayList<PokerHandEvaluation> playersEvaluation = new ArrayList<PokerHandEvaluation>();
		
		for(PokerPlayer player : playersPlaying) {			
			if (this.getCommunitaryCards().size() > 1) {
				PokerHandEvaluation handEvaluation = HandEvaluator.getBestHand(player, this.getCommunitaryCards());
				playersEvaluation.add(handEvaluation);	
			}
		}
		
		return playersEvaluation;
	}
	
	public ArrayList<PokerHandEvaluation> finishGame() {
		ArrayList<PokerPlayer> playersPlaying = this.getActivePlayers();
		ArrayList<PokerHandEvaluation> winners = new ArrayList<PokerHandEvaluation>();
		
		//evaluate hands only if exists players playing
		if (playersPlaying.size() > 1) {			
			ArrayList<PokerHandEvaluation> gameResult = new ArrayList<PokerHandEvaluation>();
			
			for(PokerPlayer player : playersPlaying) {
				PokerHandEvaluation handEvaluation = HandEvaluator.getBestHand(player, this.getCommunitaryCards());
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
		
		return winners;
	}
	
	public boolean allPlayersHasSameBet() {
		ArrayList<PokerPlayer> playerPlaying = this.getActivePlayers();
		
		for(PokerPlayer pokerPlayer : playerPlaying) {
			if (!pokerPlayer.madeAllIn() && pokerPlayer.getBet() != highestBet) {
				return false;
			}
		}
		
		return true;
	}
	
	public boolean allPlayerPlays() {
		ArrayList<PokerPlayer> playerPlaying = this.getActivePlayers();
		for(PokerPlayer pokerPlayer : playerPlaying) {
			if (pokerPlayer.getMove() == PokerPlayerMovement.Wait) {
				return false;
			}
		}
		
		return true;
	}
	
	public ArrayList<PokerPlayer> getPlayers() {
		return players;
	}
	
	public ArrayList<PokerPlayer> getActivePlayers() {
		ArrayList<PokerPlayer> activePlayers = new ArrayList<PokerPlayer>();
		
		for(PokerPlayer pokerPlayer : players) {
			if (pokerPlayer.getMove() != PokerPlayerMovement.Fold && 
				pokerPlayer.getMove() != PokerPlayerMovement.Out &&
				pokerPlayer.getMove() != PokerPlayerMovement.AllIn) {
				activePlayers.add(pokerPlayer);
			}
		}
		
		return activePlayers;
	}

	public void setPlayers(ArrayList<PokerPlayer> players) {
		this.players = players;
	}

	public int getPot() {
		return pot;
	}

	public void setPot(int pot) {
		this.pot = pot;
	}	
	
	public int getHighestBet() {
		return highestBet;
	}

	public void setHighestBet(int highestBet) {
		this.highestBet = highestBet;
	}

	public int getPlayerTurnIndex() {
		if (playerIndex >= this.players.size()) {
			return 0;
		}
		
		return playerIndex;
	}

	public void setPlayerTurnIndex(int turnPosition) {
		if (turnPosition > this.getActivePlayers().size()) {
			this.playerIndex = 0;
		}
		else {
			this.playerIndex = turnPosition;
		}
	}

	public ArrayList<PokerCard> getCommunitaryCards() {
		return communitaryCards;
	}

	public void setTableCards(ArrayList<PokerCard> table) {
		this.communitaryCards = table;
	}
	
	public void nextTurn(PokerPlayerMovement decision) {		
		if (getPlayerTurnIndex() + 1 >= this.getActivePlayers().size()) {
			this.setPlayerTurnIndex(0) ;
		}
		else {
			if (decision != PokerPlayerMovement.Fold) {
				setPlayerTurnIndex(getPlayerTurnIndex() + 1);
			}		
		}
	}
	
	public boolean isFinish() {
		return this.getActivePlayers().size() > 1;
	}

	public int getBottomPosition() {
		return bottomPosition;
	}

	public void setBottomPosition(int bottomPosition) {
		this.bottomPosition = bottomPosition;
	}
	
	public void updatePlayerMovements() {
		for (PokerPlayer player : this.getActivePlayers()) {
			player.setMove(PokerPlayerMovement.Wait);
		}
	}
	
	private void showCommunitaryCard() {
		PokerCard card = deck.shuffleCard();
		getCommunitaryCards().add(card);
	}
	
	private void notifyRaise(PokerPlayer player) {
		ArrayList<PokerPlayer> activePlayers = this.getActivePlayers();
		for (PokerPlayer pokerPlayer : activePlayers) {
			boolean updateMovement = pokerPlayer.getMove() == PokerPlayerMovement.Call || pokerPlayer.getMove() == PokerPlayerMovement.Raise;
			if (pokerPlayer != player && updateMovement) {
				pokerPlayer.setMove(PokerPlayerMovement.Wait);
			}
		}
	}
}
