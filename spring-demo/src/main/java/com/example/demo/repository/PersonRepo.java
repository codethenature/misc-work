package com.example.demo.repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.example.demo.bean.LoginDetails;
import com.example.demo.bean.Person;

@Component
public class PersonRepo {
	
	Map<Long, Person> persons;
	
	public PersonRepo() {
		persons = new HashMap<>();
	}
	
	public boolean isValid(Person person) {
		// TODO: implement a proper check of data. Check if already registered ?
		return true;
	}
	
	public void persist(Person person) {
		// TODO: add persistent logic.
		persons.put(person.getId(), person);
	}
	
	public boolean checkPersonExists( long id ) {
		return persons.containsKey(id);
	}
	
	public Person getPerson( long id ) {
		return persons.get(id);
	}
	
	public boolean authenticate(long userId, LoginDetails login) {
		
		if (checkPersonExists (userId)) {
			Person person = persons.get(userId);
			return login.getEmail().equalsIgnoreCase(person.getEmail()) ?
					(login.getPassword().equals(person.getPassword()) ? true : false )
					: false ;
		} else {
			return false;
		}
	}
	
}
