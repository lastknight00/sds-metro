package com.sdsmetro.store.mongo.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.sdsmetro.store.mongo.document.LoginTimeDoc;

public interface LoginTimeMongoRepository extends MongoRepository<LoginTimeDoc, String> {
	//
	void deleteByCitizenId(String citizenId);

	List<LoginTimeDoc> findByCitizenId(String citizenId, PageRequest pageRequest);

}
