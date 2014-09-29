package texasHoldemPoker.Common;

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
	
	public static boolean StringIsNullOrEmpty(String str)
	{
		return (str != null && !str.isEmpty());	
	}
}
