package com.hackathon.model;

public class DefaultCategory {

	private String name;

	public DefaultCategory(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "DefaultCategory [name=" + name + "]";
	}

}
