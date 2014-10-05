package texasHoldemPoker.Persistence.Sql.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import texasHoldemPoker.Common.QueryBuilder;
import texasHoldemPoker.Model.Player;
import texasHoldemPoker.Persistence.Sql.SqlLiteConnection;

public class PlayerDAO implements IPlayerDAO {

	@Override
	public ArrayList<Player> getAllPlayers()
	{
		String query = "SELECT Id, name, email, balance FROM Player";
		return this.getjugadoresByQuery(query);
	}
	
	@Override 
	public ArrayList<Player> getAllPlayers(String name)
	{
		String query = "SELECT Id, name, email, balance FROM Player WHERE name LIKE ";
		query += QueryBuilder.getParameterWithQuotesAndLikeOperator(name);
		return this.getjugadoresByQuery(query);
	}	
	
	@Override
	public Player getPlayer(String nombre)
	{
		String query = "Select Id, name, email, balance from Player Where Name = " 
			+ QueryBuilder.getParameterWithQuotes(nombre);
		
		ArrayList<Player> jugadores = this.getjugadoresByQuery(query); 
		
		return jugadores.iterator().next();
	}
	
	@Override
	public void insertPlayer(Player player)
	{
		String query = "insert into Player (name, email, balance) values("
			+ QueryBuilder.getParameterWithQuotesAndComma(player.getName()) 
			+ QueryBuilder.getParameterWithQuotesAndComma(player.getEmail()) 
			+ QueryBuilder.getParameterWithEndInsertStatement(player.getSalary());
		SqlLiteConnection.executeQuery(query);
	}
	
	@Override
	public void updatePlayer(Player player)
	{
		String query = "update Player set " 
			+ "email = " + QueryBuilder.getParameterWithQuotesAndComma(player.getEmail()) 
			+ "balance = " + QueryBuilder.getParameterWithFinalSpace(player.getSalary())
			+ "where name = " + QueryBuilder.getParameterWithQuotes(player.getName());
		SqlLiteConnection.executeQuery(query);
	}
	
	@Override	
	public void updateSalary(int playerId, double salary) {
		String query = "update Player set "  
				+ "balance = " + QueryBuilder.getParameterWithFinalSpace(String.valueOf(salary))
				+ "where Id = " + QueryBuilder.getParameterWithEndStatement(playerId);
		SqlLiteConnection.executeQuery(query);
	}
	
	@SuppressWarnings("finally")
	private ArrayList<Player> getjugadoresByQuery(String query)
	{
		ArrayList<Player> players = new ArrayList<Player>();
		
		try 
		{
			Connection connection = SqlLiteConnection.getConnection();
		    Statement statement = connection.createStatement();
	        ResultSet resultSet;

	        resultSet = statement.executeQuery(query);
		    while (resultSet.next() ) 
		    {
		    	int playerId = resultSet.getInt("playerId");
		        String nombre = resultSet.getString("name");
		        String email = resultSet.getString("email");
		        int saldo = resultSet.getInt("balance");
		        
		        Player player = new Player(playerId, nombre, email, saldo);
		        
		        players.add(player);
		    }
			
		    SqlLiteConnection.closeConnection(connection);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			return players;
		}
	}	
}
