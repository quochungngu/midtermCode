package com.cisc181.core;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.cisc181.eNums.eTitle;

public class Staff_Test {
	
	private static ArrayList<Staff> staff = new ArrayList();
		
	@BeforeClass
	public static void setup() {
		try {
			staff.add(new Staff("Billy", "Bob", "Joe", (new SimpleDateFormat("dd/MM/yyyy")).parse("01/02/1990"),
					"Skeet Street", "(123)-456-7890", "feet@email.com", "12 AM to 6 AM", 5,
					50000, (new SimpleDateFormat("dd/MM/yyyy")).parse("01/02/2008"), eTitle.MRS));
			staff.add(new Staff("Billy", "Bob", "Joe", (new SimpleDateFormat("dd/MM/yyyy")).parse("01/02/1990"),
					"Skeet Street", "(123)-456-7890", "feet@email.com", "12 AM to 6 AM", 5,
					100000, (new SimpleDateFormat("dd/MM/yyyy")).parse("01/02/2008"), eTitle.MRS));
			staff.add(new Staff("Billy", "Bob", "Joe", (new SimpleDateFormat("dd/MM/yyyy")).parse("01/02/1990"),
					"Skeet Street", "(123)-456-7890", "feet@email.com", "12 AM to 6 AM", 5,
					95000, (new SimpleDateFormat("dd/MM/yyyy")).parse("01/02/2008"), eTitle.MRS));
			staff.add(new Staff("Billy", "Bob", "Joe", (new SimpleDateFormat("dd/MM/yyyy")).parse("01/02/1990"),
					"Skeet Street", "(123)-456-7890", "feet@email.com", "12 AM to 6 AM", 5,
					40000, (new SimpleDateFormat("dd/MM/yyyy")).parse("01/02/2008"), eTitle.MRS));
			staff.add(new Staff("Billy", "Bob", "Joe", (new SimpleDateFormat("dd/MM/yyyy")).parse("01/02/1990"),
					"Skeet Street", "(123)-456-7890", "feet@email.com", "12 AM to 6 AM", 5,
					80000, (new SimpleDateFormat("dd/MM/yyyy")).parse("01/02/2008"), eTitle.MRS));
			
		}
		catch(ParseException e) {
			System.out.println("ParseException");
		}
		catch(PersonException e) {
			System.out.println("PersonException");
		}
		
		System.out.println("");
	}
	
	@Test
	public void test() {
		double salaryAverage;
		double salariesTotal = 0;
		
		for (Staff member: staff) {
			
			salariesTotal += member.getSalary();
		}
		
		salaryAverage = salariesTotal/(staff.size());
		/* Salaries are:
		 * 50000, 100000, 95000, 40000, 80000
		 * 
		 */
		assertEquals(73000.00, salaryAverage,0.1);
	}
	
	@Test
	public void testDOBException() {
		boolean thrown = false;
		
		try {
			staff.get(0).setDOB((new SimpleDateFormat("dd/MM/yyyy")).parse("13/10/1916"));
		}
		catch(ParseException e) {
			System.out.println("ParseException");
		}
		catch(PersonException e) {
			thrown = true;
		}
		
		assertTrue(thrown);
	}
	
	@Test
	public void testPhoneException() {
		boolean thrown = false;

		try {
			staff.get(0).setPhone("234567890");
		}
		catch(PersonException e) {
			thrown = true;
			System.out.println("");
		}

		assertTrue(thrown);
		
		thrown = false;

		try {
			staff.get(0).setPhone("(123)4567890");
		}
		catch(PersonException e) {
			thrown = true;
			System.out.println("");
		}

		assertTrue(thrown);
	}
	
}