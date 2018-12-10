package com.fabHotels.timetable.model;

import java.util.Date;

public class TimeTablePojo {

	private Long sectionId;
	private Date date;
	public Long getSectionId() {
		return sectionId;
	}
	
	
	public TimeTablePojo() {
		super();
		// TODO Auto-generated constructor stub
	}


	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
