package model;

import java.util.HashMap;

public class Time {
	private int minute = 0;
	private int hour = 0;
	private int day = 0;
	private int week = 0;
	private int month = 0;
	private int year = 2017;
	
	public HashMap<String, Integer> getTimeMap()
	{
		HashMap<String, Integer> timeMap = new HashMap<String, Integer>();
		timeMap.put("minute", minute);
		timeMap.put("hour", hour);
		timeMap.put("day", day);
		timeMap.put("week", week);
		timeMap.put("month", month);
		timeMap.put("year", year);
		return timeMap;
	}
	
	public String toString()
	{
		String prefixHour = "";
		String prefixMinute = "";
		if (hour <= 9) prefixHour = "0";
		if (minute <= 9) prefixMinute = "0";
		return prefixHour + hour + ":" + prefixMinute + minute + " " + getDayString();
	}
	
	public int getMinute()
	{
		return minute;
	}
	public int getHour(){
		return hour;
	}
	public int getDay()
	{
		return day;
	}
	public int getWeek()
	{
		return week;
	}
	public int getMonth(){
		return month;
	}
	public int getYear(){
		return year;
	}
	
	public String getDayString()
	{
		switch (day){
		case 0:
			return "Monday";
		case 1:
			return "Tuesday";
		case 2:
			return "Wednesday";
		case 3: 
			return "Thursday";
		case 4:
			return "Friday";
		case 5:
			return "Saturday";
		case 6:
			return "Sunday";
		default :
			return "Error in day string";
		}
	}
	
	public void advanceTime()
	{
		// Advance the time by one minute.
        minute++;
        while (minute > 59) {
            minute -= 60;
            hour++;
        }
        while (hour > 23) {
            hour -= 24;
            day++;
        }
        while (day > 6) {
            day -= 7;
            week++;
        }
        while (week > 3)        {
        	week -= 4;
        	month++;
        }
        while (month > 11)
        {
        	month -= 12;
        	year++;
        }
	}
}
