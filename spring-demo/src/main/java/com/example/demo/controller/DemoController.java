package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.LoginDetails;
import com.example.demo.bean.Person;
import com.example.demo.bean.PersonType;
import com.example.demo.bean.Task;
import com.example.demo.repository.PersonRepo;
import com.example.demo.repository.TaskRepo;

@RestController
public class DemoController {

    @Autowired
    private PersonRepo personRepo;
    
    @Autowired
    private TaskRepo taskRepo;
    
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity < String > persistPerson(@RequestBody Person person) {
        if (personRepo.isValid(person)) {
        	personRepo.persist(person);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }
    

    @RequestMapping(value = "/login/{userId}", method = RequestMethod.POST)
    public Long login(@RequestBody LoginDetails log,
    		@PathVariable long userId) {
    	
        if (personRepo.authenticate(userId, log)) {
            return userId;
        }
        return -1L;
    }
    
    @RequestMapping(value = "/getPerson/{id}", method = RequestMethod.GET)
    public Optional<Person> getPerson(@PathVariable long id) {
        if (personRepo.checkPersonExists(id)) {
        	return Optional.of(personRepo.getPerson(id));
        }
        
        return Optional.empty();
    }
    
    @RequestMapping(value = "/createTask/{userId}", method = RequestMethod.POST)
    public ResponseEntity < String > createTask(@RequestBody Task task, 
    		@PathVariable long userId ) {
    	// Check user exists and it is a consumer.
    	if (personRepo.checkPersonExists(userId)) {
    		if (personRepo.getPerson(userId).getPersonType() == PersonType.Consumer) {
    			if (taskRepo.isValid(task)) {
    	        	taskRepo.persist(task, userId);
    	            return ResponseEntity.status(HttpStatus.CREATED).build();
    	        }
    		}
    	}
        
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }
    
    @RequestMapping(value = "/getTasks/{id}", method = RequestMethod.GET)
    public Optional<List <Task>> getTasks(@PathVariable long id) {
        if (personRepo.checkPersonExists(id)) {
        	if (personRepo.getPerson(id).getPersonType() == PersonType.Consumer) {
        		return Optional.of(taskRepo.getTasks(id));
        	}
        	
        }
        
        return Optional.of(new ArrayList<>());
    }
    
    
    @RequestMapping(value = "/getTasksToBeDone/{id}", method = RequestMethod.GET)
    public Optional<List <Task>> getTasksToBeDone(@PathVariable long id) {
        if (personRepo.checkPersonExists(id)) {
        	if (personRepo.getPerson(id).getPersonType() == PersonType.Worker) {
        		return Optional.of(taskRepo.getTasksToBeDone());
        	}
        	
        }
        
        return Optional.of(new ArrayList<>());
    }
    
    @RequestMapping(value = "/assignTask/{id}/{taskId}", method = RequestMethod.POST)
    public ResponseEntity < String >assignTask(@PathVariable long id,
    		@PathVariable long taskId) {
        if (personRepo.checkPersonExists(id)) {
        	if (personRepo.getPerson(id).getPersonType() == PersonType.Worker) {
        		return taskRepo.assignTask(id, taskId) ? 
        				ResponseEntity.status(HttpStatus.CREATED).build() :
        					ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
        	}
        	
        }
        
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }
}
