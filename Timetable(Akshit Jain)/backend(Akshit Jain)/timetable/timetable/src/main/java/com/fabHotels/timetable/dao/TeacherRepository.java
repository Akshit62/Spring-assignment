package com.fabHotels.timetable.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fabHotels.timetable.model.Subject;
import com.fabHotels.timetable.model.Teacher;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long> {

	List<Teacher> findBySubjectId(Subject subjectId);
	
}
