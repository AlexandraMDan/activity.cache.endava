package com.hackathon.service;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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
		Kid kid = new Kid("Alex", "15.00", new ArrayList<Task>());
		Kid kid2 = new Kid("Susan", "13.00", new ArrayList<Task>());

		children.add(kid);
		children.add(kid2);

		List<String> owners = new ArrayList<String>();
		owners.add(kid.getName());

		Parent existingParent = mongoDBConfig.getMongoTemplate().findOne(new Query(where("username").is("benW")),
				Parent.class);
		if (existingParent == null) {
			mongoDBConfig.getMongoTemplate()
					.insert(new Parent("benW", "Ben", "Wilson", children, "1520.00", new ArrayList<Task>()));
		}
	}

	public Parent getParent(String username) throws UnknownHostException {
		Query query = Query.query(Criteria.where("username").is(username));
		return mongoDBConfig.getMongoTemplate().findOne(query, Parent.class);
	}

	public Parent getParentIntroDetails(String username) throws UnknownHostException {
		Parent parent = getParent(username);
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

	public void moveMoney(String parentUser, String childName, String amount) throws UnknownHostException {
		double amountToBeMoved = Double.parseDouble(amount);
		Parent parent = getParent(parentUser);
		if (parent != null) {
			double parentSold = Double.parseDouble(parent.getSold());
			mongoDBConfig.getMongoTemplate().updateFirst(Query.query(Criteria.where("username").is(parentUser)),
					Update.update("sold", String.valueOf(parentSold - amountToBeMoved)), Parent.class);
		}

		Kid kid = mongoDBConfig.getMongoTemplate().findOne(Query.query(Criteria.where("name").is(childName)),
				Kid.class);
		if (kid != null) {
			double kidSold = Double.parseDouble(kid.getSold());
			String newKidSold = String.valueOf(kidSold + amountToBeMoved);
			List<Kid> kids = new ArrayList<Kid>();
			for (Kid parentKid : parent.getChildren()) {
				if (childName.equals(parentKid.getName())) {
					parentKid.setSold(newKidSold);
				}
				kids.add(parentKid);
			}

			mongoDBConfig.getMongoTemplate().findAndModify(new Query(where("username").is(parentUser)),
					Update.update("children", kids), Parent.class);
			mongoDBConfig.getMongoTemplate().updateFirst(Query.query(Criteria.where("name").is(childName)),
					Update.update("sold", newKidSold), Kid.class);
		}
	}

}
