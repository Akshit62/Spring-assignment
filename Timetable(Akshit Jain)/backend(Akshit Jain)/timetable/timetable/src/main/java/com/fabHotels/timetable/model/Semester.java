package com.fabHotels.timetable.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "semester")
public class Semester {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long semesterId;
	
	@Column(nullable = false)
	private String semesterName;
	
	@Column(nullable = false)
	private Date startOfSem;
	
	private Date endOfSem;
	
	@Column(nullable = false)
	private boolean isSaturdayWorking;

	
	public Semester() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getSemesterId() {
		return semesterId;
	}

	public void setSemesterId(Long semesterId) {
		this.semesterId = semesterId;
	}

	public String getSemesterName() {
		return semesterName;
	}

	public void setSemesterName(String semesterName) {
		this.semesterName = semesterName;
	}

	public Date getStartOfSem() {
		return startOfSem;
	}

	public void setStartOfSem(Date startOfSem) {
		this.startOfSem = startOfSem;
	}

	public Date getEndOfSem() {
		return endOfSem;
	}

	public void setEndOfSem(Date endOfSem) {
		this.endOfSem = endOfSem;
	}

	public boolean isSaturdayWorking() {
		return isSaturdayWorking;
	}

	public void setSaturdayWorking(boolean isSaturdayWorking) {
		this.isSaturdayWorking = isSaturdayWorking;
	}
	
	
		
}
