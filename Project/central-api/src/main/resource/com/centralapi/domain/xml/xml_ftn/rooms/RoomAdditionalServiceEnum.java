//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.18 at 07:12:02 PM CEST 
//


package com.centralapi.domain.xml.xml_ftn.rooms;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RoomAdditionalServiceEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="RoomAdditionalServiceEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="WIFI"/>
 *     &lt;enumeration value="PARKING"/>
 *     &lt;enumeration value="BREAKFAST"/>
 *     &lt;enumeration value="BEDANDBREKFAST"/>
 *     &lt;enumeration value="BEDANDBOARD"/>
 *     &lt;enumeration value="ALLINCLUSIVE"/>
 *     &lt;enumeration value="PETFRIENDLY"/>
 *     &lt;enumeration value="TV"/>
 *     &lt;enumeration value="KITCHEN"/>
 *     &lt;enumeration value="BATHROOM"/>
 *     &lt;enumeration value="ROOMCANCEL"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "RoomAdditionalServiceEnum")
@XmlEnum
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
