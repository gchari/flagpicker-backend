package com.flagpicker.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.flagpicker.demo.mongodbservice.MongoDBServiceInterface;
import com.flagpicker.demo.response.Response;

@RestController
public class FlagPickerController {
	
	@Autowired
	private MongoDBServiceInterface mongoDBService;
	
	@GetMapping("/getflag")
	public Response greeting(@RequestParam(value = "continent", defaultValue = "ALL") String continent,
			@RequestParam(value = "country", defaultValue = "ALL") String country) {
		
		String response = mongoDBService.getdata(continent, country);
		
		return new Response("success", response);
	}
	
	@PutMapping("/putflags")
	public Response putflags() {

		String response = "Data persisted.";
		if (!mongoDBService.persist()) {
			response = "error";
		}

		return new Response("success", response);
	}
}