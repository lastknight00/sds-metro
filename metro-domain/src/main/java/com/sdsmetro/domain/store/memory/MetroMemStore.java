package com.sdsmetro.domain.store.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import com.sdsmetro.domain.entity.Metro;
import com.sdsmetro.domain.store.MetroStore;

public class MetroMemStore implements MetroStore {
	
	private Map<String, Metro> metros;
	
	public MetroMemStore() {
		//
		metros = new HashMap<String, Metro>();
	}

	@Override
	public String create(Metro metro) {
		//
		metros.put(metro.getId(), metro);
		
		return metro.getId();
	}

	@Override
	public Metro retrieve(String id) throws NoSuchElementException {
		//
		if (!metros.containsKey(id)) {
			throw new NoSuchElementException("Entity not found : " + id);
		}
		
		return metros.get(id);
	}

	@Override
	public Metro retrieveByName(String metroName) {
		//
		Iterator<String> keySet = metros.keySet().iterator();
		while (keySet.hasNext()) {
			Metro currentMetro = metros.get(keySet.next());
			if (currentMetro.getName().equals(metroName)) {
				return currentMetro;
			}
		}
		
		return null;
	}
	
	@Override
	public List<Metro> retrieveAll() {
		//
		return new ArrayList<Metro>(metros.values());
	}

	@Override
	public void update(Metro metro) {
		//
		metros.put(metro.getId(), metro);
	}

	@Override
	public void delete(Metro metro) {
		//
		metros.remove(metro.getId());
	}

	@Override
	public boolean existByName(String name) {
		//
		Iterator<String> keySet = metros.keySet().iterator();
		while (keySet.hasNext()) {
			Metro currentMetro = metros.get(keySet.next());
			if (currentMetro.getName().equals(name)) {
				return true;
			}
		}
		
		return false;
	}

}
