package com.fabHotels.timetable.model;

public class ResultantTimeTable {

	private String subjectName;
	private String teacherName;
	
	
	public ResultantTimeTable() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ResultantTimeTable(String subjectName, String teacherName) {
		super();
		this.subjectName = subjectName;
		this.teacherName = teacherName;
	}

	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	
	
}
