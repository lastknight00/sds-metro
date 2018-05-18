package com.sdsmetro.container;

import com.sdsmetro.domain.store.CitizenStore;
import com.sdsmetro.domain.store.LoginStore;
import com.sdsmetro.domain.store.MetroStore;
import com.sdsmetro.domain.store.MetroStoreLycler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MetroStoreSpringLycler implements MetroStoreLycler {
    //
    @Autowired
    private MetroStore metroStore;

    @Autowired
    private CitizenStore citizenStore;

    @Autowired
    private LoginStore loginStore;

    @Override
    public MetroStore requestMetroStore() {
        //
        return metroStore;
    }

    @Override
    public CitizenStore requestCitizenStore() {
        //
        return citizenStore;
    }

    @Override
    public LoginStore requestLoginStore() {
        //
        return loginStore;
    }
}