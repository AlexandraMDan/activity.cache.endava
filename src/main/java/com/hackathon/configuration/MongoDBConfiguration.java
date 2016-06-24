package com.hackathon.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import com.mongodb.MongoClient;

@Configuration
public class MongoDBConfiguration {

	public @Bean MongoOperations getMongoTemplate() throws Exception {
		MongoOperations mongoOps = new MongoTemplate(new MongoClient(), "database");
		return mongoOps;
	}

}
