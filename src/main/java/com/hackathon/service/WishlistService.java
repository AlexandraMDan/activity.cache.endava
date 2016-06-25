package com.hackathon.service;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
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

}
