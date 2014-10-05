package texasHoldemPoker.Persistence.Sql.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import texasHoldemPoker.Common.DateHelper;
import texasHoldemPoker.Common.QueryBuilder;
import texasHoldemPoker.Model.SalaryHistory;
import texasHoldemPoker.Persistence.Sql.SqlLiteConnection;

public class SalaryHistoryDAO implements ISalaryHistoryDAO {

	public SalaryHistoryDAO() {
		
	}
	
	@Override
	public ArrayList<SalaryHistory> GetByPlayerId(int playerId) {
		String query = "select date, amount, balance from SalaryHistory where playerId = "
				+ QueryBuilder.getParameterWithEndStatement(playerId);
		
		return this.getSalaryHistoryForPlayer(query);	
	}

	@Override
	public void AddBalance(int amount, int balance, int playerId) {
		
		String query = "insert into SalaryHistory(amount, balance, playerId) values ("
				+ QueryBuilder.getParameterWithComma(amount)
				+ QueryBuilder.getParameterWithComma(balance)
				+ QueryBuilder.getParameterWithEndInsertStatement(playerId);
		
		SqlLiteConnection.executeQuery(query);		
	}

	@SuppressWarnings("finally")
	private ArrayList<SalaryHistory> getSalaryHistoryForPlayer(String query)
	{
		ArrayList<SalaryHistory> salaryHistories = new ArrayList<SalaryHistory>();
		
		try 
		{
			Connection connection = SqlLiteConnection.getConnection();
		    Statement statement = connection.createStatement();
	        ResultSet resultSet;

	        resultSet = statement.executeQuery(query);
	        
		    while (resultSet.next() ) 
		    {
		        String date = resultSet.getString("date");
		        String amount = resultSet.getString("amount");
		        String balance = resultSet.getString("balance");
		        
		        Date salaryHistoryDate = DateHelper.parseString(date);
		        double salaryHistoryAmount = Double.parseDouble(amount);
		        double salaryHistoryBalance = Double.parseDouble(balance);
		        
		        SalaryHistory salaryHistory = 
		        		new SalaryHistory
		        		(
	        				salaryHistoryDate,
	        				salaryHistoryAmount,
	        				salaryHistoryBalance
        				);
	        	
		        salaryHistories.add(salaryHistory);
		    }
			
		    SqlLiteConnection.closeConnection(connection);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			return salaryHistories;
		}		
	}
}
