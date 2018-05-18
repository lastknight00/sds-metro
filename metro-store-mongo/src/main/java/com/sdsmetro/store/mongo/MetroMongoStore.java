package com.sdsmetro.store.mongo;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sdsmetro.domain.entity.Metro;
import com.sdsmetro.domain.store.MetroStore;
import com.sdsmetro.store.mongo.document.MetroDoc;
import com.sdsmetro.store.mongo.repository.MetroMongoRepository;

import io.naraplatform.share.exception.store.AlreadyExistsException;

@Repository
public class MetroMongoStore implements MetroStore {

	@Autowired
	private MetroMongoRepository metroMongoRepository;

	public String create(Metro metro) {
		//
		String id = metro.getId();
		if (metroMongoRepository.existsById(id)) throw new AlreadyExistsException(String.format("Metro document[ID:%s] already exist.", id));

		MetroDoc metroDoc = MetroDoc.toDocument(metro);
		metroMongoRepository.save(metroDoc);

		return id;
	}

	public Metro retrieve(String id) throws NoSuchElementException {
		//
		Optional<MetroDoc> metroDoc = metroMongoRepository.findById(id);
		if (!metroDoc.isPresent()) throw new NoSuchElementException(String.format("No Metro[%s] to retrieve.", id));

		return metroDoc.get().toDomain();
	}

	public Metro retrieveByName(String metroName) {
		//
		MetroDoc metroDoc = metroMongoRepository.findByName(metroName);
		if (metroDoc == null) return null;
		return metroDoc.toDomain();
	}

	public List<Metro> retrieveAll() {
		//
		List<MetroDoc> metroDocs = metroMongoRepository.findAll();
		return MetroDoc.toDomains(metroDocs);
	}

	public void update(Metro metro) {
		//
		if (!metroMongoRepository.existsById(metro.getId())) throw new NoSuchElementException(String.format("No Metro document[ID:%s] to update.", metro.getId()));
		MetroDoc metroDoc = MetroDoc.toDocument(metro);
		metroMongoRepository.save(metroDoc);
	}

	public void delete(Metro metro) {
		//
		metroMongoRepository.deleteById(metro.getId());
	}

	public boolean existByName(String name) {
		//
		MetroDoc metroDoc = metroMongoRepository.findByName(name);
		if (metroDoc == null || metroDoc.getId() == null) return false;
		return true;
	}

}
