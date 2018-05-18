package com.sdsmetro.domain.entity;


import io.naraplatform.share.domain.*;
import io.naraplatform.share.domain.granule.Name;
import io.naraplatform.share.domain.granule.Phone;
import io.naraplatform.share.domain.granule.TieredEmail;
import io.naraplatform.share.util.json.JsonUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Citizen extends NaraEntity implements NaraAggregate {
    //
    private long sequence;
    private Name name;
    private String username;
    private String email;
    private String phone;           // optional
    private boolean guest;
    private long time;

    private String metroId;

    transient List<LoginTime> loginTimes;
    transient LoginUser loginUser;

    public Citizen(String id) {
        //
        super(id);
    }

    public Citizen(String metroId, Name name, String email) {
        //
        super();
        this.name = name;
        this.username = "";
        this.email = email;
        this.time = System.currentTimeMillis();
        this.metroId = metroId;
    }

    @Override
    public String toString() {
        //
        return toJson();
    }

    public static Citizen getSample() {
        //
        Metro metro = Metro.getSample();
        Name name = Name.getSample();
        String email = TieredEmail.getSample().getEmail();

        Citizen sample = new Citizen(metro.getId(), name, email);
        sample.setUsername("doitfriend");
        sample.setPhone(Phone.getSample().getDisplayNumber());

        return sample;
    }

    public void setValues(NameValueList nameValues) {
        //
        for(NameValue nameValue : nameValues.list()) {
            String value = nameValue.getValue();
            switch(nameValue.getName()) {
                case "name":        this.name = Name.fromJson(value); break;
                case "username":    this.username = value; break;
                case "email":       this.email = value; break;
                case "phone":       this.phone = value; break;
                case "guest":       this.guest = Boolean.valueOf(value); break;
            }
        }
    }

    public IdName getIdName() {
        //
        return new IdName(getId(), getName().getDisplayName());
    }

    public static Citizen fromJson(String json) {
        //
        return JsonUtil.fromJson(json, Citizen.class);
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
    }
}
