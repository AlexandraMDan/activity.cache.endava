package com.hackathon.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Task {

	private String name;
	private String description;
	private int priority;
	private double amount;
	private String status;
	private List<String> owners;

	public Task(@JsonProperty("name") String name, @JsonProperty("description") String description, @JsonProperty("priority") int priority, @JsonProperty("amount") double amount, @JsonProperty("status") String status, @JsonProperty("owners") List<String> owners  ){
		this.name=name;
		this.description=description;
		this.priority=priority;
		this.amount= amount;
		this.status=status;
		this.owners=owners;
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

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getOwners() {
		return owners;
	}

	public void setOwners(List<String> owners) {
		this.owners = owners;
	}

}
