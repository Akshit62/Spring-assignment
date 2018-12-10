package com.fabHotels.timetable.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fabHotels.timetable.model.Teacher;
import com.fabHotels.timetable.service.TeacherService;

@RestController
public class TeacherController {

	@Autowired
	private TeacherService teacherService;
	
	@PostMapping(path = "/api/subject/{subjectId}/teacher" ,produces = "application/json")
	public Teacher addTeacher(@RequestBody Teacher teacher,@PathVariable("subjectId") Long id) {
		return teacherService.addTeacher(id, teacher);
	}
	
	@GetMapping(path = "/api/subject/{subjectId}/teacher" , produces = "application/json")
	public List<Teacher> getAllTeachers(@PathVariable("subjectId") Long id) {
		return teacherService.getTeachersBySubject(id);
	}
	
	@DeleteMapping(path = "/api/subject/{subjectId}/teacher/{teacherId}", produces = "application/json")
	public boolean removeTeacher(@PathVariable("subjectId") Long id,@PathVariable("teacherId") Long id1) {
		return teacherService.removeTeacher(id, id1);
	}
	
	@GetMapping(path = "/api/subject/teachers", produces = "application/json")
	public  Map<String,List<Teacher>> getTeachers() {
		return teacherService.getAllTeachersBySubject();
	}
	
	@GetMapping(path = "/api/subject/pool/teachers", produces = "application/json")
	public  Map<String,List<Teacher>> getPoolTeachers() {
		return teacherService.getAllTeachersByCourse();
	}
	
}
