package com.sdsmetro.domain.spec;

import com.sdsmetro.domain.entity.Citizen;
import com.sdsmetro.domain.entity.DisqualifiedCitizen;
import com.sdsmetro.domain.spec.sdo.CitizenCdo;
import io.naraplatform.share.domain.NameValueList;

import java.util.List;

public interface CitizenService {
    //
    String registerCitizen(CitizenCdo citizenCdo);
    String registerMetroAdminCitizen(CitizenCdo citizenCdo);
    Citizen findCitizen(String citizenId);
    List<Citizen> findCitizenByEmail(String email);
    Citizen findCitizenByMetroEmail(String metroId, String email);
    List<Citizen> findCitizenByMetro(String metroId, int offset, int limit);
    void modifyCitizen(String citizenId, NameValueList nameValues);
    String disqualifyCitizen(String citizenId);
    List<DisqualifiedCitizen> findDisqualifiedCitizenByMetro(String metroId);

    boolean existsCitizenEmail(String metroId, String email);
    boolean existsCitizenUsername(String metroId, String username);
}