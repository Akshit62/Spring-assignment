package com.fabHotels.timetable.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fabHotels.timetable.model.ResultantTimeTable;
import com.fabHotels.timetable.model.TimeTablePojo;
import com.fabHotels.timetable.model.TimeTablePojo1;
import com.fabHotels.timetable.service.TimeTableService;

@RestController
public class TimeTableController {

	@Autowired
	private TimeTableService timeTableService;
	
	/*
	 * Creating time table
	 */
	@PostMapping(path = "/api/section/{sectionId}/timetable" ,produces = "application/json")
	public boolean createTimeTableForSection(@PathVariable("sectionId") Long id) {
		return timeTableService.createTimeTableForSection(id);
	}
	
	@PostMapping(path = "/api/class/{classId}/timetable" ,produces = "application/json")
	public boolean createTimeTableForClass(@PathVariable("classId") Long id) {
		return timeTableService.createTimeTableForClass(id);
	}
	
	@PostMapping(path = "/api/semester/{semesterId}/timetable" ,produces = "application/json")
	public boolean createTimeTableForSemester(@PathVariable("semesterId") Long id) {
		return timeTableService.createTimeTableForSemester(id);
	}
	
	
	/*
	 * Updating time table
	 */
	@PutMapping(path = "/api/section/{sectionId}/timetable" ,produces = "application/json")
	public boolean updateTimeTableForSection(@PathVariable("sectionId") Long id) {
		return timeTableService.updateTimeTableForSection(id);
	}
	
	@PutMapping(path = "/api/class/{classId}/timetable" ,produces = "application/json")
	public boolean updateTimeTableForClass(@PathVariable("classId") Long id) {
		return timeTableService.updateTimeTableForClass(id);
	}
	
	@PutMapping(path = "/api/semester/{semesterId}/timetable" ,produces = "application/json")
	public boolean updateTimeTableForSemester(@PathVariable("semesterId") Long id) {
		return timeTableService.updateTimeTableForSemester(id);
	}
	
	
	/*
	 * Deleting time table
	 */
	@DeleteMapping(path = "/api/section/{sectionId}/timetable" ,produces = "application/json")
	public boolean removeTimeTableForSection(@PathVariable("sectionId") Long id) {
		return timeTableService.removeTimeTableForSection(id);
	}
	
	@DeleteMapping(path = "/api/class/{classId}/timetable" ,produces = "application/json")
	public boolean removeTimeTableForClass(@PathVariable("classId") Long id) {
		return timeTableService.removeTimeTableForClass(id);
	}
	
	@DeleteMapping(path = "/api/semester/{semesterId}/timetable" ,produces = "application/json")
	public boolean removeTimeTableForSemester(@PathVariable("semesterId") Long id) {
		return timeTableService.removeTimeTableForSemester(id);
	}
	
	
	/*
	 * Viewing timetable
	 */
	@PostMapping(path = "/api/section/timetable", produces = "application/json")
	@ResponseStatus(value=HttpStatus.OK)
	public List<ResultantTimeTable> getTimeTableForSection(@RequestBody TimeTablePojo pojo) {
		return timeTableService.viewTimeTableForSection(pojo.getSectionId(), pojo.getDate());
	}
	
	@PostMapping(path = "/api/teacher/timetable", produces = "application/json")
	public List<String> getTimeTableForTeacher(@RequestBody TimeTablePojo1 pojo) {
		return timeTableService.viewTimeTableForClass(pojo.getTeacherId(), pojo.getDate());
	}
	
}
