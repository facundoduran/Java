package texasHoldemPoker.Common;

public class QueryBuilder {
	public final static String Quote = "'";
	public final static String Comma = ",";
	public final static String FinalSpace = " ";
	public final static String EndInsert = ");";
	public final static String End = ";";
	public final static String LikeOperator = "%";
	
	public static String getParameterWithQuotes(String parameter)
	{
		return Quote + parameter + Quote;
	}
	
	public static String getParameterWithQuotesAndComma(String parameter)
	{
		return Quote + parameter + Quote + Comma;
	}
	
	public static String getParameterWithQuotesAndEndStatement(String parameter)
	{
		return Quote + parameter + Quote + End;
	}
	
	public static String getParameterWithQuotesAndLikeOperator(String parameter)
	{
		return Quote + parameter + LikeOperator + Quote;
	}
	
	public static String getParameterWithEndInsertStatement(int parameter)
	{
		return Integer.toString(parameter)+ EndInsert;
	}
	
	public static String getParameterWithEndStatement(int parameter)
	{
		return Integer.toString(parameter)+ End;
	}
	
	public static String getParameterWithFinalSpace(String parameter)
	{
		return parameter + FinalSpace; 
	}
	
	public static String getParameterWithFinalSpace(int parameter)
	{
		return parameter + FinalSpace; 		
	}
}
