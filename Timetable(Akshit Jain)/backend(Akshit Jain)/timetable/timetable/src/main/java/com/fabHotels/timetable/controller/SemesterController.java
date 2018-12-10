package com.fabHotels.timetable.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fabHotels.timetable.model.Semester;
import com.fabHotels.timetable.service.SemesterService;

@RestController
public class SemesterController {

	@Autowired
	private SemesterService semesterService;
	
	@PostMapping(path ="/api/semester",produces = "application/json")
	public Semester addSemester(@RequestBody Semester semester) {
		return semesterService.addSemester(semester);
	}
	
	@GetMapping(path = "/api/semester/{semesterId}",produces = "application/json")
	public Semester getSemester(@PathVariable("semesterId") Long id) {
		return semesterService.getSemester(id);
	}
	
	@GetMapping(path = "/api/semester",produces = "application/json")
	public List<Semester> getAllSemesters(){
		return semesterService.getAllSemesters();
	}
	
	@DeleteMapping(path = "/api/semester/{semesterId}")
	public boolean removeSemester(@PathVariable("semesterId") Long id) {
		return semesterService.removeSemester(id);
	}
	
	@PutMapping(path = "/api/semester/{semesterId}", produces = "application/json")
	public Semester updateSemester(@RequestBody Semester semester,@PathVariable("semesterId") Long id) {
		return semesterService.updateSemester(semester);
	}
}
