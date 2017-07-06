package org.will.app.business;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonParse
{
	public static Date formatDate(String date)
	{
		String format = "yyyy-MM-dd'T'HH:mm:ss'Z'";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date targetDate = null;
		try 
		{
			targetDate = sdf.parse(date);
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		return targetDate;
	}
}
