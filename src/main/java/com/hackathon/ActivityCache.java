package com.hackathon;

import java.net.UnknownHostException;

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
import com.hackathon.service.KidService;
import com.hackathon.service.ParentService;
import com.hackathon.service.TaskService;

@SpringBootApplication
@RestController
@CrossOrigin
public class ActivityCache {
	private static final Log log = LogFactory.getLog(ActivityCache.class);

	@Autowired
	private ParentService parentService;
	@Autowired
	private KidService kidService;
	@Autowired
	private TaskService taskService;

	public static void main(String[] args) throws Exception {

		SpringApplication.run(ActivityCache.class, args);
	}

	@RequestMapping("/addParent")
	public void addParent() throws UnknownHostException {
		parentService.insertParent();
	}

	@RequestMapping("/getParent")
	public Parent getPatent() throws UnknownHostException {
		return parentService.getParent();
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
	 * "status":"DONE", "owners":["Alex, Raluca"], "category":"Sport"}
	 * 
	 */
	@RequestMapping(value = "/insertTask", method = RequestMethod.POST)
	public ResponseEntity<Task> insertTask(@RequestBody Task task) throws UnknownHostException {
		taskService.insertTask(task);

		return new ResponseEntity<Task>(task, HttpStatus.OK);
	}

	@RequestMapping(value = "/getKidTasks", method = RequestMethod.GET)
	public Kid getKidTasks(@RequestParam(value = "name") String name) throws UnknownHostException {
		return kidService.getKidTasks(name);
	}

	@Autowired
	private void insertCildren() throws UnknownHostException {
		kidService.deleteChildren();
		kidService.insertChildren();
		log.info("Cildren successfully inserted.");
	}

}
