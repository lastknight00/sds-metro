package com.sdsmetro.domain.logic;

import com.sdsmetro.domain.entity.Citizen;
import com.sdsmetro.domain.entity.LoginTime;
import com.sdsmetro.domain.entity.LoginUser;
import com.sdsmetro.domain.spec.LoginService;
import com.sdsmetro.domain.spec.sdo.LoginUserCdo;
import com.sdsmetro.domain.store.CitizenStore;
import com.sdsmetro.domain.store.LoginStore;
import com.sdsmetro.domain.store.MetroStoreLycler;
import io.naraplatform.share.domain.NameValueList;

import java.util.List;

public class LoginLogic implements LoginService {
    //
    private CitizenStore citizenStore;
    private LoginStore loginStore;

    public LoginLogic(MetroStoreLycler storeLycler) {
        //
        this.citizenStore = storeLycler.requestCitizenStore();
        this.loginStore = storeLycler.requestLoginStore();
    }

    @Override
    public String registerLoginUser(String citizenId, LoginUserCdo loginUserCdo) {
        //
        Citizen citizen = citizenStore.retrieve(citizenId);

        LoginUser loginUser = new LoginUser(citizen, loginUserCdo.getPassword());
        loginUser.setUsername(loginUserCdo.getUsername());

        loginStore.create(loginUser);

        return loginUser.getId();
    }

    @Override
    public LoginUser findLoginUser(String citizenId) {
        //
        return loginStore.retrieve(citizenId);
    }

    @Override
    public List<LoginUser> findLoginUserByMetro(String metroId, int offset, int limit) {
        //
        return loginStore.retrieveByMetroId(metroId, offset, limit);
    }

    @Override
    public void modifyLoginUser(String citizenId, NameValueList nameValues) {
        //
        LoginUser loginUser = loginStore.retrieve(citizenId);
        loginUser.setValues(nameValues);
        loginStore.update(loginUser);
    }

    @Override
    public void removeLoginUser(String citizenId) {
        //
        LoginUser loginUser = loginStore.retrieve(citizenId);
        loginStore.delete(loginUser);
    }

    @Override
    public List<LoginTime> findLoginTimeByCitizen(String citizenId, int offset, int limit) {
        //
        return loginStore.retrieveLoginTimeByCitizenId(citizenId, offset, limit);
    }

    @Override
    public void removeLoginTimesByCitizen(String citizenId) {
        //
        loginStore.deleteLoginTimesByCitizenId(citizenId);
    }

}