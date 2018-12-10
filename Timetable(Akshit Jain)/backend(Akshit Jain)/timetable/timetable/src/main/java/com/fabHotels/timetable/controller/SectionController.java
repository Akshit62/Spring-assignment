package com.fabHotels.timetable.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fabHotels.timetable.model.Section;
import com.fabHotels.timetable.model.Subject;
import com.fabHotels.timetable.model.SubjectPojo;
import com.fabHotels.timetable.service.SectionService;

@RestController
public class SectionController {

	@Autowired
	private SectionService sectionService;
	
	@PostMapping(path = "/api/class/{classId}/section" ,produces = "application/json")
	public Section addSection(@RequestBody Section section,@PathVariable("classId") Long id) {
		return sectionService.addSection(id, section);
	}
	
	@GetMapping(path = "/api/class/{classId}/section" , produces = "application/json")
	public List<Section> getAllSections(@PathVariable("classId") Long id) {
		return sectionService.getAllSections(id);
	}
	
	@DeleteMapping(path = "/api/class/{classId}/section/{sectionId}", produces = "application/json")
	public boolean removeSection(@PathVariable("classId") Long id,@PathVariable("sectionId") Long id1) {
		return sectionService.removeSection(id, id1);
	}
	
	@GetMapping(path = "/api/class/{classId}/section/{sectionId}", produces = "application/json")
	public Section getSection(@PathVariable("classId") Long id,@PathVariable("sectionId") Long id1) {
		return sectionService.getSection(id1);
	}
	
	@PostMapping( path = "/api/section/{sectionId}/subjects", produces = "application/json")
	public List<Subject> addSubjects(@RequestBody SubjectPojo subjectPojo,@PathVariable("sectionId") Long id) {
		List<Subject> list = new ArrayList<>();
		for(Subject x :subjectPojo.getSubjectList()) {
			list.add(x);
		}
		return sectionService.addSubjects(list, id);
	}
	
	@GetMapping(path = "/api/section/{sectionId}/subjects", produces = "application/json")
	public Set<Subject> getSubjects(@PathVariable("sectionId") Long id) {
		return sectionService.getSubjects(id);
	}
	
	@DeleteMapping(path = "/api/section/{sectionId}/subjects", produces = "application/json")
	public boolean removeSubjects(@PathVariable("sectionId") Long id) {
		return sectionService.removeSubjects(id);
	}
}

