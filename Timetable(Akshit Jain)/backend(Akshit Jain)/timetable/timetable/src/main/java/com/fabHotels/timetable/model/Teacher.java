package com.fabHotels.timetable.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "teacher")
public class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long teacherId;
	
	@Column(nullable = false)
	private String teacherName;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "subjectId", nullable = false)
	private Subject subjectId;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Pool poolName;

	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Subject getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Subject subjectId) {
		this.subjectId = subjectId;
	}

	public Pool getPoolName() {
		return poolName;
	}

	public void setPoolName(Pool poolName) {
		this.poolName = poolName;
	}

	
	
	
}
