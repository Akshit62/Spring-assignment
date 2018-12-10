package com.fabHotels.timetable.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "holiday")
public class Holiday {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long holidayId;
	
	@Column(nullable = false)
	private String holidayName;
	
	@Column(nullable = false)
	private Date holidayDate;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "semesterid", nullable = false)
	private Semester semesterId;
	
	
	public Holiday() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getHolidayId() {
		return holidayId;
	}

	public void setHolidayId(Long holidayId) {
		this.holidayId = holidayId;
	}

	public String getHolidayName() {
		return holidayName;
	}

	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}

	public Date getHolidayDate() {
		return holidayDate;
	}

	public void setHolidayDate(Date holidayDate) {
		this.holidayDate = holidayDate;
	}

	public Semester getSemesterId() {
		return semesterId;
	}

	public void setSemesterId(Semester semesterId) {
		this.semesterId = semesterId;
	}
	
	
	
}
