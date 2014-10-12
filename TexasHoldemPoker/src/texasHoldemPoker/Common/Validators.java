package texasHoldemPoker.Common;

import java.util.ArrayList;
import texasHoldemPoker.Model.Player;

public class Validators {
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    int numb = Integer.parseInt(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	
	public static boolean StringIsNotNullOrEmpty(String str)
	{
		return (str != null && !str.isEmpty());	
	}
	
	public static ArrayList<String> somePlayerHasSalaryLessThanBigBlind(ArrayList<Player> players, int bigBlind) {
		ArrayList<String> playersCantPlay = new ArrayList<String>();
		
		for (Player player : players) {
			if (player.getSalary() <= bigBlind) {
				playersCantPlay.add(player.getName());
			}
		}
		
		return playersCantPlay;
	}
}
