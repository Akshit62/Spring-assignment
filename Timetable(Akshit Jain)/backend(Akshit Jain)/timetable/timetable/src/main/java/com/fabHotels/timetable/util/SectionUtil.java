package com.fabHotels.timetable.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fabHotels.timetable.model.Subject;

public class SectionUtil {

	public static boolean isContainingAllSubjects(List<Subject> actualList,List<Subject> expectedList) {
		List<String> actual = new ArrayList<>();
		List<String> expected = new ArrayList<>();
		
		for(int i = 0;i < 8;i++) {
			actual.add(actualList.get(i).getSubjectName());
			expected.add(expectedList.get(i).getSubjectName());
		}
		
		Collections.sort(actual);
		Collections.sort(expected);
		
		for(int i = 0;i < 8;i++) {
			if(!actual.get(i).equals(expected.get(i))){
				return false;
			}
		}
		return true;
	}
}
