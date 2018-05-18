package com.sdsmetro.domain.spec;

import com.sdsmetro.domain.entity.LoginTime;
import com.sdsmetro.domain.entity.LoginUser;
import com.sdsmetro.domain.spec.sdo.LoginUserCdo;
import io.naraplatform.share.domain.NameValueList;

import java.util.List;

public interface LoginService {
    //
    String registerLoginUser(String citizenId, LoginUserCdo loginUserCdo);
    LoginUser findLoginUser(String citizenId);
    List<LoginUser> findLoginUserByMetro(String metroId, int offset, int limit);
    void modifyLoginUser(String citizenId, NameValueList nameValues);
    void removeLoginUser(String citizenId);
    List<LoginTime> findLoginTimeByCitizen(String citizenId, int offset, int limit);
    void removeLoginTimesByCitizen(String citizenId);
}
