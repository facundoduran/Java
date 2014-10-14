package texasHoldemPoker.Persistence.Sql.Dao;
import java.util.ArrayList;

import texasHoldemPoker.Model.*;

public interface IPlayerDAO {
	ArrayList<Player> getAllPlayers();
	
	ArrayList<Player> getAllPlayers(String name);
	
	ArrayList<Player> getPlayersInList(ArrayList<String> playerNames);
	
	Player getPlayer(String name);
	
	void insertPlayer(Player player);
	
	void updatePlayer(Player player);
	
	void updateSalary(int playerId, double salary);

	void updateSalary(String playerName, double salary);
}
