package com.hackathon;

import java.net.UnknownHostException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.model.Parent;
import com.hackathon.service.ParentService;

@SpringBootApplication
@RestController
public class ActivityCache {
	private static final Log log = LogFactory.getLog(ActivityCache.class);

	@Autowired
	private ParentService parentService;

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

}
