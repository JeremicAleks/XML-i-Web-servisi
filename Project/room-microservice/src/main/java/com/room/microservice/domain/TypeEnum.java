package com.room.microservice.domain;



public enum TypeEnum {

    HOTEL,
    BEDANDBREKFAST,
    APARTMAN;

    public String value() {
        return name();
    }

    public static TypeEnum fromValue(String v) {
        return valueOf(v);
    }

}
