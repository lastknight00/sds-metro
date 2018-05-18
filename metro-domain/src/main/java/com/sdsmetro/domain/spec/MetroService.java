package com.sdsmetro.domain.spec;

import com.sdsmetro.domain.entity.Metro;
import com.sdsmetro.domain.spec.sdo.MetroCdo;
import io.naraplatform.share.domain.NameValueList;

import java.util.List;

public interface MetroService {
    //
    String buildMetro(MetroCdo metroCdo);
    Metro findMetro(String metroId);
    boolean existMetroByName(String name);
    List<Metro> findMetros();
    void modifyMetro(String metroId, NameValueList nameValues);
    void removeMetro(String metroId);
}