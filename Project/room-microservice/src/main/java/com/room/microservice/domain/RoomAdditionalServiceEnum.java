package com.room.microservice.domain;




public enum RoomAdditionalServiceEnum {

    WIFI,
    PARKING,
    BREAKFAST,
    BEDANDBREKFAST,
    BEDANDBOARD,
    ALLINCLUSIVE,
    PETFRIENDLY,
    TV,
    KITCHEN,
    BATHROOM,
    ROOMCANCEL;

    public String value() {
        return name();
    }

    public static RoomAdditionalServiceEnum fromValue(String v) {
        return valueOf(v);
    }

}
