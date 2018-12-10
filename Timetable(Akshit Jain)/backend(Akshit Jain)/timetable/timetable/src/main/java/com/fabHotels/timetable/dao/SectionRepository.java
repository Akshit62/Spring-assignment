package com.fabHotels.timetable.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fabHotels.timetable.model.Section;
import com.fabHotels.timetable.model.Class;

@Repository
public interface SectionRepository extends CrudRepository<Section,Long>{
	
	Section findBySectionName(String sectionName);
	List<Section> findByClassId(Class classId);
 	
}
