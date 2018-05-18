package com.sdsmetro.store.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sdsmetro.store.mongo.document.DisqualifiedCitizenDoc;

public interface DisqualifiedCitizenMongoRepository extends MongoRepository<DisqualifiedCitizenDoc, String> {
	//
	List<DisqualifiedCitizenDoc> findByMetroId(String metroId);

}
