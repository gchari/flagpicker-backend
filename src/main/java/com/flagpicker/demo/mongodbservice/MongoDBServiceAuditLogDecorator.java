package com.flagpicker.demo.mongodbservice;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import java.util.logging.Logger;

public class MongoDBServiceAuditLogDecorator implements MongoDBServiceInterface {

	@Autowired
	@Qualifier("delegate")
	private MongoDBServiceInterface mongoDBService;
	
	private static final Logger LOGGER = Logger.getLogger(null);
	 
	@Override
	public String getdata(String continent, String country) {
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		Long timestart = timestamp.getTime();
		String response = mongoDBService.getdata(continent, country);
		Long timeend = timestamp.getTime();
		
		LOGGER.info("Time Elapsed for getdata call: " + (timeend-timestart));
		
		return response;
	}
	
	@Override
	public Boolean persist() {
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		Long timestart = timestamp.getTime();
		Boolean response = mongoDBService.persist();
		Long timeend = timestamp.getTime();
		
		LOGGER.info("Time Elapsed for getdata call: " + (timeend-timestart));
		
		return response;
	}
}