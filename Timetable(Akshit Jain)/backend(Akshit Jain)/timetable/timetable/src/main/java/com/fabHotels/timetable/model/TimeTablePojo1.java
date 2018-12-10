package com.fabHotels.timetable.model;

import java.util.Date;

public class TimeTablePojo1 {
	
	private Long teacherId;
	private Date date;
	
	
	public TimeTablePojo1() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TimeTablePojo1(Long teacherId, Date date) {
		super();
		this.teacherId = teacherId;
		this.date = date;
	}
	public Long getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	
}
