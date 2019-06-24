//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.24 at 07:28:51 PM CEST 
//


package com.centralapi.domain.xml.xml_ftn.rooms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.centralapi.domain.xml.xml_ftn.reservation.Reservation;


/**
 * <p>Java class for RateAndComment complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RateAndComment">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="comment" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="rating">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger">
 *               &lt;maxInclusive value="10"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="isAllowed" type="{http://www.w3.org/2001/XMLSchema}boolean" form="qualified"/>
 *         &lt;element ref="{http://www.xml-ftn.xml.domain.centralapi.com/Rooms}Room"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
@XmlType(name = "RateAndComment", propOrder = {
    "comment",
    "rating",
    "isAllowed",
    "room",
    "id",
    "reservation"
})
public class RateAndComment {

    @XmlElement(required = true)
    protected String comment;
    protected int rating;
    protected boolean isAllowed;
    @XmlElement(name = "Room", required = true)
    protected Room room;
    protected long id;
    @XmlElement(name = "Reservation", namespace = "http://www.xml-ftn.xml.domain.centralapi.com/Reservation", required = true)
    protected Reservation reservation;

    /**
     * Gets the value of the comment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the value of the comment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComment(String value) {
        this.comment = value;
    }

    /**
     * Gets the value of the rating property.
     * 
     */
    public int getRating() {
        return rating;
    }

    /**
     * Sets the value of the rating property.
     * 
     */
    public void setRating(int value) {
        this.rating = value;
    }

    /**
     * Gets the value of the isAllowed property.
     * 
     */
    public boolean isIsAllowed() {
        return isAllowed;
    }

    /**
     * Sets the value of the isAllowed property.
     * 
     */
    public void setIsAllowed(boolean value) {
        this.isAllowed = value;
    }

    /**
     * Gets the value of the room property.
     * 
     * @return
     *     possible object is
     *     {@link Room }
     *     
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Sets the value of the room property.
     * 
     * @param value
     *     allowed object is
     *     {@link Room }
     *     
     */
    public void setRoom(Room value) {
        this.room = value;
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
