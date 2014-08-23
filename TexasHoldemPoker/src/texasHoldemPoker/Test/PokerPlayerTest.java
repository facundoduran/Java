package texasHoldemPoker.Test;

import static org.junit.Assert.*;

import org.junit.Test;

import texasHoldemPoker.Model.*;

public class PokerPlayerTest {
	
	@Test
	public void pokerPlayerRaiseTest() {
		PokerPlayer pokerPlayer = new PokerPlayer("facundo");
		
		int balanceAmount = 50;
		
		int raiseAmount = 20;
		
		pokerPlayer.setBalance(balanceAmount);
		
		pokerPlayer.raise(raiseAmount);
		
		assertEquals(pokerPlayer.getBalance(), balanceAmount - raiseAmount);
	}
	
	@Test
	public void allInTest() {
		PokerPlayer pokerPlayer = new PokerPlayer("facundo");
		
		int balanceAmount = 50;
		
		pokerPlayer.setBalance(balanceAmount);
		
		pokerPlayer.allIn();
			
		assertEquals(pokerPlayer.getBalance(), 0);
	}
}
