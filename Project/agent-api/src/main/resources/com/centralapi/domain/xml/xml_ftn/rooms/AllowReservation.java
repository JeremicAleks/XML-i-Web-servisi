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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.centralapi.domain.xml.xml_ftn.reservation.ReservationStateEnum;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="reservationId" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *         &lt;element name="revationState" type="{http://www.xml-ftn.xml.domain.centralapi.com/Reservation}ReservationStateEnum"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "reservationId",
    "revationState"
})
@XmlRootElement(name = "allowReservation")
public class AllowReservation {

    @XmlElement(required = true)
    protected Object reservationId;
    @XmlElement(required = true)
    protected ReservationStateEnum revationState;

    /**
     * Gets the value of the reservationId property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getReservationId() {
        return reservationId;
    }

    /**
     * Sets the value of the reservationId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setReservationId(Object value) {
        this.reservationId = value;
    }

    /**
     * Gets the value of the revationState property.
     * 
     * @return
     *     possible object is
     *     {@link ReservationStateEnum }
     *     
     */
    public ReservationStateEnum getRevationState() {
        return revationState;
    }

    /**
     * Sets the value of the revationState property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReservationStateEnum }
     *     
     */
    public void setRevationState(ReservationStateEnum value) {
        this.revationState = value;
    }

}
