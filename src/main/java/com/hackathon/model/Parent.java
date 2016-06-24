package com.hackathon.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Parent {
	private Currency currency;
	private String username;
	private List<Kid> children;
	private double sold;
	private Category category;

	public Parent(@JsonProperty("currency") Currency currency, @JsonProperty("username") String username,
			@JsonProperty("kid") List<Kid> children, @JsonProperty("sold") double sold,
			@JsonProperty("category") Category category) {
		this.currency = currency;
		this.username = username;
		this.children = children;
		this.sold = sold;
		this.category = category;
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

	public Category getCategory() {
		return category;
	}

	@Override
	public String toString() {
		return "Parent [currency=" + currency + ", username=" + username + ", kid=" + children + ", sold=" + sold
				+ ", category=" + category + "]";
	}

}
