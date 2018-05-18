package com.sdsmetro.store.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sdsmetro.store.mongo.document.MetroDoc;

public interface MetroMongoRepository extends MongoRepository<MetroDoc, String> {
	//
	MetroDoc findByName(String name);

}
