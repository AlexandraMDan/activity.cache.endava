package com.hackathon;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.model.Kid;
import com.hackathon.model.Parent;
import com.hackathon.model.Task;
import com.hackathon.model.WishProduct;
import com.hackathon.service.KidService;
import com.hackathon.service.ParentService;
import com.hackathon.service.TaskService;
import com.hackathon.service.WishlistService;

@SpringBootApplication
@RestController
@CrossOrigin
public class ActivityCache {
	private static final Log log = LogFactory.getLog(ActivityCache.class);
	private static final String KID_NAME_1 = "Alex";
	private static final String KID_NAME_2 = "Susan";
	private static final String PARENT_USERNAME = "benW";

	@Autowired
	private ParentService parentService;
	@Autowired
	private KidService kidService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private WishlistService wishlistService;

	public static void main(String[] args) throws Exception {

		SpringApplication.run(ActivityCache.class, args);
	}

	// parentIntro
	@RequestMapping("/parentIntro")
	public Parent getParentIntro(@RequestParam(value = "username") String username) throws UnknownHostException {
		return parentService.getParentIntroDetails(username);
	}

	/**
	 * JSON example: {"name":"Pick up toys", "description":"", "amount":"1",
	 * "status":"TODO", "owners":["Alex"], "category":"Chores"} <br/>
	 * <br/>
	 * {"name":"Pick up toys 2", "description":"", "amount":"2",
	 * "status":"DONE", "owners":["Alex, Susan"], "category":"Sport"}
	 * 
	 */
	@CrossOrigin
	@RequestMapping(value = "/insertTask", method = RequestMethod.POST)
	public ResponseEntity<Task> insertTask(@RequestBody Task task) throws UnknownHostException {
		taskService.insertTask(task);

		return new ResponseEntity<Task>(task, HttpStatus.OK);
	}

	@RequestMapping(value = "/getKidTasks", method = RequestMethod.GET)
	public Kid getKidTasks(@RequestParam(value = "name") String name) throws UnknownHostException {
		return kidService.getKidTasks(name);
	}

	@CrossOrigin
	@RequestMapping(value = "/updateTaskByKid", method = RequestMethod.POST)
	public Task updateTaskByKid(@RequestParam(value = "id") String id, @RequestParam(value = "status") String status)
			throws UnknownHostException {
		Task task = taskService.updateTaskById(id, status);
		if ("APPROVED".equals(status)) {
			parentService.moveMoney(PARENT_USERNAME, task.getOwners().get(0), task.getAmount());
		}
		return task;
	}

	@CrossOrigin
	@RequestMapping(value = "/insertWishlist", method = RequestMethod.POST)
	public ResponseEntity<WishProduct> insertWishlist(@RequestBody WishProduct product) throws UnknownHostException {
		wishlistService.insertWishlist(product);
		return new ResponseEntity<WishProduct>(product, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getWishlistByUser", method = RequestMethod.GET)
	public List<WishProduct> getWishlistByUser(@RequestParam(value = "username") String username) throws UnknownHostException {
		return wishlistService.getWishlistByUser(username);
	}

	@Autowired
	private void insertChildren() throws UnknownHostException {
		kidService.deleteChildren();
		kidService.insertChildren();
		log.info("Cildren successfully inserted.");
	}

	@Autowired
	private void insertTask() throws UnknownHostException {
		List<String> owners = new ArrayList<String>();
		owners.add(KID_NAME_1);
		taskService.insertTask(new Task(null, "Pick up toys", "", "0.25", "TODO", owners, "Chores"));

		List<String> owners2 = new ArrayList<String>();
		owners2.add(KID_NAME_2);
		taskService.insertTask(
				new Task(null, "Wash teeth before bed for a whole week", "", "0.10", "TODO", owners2, "Health"));

		taskService.insertTask(new Task(null, "Finish homework before supper", "", "1.00", "DONE", owners, "School"));
		taskService.insertTask(new Task(null, "Bike to school", "", "0.25", "APPROVED", owners2, "Sport"));
	}

	@Autowired
	public void addParentOnStartUp() throws UnknownHostException {
		parentService.insertParent();
	}

}
