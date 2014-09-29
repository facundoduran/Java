package texasHoldemPoker.Persistence.Sql.Dao;
import java.util.ArrayList;

import texasHoldemPoker.Model.*;

public interface IPlayerDAO {
	ArrayList<Player> getAllPlayers();
	
	Player getPlayer(String name);
	
	void insertPlayer(Player player);
	
	void updatePlayer(Player player);
}
