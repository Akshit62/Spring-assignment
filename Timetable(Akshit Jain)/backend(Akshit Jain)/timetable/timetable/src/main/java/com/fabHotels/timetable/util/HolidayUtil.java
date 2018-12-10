package com.fabHotels.timetable.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fabHotels.timetable.model.Holiday;

public class HolidayUtil {
	
	public static boolean checkForSunday(Date date) {
		Calendar c1=Calendar.getInstance();
		c1.setTime(date);
		if(c1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			return true;
		}
		return false;
	}
	
	public static boolean checkForSaturday(Date date) {
		Calendar c1=Calendar.getInstance();
		c1.setTime(date);
		if(c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			return true;
		}
		return false;
	}
	
	public static List<Date> getSaturdayList(Date start, Date end) {
		List<Date> list = new ArrayList<>();
		
		Calendar c1=Calendar.getInstance();
		c1.setTime(end);
		Calendar c2=Calendar.getInstance();
		c2.setTime(start);
		while(c1.after(c2)){
		if(c2.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY){
			list.add(c2.getTime());
			c2.add(Calendar.DATE,7);
		}
		else
			c2.add(Calendar.DATE,1);
		}
		return list;
	}
	
	public static List<Date> getSundayList(Date start,Date end) {
		List<Date> list = new ArrayList<>();
		
		Calendar c1=Calendar.getInstance();
		c1.setTime(end);
		Calendar c2=Calendar.getInstance();
		c2.setTime(start);
		while(c1.after(c2)){
		if(c2.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
			list.add(c2.getTime());
			c2.add(Calendar.DATE,7);
		}
		else
			c2.add(Calendar.DATE,1);
		}
		return list;
	}
	
	public static List<Date> getPublicHolidaysList(Date start,Date end) {
		List<Date> list = new ArrayList<>();
		
		// Can use some api to fetch real time data for public holidays and hence integrate
		return list;	
	}
	
	public static boolean checkForPublicHoliday(Date date) {
		//integrate some api to fetch in real time for public holidays
		return false;
	}
	
	public static List<Date> getWorkingDays(List<Holiday> holidays,Date start,Date end,boolean Saturday) {
		List<Date> list = new ArrayList<>();
		
		Calendar c1=Calendar.getInstance();
		c1.setTime(end);
		Calendar c2=Calendar.getInstance();
		c2.setTime(start);
		while(c1.after(c2)) {
			if((Saturday == false && c2.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) || checkForPublicHoliday(c2.getTime()) == true || c2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
				//Do nothing
			}
			else if(isDeclaredHoliday(c2.getTime(),holidays)){
				//Do nothing
			}
			else{
			list.add(c2.getTime());
			}
			c2.add(Calendar.DATE,1);
		}
		return list;
	}
	
	public static boolean isDeclaredHoliday(Date date,List<Holiday> holidays) {
		for(Holiday holiday : holidays) {
			if(date.equals(holiday.getHolidayDate())){
				return true;
			}
		}
		return false;
	}
}
