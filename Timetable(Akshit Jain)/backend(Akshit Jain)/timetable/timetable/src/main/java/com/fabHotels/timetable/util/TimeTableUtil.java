package com.fabHotels.timetable.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.fabHotels.timetable.model.ResultantTimeTable;
import com.fabHotels.timetable.model.Section;
import com.fabHotels.timetable.model.Subject;
import com.fabHotels.timetable.model.Teacher;

public class TimeTableUtil {

	private static Map<Long,Integer> teacherCount = new HashMap<>();
	
	public static boolean isTeacherAvailableForSubject(Set<Subject> subjects,List<Teacher> teachers) {
			for(Subject subject : subjects) {
				boolean flag = false;
				for(Teacher teacher :teachers) {
					if(teacher.getSubjectId().equals(subject)) {
						flag = true;
						break;
					}
				}
				if(flag == false) return false;
			}
			return true;
	}
	
	public static Map<Subject,Teacher> getTeacherSubjectPair(Set<Subject> subjects,List<Teacher> teachers) {
			Map<Subject,Teacher> map = new HashMap<>();
			List<List<Teacher>> list = new ArrayList<>();
			List<Subject> mapSubjects = new ArrayList<>(new TreeSet<Subject>(subjects));
			
			for(Subject subject : mapSubjects) {
				List<Teacher> temp = new ArrayList<>();
				for(Teacher teacher : teachers) {
					if(teacher.getSubjectId().equals(subject.getSubjectId())){
						temp.add(teacher);
					}
				}
				list.add(temp);
			}
			
			for(int i = 0;i < list.size();i++) {
				int teacherWithMinSubjectsAllocated = Integer.MAX_VALUE;
				int indexOfMinTeacher = -1;
				boolean flag = false;
				for(int j = 0;j < list.get(i).size();j++) {
					if(!teacherCount.containsKey(list.get(i).get(j))) {
						teacherCount.put(list.get(i).get(j).getTeacherId(),1);
						map.put(mapSubjects.get(i),list.get(i).get(j));
						flag = true;
						break;
					}
					else{
						int value = teacherCount.get(list.get(i).get(j).getTeacherId());
						if(value < teacherWithMinSubjectsAllocated) {
							teacherWithMinSubjectsAllocated = value;
							indexOfMinTeacher = j;
						}
					}
				}
				if(flag == false) {
				map.put(mapSubjects.get(i),list.get(i).get(indexOfMinTeacher));
				}
			}
			return map;
	}
	
	public static List<ResultantTimeTable> makeTimeTableForSection(List<Teacher> teachers,List<Subject> subjects,Date date) {
		List<ResultantTimeTable> result = new ArrayList<>();

		int index = findOrderOfAllocation(date);
		for(int i = index; i < 8; i++){
			result.add(new ResultantTimeTable(subjects.get(i).getSubjectName(), teachers.get(i).getTeacherName()));
		}
		for(int i = 0;i < index;i++) {
			result.add(new ResultantTimeTable(subjects.get(i).getSubjectName(), teachers.get(i).getTeacherName()));
		}
		return result;
	}
	
	public static List<String> makeTimeTableForTeacher(List<Section> sections,Date date) {
		List<String> result = new ArrayList<>();
		int index = findOrderOfAllocation(date);
		if(sections.size() < index){
			for(int i = 0;i < sections.size();i++) {
				result.add(sections.get(i).getSectionName());
			}
		}
		else {
			for(int i = index;i < sections.size();i++) {
				result.add(sections.get(i).getSectionName());
			}
			for(int i = 0;i < index;i++) {
				result.add(sections.get(i).getSectionName());
			}
		}
		return result;
	}
	
	public static int findOrderOfAllocation(Date date) {
		Calendar c1=Calendar.getInstance();
		c1.setTime(date);
		int index = -1;
		switch(c1.get(Calendar.DAY_OF_WEEK)){
			case Calendar.MONDAY:
				index = 0;
				break;
			case Calendar.TUESDAY:
				index = 1;
				break;
			case Calendar.WEDNESDAY:
				index = 2;
				break;
			case Calendar.THURSDAY:
				index = 3;
				break;
			case Calendar.FRIDAY:
				index = 4;
				break;
			case Calendar.SATURDAY:
				index = 5;
				break;	
			default :
				index = 6;
		}
		return index;
	}
}
