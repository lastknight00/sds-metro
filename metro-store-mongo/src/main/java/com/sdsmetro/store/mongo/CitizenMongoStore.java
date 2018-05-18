package com.sdsmetro.store.mongo;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.sdsmetro.domain.entity.Citizen;
import com.sdsmetro.domain.entity.DisqualifiedCitizen;
import com.sdsmetro.domain.store.CitizenStore;
import com.sdsmetro.store.mongo.document.CitizenDoc;
import com.sdsmetro.store.mongo.document.DisqualifiedCitizenDoc;
import com.sdsmetro.store.mongo.repository.CitizenMongoRepository;
import com.sdsmetro.store.mongo.repository.DisqualifiedCitizenMongoRepository;

import io.naraplatform.share.exception.store.AlreadyExistsException;

@Repository
public class CitizenMongoStore implements CitizenStore {

	@Autowired
	CitizenMongoRepository citizenMongoRepository;
	@Autowired
	DisqualifiedCitizenMongoRepository disqualifiedCitizenMongoRepository;

	public String create(Citizen citizen) {
		//
		String id = citizen.getId();
		if (citizenMongoRepository.existsById(id)) throw new AlreadyExistsException(String.format("Citizen document[ID:%s] already exist.", id));

		CitizenDoc citizenDoc = CitizenDoc.toDocument(citizen);
		citizenMongoRepository.save(citizenDoc);

		return id;
	}

	public Citizen retrieve(String id) throws NoSuchElementException {
		//
		Optional<CitizenDoc> citizenDoc = citizenMongoRepository.findById(id);
		if (!citizenDoc.isPresent()) throw new NoSuchElementException(String.format("No citizen[%s] to retrieve.", id));

		return citizenDoc.get().toDomain();
	}

	public List<Citizen> retrieveByName(String metroId, String name) {
		//
		List<CitizenDoc> citizenDocs = citizenMongoRepository.findByMetroIdAndName_DisplayName(metroId, name);
		return CitizenDoc.toDomains(citizenDocs);
	}

	public Citizen retrieveByMetroEmail(String metroId, String email) {
		//
		CitizenDoc citizenDoc = citizenMongoRepository.findByMetroIdAndEmail(metroId, email);
		if (citizenDoc == null) return null;
		return citizenDoc.toDomain();
	}

	public Citizen retrieveByMetroUsername(String metroId, String username) {
		//
		CitizenDoc citizenDoc = citizenMongoRepository.findByMetroIdAndUsername(metroId, username);
		if (citizenDoc == null) return null;
		return citizenDoc.toDomain();
	}

	public List<Citizen> retrieveByEmail(String email) {
		//
		List<CitizenDoc> citizenDocs = citizenMongoRepository.findByEmail(email);
		return CitizenDoc.toDomains(citizenDocs);
	}

	public List<Citizen> retrieveByMetro(String metroId, int offset, int limit) {
		//
		List<CitizenDoc> citizenDocs = citizenMongoRepository.findByMetroId(metroId, PageRequest.of(offset, limit));
		return citizenDocs.stream()
				.map(doc -> doc.toDomain())
				.collect(Collectors.toList());
	}

	public void update(Citizen citizen) {
		//
		if (!citizenMongoRepository.existsById(citizen.getId())) throw new NoSuchElementException(String.format("No citizen document[ID:%s] to update.", citizen.getId()));
		CitizenDoc citizenDoc = CitizenDoc.toDocument(citizen);
		citizenMongoRepository.save(citizenDoc);
	}

	public void delete(Citizen citizen) {
		//
		citizenMongoRepository.deleteById(citizen.getId());
	}

	public void createDisqualified(DisqualifiedCitizen citizen) {
		//
		String id = citizen.getId();
		if (disqualifiedCitizenMongoRepository.existsById(id)) throw new AlreadyExistsException(String.format("DisqualifiedCitizen document[ID:%s] already exist.", id));

		DisqualifiedCitizenDoc citizenDoc = DisqualifiedCitizenDoc.toDocument(citizen);
		disqualifiedCitizenMongoRepository.save(citizenDoc);
	}

	public DisqualifiedCitizen retrieveDisqualified(String id) throws NoSuchElementException {
		//
		Optional<DisqualifiedCitizenDoc> citizenDoc = disqualifiedCitizenMongoRepository.findById(id);
		if (!citizenDoc.isPresent()) throw new NoSuchElementException(String.format("No citizen[%s] to retrieve.", id));

		return citizenDoc.get().toDomain();
	}

	public List<DisqualifiedCitizen> retrieveDisqualifiedByMetro(String metroId) {
		//
		List<DisqualifiedCitizenDoc> citizenDocs = disqualifiedCitizenMongoRepository.findByMetroId(metroId);
		return DisqualifiedCitizenDoc.toDomains(citizenDocs);
	}

	public void delete(DisqualifiedCitizen citizen) {
		//
		disqualifiedCitizenMongoRepository.deleteById(citizen.getId());
	}

}
