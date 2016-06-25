package com.hackathon.service;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.hackathon.configuration.MongoDBConfiguration;
import com.hackathon.model.Task;

@Service
public class TaskService {

	@Autowired
	private MongoDBConfiguration mongoDBConfig;

	public void insertTask(Task task) throws UnknownHostException {

		Task existingTask = mongoDBConfig.getMongoTemplate().findOne(new Query(where("name").is(task.getName())),
				Task.class);

		if (task != null && existingTask == null) {
			mongoDBConfig.getMongoTemplate().insert(task);
		}
	}

	public Task updateTaskByKid(String id, String status) throws UnknownHostException {
		Update update = new Update();
		update.set("status", status);
		mongoDBConfig.getMongoTemplate().findAndModify(new Query(where("id").is(id)), update, Task.class);
		return mongoDBConfig.getMongoTemplate().findOne(new Query(where("id").is(id)), Task.class);
	}
}
