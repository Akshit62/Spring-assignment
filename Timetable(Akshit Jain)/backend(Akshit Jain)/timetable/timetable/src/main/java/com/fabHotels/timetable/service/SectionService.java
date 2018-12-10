package com.fabHotels.timetable.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.fabHotels.timetable.dao.SectionRepository;
import com.fabHotels.timetable.exception.CustomDataIntegrityException;
import com.fabHotels.timetable.exception.ObjectNotFoundException;
import com.fabHotels.timetable.model.Class;
import com.fabHotels.timetable.model.Section;
import com.fabHotels.timetable.model.Subject;
import com.fabHotels.timetable.util.Constants;
import com.fabHotels.timetable.util.SectionUtil;

@Service
public class SectionService {

	@Autowired
	private ClassService classService;

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private SectionRepository sectionRepository;

	/*
	 * Creating a section for a particular class
	 */
	public Section addSection(Long classId,Section section) {
		try{
			if(classService.getClass(classId) == null) {
				throw new ObjectNotFoundException("Class does not exist");
			}
			if(sectionRepository.findBySectionName(section.getSectionName()) != null) {
				throw new ObjectNotFoundException("Section already exists for this class");
			}
			Class class1 = classService.getClass(classId);
			section.setClassId(class1);
			sectionRepository.save(section);
		}
		catch(DataIntegrityViolationException exception) {
			throw new CustomDataIntegrityException("Fields cannot be null");
		}
		return section;
	}

	/*
	 * Getting all sections of a particular class
	 */
	public List<Section> getAllSections(Long classId){
		List<Section> list = new ArrayList<>();
		if(classService.getClass(classId) == null) {
			throw new ObjectNotFoundException("Class does not exist");
		}
		Class class1 = classService.getClass(classId);
		list = sectionRepository.findByClassId(class1);
		return list;
	}

	/*
	 * Removing section of a particular class
	 */
	public boolean removeSection(Long classId,Long sectionId){
		if(classService.getClass(classId) == null) {
			throw new ObjectNotFoundException("Class does not exist");
		}
		if(!sectionRepository.existsById(sectionId)) {
			throw new ObjectNotFoundException("Section does not exist");
		}
		sectionRepository.deleteById(sectionId);
		return true;
	}

	/*
	 * Getting a particular section
	 */
	public Section getSection(Long sectionId) {
		Section section = sectionRepository.findById(sectionId).get();
		if(section == null) return null;
		else return section;
	}

	/*
	 * Adding subjects to a particular section
	 */
	public List<Subject> addSubjects(List<Subject> subjectsList,Long sectionId) {
		if(!sectionRepository.existsById(sectionId)) {
			throw new ObjectNotFoundException("Section does not exist");
		}
		if(subjectsList.size() != Constants.NUMBER_OF_PERIODS) {
			throw new ObjectNotFoundException("8 subjects should be added only");
		}
		List<Subject> subjects = subjectService.getSubjects();

		//Check if Subjects table contains all subjects contained in subjectList and no duplicates are present in subjectsList
		if(!SectionUtil.isContainingAllSubjects(subjects,subjectsList)) {
			throw new ObjectNotFoundException("One or more subjects does not exist.Please add them first");
		}

		Section section = sectionRepository.findById(sectionId).get();
		for(Subject subject : subjectsList) {
			section.getSubjects().add(subject);
			subject.getSections().add(section);
		}
		sectionRepository.save(section);
		return subjectsList;
	}

	/*
	 * Getting the eight subjects associated with that section
	 */
	public Set<Subject> getSubjects(Long sectionId) {
		Set<Subject> tset = new TreeSet<>();
		Section section = sectionRepository.findById(sectionId).get();
		Set<Subject> subject = section.getSubjects();
		tset = new TreeSet<>(subject);
		return tset;
	}

	/*
	 * Removing the eight subjects associated with section
	 */
	public boolean removeSubjects(Long sectionId) {
		if(!sectionRepository.existsById(sectionId)) {
			throw new ObjectNotFoundException("Section does not exist");
		}
		Section section = sectionRepository.findById(sectionId).get();
		Set<Subject> subjects = section.getSubjects();
		for(Subject subject :subjects) {
			if(subject.getSections().contains(section)){
				subject.getSections().remove(section);
			}
		}
		section.setSubjects(new HashSet<Subject>());
		sectionRepository.save(section);
		return true;
	}

	/*
	 * Count of total number of sections
	 */
	public double findNumberOfSections() {
		List<Section> list = (List<Section>) sectionRepository.findAll();
		return list.size();
	}

}
