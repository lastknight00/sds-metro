package com.sdsmetro.domain.spec.sdo;

import io.naraplatform.share.util.json.JsonSerializable;
import io.naraplatform.share.util.json.JsonUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MetroCdo implements JsonSerializable {
    //
    private String name;
    private String memo;

    public MetroCdo(String name) {
        //
        this.name = name;
    }

    public MetroCdo(String name, String memo) {
        //
        this.name = name;
        this.memo = memo;
    }

    @Override
    public String toString() {
        //
        return toJson();
    }

    public static MetroCdo getSample() {
        //
        return new MetroCdo("Nextree", "Nextree Soft & Nextree Consulting");
    }

    public static MetroCdo fromJson(String json) {
        //
        return JsonUtil.fromJson(json, MetroCdo.class);
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
    }
}