//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.29 at 10:30:36 PM CEST 
//


package com.centralapi.domain.xml.xml_ftn.users;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PrivilegesEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PrivilegesEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="WRITE_PRODUCT"/>
 *     &lt;enumeration value="READ_PRODUCT"/>
 *     &lt;enumeration value="DELETE_PRODUCT"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PrivilegesEnum")
@XmlEnum
public enum PrivilegesEnum {

    WRITE_PRODUCT,
    READ_PRODUCT,
    DELETE_PRODUCT;

    public String value() {
        return name();
    }

    public static PrivilegesEnum fromValue(String v) {
        return valueOf(v);
    }

}
