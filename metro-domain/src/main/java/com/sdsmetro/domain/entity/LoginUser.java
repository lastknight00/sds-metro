package com.sdsmetro.domain.entity;

import io.naraplatform.share.domain.NameValue;
import io.naraplatform.share.domain.NameValueList;
import io.naraplatform.share.domain.NaraEntity;
import io.naraplatform.share.util.json.JsonUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginUser extends NaraEntity {
    //
    private String username;        // login user name
    private String email;
    private String password;
    private long passwordUpdateTime;
    private String metroId;

    public LoginUser(Citizen citizen, String password) {
        //
        super(citizen.getId());
        this.username = citizen.getUsername();
        this.email = citizen.getEmail();
        this.password = password;
        this.passwordUpdateTime = System.currentTimeMillis();
        this.metroId = citizen.getMetroId();
    }

    public LoginUser(String id) {
        //
        super(id);
    }

    public static LoginUser getSample() {
        //
        Citizen citizen = Citizen.getSample();
        LoginUser sample = new LoginUser(citizen, "1234");

        return sample;
    }

    public void setValues(NameValueList nameValues) {
        //
        for(NameValue nameValue : nameValues.list()) {
            String value = nameValue.getValue();
            switch(nameValue.getName()) {
                case "password":
                    if (password.equals(value)) {
                        break;
                    }
                    this.password = value;
                    this.passwordUpdateTime = System.currentTimeMillis();
                    break;
                case "email":
                    this.email = value;
                    break;
            }
        }
    }

    public static LoginUser fromJson(String json) {
        //
        return JsonUtil.fromJson(json, LoginUser.class);
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
    }
}
