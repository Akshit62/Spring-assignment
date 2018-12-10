package com.fabHotels.timetable.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fabHotels.timetable.model.Holiday;
import com.fabHotels.timetable.model.Semester;

@Repository
public interface HolidayRepository extends CrudRepository<Holiday, Long> {

	Holiday getByHolidayDate(Date holidayDate);
	List<Holiday> findBySemesterId(Semester semesterId);
}
