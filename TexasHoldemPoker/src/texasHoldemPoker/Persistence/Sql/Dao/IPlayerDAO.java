package texasHoldemPoker.Persistence.Sql.Dao;
import java.util.ArrayList;

import texasHoldemPoker.Model.*;

public interface IPlayerDAO {
	ArrayList<Player> getAllPlayers();
	
	ArrayList<Player> getAllPlayers(String name);
	
	Player getPlayer(String name);
	
	void insertPlayer(Player player);
	
	void updatePlayer(Player player);
	
	void updateSalary(int playerId, double salary);
}
