package com.hackathon.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Kid {

	private String name;
	private double sold;

	public Kid(@JsonProperty("name") String name, @JsonProperty("sold") double sold) {
		this.name = name;
		this.sold = sold;
	}

	public String getName() {
		return name;
	}

	public double getSold() {
		return sold;
	}

	@Override
	public String toString() {
		return "Kid [name=" + name + ", sold=" + sold + "]";
	}

}
