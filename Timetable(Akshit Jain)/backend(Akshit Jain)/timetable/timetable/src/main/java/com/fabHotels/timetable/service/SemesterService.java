package com.fabHotels.timetable.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.fabHotels.timetable.dao.SemesterRepository;
import com.fabHotels.timetable.exception.CustomDataIntegrityException;
import com.fabHotels.timetable.exception.ObjectNotFoundException;
import com.fabHotels.timetable.model.Holiday;
import com.fabHotels.timetable.model.Semester;
import com.fabHotels.timetable.util.SemesterUtil;

@Service
public class SemesterService {

	@Autowired
	private SemesterRepository semesterRepository;

	@Autowired
	private HolidayService holidayService;
	/*
	 * Creating Semester
	 */
	public Semester addSemester(Semester semester){
		try{
			if(!SemesterUtil.isStartOfSemValid(semester.getStartOfSem())){
				throw new ObjectNotFoundException("Semester start date is wrong");
			}
			semester.setEndOfSem(SemesterUtil.setEndOfSem(semester.getStartOfSem()));
			semesterRepository.save(semester);
		}
		catch(DataIntegrityViolationException exception) {
			throw new CustomDataIntegrityException("Fields cannot be null");
		}
		return semester;
	}

	/*
	 * Updating Semester
	 */
	public Semester updateSemester(Semester semester) {
		if(!semesterRepository.existsById(semester.getSemesterId())) {
			throw new ObjectNotFoundException("Semester does not Exist");
		}
		semesterRepository.save(semester);
		return semester;
	}

	/*
	 *  Fetch all semesters
	 */
	public List<Semester> getAllSemesters() {
		List<Semester> semesters = (List<Semester>) semesterRepository.findAll();
		return semesters;
	}

	/*
	 * Get a particular semester
	 */
	public Semester getSemester(Long id) {

		if(semesterRepository.findById(id) == null) {
			throw new ObjectNotFoundException("Semester does not Exist");
		}
		Optional<Semester> semester = semesterRepository.findById(id);

		return semester.get();
	}

	/*
	 * Remove a particular semester and its associated holidays 
	 */
	public boolean removeSemester(Long id) {
		if(!semesterRepository.existsById(id)) {
			throw new ObjectNotFoundException("Semester does not Exist");
		}
		List<Holiday> holidays = holidayService.getHolidaysBySemesterId(id);
		for(Holiday holiday : holidays) {
			holidayService.removeHoliday(id, holiday.getHolidayId());
		}
		semesterRepository.deleteById(id);
		return true;
	}
}
