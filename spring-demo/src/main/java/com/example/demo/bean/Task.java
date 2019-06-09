package com.example.demo.bean;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

public class Task {
	private final long id;
	private final String name;
	private final String description;
	private final Category category;
	private final Location location;
	private final Date datetime;
	
	private Status status;
	
	// TODO: Can be deprecated, use sequence in database.
	private final AtomicLong counter = new AtomicLong();
	
	public Task(String name, String description, Category category, Location location, Date datetime) {
		super();
		this.name = name;
		this.description = description;
		this.category = category;
		this.location = location;
		this.datetime = datetime;
		id = counter.incrementAndGet();
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Category getCategory() {
		return category;
	}

	public Location getLocation() {
		return location;
	}

	public Date getDatetime() {
		return datetime;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public Status getStatus() {
		return status;
	}
	
}
