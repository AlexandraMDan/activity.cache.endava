package com.hackathon.service;

import java.net.UnknownHostException;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.hackathon.model.DefaultCategory;
import com.mongodb.MongoClient;

public class DefaultCategoryService {

	/**
	 * Inserts default categories.
	 */
	public static void insertDefaultCategory() throws UnknownHostException {
		
		MongoOperations mongoOps = new MongoTemplate(new MongoClient(), "database");
		mongoOps.insert(new DefaultCategory("Chore"));
		mongoOps.insert(new DefaultCategory("School"));
		mongoOps.insert(new DefaultCategory("Sport"));

	}
}
