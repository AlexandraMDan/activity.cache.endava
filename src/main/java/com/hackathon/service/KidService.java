package com.hackathon.service;

import java.net.UnknownHostException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackathon.configuration.MongoDBConfiguration;
import com.hackathon.model.Kid;
import com.hackathon.model.Task;

@Service
public class KidService {

	@Autowired
	private MongoDBConfiguration mongoDBConfig;

	public void insertChildren() throws UnknownHostException {
		mongoDBConfig.getMongoTemplate().insert(new Kid("Alex", "15", new ArrayList<Task>()));
		mongoDBConfig.getMongoTemplate().insert(new Kid("Raluca", "13", new ArrayList<Task>()));
	}

	public void deleteChildren() throws UnknownHostException {
		mongoDBConfig.getMongoTemplate().dropCollection(Kid.class);
	}
}
