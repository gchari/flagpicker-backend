package com.flagpicker.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import static org.assertj.core.api.Assertions.assertThat;
import com.flagpicker.demo.mongodbservice.MongoDBServiceInterface;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private MongoDBServiceInterface mongoDBService;
	
	@Test
	void datapersists() {
		Assert.isTrue(mongoDBService.persist());
	}
	
	@Test
	void getdata_succeeds_ALL() {
		String response = mongoDBService.getdata("ALL", "ALL");
		assertThat(response).contains("Africa");
		assertThat(response).contains("America");
		assertThat(response).contains("Europe");
		assertThat(response).contains("Asia");
		assertThat(response).contains("Oceania");
	}
	
	@Test
	void getdata_succeeds_one_continent() {
		String response = mongoDBService.getdata("Africa", "ALL");
		assertThat(response).contains("Africa");
		assertThat(response).doesNotContain("America");
	}

	@Test
	void getdata_succeeds_one_continent_one_country() {
		String response = mongoDBService.getdata("Africa", "Nigeria");
		assertThat(response).contains("Nigeria");
		assertThat(response).doesNotContain("Ethiopia");
	}
}
