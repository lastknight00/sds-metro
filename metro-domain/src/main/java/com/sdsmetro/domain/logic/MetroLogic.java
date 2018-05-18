package com.sdsmetro.domain.logic;

import com.sdsmetro.domain.entity.Metro;
import com.sdsmetro.domain.spec.MetroService;
import com.sdsmetro.domain.spec.sdo.MetroCdo;
import com.sdsmetro.domain.store.MetroStore;
import com.sdsmetro.domain.store.MetroStoreLycler;
import io.naraplatform.share.domain.NameValueList;

import java.util.List;

public class MetroLogic implements MetroService {
    //
    private MetroStore metroStore;

    public MetroLogic(MetroStoreLycler storeLycler) {
        //
        this.metroStore = storeLycler.requestMetroStore();
    }

    @Override
    public String buildMetro(MetroCdo metroCdo) {
        //
        String name = metroCdo.getName();
        String memo = metroCdo.getMemo();
        Metro metro = new Metro( name, memo);

        metroStore.create(metro);

        return metro.getId();
    }

    @Override
    public Metro findMetro(String metroId) {
        //
        return metroStore.retrieve(metroId);
    }

    @Override
    public boolean existMetroByName(String name) {
        //
        return metroStore.existByName(name);
    }

    @Override
    public List<Metro> findMetros() {
        //
        return metroStore.retrieveAll();
    }

    @Override
    public void modifyMetro(String metroId, NameValueList nameValues) {
        //
        Metro metro = this.findMetro(metroId);
        metro.setValues(nameValues);

        metroStore.update(metro);
    }

    @Override
    public void removeMetro(String metroId) {
        //
        Metro metro = metroStore.retrieve(metroId);
        metroStore.delete(metro);
    }
}