package com.fabHotels.timetable.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.fabHotels.timetable.dao.SubjectRepository;
import com.fabHotels.timetable.exception.CustomDataIntegrityException;
import com.fabHotels.timetable.model.Subject;
import com.fabHotels.timetable.model.Teacher;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class SubjectService {

	@Autowired
	private SubjectRepository subjectRepository;

	@Autowired
	private TeacherService teacherService;
	/*
	 * Creating a subject
	 */
	public Subject addSubject(Subject subject1) throws ObjectNotFoundException{
		try{
			if(subjectRepository.findBySubjectName(subject1.getSubjectName()) != null) {
				throw new ObjectNotFoundException("Subject already exists");
			}
			subjectRepository.save(subject1);
		}
		catch(DataIntegrityViolationException exception) {
			throw new CustomDataIntegrityException("Fields cannot be null");
		}
		return subject1;
	}

	/*
	 * Fetching all subjects
	 */
	public List<Subject> getSubjects() {
		List<Subject> list = (List<Subject>) subjectRepository.findAll();
		return list;
	}

	/*
	 * Fetching a particular subject
	 */
	public Subject getSubject(Long id) {
		Optional<Subject> subject1 = null;
		subject1 = subjectRepository.findById(id);
		return subject1.get();
	}

	/*
	 * Removing a particular subject
	 */
	public boolean removeSubject(Long id) {
		try{
			if(!subjectRepository.existsById(id)) {
				throw new ObjectNotFoundException("Subject does not exist");
			}
			List<Teacher> teachers = teacherService.getTeachersBySubject(id);
			for(Teacher teacher : teachers) {
				teacherService.removeTeacher(id, teacher.getTeacherId());
			}
			subjectRepository.deleteById(id);
			return true;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	/*
	 * Getting count of total subjects
	 */
	public double getTotalSubjects() {
		List<Subject> list = (List<Subject>) subjectRepository.findAll();
		return list.size();
	}
}
