package com.sdsmetro.domain.store.memory;

import com.sdsmetro.domain.store.CitizenStore;
import com.sdsmetro.domain.store.LoginStore;
import com.sdsmetro.domain.store.MetroStore;
import com.sdsmetro.domain.store.MetroStoreLycler;

public class MetroMemStoreLycler implements MetroStoreLycler {
	
	private static MetroStore metroStore = new MetroMemStore();
	private static CitizenStore citizenStore = new CitizenMemStore();
	private static LoginStore loginStore = new LoginMemStore();
	
	@Override
	public MetroStore requestMetroStore() {
		return metroStore;
	}

	@Override
	public CitizenStore requestCitizenStore() {
		return citizenStore;
	}

	@Override
	public LoginStore requestLoginStore() {
		return loginStore;
	}}