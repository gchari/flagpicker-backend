package com.flagpicker.demo.model;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface FlagsRepository extends MongoRepository<ContinentFlags, String> {

  public FlagsRepository findByContinent(String continent);

}