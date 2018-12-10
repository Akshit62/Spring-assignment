package com.fabHotels.timetable.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fabHotels.timetable.model.TimeTable;
import com.fabHotels.timetable.model.TimeTableIdentity;

@Repository
public interface TimeTableRepository extends CrudRepository<TimeTable, TimeTableIdentity> {

	 List<TimeTable> findByTimeTableIdentitySectionId(Long sectionId);
	 List<TimeTable> findByTeacherId(Long teacherId);
}
