package io.naraplatform.share.domain.granule;

import io.naraplatform.share.domain.IdName;
import io.naraplatform.share.domain.Tier;
import io.naraplatform.share.util.json.JsonSerializable;
import io.naraplatform.share.util.json.JsonUtil;

public class Admin implements JsonSerializable {
    //
    private Tier tier;
    private String id;
    private String name;
    private long time;

    public Admin() {
        //
    }

    public Admin(IdName idName) {
        //
        this(Tier.Primary, idName.getId(), idName.getName());
    }

    public Admin(String id, String name) {
        //
        this(Tier.Primary, id, name);
    }

    public Admin(Tier tier, String id, String name) {
        //
        this.tier = tier;
        this.id = id;
        this.name = name;
        this.time = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        //
        return toJson();
    }

    public static Admin getSample() {
        //
        IdName idName = IdName.getSample();
        Admin sample = new Admin(idName.getId(), idName.getName());

        return sample;
    }

    public static Admin fromJson(String json) {
        //
        return JsonUtil.fromJson(json, Admin.class);
    }

    public Tier getTier() {
        return tier;
    }

    public void setTier(Tier tier) {
        this.tier = tier;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
    }
}
