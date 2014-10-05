package texasHoldemPoker.Common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateHelper {

	public static String getCurrentDateAsString() {
		DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
		Calendar calendar = Calendar.getInstance();
		
		String currentDate = dateFormat.format((calendar.getTime()));
		return currentDate;		
	}
	
	public static Date getCurrentDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
		Calendar calendar = Calendar.getInstance();		
		return calendar.getTime();
	}
	
	public static Date parseString(String date) {
		DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
		Date parsedDate = new Date();
		
		try {
			parsedDate = dateFormat.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return parsedDate;
	}
}