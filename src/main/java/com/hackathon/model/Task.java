package com.hackathon.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Task {

	private String id;
	private String name;
	private String description;
	private String amount;
	private String status;
	private List<String> owners;
	private String category;

	public Task(@JsonProperty("id") String id, @JsonProperty("name") String name,
			@JsonProperty("description") String description, @JsonProperty("amount") String amount,
			@JsonProperty("status") String status, @JsonProperty("owners") List<String> owners, String category) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.amount = amount;
		this.status = status;
		this.owners = owners;
		this.category = category;
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

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		// TODO owners
		return "Task [ name=" + name + ", description=" + description + ", amount=" + amount + ", status=" + status
				+ ", category=" + "]";
	}

}
