package com.sdsmetro.container.bean;

import com.sdsmetro.domain.logic.LoginLogic;
import com.sdsmetro.domain.store.MetroStoreLycler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("loginLogic")
@Transactional
public class LoginSpringLogic extends LoginLogic {
    //
    @Autowired
    public LoginSpringLogic(MetroStoreLycler storeLycler) {
        //
        super(storeLycler);
    }
}
