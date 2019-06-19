package com.room.microservice.domain;


public enum CategoryEnum {

    UNCATEGORIZAED,
    ONESTAR,
    TWOSTAR,
    THREESTAR,
    FOURSTAR,
    FIVESTAR;

    public String value() {
        return name();
    }

    public static CategoryEnum fromValue(String v) {
        return valueOf(v);
    }

}
