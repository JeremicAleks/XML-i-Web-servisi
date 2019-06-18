//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.18 at 06:33:13 PM CEST 
//


package com.centralapi.domain.xml.xml_ftn.rooms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RoomAdditionalService complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RoomAdditionalService">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="additionalServiceType" type="{http://www.xml-ftn.xml.domain.centralapi.com/Rooms}RoomAdditionalServiceEnum"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RoomAdditionalService", propOrder = {
    "price",
    "additionalServiceType"
})
@XmlSeeAlso({
    RoomCancel.class
})
public class RoomAdditionalService {

    protected double price;
    @XmlElement(required = true)
    protected RoomAdditionalServiceEnum additionalServiceType;

    /**
     * Gets the value of the price property.
     * 
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     */
    public void setPrice(double value) {
        this.price = value;
    }

    /**
     * Gets the value of the additionalServiceType property.
     * 
     * @return
     *     possible object is
     *     {@link RoomAdditionalServiceEnum }
     *     
     */
    public RoomAdditionalServiceEnum getAdditionalServiceType() {
        return additionalServiceType;
    }

    /**
     * Sets the value of the additionalServiceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link RoomAdditionalServiceEnum }
     *     
     */
    public void setAdditionalServiceType(RoomAdditionalServiceEnum value) {
        this.additionalServiceType = value;
    }

}
