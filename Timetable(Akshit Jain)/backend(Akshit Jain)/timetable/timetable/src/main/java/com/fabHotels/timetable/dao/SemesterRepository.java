package com.fabHotels.timetable.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fabHotels.timetable.model.Semester;

@Repository
public interface SemesterRepository extends CrudRepository<Semester, Long>{

}
