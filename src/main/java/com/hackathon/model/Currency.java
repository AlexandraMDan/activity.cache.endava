package com.hackathon.model;

public class Currency {

	private String currencyCode;

	public Currency(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	@Override
	public String toString() {
		return "Currency [currencyCode=" + currencyCode + "]";
	}

}
