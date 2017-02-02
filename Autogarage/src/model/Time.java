package model;

import java.util.HashMap;

public class Time {
	private int minute;
	private int hour;
	private int day;
	private int week;
	private int month;
	private int year;
	
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
