package com.hackathon.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WishProduct {

	private String id;
	private String name;
	private String status;
	private String price;

	public WishProduct(@JsonProperty("id") String id, @JsonProperty("name") String name,
			@JsonProperty("status") String status, @JsonProperty("price") String price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getStatus() {
		return status;
	}

	public String getPrice() {
		return price;
	}

}
