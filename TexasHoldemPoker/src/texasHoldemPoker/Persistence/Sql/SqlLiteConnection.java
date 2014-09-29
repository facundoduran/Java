package texasHoldemPoker.Persistence.Sql;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;

public class SqlLiteConnection {
	
	private final static String databaseSource = "jdbc:sqlite:Database\\TexasHoldemPoker\\";
	private final static String connectorJDBC = "org.sqlite.JDBC";
		
	public static void executeQuery(String query)
	{
		Connection connection = getConnection();
		try 
		{
		    Statement statement = connection.createStatement();
		    statement.executeUpdate(query);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}			
		finally
		{
			closeConnection(connection);
		}
	}
	
	public static Connection getConnection()
	{
		Connection connection = null;
		try 
		{
		    Class.forName(connectorJDBC);	 
		    connection = DriverManager.getConnection(databaseSource);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}  		
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			return connection;
		}
	}
	
	public static void closeConnection(Connection connection)
	{
		try 
		{
			if (connection != null) 
			{
				connection.close();				
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}		
}