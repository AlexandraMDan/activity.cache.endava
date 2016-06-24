package com.hackathon;

import java.net.UnknownHostException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.service.DefaultCategoryService;

@SpringBootApplication
@RestController
public class ActivityCache {
	private static final Log log = LogFactory.getLog(ActivityCache.class);

	public static void main(String[] args) {
		try {
			DefaultCategoryService.insertDefaultCategory();
			log.info("Default Categories successfully inserted.");
		} catch (UnknownHostException e) {
			log.error("Exception when inserting default categories.", e);
		}

		SpringApplication.run(ActivityCache.class, args);
	}
	

}
