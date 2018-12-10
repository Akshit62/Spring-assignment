package com.fabHotels.timetable.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fabHotels.timetable.model.Holiday;
import com.fabHotels.timetable.service.HolidayService;

@RestController
public class HolidayController {

	@Autowired
	private HolidayService holidayService;
	
	@PostMapping(path = "/api/semester/{semesterId}/holiday", produces ="application/json")
	public Holiday addHoliday(@RequestBody Holiday holiday,@PathVariable("semesterId") Long id) {
		return holidayService.addHoliday(id, holiday);
	}
	
	@DeleteMapping(path = "/api/semester/{semesterId}/holiday/{holidayId}", produces = "application/json")
	public boolean removeHoliday(@PathVariable("semesterId") Long id,@PathVariable("holidayId") Long id1) {
		return holidayService.removeHoliday(id, id1);
	}
	
	@GetMapping(path = "/api/semester/{semesterId}/holiday", produces = "application/json")
	public Map<String,List<Date>> getAllHolidays(@PathVariable(value = "semesterId") Long id) {
		return holidayService.getAllHolidays(id);
	}
	
	@GetMapping(path ="/api/semester/{semesterId}/working" , produces = "application/json")
	public List<Date> getAllWorkingDays(@PathVariable("semesterId") Long id) {
		return holidayService.getAllWorkingDays(id);
	}
}
