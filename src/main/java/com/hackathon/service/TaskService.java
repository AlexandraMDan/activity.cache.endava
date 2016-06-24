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

		if (task != null) {
			mongoDBConfig.getMongoTemplate().insert(task);
		}
	}
}
