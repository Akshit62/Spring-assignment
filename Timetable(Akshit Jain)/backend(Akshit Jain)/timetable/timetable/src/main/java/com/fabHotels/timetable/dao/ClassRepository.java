package com.fabHotels.timetable.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.fabHotels.timetable.model.Class;

@Repository
public interface ClassRepository extends CrudRepository<Class, Long> {

	Class findByClassName(String className);
	
}
