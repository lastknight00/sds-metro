package com.sdsmetro.domain.entity;


import io.naraplatform.share.domain.NaraEntity;
import io.naraplatform.share.domain.granule.Name;
import io.naraplatform.share.util.json.JsonUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DisqualifiedCitizen extends NaraEntity {
    // immutable object
    //
    private Name name;
    private String email;
    private String phone;           // optional
    private boolean guest;
    private long time;
    private long disqualifiedTime;
    private String metroId;

    transient private Citizen citizen;

    public DisqualifiedCitizen(String id) {
        //
        super(id);
    }

    public DisqualifiedCitizen(Citizen citizen) {
        //
        super(citizen.getId());
        this.name = citizen.getName();
        this.email = citizen.getEmail();
        this.phone = citizen.getPhone();
        this.guest = citizen.isGuest();
        this.time = citizen.getTime();
        this.disqualifiedTime = System.currentTimeMillis();
        this.metroId = citizen.getMetroId();
    }

    public static DisqualifiedCitizen getSample() {
        //
        Citizen citizen = Citizen.getSample();

        DisqualifiedCitizen sample = new DisqualifiedCitizen(citizen);

        return sample;
    }

    public static DisqualifiedCitizen fromJson(String json) {
        //
        return JsonUtil.fromJson(json, DisqualifiedCitizen.class);
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
    }
}