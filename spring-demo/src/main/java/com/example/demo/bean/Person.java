package com.example.demo.bean;

import java.util.concurrent.atomic.AtomicLong;

public class Person {

	// TODO: Can be deprecated, use sequence in database.
	private final AtomicLong counter = new AtomicLong();
		
    private final long id;
    private final String name;
    private final String email;
    private final String password;
    private final PersonType personType;
    
	public Person(String name, String email, String password, PersonType personType) {
		super();
		this.id = counter.incrementAndGet();
		this.name = name;
		this.email = email;
		this.password = password;
		this.personType = personType;
	}
	
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public PersonType getPersonType() {
		return personType;
	}

    
}