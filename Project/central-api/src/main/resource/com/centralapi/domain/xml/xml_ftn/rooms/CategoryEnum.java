//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.19 at 12:01:06 AM CEST 
//


package com.centralapi.domain.xml.xml_ftn.rooms;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CategoryEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CategoryEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="UNCATEGORIZAED"/>
 *     &lt;enumeration value="ONESTAR"/>
 *     &lt;enumeration value="TWOSTAR"/>
 *     &lt;enumeration value="THREESTAR"/>
 *     &lt;enumeration value="FOURSTAR"/>
 *     &lt;enumeration value="FIVESTAR"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CategoryEnum")
@XmlEnum
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
