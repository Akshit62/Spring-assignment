package com.fabHotels.timetable.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabHotels.timetable.dao.TimeTableRepository;
import com.fabHotels.timetable.exception.ObjectNotFoundException;
import com.fabHotels.timetable.model.Class;
import com.fabHotels.timetable.model.ResultantTimeTable;
import com.fabHotels.timetable.model.Section;
import com.fabHotels.timetable.model.Subject;
import com.fabHotels.timetable.model.Teacher;
import com.fabHotels.timetable.model.TimeTable;
import com.fabHotels.timetable.model.TimeTableIdentity;
import com.fabHotels.timetable.util.TimeTableUtil;

@Service
public class TimeTableService {

	@Autowired
	private SectionService sectionService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private SemesterService semesterService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private ClassService classService;
	@Autowired
	private HolidayService holidayService;

	@Autowired
	private TimeTableRepository timeTableRepository;

	/*
	 * Create timetable for a particular section
	 */
	public boolean createTimeTableForSection(Long sectionId) {
		if(sectionService.getSection(sectionId) == null){
			throw new ObjectNotFoundException("Section does not exist");
		}
		if((1*subjectService.getTotalSubjects())/(teacherService.findNumberOfTeachers()*8) > 1) {
			double requiredTeachers = Math.ceil(1*subjectService.getTotalSubjects()/8);
			throw new  ObjectNotFoundException("Minimum teachers required are "  + requiredTeachers + " to arrange periods"); 
		}
		Set<Subject> subjects = sectionService.getSubjects(sectionId);
		if(subjects.size() == 0) {
			throw new ObjectNotFoundException("Add subjects to the section first");
		}
		List<Teacher> teachers = teacherService.getAllTeachers();

		if(!TimeTableUtil.isTeacherAvailableForSubject(subjects,teachers)) {
			throw new  ObjectNotFoundException("Add teachers for all subjects first!!");
		}
		Map<Subject,Teacher> map = TimeTableUtil.getTeacherSubjectPair(subjects,teachers); 
		for(Subject subject:subjects) {
			Teacher teacher = map.get(subject);
			TimeTable timeTable = new TimeTable(new TimeTableIdentity(sectionId, subject.getSubjectId()),teacher.getTeacherId());
			timeTableRepository.save(timeTable);
		}
		return true;
	}

	/*
	 * Create timetable for a class and its all sections
	 */
	public boolean createTimeTableForClass(Long classId) {
		if(classService.getClass(classId) == null){
			throw new ObjectNotFoundException("class does not exist");
		}
		if((sectionService.findNumberOfSections()*subjectService.getTotalSubjects())/(teacherService.findNumberOfTeachers()*8) > 1) {
			double requiredTeachers = Math.ceil(sectionService.findNumberOfSections()*subjectService.getTotalSubjects()/8);
			throw new  ObjectNotFoundException("Minimum teachers required are "  + requiredTeachers + " to arrange periods"); 
		}

		List<Section> sections = sectionService.getAllSections(classId);
		List<Teacher> teachers = teacherService.getAllTeachers();

		for(Section section: sections){
			Set<Subject> subjects = sectionService.getSubjects(section.getSectionId());
			if(subjects.size() == 0) {
				throw new ObjectNotFoundException("Add subjects for all the sections first");
			}
			if(!TimeTableUtil.isTeacherAvailableForSubject(subjects,teachers)) {
				throw new  ObjectNotFoundException("Add teachers for all subjects first!!");
			}
		}

		for(Section section:sections) {
			Set<Subject> subjects = sectionService.getSubjects(section.getSectionId());
			Map<Subject,Teacher> map = TimeTableUtil.getTeacherSubjectPair(subjects,teachers); 
			for(Subject subject:subjects) {
				Teacher teacher = map.get(subject);
				TimeTable timeTable = new TimeTable(new TimeTableIdentity(section.getSectionId(), subject.getSubjectId()),teacher.getTeacherId());
				timeTableRepository.save(timeTable);
			}
		}
		return true;
	}

	/*
	 * Create timetable for all classes
	 */
	public boolean createTimeTableForSemester(Long semesterId) {
		if(semesterService.getSemester(semesterId) == null){
			throw new ObjectNotFoundException("Semester does not exist");
		}
		if((sectionService.findNumberOfSections()*subjectService.getTotalSubjects())/(teacherService.findNumberOfTeachers()*8) > 1) {
			double requiredTeachers = Math.ceil(sectionService.findNumberOfSections()*subjectService.getTotalSubjects()/8);
			throw new  ObjectNotFoundException("Minimum teachers required are "  + requiredTeachers + " to arrange periods"); 
		}
		//Add handling for error same as above
		List<Class> classes = classService.getClasses();
		List<Teacher> teachers = teacherService.getAllTeachers();
		for(Class class1:classes) {
			List<Section> sections = sectionService.getAllSections(class1.getClassId());
			for(Section section: sections){
				Set<Subject> subjects = sectionService.getSubjects(section.getSectionId());
				if(subjects.size() == 0) {
					throw new ObjectNotFoundException("Add subjects for all the sections first");
				}
				if(!TimeTableUtil.isTeacherAvailableForSubject(subjects,teachers)) {
					throw new  ObjectNotFoundException("Add teachers for all subjects first!!");
				}
			}
		}

		for(Class class1 : classes) {
			List<Section> sections = sectionService.getAllSections(class1.getClassId());
			for(Section section: sections){
				Set<Subject> subjects = sectionService.getSubjects(section.getSectionId());
				Map<Subject,Teacher> map = TimeTableUtil.getTeacherSubjectPair(subjects,teachers); 
				for(Subject subject:subjects) {
					Teacher teacher = map.get(subject);
					TimeTable timeTable = new TimeTable(new TimeTableIdentity(section.getSectionId(), subject.getSubjectId()),teacher.getTeacherId());
					timeTableRepository.save(timeTable);
				}
			}
		}
		return true;
	}

	/*
	 * update timetable for a particular section
	 */
	public boolean updateTimeTableForSection(Long sectionId) {
		if(sectionService.getSection(sectionId) == null){
			throw new ObjectNotFoundException("Section does not exist");
		}
		if((1*subjectService.getTotalSubjects())/(teacherService.findNumberOfTeachers()*8) > 1) {
			double requiredTeachers = Math.ceil(1*subjectService.getTotalSubjects()/8);
			throw new  ObjectNotFoundException("Minimum teachers required are "  + requiredTeachers + " to arrange periods"); 
		}
		Set<Subject> subjects = sectionService.getSubjects(sectionId);
		if(subjects.size() == 0) {
			throw new ObjectNotFoundException("Add subjects to the section first");
		}
		List<Teacher> teachers = teacherService.getAllTeachers();

		if(!TimeTableUtil.isTeacherAvailableForSubject(subjects,teachers)) {
			throw new  ObjectNotFoundException("Add teachers for all subjects first!!");
		}
		Map<Subject,Teacher> map = TimeTableUtil.getTeacherSubjectPair(subjects,teachers); 
		for(Subject subject:subjects) {
			Teacher teacher = map.get(subject);
			TimeTable timeTable = null;
			if(timeTableRepository.existsById(new TimeTableIdentity(sectionId, subject.getSubjectId()))){
				timeTable = timeTableRepository.findById(new TimeTableIdentity(sectionId, subject.getSubjectId())).get();
				timeTable.setTeacherId(teacher.getTeacherId());
			}
			else{
				timeTable = new TimeTable(new TimeTableIdentity(sectionId, subject.getSubjectId()),teacher.getTeacherId());
			}
			timeTableRepository.save(timeTable);
		}
		return true;
	}

	/*
	 * update timetable for a particular class and its all sections
	 */
	public boolean updateTimeTableForClass(Long classId) {
		if(classService.getClass(classId) == null){
			throw new ObjectNotFoundException("class does not exist");
		}
		if((sectionService.findNumberOfSections()*subjectService.getTotalSubjects())/(teacherService.findNumberOfTeachers()*8) > 1) {
			double requiredTeachers = Math.ceil(sectionService.findNumberOfSections()*subjectService.getTotalSubjects()/8);
			throw new  ObjectNotFoundException("Minimum teachers required are "  + requiredTeachers + " to arrange periods"); 
		}

		List<Section> sections = sectionService.getAllSections(classId);
		List<Teacher> teachers = teacherService.getAllTeachers();

		for(Section section: sections){
			Set<Subject> subjects = sectionService.getSubjects(section.getSectionId());
			if(subjects.size() == 0) {
				throw new ObjectNotFoundException("Add subjects for all the sections first");
			}
			if(!TimeTableUtil.isTeacherAvailableForSubject(subjects,teachers)) {
				throw new  ObjectNotFoundException("Add teachers for all subjects first!!");
			}
		}

		for(Section section:sections) {
			Set<Subject> subjects = sectionService.getSubjects(section.getSectionId());
			Map<Subject,Teacher> map = TimeTableUtil.getTeacherSubjectPair(subjects,teachers); 
			for(Subject subject:subjects) {
				Teacher teacher = map.get(subject);
				TimeTable timeTable = null;
				if(timeTableRepository.existsById(new TimeTableIdentity(section.getSectionId(), subject.getSubjectId()))){
					timeTable = timeTableRepository.findById(new TimeTableIdentity(section.getSectionId(), subject.getSubjectId())).get();
					timeTable.setTeacherId(teacher.getTeacherId());
				}
				else{
					timeTable = new TimeTable(new TimeTableIdentity(section.getSectionId(), subject.getSubjectId()),teacher.getTeacherId());
				}
				timeTableRepository.save(timeTable);
			}
		}
		return true;
	}

	/*
	 * update timetable for all classes
	 */
	public boolean updateTimeTableForSemester(Long semesterId) {
		if(semesterService.getSemester(semesterId) == null){
			throw new ObjectNotFoundException("Semester does not exist");
		}
		if((sectionService.findNumberOfSections()*subjectService.getTotalSubjects())/(teacherService.findNumberOfTeachers()*8) > 1) {
			double requiredTeachers = Math.ceil(sectionService.findNumberOfSections()*subjectService.getTotalSubjects()/8);
			throw new  ObjectNotFoundException("Minimum teachers required are "  + requiredTeachers + " to arrange periods"); 
		}
		List<Class> classes = classService.getClasses();
		List<Teacher> teachers = teacherService.getAllTeachers();
		for(Class class1:classes) {
			List<Section> sections = sectionService.getAllSections(class1.getClassId());
			for(Section section: sections){
				Set<Subject> subjects = sectionService.getSubjects(section.getSectionId());
				if(subjects.size() == 0) {
					throw new ObjectNotFoundException("Add subjects for all the sections first");
				}
				if(!TimeTableUtil.isTeacherAvailableForSubject(subjects,teachers)) {
					throw new  ObjectNotFoundException("Add teachers for all subjects first!!");
				}
			}
		}

		for(Class class1 : classes) {
			List<Section> sections = sectionService.getAllSections(class1.getClassId());
			for(Section section: sections){
				Set<Subject> subjects = sectionService.getSubjects(section.getSectionId());
				Map<Subject,Teacher> map = TimeTableUtil.getTeacherSubjectPair(subjects,teachers); 
				for(Subject subject:subjects) {
					Teacher teacher = map.get(subject);
					TimeTable timeTable = null;
					if(timeTableRepository.existsById(new TimeTableIdentity(section.getSectionId(), subject.getSubjectId()))){
						timeTable = timeTableRepository.findById(new TimeTableIdentity(section.getSectionId(), subject.getSubjectId())).get();
						timeTable.setTeacherId(teacher.getTeacherId());
					}
					else{
						timeTable = new TimeTable(new TimeTableIdentity(section.getSectionId(), subject.getSubjectId()),teacher.getTeacherId());
					}
					timeTableRepository.save(timeTable);
				}
			}
		}

		return true;
	}

	/*
	 * delete timetable for a particular section
	 */
	public boolean removeTimeTableForSection(Long sectionId) {
		if(sectionService.getSection(sectionId) == null){
			throw new ObjectNotFoundException("Section does not exist");
		}
		List<TimeTable> tableList = timeTableRepository.findByTimeTableIdentitySectionId(sectionId);
		for(TimeTable timeTable:tableList) {
			timeTableRepository.deleteById(new TimeTableIdentity(sectionId,timeTable.getTimeTableIdentity().getSubjectId()));
		}
		return true;
	}

	/*
	 * remove timetable for a class and its all sections
	 */
	public boolean removeTimeTableForClass(Long classId) {
		if(classService.getClass(classId) == null) {
			throw new ObjectNotFoundException("Class does not exist");
		}
		List<Section> sectionsList = sectionService.getAllSections(classId);
		for(Section section : sectionsList) {
			List<TimeTable> tableList = timeTableRepository.findByTimeTableIdentitySectionId(section.getSectionId());
			for(TimeTable timeTable:tableList) {
				timeTableRepository.deleteById(new TimeTableIdentity(section.getSectionId(),timeTable.getTimeTableIdentity().getSubjectId()));
			}
		}
		return true;
	}

	/*
	 * remove timetable for all classes
	 */
	public boolean removeTimeTableForSemester(Long semesterId) {
		if(semesterService.getSemester(semesterId) == null) {
			throw new ObjectNotFoundException("Semester does not exist");
		}
		timeTableRepository.deleteAll();
		return true;
	}

	/*
	 * Show timetable for a particular section on a particular date
	 */
	public List<ResultantTimeTable> viewTimeTableForSection(Long sectionId,Date date) {
		List<ResultantTimeTable> result = new ArrayList<>();
		if(sectionService.getSection(sectionId) == null){
			throw new ObjectNotFoundException("Section does not exist");
		}
		if(holidayService.checkForHoliday(date)) {
			throw new ObjectNotFoundException("Holiday, Chill Guys");
		}
		List<TimeTable> tableList = timeTableRepository.findByTimeTableIdentitySectionId(sectionId);
		if(tableList.size() == 0) {
			throw new ObjectNotFoundException("Either subjects are not added to class or timetable is not created for this class ");
		}
		List<Long> subjects = new ArrayList<>();
		List<Long> teachers = new ArrayList<>();

		for(TimeTable timeTable : tableList) {
			subjects.add(timeTable.getTimeTableIdentity().getSubjectId());
			teachers.add(timeTable.getTeacherId());
		}
		List<Subject> subjectsList = new ArrayList<>();
		List<Teacher> teachersList = new ArrayList<>();

		for(int i = 0;i < 8;i++) {
			teachersList.add(teacherService.findTeacher(teachers.get(i)));
			subjectsList.add(subjectService.getSubject(subjects.get(i)));
		}
		result = TimeTableUtil.makeTimeTableForSection(teachersList,subjectsList,date);
		return result;
	}

	/*
	 * show timetable for a particular teacher on a particular date
	 */
	public List<String> viewTimeTableForClass(Long teacherId,Date date) {
		List<String> list = new ArrayList<>();
		if(teacherService.findTeacher(teacherId) == null) {
			throw new ObjectNotFoundException("Teacher does not exist");
		}
		if(holidayService.checkForHoliday(date)) {
			throw new ObjectNotFoundException("Holiday, Chill guys");
		}
		List<TimeTable> tableList = timeTableRepository.findByTeacherId(teacherId);
		if(tableList.size() == 0) {
			throw new ObjectNotFoundException("This teacher is not yet allocated any period");
		}
		List<Section> sections = new ArrayList<>();
		for(TimeTable timeTable : tableList) {
			sections.add(sectionService.getSection(timeTable.getTimeTableIdentity().getSectionId()));
		}
		list = TimeTableUtil.makeTimeTableForTeacher(sections,date);
		return list;
	}

}
