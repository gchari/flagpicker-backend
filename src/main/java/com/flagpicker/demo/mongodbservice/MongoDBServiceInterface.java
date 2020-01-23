package com.flagpicker.demo.mongodbservice;

public interface MongoDBServiceInterface {
	
	/*
	 * Gets data from MongoDB.
	 */
	public String getdata(String continent, String country);
	
	/*
	 * Copies data from file to MongoDB.
	 */
	public Boolean persist();
}