package com.example.demo.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.example.demo.bean.Status;
import com.example.demo.bean.Task;

/*
 * Using in memory database now.
 * These classes can be converted to JPA for persisting 
 * data into database.
 */

@Component
public class TaskRepo {
	Map<Long, List<Task> > consumerIDTaskMap;
	Map<Long, List<Task> > workerIDTaskMap;
	Map<Long, Task> tasks;
	
	public TaskRepo() {
		consumerIDTaskMap = new HashMap<>();
		workerIDTaskMap = new HashMap<>();
		tasks = new HashMap<>();
	}
	
	public boolean isValid(Task task) {
		// TODO: implement a proper check of data. Check if already registered ?
		return true;
	}
	
	public void persist(Task task, long userId) {
		// TODO: add persistent logic.
		tasks.put(task.getId(), task);
		List<Task> list = consumerIDTaskMap.get(userId);
		if(list == null ) {
			list = new ArrayList<>();
		}
		list.add(task);
		consumerIDTaskMap.put(userId,  list);
	}
	
	public boolean checkTaskExists( long id ) {
		return tasks.containsKey(id);
	}
	
	public Task getTask( long id ) {
		return tasks.get(id);
	}
	
	public List<Task> getTasks(long userId) {
		List<Task> output = new ArrayList<>();
		
		for( Task task : consumerIDTaskMap.get(userId) ) {
			if( task.getStatus() == Status.Completed ) {
				output.add(task);
			}
		}
		return output;
	}
	
	public List<Task> getTasksToBeDone() {
		List<Task> output = new ArrayList<>();
		
		for( Task task : tasks.values() ) {
			if( task.getStatus() == Status.Ready ) {
				output.add(task);
			}
		}
		return output;
	}
	
	public boolean assignTask(long userId, long taskId) {
		// check if this task is being already assigned?
		Task task = tasks.get(taskId);
		if(task.getStatus() == Status.Ready) {
			task.setStatus(Status.Assigned);  // TODO: Handle concurrency here.
			List<Task> list = workerIDTaskMap.get(userId);
			if(list == null ) {
				list = new ArrayList<>();
			}
			list.add(task);
			workerIDTaskMap.put(userId, list);
			return true;
		} else {
			return false;
		}
	}
	
	
}
