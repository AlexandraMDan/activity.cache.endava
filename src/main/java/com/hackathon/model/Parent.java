package com.hackathon.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Parent {
	private Currency currency;
	private String username;
	private Kid kid;
	private double sold;
	private Category category;

	public Parent(@JsonProperty("currency") Currency currency, @JsonProperty("username") String username,
			@JsonProperty("kid") Kid kid, @JsonProperty("sold") double sold,
			@JsonProperty("category") Category category) {
		this.currency = currency;
		this.username = username;
		this.kid = kid;
		this.sold = sold;
		this.category = category;
	}

	public Currency getCurrency() {
		return currency;
	}

	public String getUsername() {
		return username;
	}

	public Kid getKid() {
		return kid;
	}

	public double getSold() {
		return sold;
	}

	public Category getCategory() {
		return category;
	}

	@Override
	public String toString() {
		return "Parent [currency=" + currency + ", username=" + username + ", kid=" + kid + ", sold=" + sold
				+ ", category=" + category + "]";
	}

}
