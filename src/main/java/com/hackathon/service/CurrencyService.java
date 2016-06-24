package com.hackathon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackathon.configuration.MongoDBConfiguration;
import com.hackathon.model.Currency;

@Service
public class CurrencyService {

	@Autowired
	private MongoDBConfiguration mongoDbConfig;

	public void insertCurrency() throws Exception {

		mongoDbConfig.getMongoTemplate().insert(new Currency("EUR"));
		mongoDbConfig.getMongoTemplate().insert(new Currency("GBP"));
		mongoDbConfig.getMongoTemplate().insert(new Currency("USD"));

	}

	public void deleteCurrency() throws Exception {
		mongoDbConfig.getMongoTemplate().dropCollection(Currency.class);
	}

}
