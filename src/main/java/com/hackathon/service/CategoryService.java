package com.hackathon.service;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.hackathon.configuration.MongoDBConfiguration;
import com.hackathon.model.Category;
import com.hackathon.model.DefaultCategory;
import com.hackathon.model.Parent;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
public class CategoryService {

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
	
	public List<Category> getCategories(String username, String kidName ) throws UnknownHostException{
		List<Category> categories = new ArrayList<>();
		Parent parent = mongoDbConfig.getMongoTemplate().findOne(new Query(where("username").is(username)), Parent.class);
		
		//TODO
		
		return categories;
	}
	
}
