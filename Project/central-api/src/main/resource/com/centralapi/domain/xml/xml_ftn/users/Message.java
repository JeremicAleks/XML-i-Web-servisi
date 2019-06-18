//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.18 at 06:23:10 PM CEST 
//


package com.centralapi.domain.xml.xml_ftn.users;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.centralapi.domain.xml.xml_ftn.reservation.Reservation;


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
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="from" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="to" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element ref="{http://www.xml-ftn.xml.domain.centralapi.com/Reservation}Reservation"/>
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
    "id",
    "message",
    "from",
    "to",
    "reservation"
})
@XmlRootElement(name = "Message")
public class Message {

    protected long id;
    @XmlElement(required = true)
    protected String message;
    protected long from;
    protected long to;
    @XmlElement(name = "Reservation", namespace = "http://www.xml-ftn.xml.domain.centralapi.com/Reservation", required = true)
    protected Reservation reservation;

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Gets the value of the from property.
     * 
     */
    public long getFrom() {
        return from;
    }

    /**
     * Sets the value of the from property.
     * 
     */
    public void setFrom(long value) {
        this.from = value;
    }

    /**
     * Gets the value of the to property.
     * 
     */
    public long getTo() {
        return to;
    }

    /**
     * Sets the value of the to property.
     * 
     */
    public void setTo(long value) {
        this.to = value;
    }

    /**
     * Gets the value of the reservation property.
     * 
     * @return
     *     possible object is
     *     {@link Reservation }
     *     
     */
    public Reservation getReservation() {
        return reservation;
    }

    /**
     * Sets the value of the reservation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Reservation }
     *     
     */
    public void setReservation(Reservation value) {
        this.reservation = value;
    }

}
