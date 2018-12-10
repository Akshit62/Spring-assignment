package com.fabHotels.timetable.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.fabHotels.timetable.dao.TeacherRepository;
import com.fabHotels.timetable.exception.CustomDataIntegrityException;
import com.fabHotels.timetable.exception.ObjectNotFoundException;
import com.fabHotels.timetable.model.Subject;
import com.fabHotels.timetable.model.Teacher;
import com.fabHotels.timetable.util.TeacherUtil;

@Service
public class TeacherService {

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private TeacherRepository teacherRepository;

	/*
	 * Creating teacher of a particular subject
	 */
	public Teacher addTeacher(Long subjectId,Teacher teacher) {
		try{
			if(subjectService.getSubject(subjectId) == null) {
				throw new ObjectNotFoundException("Subject does not exist");
			}
			Subject subject = subjectService.getSubject(subjectId);
			teacher.setSubjectId(subject);
			teacherRepository.save(teacher);
		}
		catch(DataIntegrityViolationException exception) {
			throw new CustomDataIntegrityException("Fields cannot be null");
		}
		return teacher;
	}

	/*
	 * Getting list of all teachers
	 */
	public List<Teacher> getAllTeachers() {
		return (List<Teacher>) teacherRepository.findAll();
	}

	/*
	 * Getting list of all teachers subjectwise
	 */
	public Map<String,List<Teacher>> getAllTeachersBySubject() {
		Map<String,List<Teacher>> map = new HashMap<>();
		List<Subject> subjectsList = subjectService.getSubjects();
		List<Teacher> teachersList = (List<Teacher>) teacherRepository.findAll();
		for(Subject subject : subjectsList) {
			List<Teacher> temp = new ArrayList<>();
			temp = TeacherUtil.getTeachersForSubjectId(subject.getSubjectId(),teachersList);
			map.put(subject.getSubjectName(),temp);
		}

		return map;
	}

	/*
	 * Getting list of teachers by subject
	 */
	public List<Teacher> getTeachersBySubject(Long subjectId) {
		List<Teacher> list = new ArrayList<>();
		if(subjectService.getSubject(subjectId) == null) {
			throw new ObjectNotFoundException("Subject does not exist");
		}
		Subject subject = subjectService.getSubject(subjectId);
		list = teacherRepository.findBySubjectId(subject);
		return list;
	}

	/*
	 * Getting list of all teachers courseWise
	 */
	public Map<String,List<Teacher>> getAllTeachersByCourse() {
		Map<String,List<Teacher>> map = new HashMap<>();
		List<Teacher> list = (List<Teacher>) teacherRepository.findAll();
		map = TeacherUtil.getTeachersByCourse(list);
		return map;
	}

	/*
	 * Removing a particular teacher
	 */
	public boolean removeTeacher(Long subjectId,Long teacherId) {
		if(subjectService.getSubject(subjectId) == null) {
			throw new ObjectNotFoundException("Subject does not exist");
		}
		if(!teacherRepository.existsById(teacherId)) {
			throw new ObjectNotFoundException("Teacher does not exist");
		}
		teacherRepository.deleteById(teacherId);
		return true;
	}

	/*
	 * Getting count of total teachers
	 */
	public double findNumberOfTeachers() {
		List<Teacher> list = (List<Teacher>) teacherRepository.findAll();
		return list.size();
	}

	/*
	 * Finding a particular teacher
	 */
	public Teacher findTeacher(Long teacherId) {
		if(!teacherRepository.existsById(teacherId)) {
			return null;
		}
		return teacherRepository.findById(teacherId).get();
	}
}
