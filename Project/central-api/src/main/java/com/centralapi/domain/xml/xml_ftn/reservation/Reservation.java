//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.19 at 12:01:06 AM CEST 
//


package com.centralapi.domain.xml.xml_ftn.reservation;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="checkIn" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="checkOut" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="state" type="{http://www.xml-ftn.xml.domain.centralapi.com/Reservation}ReservationStateEnum"/>
 *         &lt;element ref="{http://www.xml-ftn.xml.domain.centralapi.com/Reservation}MessageTable"/>
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
    "checkIn",
    "checkOut",
    "id",
    "state",
    "messageTable"
})
@XmlRootElement(name = "Reservation")
@Entity
public class Reservation {

    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected Date checkIn;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected Date checkOut;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    @XmlElement(required = true)
    @Enumerated
    protected ReservationStateEnum state;
    @XmlElement(name = "MessageTable", required = true)
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    protected MessageTable messageTable;

    /**
     * Gets the value of the checkIn property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public Date getCheckIn() {
        return checkIn;
    }

    /**
     * Sets the value of the checkIn property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCheckIn(Date value) {
        this.checkIn = value;
    }

    /**
     * Gets the value of the checkOut property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public Date getCheckOut() {
        return checkOut;
    }

    /**
     * Sets the value of the checkOut property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCheckOut(Date value) {
        this.checkOut = value;
    }

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
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link ReservationStateEnum }
     *     
     */
    public ReservationStateEnum getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReservationStateEnum }
     *     
     */
    public void setState(ReservationStateEnum value) {
        this.state = value;
    }

    /**
     * Gets the value of the messageTable property.
     * 
     * @return
     *     possible object is
     *     {@link MessageTable }
     *     
     */
    public MessageTable getMessageTable() {
        return messageTable;
    }

    /**
     * Sets the value of the messageTable property.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageTable }
     *     
     */
    public void setMessageTable(MessageTable value) {
        this.messageTable = value;
    }

}
