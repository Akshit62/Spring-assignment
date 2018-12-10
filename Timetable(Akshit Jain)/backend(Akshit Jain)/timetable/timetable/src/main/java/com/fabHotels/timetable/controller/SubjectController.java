package com.fabHotels.timetable.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fabHotels.timetable.model.Subject;
import com.fabHotels.timetable.service.SubjectService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
public class SubjectController {

	@Autowired
	private SubjectService subjectService;
	
	@PostMapping(path = "/api/subject", produces = "application/json")
	public Subject addSubject(@RequestBody Subject subject1) throws ObjectNotFoundException {
		return subjectService.addSubject(subject1);
	}
	
	@GetMapping(path = "/api/subject", produces = "application/json")
	public List<Subject> getAllSubjects() {
		return subjectService.getSubjects();
	}
	
	@DeleteMapping(path = "/api/subject/{subjectId}", produces = "application/json")
	public boolean removeSubject(@PathVariable("subjectId") Long id) {
		return subjectService.removeSubject(id);
	}
	
	@GetMapping(path = "/api/subject/{subjectId}", produces = "application/json")
	public Subject getSubject(@PathVariable("subjectId") Long id) {
		return subjectService.getSubject(id);
	}
}
