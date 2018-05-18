package com.sdsmetro.store.mongo.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.sdsmetro.store.mongo.document.CitizenDoc;

public interface CitizenMongoRepository extends MongoRepository<CitizenDoc, String> {
	//
	CitizenDoc findByMetroIdAndUsername(String metroId, String username);

	CitizenDoc findByMetroIdAndEmail(String metroId, String email);

	List<CitizenDoc> findByEmail(String email);

	List<CitizenDoc> findByMetroIdAndName_DisplayName(String metroId, String displayName);

	List<CitizenDoc> findByMetroId(String metroId, Pageable pageable);
	
}
