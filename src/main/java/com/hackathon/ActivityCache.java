package com.hackathon;

import java.net.UnknownHostException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.service.CurrencyService;
import com.hackathon.service.DefaultCategoryService;

@SpringBootApplication
@RestController
public class ActivityCache {
	private static final Log log = LogFactory.getLog(ActivityCache.class);

	@Autowired
	private DefaultCategoryService defaultCategoryService;
	@Autowired
	private CurrencyService currencyService;

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

}
