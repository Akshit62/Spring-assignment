package com.fabHotels.timetable.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "class")
public class Class {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long classId;
	
	@Column(nullable = false)
	private String className;
	
	public Class() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getClassId() {
		return classId;
	}
	public void setClassId(Long classId) {
		this.classId = classId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
}
