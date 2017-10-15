package com.cisc181.core;

public class PersonException extends Exception{
	
	private Person person;
	
	public PersonException(Person person, String message) {
		super(message);
		System.out.println(message);
		this.person = person;
		// PersonExceptions are tried and caught in setDOB() and setPhone() in class Person
	}
	
	public Person getPerson() {
		return person;
	}
	
}
