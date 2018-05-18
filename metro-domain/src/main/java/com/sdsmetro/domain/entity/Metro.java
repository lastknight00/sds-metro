package com.sdsmetro.domain.entity;


import io.naraplatform.share.domain.NameValue;
import io.naraplatform.share.domain.NameValueList;
import io.naraplatform.share.domain.NaraAggregate;
import io.naraplatform.share.domain.NaraEntity;
import io.naraplatform.share.domain.granule.Admin;
import io.naraplatform.share.domain.granule.AdminList;
import io.naraplatform.share.util.json.JsonUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Metro extends NaraEntity implements NaraAggregate {
    //
    private String name;
    private String memo;
    private AdminList admins;
    private long time;

    transient private List<Citizen> citizens;
    transient private List<DisqualifiedCitizen> disqualifiedCitizens;

    public Metro(String id) {
        //
        super(id);
    }

    public Metro(String name, String memo) {
        //
        super();
        this.name = name;
        this.memo = memo;
        this.admins = new AdminList();
        this.time = System.currentTimeMillis();
    }

    public static Metro getSample() {
        //
        String name = "Nextree";
        String memo = "Nextree Groupware";

        Metro sample = new Metro(name, memo);
        sample.getAdmins().add(Admin.getSample());

        return sample;
    }

    public void setValues(NameValueList nameValues) {
        //
        for(NameValue nameValue : nameValues.list()) {
            String value = nameValue.getValue();
            switch(nameValue.getName()) {
                case "name":        this.name = value; break;
                case "memo":        this.memo = value; break;
                case "admins":      this.admins = AdminList.fromJson(value); break;
            }
        }
    }

    public static Metro fromJson(String json) {
        //
        return JsonUtil.fromJson(json, Metro.class);
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
    }
}
