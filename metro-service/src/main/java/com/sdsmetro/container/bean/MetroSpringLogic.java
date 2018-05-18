package com.sdsmetro.container.bean;

import com.sdsmetro.domain.logic.MetroLogic;
import com.sdsmetro.domain.store.MetroStoreLycler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("metroLogic")
@Transactional
public class MetroSpringLogic extends MetroLogic {
    //
    @Autowired
    public MetroSpringLogic(MetroStoreLycler storeLycler) {
        //
        super(storeLycler);
    }
}
