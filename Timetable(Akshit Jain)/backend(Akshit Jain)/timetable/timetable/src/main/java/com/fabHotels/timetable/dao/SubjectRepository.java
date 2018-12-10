package com.fabHotels.timetable.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fabHotels.timetable.model.Subject;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, Long>{

	Subject findBySubjectName(String subjectName);
	
}
