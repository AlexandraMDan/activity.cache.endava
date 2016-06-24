package com.hackathon.model;

import java.util.List;

public class Category {

	private String name;
	private String description;
	private List<String> tasks;

	public Category(String name, String description, List<String> tasks) {
		this.setName(name);
		this.setDescription(description);
		this.setTasks(tasks);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getTasks() {
		return tasks;
	}

	public void setTasks(List<String> tasks) {
		this.tasks = tasks;
	}

}
