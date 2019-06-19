package com.room.microservice.domain;

public enum ReservationStateEnum {

    ALLOWED,
    NOTALLOWED,
    PENDING;

    public String value() {
        return name();
    }

    public static ReservationStateEnum fromValue(String v) {
        return valueOf(v);
    }

}