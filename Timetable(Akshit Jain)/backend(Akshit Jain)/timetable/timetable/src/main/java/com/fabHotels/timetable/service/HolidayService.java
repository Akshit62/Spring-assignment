package com.fabHotels.timetable.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.fabHotels.timetable.dao.HolidayRepository;
import com.fabHotels.timetable.exception.CustomDataIntegrityException;
import com.fabHotels.timetable.exception.ObjectNotFoundException;
import com.fabHotels.timetable.model.Holiday;
import com.fabHotels.timetable.model.Semester;
import com.fabHotels.timetable.util.Constants;
import com.fabHotels.timetable.util.HolidayUtil;

@Service
public class HolidayService {

	@Autowired
	private HolidayRepository holidayRepository;

	@Autowired
	private SemesterService semesterService;

	/*
	 * Creating holiday for a particular semester
	 */
	public Holiday addHoliday(Long semesterId,Holiday holiday) {
		try{
			if(semesterService.getSemester(semesterId) == null) {
				throw new ObjectNotFoundException("Semester does not exist");
			}
			if(holidayRepository.getByHolidayDate(holiday.getHolidayDate()) != null) {
				throw new ObjectNotFoundException("Holiday already exists on this date");
			}
			Semester semester = semesterService.getSemester(semesterId);
			holiday.setSemesterId(semester);
			holidayRepository.save(holiday);
		}
		catch(DataIntegrityViolationException exception) {
			throw new CustomDataIntegrityException("Fields cannot be null");
		}
		return holiday;
	}

	/*
	 * Removing holiday for a particular semester
	 */
	public boolean removeHoliday(Long semesterId,Long holidayId) {
		if(semesterService.getSemester(semesterId) == null) {
			throw new ObjectNotFoundException("Semester does not exist");
		}
		if(!holidayRepository.existsById(holidayId)) {
			throw new ObjectNotFoundException("Holiday does not exist ");
		}
		holidayRepository.deleteById(holidayId);
		return true;
	}

	/*
	 * Fetching all holidays for a particular semester
	 */
	public Map<String,List<Date>> getAllHolidays(Long semesterId) {
		Map<String,List<Date>> map = new HashMap<>();
		if(semesterService.getSemester(semesterId) == null) {
			throw new ObjectNotFoundException("Semester does not exist");
		}
		Semester semester = semesterService.getSemester(semesterId);
		List<Holiday> list = holidayRepository.findBySemesterId(semester);
		List<Date> dateList = new ArrayList<>();
		for(Holiday holiday : list){
			dateList.add(holiday.getHolidayDate());
		}
		map.put(Constants.NONWORKING_DAYS,dateList);

		if(semester.isSaturdayWorking() == false) {
			map.put(Constants.SATURDAY_HOLIDAYS,HolidayUtil.getSaturdayList(semester.getStartOfSem(),semester.getEndOfSem()));
		}
		map.put(Constants.SUNDAY_HOLIDAYS,HolidayUtil.getSundayList(semester.getStartOfSem(),semester.getEndOfSem()));
		map.put(Constants.PUBLIC_HOLIDAYS,HolidayUtil.getPublicHolidaysList(semester.getStartOfSem(),semester.getEndOfSem()));
		return map;
	}

	/*
	 * Fetch all working days
	 */
	public List<Date> getAllWorkingDays(Long semesterId) {
		List<Date> dateList = new ArrayList<>();
		if(semesterService.getSemester(semesterId) == null) {
			throw new ObjectNotFoundException("semester does not exist");
		}
		Semester semester = semesterService.getSemester(semesterId);
		List<Holiday> list = holidayRepository.findBySemesterId(semester);
		dateList = HolidayUtil.getWorkingDays(list,semester.getStartOfSem(),semester.getEndOfSem(),semester.isSaturdayWorking());
		return dateList;
	}

	/*
	 * Check if the particular day is a Holiday
	 */
	public boolean checkForHoliday(Date date) {
		//return true if  a holiday
		boolean publicHoliday = HolidayUtil.checkForPublicHoliday(date);
		boolean sunday = HolidayUtil.checkForSunday(date);
		if(holidayRepository.getByHolidayDate(date) != null || publicHoliday || sunday) {
			return true;
		}
		return false;
	}
	
	public List<Holiday> getHolidaysBySemesterId(Long id) {
		Semester semester = semesterService.getSemester(id);
		return holidayRepository.findBySemesterId(semester);
	}
}
