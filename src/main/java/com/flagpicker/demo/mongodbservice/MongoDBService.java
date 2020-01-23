package com.flagpicker.demo.mongodbservice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flagpicker.demo.model.ContinentFlags;
import com.flagpicker.demo.model.Flag;
import com.flagpicker.demo.model.FlagsRepository;

@Component("delegate")
public class MongoDBService implements MongoDBServiceInterface {

	@Autowired
	private FlagsRepository repository;
	
	@Override
	public String getdata(String continent, String country) {
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		String response = "None";
		try {
			if (continent.equalsIgnoreCase("ALL")) {
				List<ContinentFlags> tmp = repository.findAll();
				response = objectMapper.writeValueAsString(tmp);
				
			} else {
				Optional<ContinentFlags> tmp = repository.findById(continent);
				if (!country.equalsIgnoreCase("ALL")) {
					ContinentFlags tmp1 = tmp.get();
					for (Flag f: tmp1.countries) {
						if (country.equalsIgnoreCase(f.name)) {
							response = objectMapper.writeValueAsString(f);
						}
					}
				} else {
					response = objectMapper.writeValueAsString(tmp.get());
				}
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			
		}
		
		return response;
	}
	
	@Override
	public Boolean persist() {

		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			JsonNode jsonNode = objectMapper.readTree(new File("src/main/resources/static/continents.json.txt"));
		    Iterator<JsonNode> iterator = jsonNode.elements();

	        while (iterator.hasNext()) {
	           JsonNode continentFlags = iterator.next();
	           Iterator<JsonNode> iterator2 = continentFlags.path("countries").elements();
	           List<Flag> flags = new ArrayList<>();
	           
	           while (iterator2.hasNext()) {
	        	   JsonNode countries = iterator2.next();
	        	   flags.add(new Flag(countries.path("name").asText(), countries.path("flag").asText()));
	           }
	           Flag[] arr = new Flag[flags.size()]; 
	           ContinentFlags flagdata = new ContinentFlags(continentFlags.path("continent").asText(), (Flag[]) flags.toArray(arr));
	           repository.save(flagdata);
	        }
		    
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			// Logging
			e.printStackTrace();
		} catch (IOException e) {
			// Logging
			e.printStackTrace();
		}

		return Boolean.TRUE;
	}
}