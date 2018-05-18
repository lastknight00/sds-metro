package com.sdsmetro.domain.spec.sdo;

import com.sdsmetro.domain.entity.Citizen;
import io.naraplatform.share.util.json.JsonSerializable;
import io.naraplatform.share.util.json.JsonUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginUserCdo  implements JsonSerializable {
    //
    private String username;
    private String password;

    public LoginUserCdo(String username, String password) {
        //
        this.username = username;
        this.password = password;
    }

    public static LoginUserCdo getSample() {
        //
        Citizen citizen = Citizen.getSample();
        String username = "doitfriend";
        String password = "1234";

        LoginUserCdo sample = new LoginUserCdo(username, password);

        return sample;
    }

    public static LoginUserCdo fromJson(String json) {
        //
        return JsonUtil.fromJson(json, LoginUserCdo.class);
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
    }
}