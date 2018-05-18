package com.sdsmetro.domain.store.memory;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import com.sdsmetro.domain.entity.LoginTime;
import com.sdsmetro.domain.entity.LoginUser;
import com.sdsmetro.domain.store.LoginStore;

public class LoginMemStore implements LoginStore {
	
	private Map<String, List<LoginTime>> loginTimes;
	private Map<String, LoginUser> loginUsers;
	
	public LoginMemStore() {
		//
		loginUsers = new HashMap<String, LoginUser>();
		loginTimes = new HashMap<String, List<LoginTime>>();
	}

	@Override
	public void create(LoginUser loginUser) {
		//
		loginUsers.put(loginUser.getId(), loginUser);
	}

	@Override
	public LoginUser retrieve(String id) throws NoSuchElementException {
		//
		if (!loginUsers.containsKey(id)) {
			throw new NoSuchElementException("Entity not found : " + id);
		}
		
		return loginUsers.get(id);
	}

	@Override
	public LoginUser retrieveByMetroIdAndEmail(String metroId, String email) {
		//
		Iterator<String> keySet = loginUsers.keySet().iterator();
		while (keySet.hasNext()) {
			LoginUser currentLoginUser = loginUsers.get(keySet.next());
			if (currentLoginUser.getMetroId().equals(metroId)
					&& currentLoginUser.getEmail().equals(email)) {
				return currentLoginUser;
			}
		}
		
		return null;
	}

	@Override
	public LoginUser retrieveByMetroIdAndUserName(String metroId, String userName) {
		//
		Iterator<String> keySet = loginUsers.keySet().iterator();
		while (keySet.hasNext()) {
			LoginUser currentLoginUser = loginUsers.get(keySet.next());
			if (currentLoginUser.getMetroId().equals(metroId)
					&& currentLoginUser.getUsername().equals(userName)) {
				return currentLoginUser;
			}
		}
		
		return null;
	}

	@Override
	public List<LoginUser> retrieveByMetroId(String metroId, int offset, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(LoginUser loginUser) {
		//
		loginUsers.put(loginUser.getId(), loginUser);
	}

	@Override
	public void delete(LoginUser loginUser) {
		//
		loginUsers.remove(loginUser.getId());
		
	}

	@Override
	public void createLoginTime(LoginTime loginTime) {
		//
		if (loginTimes.containsKey(loginTime.getCitizenId())) {
			loginTimes.get(loginTime.getCitizenId()).add(loginTime);
		} else {
			loginTimes.put(loginTime.getCitizenId(), Arrays.asList(loginTime));
		}
	}

	@Override
	public List<LoginTime> retrieveLoginTimeByCitizenId(String citizenId, int offset, int limit) {
		//
		if (!loginTimes.containsKey(citizenId)) {
			return Collections.emptyList();
		}
		
		List<LoginTime> currentLoginTimes = loginTimes.get(citizenId);
		if (currentLoginTimes.size() >= offset + limit) {
			return currentLoginTimes.subList(offset, offset + limit);
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	@Override
	public void deleteLoginTime(String id) {
		//
		throw new RuntimeException("Not Implemented!");
	}

	@Override
	public void deleteLoginTimesByCitizenId(String citizenId) {
		//
		loginTimes.remove(citizenId);
	}

}
