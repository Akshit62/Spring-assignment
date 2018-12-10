package com.fabHotels.timetable.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.fabHotels.timetable.dao.ClassRepository;
import com.fabHotels.timetable.exception.CustomDataIntegrityException;
import com.fabHotels.timetable.exception.ObjectNotFoundException;
import com.fabHotels.timetable.model.Class;
import com.fabHotels.timetable.model.Section;

@Service
public class ClassService {

	@Autowired
	private ClassRepository classRepository;

	@Autowired
	private SectionService sectionService;
	/*
	 * Creating a class
	 */
	public Class addClass(Class class1){
		try{
			if(classRepository.findByClassName(class1.getClassName()) != null) {
				throw new ObjectNotFoundException("Class Already exists");
			}
			classRepository.save(class1);
		}
		catch(DataIntegrityViolationException exception) {
			throw new CustomDataIntegrityException("Fields cannot be null");
		}
		return class1;
	}

	/*
	 * Fetching all classes
	 */
	public List<Class> getClasses() {
		List<Class> list = (List<Class>) classRepository.findAll();
		return list;
	}

	/*
	 * Fetching a particular class
	 */
	public Class getClass(Long id) {
		Optional<Class> class1 = null;
		if(classRepository.findById(id) == null) {
			throw new ObjectNotFoundException("Class Does not exist");
		}
		class1 = classRepository.findById(id);
		return class1.get();
	}

	/*
	 * Removing a particular class and its associated sections
	 */
	public boolean removeClass(Long id) {
		if(!classRepository.existsById(id)) {
			throw new ObjectNotFoundException("Class Does not exist");
		}
		List<Section> sections = sectionService.getAllSections(id);
		for(Section section : sections) {
			sectionService.removeSection(id, section.getSectionId());
		}
		classRepository.deleteById(id);
		return true;
	}

}
