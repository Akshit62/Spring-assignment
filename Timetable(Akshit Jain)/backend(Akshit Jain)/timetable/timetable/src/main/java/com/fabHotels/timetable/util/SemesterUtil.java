package com.fabHotels.timetable.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class SemesterUtil {

	public static boolean isStartOfSemValid(Date semDate) {
		LocalDate date1 = LocalDate.now().plusMonths(6);
		Date futureDate = Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		if(semDate.before(futureDate) || semDate.before(new Date())) {
			return false;
		}
		return true;
	}
	
	public static Date setEndOfSem(Date semDate) {
		LocalDate futureDate = semDate.toInstant()
			      .atZone(ZoneId.systemDefault())
			      .toLocalDate();
		LocalDate date1 = futureDate.plusMonths(6);
		return Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
}
