package com.hackathon.service;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.net.UnknownHostException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.hackathon.configuration.MongoDBConfiguration;
import com.hackathon.model.WishProduct;

@Service
public class WishlistService {

	@Autowired
	private MongoDBConfiguration mongoDbConfig;

	public void insertWishlist(WishProduct product) throws UnknownHostException {
		mongoDbConfig.getMongoTemplate().insert(product);
	}

	public List<WishProduct> getWishlistByUser(String username) throws UnknownHostException {
		return mongoDbConfig.getMongoTemplate().find(new Query(where("username").is(username)), WishProduct.class);
	}

}
