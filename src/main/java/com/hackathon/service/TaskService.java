package com.hackathon.service;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackathon.configuration.MongoDBConfiguration;
import com.hackathon.model.Task;

@Service
public class TaskService {
	@Autowired
	private MongoDBConfiguration mongoDBConfig;

	public void insertTask(Task task) throws UnknownHostException {
		// List<String> owners = new ArrayList<String>();
		// owners.add("Alex");
		//
		// List<String> owners2 = new ArrayList<String>();
		// owners2.add("Alex");
		// owners2.add("Raluca");

		// mongoDBConfig.getMongoTemplate().insert(new Task("1", "Pick up toys",
		// "", "100", "TODO", owners, "Sport"));
		// mongoDBConfig.getMongoTemplate().insert(new Task("2", "Pick up toys
		// 2", "", "100", "DONE", owners2, "Chores"));

		if (task != null) {
			mongoDBConfig.getMongoTemplate().insert(task);
		}
	}
}
