//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.08 at 01:12:03 AM CEST 
//


package com.centralapi.domain.xml.xml_ftn.rooms;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TypeEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="HOTEL"/>
 *     &lt;enumeration value="BEDANDBREKFAST"/>
 *     &lt;enumeration value="APARTMAN"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TypeEnum")
@XmlEnum
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
