package com.sdsmetro.container.bean;

import com.sdsmetro.domain.logic.CitizenLogic;
import com.sdsmetro.domain.store.MetroStoreLycler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("citizenLogic")
@Transactional
public class CitizenSpringLogic extends CitizenLogic {
    //
    @Autowired
    public CitizenSpringLogic(MetroStoreLycler storeLycler) {
        //
        super(storeLycler);
    }
}
