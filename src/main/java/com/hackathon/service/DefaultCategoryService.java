package com.hackathon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackathon.configuration.MongoDBConfiguration;
import com.hackathon.model.DefaultCategory;

@Service
public class DefaultCategoryService {

	@Autowired
	private MongoDBConfiguration mongoDbConfig;

	/**
	 * Inserts default categories.
	 */
	public void insertDefaultCategory() throws Exception {

		mongoDbConfig.getMongoTemplate().insert(new DefaultCategory("Chore"));
		mongoDbConfig.getMongoTemplate().insert(new DefaultCategory("School"));
		mongoDbConfig.getMongoTemplate().insert(new DefaultCategory("Sport"));

	}

	public void deleteDefaultCategory() throws Exception {
		mongoDbConfig.getMongoTemplate().dropCollection(DefaultCategory.class);
	}
}
