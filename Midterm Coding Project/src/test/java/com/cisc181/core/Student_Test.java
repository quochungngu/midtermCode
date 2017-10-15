package com.cisc181.core;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.cisc181.eNums.eMajor;

public class Student_Test {

	private static ArrayList<Course> course;
	private static ArrayList<Semester> semester;
	private static ArrayList<Section> section;
	private static ArrayList<Student> student;
	private static ArrayList<Enrollment> enroll;


	@BeforeClass
	public static void setup() {
		course = new ArrayList();
		semester = new ArrayList();
		section = new ArrayList();
		student = new ArrayList();
		enroll = new ArrayList();

		course.add(new Course(UUID.randomUUID(), "LrnBoutMonies", 0, eMajor.BUSINESS));
		course.add(new Course(UUID.randomUUID(), "BeepBoopBop", 0, eMajor.COMPSI));
		course.add(new Course(UUID.randomUUID(), "KAAAABOOOM", 0, eMajor.CHEM));

		try {
			semester.add(new Semester(UUID.randomUUID(), new SimpleDateFormat("dd/MM/yyyy").parse("06/02/2017")
					, new SimpleDateFormat("dd/MM/yyyy").parse("24/05/2017")));
			semester.add(new Semester(UUID.randomUUID(), new SimpleDateFormat("dd/MM/yyyy").parse("29/08/2017")
					, new SimpleDateFormat("dd/MM/yyyy").parse("15/12/2017")));

			for(int i = 0; i<10; i++) {
				// They were all born from test tubes as 18-year olds in 2015
				student.add(new Student("HarryClone", "HP", "Pooper", 
						new SimpleDateFormat("dd/MM/yyyy").parse("06/02/2015"), eMajor.CHEM,
						"British Lane", "(333)-444-5555", "fangirlssuck@britishemail.com"));
			}

		}
		catch (ParseException e) {
			System.out.println("ParseException");
		}
		catch (PersonException e) {
			System.out.println("PersonException");
		}
		
		// create 6 sections with course and semester IDs
		// Every 3rd section index is a new course; indices 0 to 1 and indices 2 to 3
		// are different courses
		for (int courseIndex = 0; courseIndex < course.size(); courseIndex++) {
			for (int semesterIndex = 0; semesterIndex < semester.size(); semesterIndex++) {
				section.add(new Section(course.get(courseIndex).getCourseID(),
						semester.get(semesterIndex).getSemesterID(), UUID.randomUUID(), 100));
			}
		}
		
		// add 60 enrollments with unique section and student IDs
		// Same student every 11th enroll index; index 0 and 10 are same student
		// DIFFERENT section every 11th enroll index; indices 0 to 9 and indices 10 to 19
		// are different sections
		for (int sectionIndex = 0; sectionIndex < section.size(); sectionIndex++) {
			for (int studentIndex = 0; studentIndex < student.size(); studentIndex++) {
				enroll.add(new Enrollment(student.get(studentIndex).getStudentID(),
						section.get(sectionIndex).getSectionID()));
			}
		}
		
		// add grades to enroll
		for(int enrollIndex = 0; enrollIndex < enroll.size(); enrollIndex++) {
			enroll.get(enrollIndex).SetGrade((double) (enrollIndex+30));
			// grade assigned is enrollIndex + 30
			// lowest grade = 30; highest grade = 89;
		}
		
		System.out.println("");
	}

	@Test
	public void studentGPATest() {
		double[] studentGPA = new double[student.size()];
		
		for(int studentIndex = 0; studentIndex < student.size(); studentIndex++) {
			
			int sectionIndex = 0;
			double totalGPA = 0;

			System.out.print("Student "+studentIndex+": ");
			
			for (int enrollIndicesForStudent = 0+studentIndex; 
					enrollIndicesForStudent < enroll.size(); enrollIndicesForStudent += 10) {
				// enrollsIndicesForStudent +=10 because every 11th index is the same student
				// so index 0 and 10 are the same student
				double GPA = 0;
				
				
				// The 2 following asserts is just to show that the GPA
				// is calculated using the right student and section
				assertEquals(enroll.get(enrollIndicesForStudent).getStudentID(),
						student.get(studentIndex).getStudentID());
				assertEquals(enroll.get(enrollIndicesForStudent).getSectionID(),
						section.get(sectionIndex).getSectionID());
				
				sectionIndex++;
				
				System.out.print(enroll.get(enrollIndicesForStudent).getGrade()+" ");
				/* Standard GPA for grades; converts grade to GPA
				 * Grades for each student will be printed in console using the command above.
				 * That'll help you verify.
				 * F = 0.0: 	grade < 65
				 * D = 1.0:		 65 <= grade < 67
				 * D+ = 1.3:	 67 <= grade < 70
				 * C- = 1.7:	 70 <= grade < 73
				 * C = 2.0: 	73 <= grade < 77
				 * C- = 2.3: 	77 <= grade < 80
				 * B- = 2.7: 	80 <= grade < 83
				 * B = 3.0: 	83 <= grade < 87
				 * B+ = 3.3: 	87 <= grade < 90
				 * A- = 3.7: 	90 <= grade < 93
				 * A = 4.0: 	93 <= grade
				 */
				if (enroll.get(enrollIndicesForStudent).getGrade() < 65.00)
					GPA = 0.0;
				else if (enroll.get(enrollIndicesForStudent).getGrade() >= 65.00 &&
						enroll.get(enrollIndicesForStudent).getGrade() < 67.00)
					GPA = 1.0;
				else if (enroll.get(enrollIndicesForStudent).getGrade() >= 67.00 &&
						enroll.get(enrollIndicesForStudent).getGrade() < 70.00)
					GPA = 1.3;
				else if (enroll.get(enrollIndicesForStudent).getGrade() >= 70.00 &&
						enroll.get(enrollIndicesForStudent).getGrade() < 73.00)
					GPA = 1.7;
				else if (enroll.get(enrollIndicesForStudent).getGrade() >= 73.00 &&
						enroll.get(enrollIndicesForStudent).getGrade() < 77.00)
					GPA = 2.0;
				else if (enroll.get(enrollIndicesForStudent).getGrade() >= 77.00 &&
						enroll.get(enrollIndicesForStudent).getGrade() < 80.00)
					GPA = 2.3;
				else if (enroll.get(enrollIndicesForStudent).getGrade() >= 80.00 &&
						enroll.get(enrollIndicesForStudent).getGrade() < 83.00)
					GPA = 2.7;
				else if (enroll.get(enrollIndicesForStudent).getGrade() >= 83.00 &&
						enroll.get(enrollIndicesForStudent).getGrade() < 87.00)
					GPA = 3.0;
				else if (enroll.get(enrollIndicesForStudent).getGrade() >= 87.00 &&
						enroll.get(enrollIndicesForStudent).getGrade() < 90.00)
					GPA = 3.3;
				else if (enroll.get(enrollIndicesForStudent).getGrade() >= 90.00 &&
						enroll.get(enrollIndicesForStudent).getGrade() < 93.00)
					GPA = 3.7;
				else if (enroll.get(enrollIndicesForStudent).getGrade() >= 93.00)
					GPA = 4.0;
				else
					System.out.println("What the heck?");
				
				totalGPA += GPA;
			}
			System.out.println("");
			// assume classes have equal credits
			studentGPA[studentIndex] = totalGPA/section.size();
		}
		
		assertEquals(studentGPA[0], 0.73, 0.1);
		assertEquals(studentGPA[1], 0.73, 0.1);
		assertEquals(studentGPA[2], 0.73, 0.1);
		assertEquals(studentGPA[3], 0.83, 0.1);
		assertEquals(studentGPA[4], 0.83, 0.1);
		assertEquals(studentGPA[5], 1.0, 0.1);
		assertEquals(studentGPA[6], 1.0, 0.1);
		assertEquals(studentGPA[7], 1.15, 0.1);
		assertEquals(studentGPA[8], 1.15, 0.1);
		assertEquals(studentGPA[9], 1.15, 0.1);
		System.out.println("");
	}

	@Test
	public void courseAverageTest() {
		double[] courseAverageGrades = new double[course.size()];
		int sectionIndex = 0;

		for (int courseIndex = 0; courseIndex < course.size(); courseIndex++) {
			double totalCourseGrade = 0;
			System.out.print("Course "+courseIndex+": ");

			for (int semesterIndex = 0; semesterIndex < semester.size(); semesterIndex++) {
				for (int enrollIndicesForSection = student.size()*sectionIndex; 
						enrollIndicesForSection < student.size()*(sectionIndex+1); 
						enrollIndicesForSection++) {
					/*enrollIndicesForSection < student.size() because the sections are
					 * indices of enroll are group by sections with "x" number of students
					 * so indices 0 to 9 are the same section and 10 to 19 are another section
					 * 
					 * int enrollIndicesForSection = student.size()*sectionIndex
					 * because of the reason above
					 */


					/*The first assert makes sure enroll and section has the same SectionID
					 * The second assert makes sure that the section with that SectionID
					 * has the same CourseID as course
					 * The third assert does what the 2nd assert does but with semesterID
					 * and semester
					 */
					assertEquals(enroll.get(enrollIndicesForSection).getSectionID(),
							section.get(sectionIndex).getSectionID());
					assertEquals(section.get(sectionIndex).getCourseID(),
							course.get(courseIndex).getCourseID());
					assertEquals(section.get(sectionIndex).getSemesterID(),
							semester.get(semesterIndex).getSemesterID());

					totalCourseGrade += enroll.get(enrollIndicesForSection).getGrade();
					System.out.print(enroll.get(enrollIndicesForSection).getGrade()+"; ");

				}
				
				sectionIndex++;
			}
			// Sections in the groups of section indices 0/1, 2/3, and 4/5 are the same course
			System.out.println("");
			double averageCourseGrade = totalCourseGrade/(student.size()*semester.size());
			courseAverageGrades[courseIndex] = averageCourseGrade;
		}

		assertEquals(courseAverageGrades[0],39.5,0.1);
		assertEquals(courseAverageGrades[1],59.5,0.1);
		assertEquals(courseAverageGrades[2],79.5,0.1);
	}
}