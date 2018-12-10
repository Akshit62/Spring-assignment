package com.fabHotels.timetable.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class TimeTableIdentity implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long sectionId;
	
	private Long subjectId;

	public TimeTableIdentity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TimeTableIdentity(Long sectionId, Long subjectId) {
		super();
		this.sectionId = sectionId;
		this.subjectId = subjectId;
	}

	public Long getSectionId() {
		return sectionId;
	}

	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sectionId == null) ? 0 : sectionId.hashCode());
		result = prime * result + ((subjectId == null) ? 0 : subjectId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TimeTableIdentity other = (TimeTableIdentity) obj;
		if (sectionId == null) {
			if (other.sectionId != null)
				return false;
		} else if (!sectionId.equals(other.sectionId))
			return false;
		if (subjectId == null) {
			if (other.subjectId != null)
				return false;
		} else if (!subjectId.equals(other.subjectId))
			return false;
		return true;
	}


	
}
