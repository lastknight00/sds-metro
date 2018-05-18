package com.sdsmetro.domain.spec.sdo;

import com.sdsmetro.domain.entity.Metro;
import io.naraplatform.share.domain.granule.Name;
import io.naraplatform.share.domain.granule.TieredEmail;
import io.naraplatform.share.util.json.JsonSerializable;
import io.naraplatform.share.util.json.JsonUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CitizenCdo implements JsonSerializable {
    //
    private String metroId;
    private Name name;
    private String email;

    private String username;
    private String password;
    private String phone;

    public CitizenCdo(String metroId, Name name, String email) {
        //
        this.metroId = metroId;
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return toJson();
    }

    public static CitizenCdo getSample() {
        //
        String metroId = Metro.getSample().getId();
        Name name = Name.getSample();
        String email = TieredEmail.getSample().getEmail();

        CitizenCdo sample = new CitizenCdo(metroId, name, email);

        return sample;
    }

    public static CitizenCdo fromJson(String json) {
        //
        return JsonUtil.fromJson(json, CitizenCdo.class);
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
    }
}