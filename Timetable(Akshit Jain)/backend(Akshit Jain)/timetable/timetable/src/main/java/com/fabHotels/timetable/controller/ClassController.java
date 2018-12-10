package com.fabHotels.timetable.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fabHotels.timetable.model.Class;
import com.fabHotels.timetable.service.ClassService;

@RestController
public class ClassController {

	@Autowired
	private ClassService classService;
	
	@PostMapping(path = "/api/class" , produces = "application/json")
	public Class createClass(@RequestBody Class class1) {
		return classService.addClass(class1);
	}
	
	@GetMapping(path = "/api/class", produces = "application/json")
	public List<Class> getAllClasses() {
		return classService.getClasses();
	}
	
	@GetMapping(path = "/api/class/{classId}", produces = "application/json")
	public Class getClass(@PathVariable("classId") Long id) {
		return classService.getClass(id);
	}
	
	@DeleteMapping(path = "/api/class/{classId}" ,produces = "application/json")
	public boolean removeClass(@PathVariable("classId") Long id) {
		return classService.removeClass(id);
	}
}
