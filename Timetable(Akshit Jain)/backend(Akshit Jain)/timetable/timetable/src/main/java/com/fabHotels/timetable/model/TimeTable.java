package com.fabHotels.timetable.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name = "timeschedule")
public class TimeTable {

	 @EmbeddedId
	 private TimeTableIdentity timeTableIdentity;
	 
	 @Column(nullable = false)
	 private Long teacherId;

	public TimeTable() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TimeTable(TimeTableIdentity timeTableIdentity, Long teacherId) {
		super();
		this.timeTableIdentity = timeTableIdentity;
		this.teacherId = teacherId;
	}

	public TimeTableIdentity getTimeTableIdentity() {
		return timeTableIdentity;
	}

	public void setTimeTableIdentity(TimeTableIdentity timeTableIdentity) {
		this.timeTableIdentity = timeTableIdentity;
	}

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	 
}
