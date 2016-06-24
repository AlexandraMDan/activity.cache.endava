package com.hackathon.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Parent {
	private Currency currency;
	private String username;
	private String firstname;
	private String lastname;
	private List<Kid> children;
	private double sold;
	private List<Category> categories;

	public Parent(@JsonProperty("currency") Currency currency, @JsonProperty("username") String username,
			@JsonProperty("firstname") String firstname, @JsonProperty("lastname") String lastname,
			@JsonProperty("kid") List<Kid> children, @JsonProperty("sold") double sold,
			@JsonProperty("category") List<Category> categories) {
		this.currency = currency;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.children = children;
		this.sold = sold;
		this.categories = categories;
	}

	public Currency getCurrency() {
		return currency;
	}

	public String getUsername() {
		return username;
	}

	public List<Kid> getChildren() {
		return children;
	}

	public double getSold() {
		return sold;
	}

	public List<Category> getCategories() {
		return categories;
	}
	
	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	@Override
	public String toString() {
		return "Parent [currency=" + currency + ", username=" + username + ", kid=" + children + ", sold=" + sold
				+ ", category=" + categories + "]";
	}

}
