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
import com.hackathon.model.Parent;
import com.hackathon.model.Task;

@Service
public class ParentService {

	@Autowired
	private MongoDBConfiguration mongoDBConfig;

	public void insertParent() throws UnknownHostException {
		List<Kid> children = new ArrayList<Kid>();
		Kid kid = new Kid("Alex", "15", new ArrayList<Task>());
		Kid kid2 = new Kid("Raluca", "13", new ArrayList<Task>());

		children.add(kid);
		children.add(kid2);

		List<String> owners = new ArrayList<String>();
		owners.add(kid.getName());

		List<Task> tasks = new ArrayList<Task>();
		Task task = new Task("1", "Pick up toys", "", "100", "TODO", owners, "Sport");
		tasks.add(task);
		tasks.add(new Task("2", "Pick up toys 2", "", "100", "DONE", owners, "Chores"));

		mongoDBConfig.getMongoTemplate().insert(new Parent("popescui", "I", "Popescu", children, "1520", tasks));
	}

	public Parent getParent() throws UnknownHostException {
		return mongoDBConfig.getMongoTemplate().findOne(new Query(where("category.tasks.owners").is("Alex")),
				Parent.class);
	}

	public Parent getParentIntroDetails(String username) throws UnknownHostException {
		Query query = Query.query(Criteria.where("username").is(username));

		Parent parent = mongoDBConfig.getMongoTemplate().findOne(query, Parent.class);
		List<Task> doneTasks = mongoDBConfig.getMongoTemplate().find(new Query(where("status").is("DONE")), Task.class);
		List<Kid> children = mongoDBConfig.getMongoTemplate().find(new Query(), Kid.class);

		for (Kid kid : children) {
			if (kid.getTasks() == null) {
				kid.setTasks(new ArrayList<>());
			}
			for (Task task : doneTasks) {
				if (task.getOwners().contains(kid.getName())) {
					kid.getTasks().add(task);
				}
			}
		}
		parent.setTasks(new ArrayList<>());
		parent.setChildren(children);

		return parent;
	}

}
