package com.hackathon.service;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.hackathon.configuration.MongoDBConfiguration;
import com.hackathon.model.Kid;
import com.hackathon.model.Task;

@Service
public class KidService {

	@Autowired
	private MongoDBConfiguration mongoDBConfig;

	public void insertChildren() throws UnknownHostException {
		mongoDBConfig.getMongoTemplate()
				.insert(new Kid("Alex", "15.00", new ArrayList<Task>()));
		mongoDBConfig.getMongoTemplate()
				.insert(new Kid("Susan", "13.00", new ArrayList<Task>()));
	}

	public void deleteChildren() throws UnknownHostException {
		mongoDBConfig.getMongoTemplate().dropCollection(Kid.class);
	}

	public Kid getKidTasks(String name) throws UnknownHostException {
		Kid kid = mongoDBConfig.getMongoTemplate().findOne(Query.query(Criteria.where("name").is(name)), Kid.class);
		List<Task> tasks = mongoDBConfig.getMongoTemplate().find(new Query(where("owners").regex(name)), Task.class);
		return new Kid(kid.getName(), kid.getSold(), tasks);
	}

}
