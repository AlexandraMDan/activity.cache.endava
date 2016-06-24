package com.hackathon.service;

import java.net.UnknownHostException;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.hackathon.model.Currency;
import com.mongodb.MongoClient;

public class CurrencyService {

	public static void insertCurrency() throws UnknownHostException {
		MongoOperations mongoOps = new MongoTemplate(new MongoClient(), "database");
		mongoOps.insert(new Currency("EUR"));
		mongoOps.insert(new Currency("GBP"));
		mongoOps.insert(new Currency("USD"));

	}

}
