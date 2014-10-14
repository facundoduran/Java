package texasHoldemPoker.Model;

import java.util.*;

import texasHoldemPoker.Business.HandEvaluator;
import texasHoldemPoker.Business.TieEvaluator;

public class PokerGame {
		
	private Deck deck;
	private ArrayList<PokerPlayer> players;
	private ArrayList<PokerCard> communitaryCards;
	private int pot;
	private int turnPosition;
	private int bigBlind;
	private int smallBlind;

	public PokerGame(int bigBlind) {
		this.deck = new Deck();
		this.setTableCards(new ArrayList<PokerCard>());
		this.players = new ArrayList<PokerPlayer>();
		this.setBigBlind(bigBlind);
		this.smallBlind = this.getBigBlind() /2 ;
		this.pot = this.getBigBlind() + smallBlind;	
	}
	
	public void startGame(int playerIndexWithBigBlindPos) {		
		this.setPlayerTurnIndex(playerIndexWithBigBlindPos);		
		int smallBlindPos = getPlayerTurnIndex() == 0 ? players.size() - 1 : getPlayerTurnIndex() - 1;
		PokerPlayer smallBlindPlayer = this.players.get(smallBlindPos);
		smallBlindPlayer.call(this.smallBlind);		
		
		PokerPlayer bigBlindPlayer = this.players.get(playerIndexWithBigBlindPos);
		bigBlindPlayer.setHasBigBlind(true);		
		long amount = this.getBigBlind() - bigBlindPlayer.getBet();
		bigBlindPlayer.call(amount);
		
		this.pot = this.getBigBlind() + smallBlind;
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
		PokerPlayer playerTurn = this.getPlayingPlayers().get(getPlayerTurnIndex());
		return playerTurn;
	}
	
	public void playTurn(PokerPlayerDecision decision) {
		PokerPlayer playerTurn = this.getPlayer();
		
		if(playerTurn !=null) {		
			if (decision == PokerPlayerDecision.Leave) {
				playerTurn.leave();
			}
			
			if (decision == PokerPlayerDecision.Call) {
				if (this.getBigBlind() != playerTurn.getBet()) {
					long amount = this.getBigBlind() - playerTurn.getBet();
					playerTurn.call(amount);
					this.pot += amount;	
				}
			}
			
			if (decision == PokerPlayerDecision.AllIn) {
				playerTurn.allIn();
			}
			
			this.nextTurn(decision);
		}
	}
	
	public void playTurn(PokerPlayerDecision decision, int bet) {
		PokerPlayer playerTurn = this.getPlayer();
		
		if(playerTurn !=null) {
			
			if (decision == PokerPlayerDecision.Raise) {
				
				if (playerTurn.getBalance() <= bet) {
					playerTurn.allIn();
				}
				else {
					playerTurn.raise(bet);
					this.pot += bet;
					this.bigBlind += bet;
				}				
			}
		}
		
		nextTurn(decision);
	}
		
	public void flop() {
		for(int i = 0; i < 3; i++) {
			this.addCardInTable();
		}
	}
	
	public void turn() {
		this.addCardInTable();
	}
	
	public void river() {
		this.addCardInTable();
	}
	
	public ArrayList<PokerHandEvaluation> getPlayersEvaluation() {
		ArrayList<PokerPlayer> playersPlaying = this.getPlayingPlayers();

		ArrayList<PokerHandEvaluation> playersEvaluation = new ArrayList<PokerHandEvaluation>();

		
		for(PokerPlayer player : playersPlaying) {
			PokerHandEvaluation handEvaluation = HandEvaluator.getBestHand(player, this.getCommunitaryCards());
			playersEvaluation.add(handEvaluation);
		}
		
		return playersEvaluation;
	}
	
	public ArrayList<PokerHandEvaluation> finishGame() {
		//evaluate hands
		ArrayList<PokerPlayer> playersPlaying = this.getPlayingPlayers();
		ArrayList<PokerHandEvaluation> winners = new ArrayList<PokerHandEvaluation>();
		
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
			else if (winners.size() == 1){
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
		ArrayList<PokerPlayer> playerPlaying = this.getPlayingPlayers();
		
		for(PokerPlayer pokerPlayer : playerPlaying) {
			if (!pokerPlayer.madeAllIn() && pokerPlayer.getBet() != bigBlind) {
				return false;
			}
		}
		
		return true;
	}
	
	public ArrayList<PokerPlayer> getPlayers() {
		return players;
	}
	
	public ArrayList<PokerPlayer> getPlayingPlayers() {
		ArrayList<PokerPlayer> playerPlaying = new ArrayList<PokerPlayer>();
		
		for(PokerPlayer pokerPlayer : players) {
			if (pokerPlayer.getDecision() != PokerPlayerDecision.Leave){
				playerPlaying.add(pokerPlayer);
			}
		}
		
		return playerPlaying;
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
	
	public int getBigBlind() {
		return bigBlind;
	}

	public void setBigBlind(int bigBlind) {
		this.bigBlind = bigBlind;
	}

	public int getPlayerTurnIndex() {
		if (turnPosition >= this.players.size()) {
			return 0;
		}
		
		return turnPosition;
	}

	public void setPlayerTurnIndex(int bigBlindPos) {
		if (bigBlindPos > this.getPlayingPlayers().size()) {
			this.turnPosition = 0;
		}
		else {
			this.turnPosition = bigBlindPos;
		}
	}

	public ArrayList<PokerCard> getCommunitaryCards() {
		return communitaryCards;
	}

	public void setTableCards(ArrayList<PokerCard> table) {
		this.communitaryCards = table;
	}

	private void addCardInTable() {
		PokerCard card = deck.shuffleCard();
		getCommunitaryCards().add(card);
	}
	
	private void nextTurn(PokerPlayerDecision decision) {		
		if (getPlayerTurnIndex() +1 >= this.getPlayingPlayers().size()) {
			this.setPlayerTurnIndex(0) ;
		}
		else {
			if (decision != PokerPlayerDecision.Leave) {
				setPlayerTurnIndex(getPlayerTurnIndex() + 1);
			}		
		}
	}
}
