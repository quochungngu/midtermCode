package com.cisc181.core;

import java.util.UUID;

public class Enrollment {
	
	private UUID SectionID;
	private UUID StudentID;
	private UUID EnrollmentID;
	private double Grade;
	
	private Enrollment() {
		
	}
	
	public Enrollment(UUID StudentID, UUID SectionID) {
		this.SectionID = SectionID;
		this.StudentID = StudentID;
		
		
		EnrollmentID = UUID.randomUUID();
	}
	
	public double getGrade() {
		return Grade;
	}

	public void SetGrade(double grade) { // done by hand
		this.Grade = grade;
	}

	public UUID getSectionID() {
		return SectionID;
	}

	public void setSectionID(UUID sectionID) {
		SectionID = sectionID;
	}

	public UUID getStudentID() {
		return StudentID;
	}

	public void setStudentID(UUID studentID) {
		StudentID = studentID;
	}

	public UUID getEnrollmentID() {
		return EnrollmentID;
	}

	public void setEnrollmentID(UUID enrollmentID) {
		EnrollmentID = enrollmentID;
	}
	
}
