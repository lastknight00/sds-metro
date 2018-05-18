package com.sdsmetro.store.mongo.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.sdsmetro.store.mongo.document.LoginUserDoc;

public interface LoginUserMongoRepository extends MongoRepository<LoginUserDoc, String> {
	//
	LoginUserDoc findByMetroIdAndEmail(String metroId, String email);

	LoginUserDoc findByMetroIdAndUsername(String metroId, String username);

	List<LoginUserDoc> findByMetroId(String metroId, PageRequest pageRequest);

}
