package com.sdsmetro.domain.store.memory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import com.sdsmetro.domain.entity.Citizen;
import com.sdsmetro.domain.entity.DisqualifiedCitizen;
import com.sdsmetro.domain.store.CitizenStore;

public class CitizenMemStore implements CitizenStore {
	
	private Map<String, List<DisqualifiedCitizen>> disqualifiedCitizens;
	private Map<String, Citizen> citizens;
	
	public CitizenMemStore() {
		//
		citizens = new HashMap<String, Citizen>();
		disqualifiedCitizens = new HashMap<String, List<DisqualifiedCitizen>>();
	}

	@Override
	public String create(Citizen citizen) {
		//
		citizens.put(citizen.getId(), citizen);
		return citizen.getId();
	}

	@Override
	public Citizen retrieve(String id) throws NoSuchElementException {
		//
		if (!citizens.containsKey(id)) {
			throw new NoSuchElementException("Entity not found : " + id);
		}
		
		return citizens.get(id);
	}

	@Override
	public List<Citizen> retrieveByName(String metroId, String name) {
		//
//		Iterator<String> keySet = citizens.keySet().iterator();
//		while (keySet.hasNext()) {
//			Citizen currentCitizen = citizens.get(keySet.next());
//			if (currentCitizen.getMetroId().equals(metroId)
//					&& currentCitizen.getName().equals(name)) {
//				return currentCitizen;
//			}
//		}
		
		return Collections.emptyList();
	}

	@Override
	public Citizen retrieveByMetroEmail(String metroId, String email) {
		//
		Iterator<String> keySet = citizens.keySet().iterator();
		while (keySet.hasNext()) {
			Citizen currentCitizen = citizens.get(keySet.next());
			if (currentCitizen.getMetroId().equals(metroId)
					&& currentCitizen.getEmail().equals(email)) {
				return currentCitizen;
			}
		}
		
		return null;
	}

	@Override
	public Citizen retrieveByMetroUsername(String metroId, String username) {
		//
		Iterator<String> keySet = citizens.keySet().iterator();
		while (keySet.hasNext()) {
			Citizen currentCitizen = citizens.get(keySet.next());
			if (currentCitizen.getMetroId().equals(metroId)
					&& currentCitizen.getUsername().equals(username)) {
				return currentCitizen;
			}
		}
		
		return null;
	}

	@Override
	public List<Citizen> retrieveByEmail(String email) {
		//
		List<Citizen> results = new ArrayList<Citizen>();
		
		Iterator<String> keySet = citizens.keySet().iterator();
		while (keySet.hasNext()) {
			Citizen currentCitizen = citizens.get(keySet.next());
			if (currentCitizen.getEmail().equals(email)) {
				results.add(currentCitizen);
			}
		}
		
		return results;
	}

	@Override
	public List<Citizen> retrieveByMetro(String metroId, int offset, int limit) {
		//
		List<Citizen> results = new ArrayList<Citizen>();
		
		Iterator<String> keySet = citizens.keySet().iterator();
		while (keySet.hasNext()) {
			Citizen currentCitizen = citizens.get(keySet.next());
			if (currentCitizen.getMetroId().equals(metroId)) {
				results.add(currentCitizen);
			}
		}
		
		if (results.size() >= offset + limit) {
			return results.subList(offset, offset+limit);
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	@Override
	public void update(Citizen citizen) {
		//
		citizens.put(citizen.getId(), citizen);
	}

	@Override
	public void delete(Citizen citizen) {
		//
		citizens.remove(citizen.getId());
	}

	@Override
	public void createDisqualified(DisqualifiedCitizen citizen) {
		//
		if (disqualifiedCitizens.containsKey(citizen.getMetroId())) {
			disqualifiedCitizens.get(citizen.getMetroId()).add(0, citizen);
		} else {
			disqualifiedCitizens.put(citizen.getMetroId(), Arrays.asList(citizen));
		}
	}

	@Override
	public DisqualifiedCitizen retrieveDisqualified(String id) throws NoSuchElementException {
		//
		Iterator<String> keySet = disqualifiedCitizens.keySet().iterator();
		while (keySet.hasNext()) {
			List<DisqualifiedCitizen> currentCitizens = disqualifiedCitizens.get(keySet.next());
			for (DisqualifiedCitizen currentCitizen: currentCitizens) {
				if (currentCitizen.getId().equals(id)) {
					return currentCitizen;
				}
			}
		}
		
		return null;
	}

	@Override
	public List<DisqualifiedCitizen> retrieveDisqualifiedByMetro(String metroId) {
		//
		if (disqualifiedCitizens.containsKey(metroId)) {
			return disqualifiedCitizens.get(metroId);
		}
		
		return Collections.emptyList();
	}

	@Override
	public void delete(DisqualifiedCitizen citizen) {
		//
		throw new RuntimeException("Not Implemented!");
	}

}
