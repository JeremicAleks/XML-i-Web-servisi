//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.26 at 12:38:08 AM CEST 
//


package com.centralapi.domain.xml.xml_ftn.reservation;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReservationStateEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ReservationStateEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ALLOWED"/>
 *     &lt;enumeration value="NOTALLOWED"/>
 *     &lt;enumeration value="PENDING"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ReservationStateEnum")
@XmlEnum
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
