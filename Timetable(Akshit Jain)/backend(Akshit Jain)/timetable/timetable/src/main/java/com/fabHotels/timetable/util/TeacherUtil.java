package com.fabHotels.timetable.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fabHotels.timetable.model.Pool;
import com.fabHotels.timetable.model.Teacher;

public class TeacherUtil {

	public static List<Teacher> getTeachersForSubjectId(Long id,List<Teacher> list) {
		List<Teacher> result = new ArrayList<>();
		for(Teacher teacher: list) {
			if(teacher.getSubjectId().equals(id)) {
				result.add(teacher);
			}
		}
		return result;
	}
	
	public static Map<String,List<Teacher>> getTeachersByCourse(List<Teacher> list) {
		Map<String,List<Teacher>> map = new HashMap<>();
		for(Pool course : Pool.values()) {
			List<Teacher> temp = new ArrayList<>();
			for(Teacher teacher : list) {
				if(teacher.getPoolName().equals(course)) {
					temp.add(teacher);
				}
			}
			map.put(course.toString(),temp);
		}
		return map;
	}
}
