package com.hackathon.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Parent {
	private String username;
	private String firstname;
	private String lastname;
	private List<Kid> children;
	private String sold;
	private List<Task> tasks;

	public Parent(@JsonProperty("username") String username, @JsonProperty("firstname") String firstname,
			@JsonProperty("lastname") String lastname, @JsonProperty("children") List<Kid> children,
			@JsonProperty("sold") String sold, @JsonProperty("tasks") List<Task> tasks) {
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.children = children;
		this.sold = sold;
		this.tasks = tasks;
	}

	public String getUsername() {
		return username;
	}

	public List<Kid> getChildren() {
		return children;
	}

	public String getSold() {
		return sold;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	@Override
	public String toString() {
		return "Parent [ username=" + username + ", kid=" + children + ", sold=" + sold + "]";
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

}
