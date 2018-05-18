package com.sdsmetro.store.mongo;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.sdsmetro.domain.entity.LoginTime;
import com.sdsmetro.domain.entity.LoginUser;
import com.sdsmetro.domain.store.LoginStore;
import com.sdsmetro.store.mongo.document.LoginTimeDoc;
import com.sdsmetro.store.mongo.document.LoginUserDoc;
import com.sdsmetro.store.mongo.repository.LoginTimeMongoRepository;
import com.sdsmetro.store.mongo.repository.LoginUserMongoRepository;

import io.naraplatform.share.exception.store.AlreadyExistsException;
import io.naraplatform.share.exception.store.NonExistenceException;
import io.naraplatform.share.util.string.StringUtil;

@Repository
public class LoginMongoStore implements LoginStore {

	@Autowired
	private LoginUserMongoRepository loginUserMongoRepository;

	@Autowired
	private LoginTimeMongoRepository loginTimeMongoRepository;

	public void create(LoginUser loginUser) {
		//
		String id = loginUser.getId();
		if (loginUserMongoRepository.existsById(id)) throw new AlreadyExistsException(String.format("LoginUser document[ID:%s] already exist.", id));

		String username = loginUser.getUsername();
		String metroId = loginUser.getMetroId();
		if (StringUtil.isNotEmpty(username) &&
				(loginUserMongoRepository.findByMetroIdAndUsername(metroId, username) != null))
			throw new AlreadyExistsException(String.format("Login user[%s] already exists in metro[%s]", username, metroId));

		try {
			loginUserMongoRepository.insert(LoginUserDoc.toDocument(loginUser));
		} catch (DataIntegrityViolationException e) {
			throw new AlreadyExistsException(String.format("Login user[%s] already exists in metro[%s]", loginUser.getEmail(), metroId));
		}
	}

	public LoginUser retrieve(String id) throws NoSuchElementException {
		//
		Optional<LoginUserDoc> loginUserDoc = loginUserMongoRepository.findById(id);
		if (!loginUserDoc.isPresent())
			throw new NoSuchElementException(String.format("No login user[%s] to retrieve.", id));

		return loginUserDoc.get().toDomain();
	}

	public LoginUser retrieveByMetroIdAndEmail(String metroId, String email) {
		//
		return loginUserMongoRepository.findByMetroIdAndEmail(metroId, email).toDomain();
	}

	public LoginUser retrieveByMetroIdAndUserName(String metroId, String userName) {
		//
		return loginUserMongoRepository.findByMetroIdAndUsername(metroId, userName).toDomain();
	}

	public List<LoginUser> retrieveByMetroId(String metroId, int offset, int limit) {
		//
		List<LoginUserDoc> loginUserDocs = loginUserMongoRepository.findByMetroId(metroId, PageRequest.of(offset, limit));

		return loginUserDocs.stream()
				.map(doc -> doc.toDomain())
				.collect(Collectors.toList());
	}

	public void update(LoginUser loginUser) {
		//
		if (!loginUserMongoRepository.existsById(loginUser.getId())) throw new NonExistenceException(String.format("No login user[%s] to update.", loginUser.getId()));
		loginUserMongoRepository.save(LoginUserDoc.toDocument(loginUser));
	}

	public void delete(LoginUser loginUser) {
		//
		loginUserMongoRepository.deleteById(loginUser.getId());
	}

	public void createLoginTime(LoginTime loginTime) {
		//
		String id = loginTime.getId();
		if (loginTimeMongoRepository.existsById(id)) throw new AlreadyExistsException(String.format("Login time[%s] already exists.", id));

		loginTimeMongoRepository.insert(LoginTimeDoc.toDocument(loginTime));
	}

	public List<LoginTime> retrieveLoginTimeByCitizenId(String citizenId, int offset, int limit) {
		//
		List<LoginTimeDoc> loginTimeDocs = loginTimeMongoRepository.findByCitizenId(citizenId, PageRequest.of(offset, limit));

		return loginTimeDocs.stream()
				.map(doc -> doc.toDomain())
				.collect(Collectors.toList());
	}

	public void deleteLoginTime(String id) {
		//
		loginTimeMongoRepository.deleteById(id);
	}

	public void deleteLoginTimesByCitizenId(String citizenId) {
		//
		loginTimeMongoRepository.deleteByCitizenId(citizenId);
	}

}
