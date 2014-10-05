package texasHoldemPoker.Persistence.Sql.Dao;

import java.util.ArrayList;

import texasHoldemPoker.Model.SalaryHistory;

public interface ISalaryHistoryDAO {
	ArrayList<SalaryHistory> GetByPlayerId(int playerId);
	void AddBalance(double amount, double balance, int playerId);
}
