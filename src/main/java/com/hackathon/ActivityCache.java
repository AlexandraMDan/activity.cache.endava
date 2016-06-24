package com.hackathon;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.model.Category;
import com.hackathon.model.Parent;
import com.hackathon.model.Task;
import com.hackathon.service.CategoryService;
import com.hackathon.service.CurrencyService;
import com.hackathon.service.ParentService;

@SpringBootApplication
@RestController
public class ActivityCache {
	private static final Log log = LogFactory.getLog(ActivityCache.class);

	private static final String USERNAME = "popescui";

	@Autowired
	private CurrencyService currencyService;
	@Autowired
	private CategoryService defaultCategoryService;
	@Autowired
	private ParentService parentService;

	public static void main(String[] args) throws Exception {

		SpringApplication.run(ActivityCache.class, args);
	}

	@Autowired
	private void insertDefaultCategory() {
		try {
			defaultCategoryService.deleteDefaultCategory();
			defaultCategoryService.insertDefaultCategory();
			log.info("Default Categories successfully inserted.");
		} catch (UnknownHostException e) {
			log.error("Exception when inserting default categories.", e);
		} catch (Exception e) {
			log.error("Exception when inserting default categories.", e);
		}
	}

	@RequestMapping("/getCategories")
	public List<Category> getCategories(@RequestParam(value = "username") String username,
			@RequestParam(value = "kid") String kid) throws UnknownHostException {
		return defaultCategoryService.getCategories(username, kid);
	}

	@Autowired
	private void insertDefaultCurrency() {
		try {
			currencyService.deleteCurrency();
			currencyService.insertCurrency();
			log.info("Currencies successfully inserted");
		} catch (Exception e) {
			log.error("Exception when inserting currencies.", e);
		}
	}

	@RequestMapping("/getKidTasks")
	public Category getKidTaks(@RequestParam(value = "name") String name) {
		// mongoDbConfig.getMongoTemplate().fin
		return null;
	}

	@RequestMapping("/addParent")
	public void addParent() throws UnknownHostException {
		parentService.insertParent();
	}

	@CrossOrigin
	@RequestMapping("/getChildrenTasks")
	public List<Category> getChildrenTasks(@RequestParam(value = "name") String name) throws UnknownHostException {
		List<Category> categories = new ArrayList<Category>();

		for (Category category : parentService.getParent(USERNAME).getCategories()) {
			List<Task> tasks = new ArrayList<Task>();
			for (Task task : category.getTasks()) {
				if (task.getOwners().contains(name)) {
					tasks.add(task);
				}
			}

			if (!tasks.isEmpty()) {
				categories.add(new Category(category.getName(), category.getDescription(), tasks));
			}
		}

		return categories;
	}

	// parentIntro
	@RequestMapping("/parentIntro")
	public Parent getParentIntro(@RequestParam(value = "username") String username) throws UnknownHostException {
		return parentService.getParentIntroDetails(username);
	}

}
