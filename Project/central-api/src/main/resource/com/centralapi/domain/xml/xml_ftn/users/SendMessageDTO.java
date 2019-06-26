//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.26 at 12:31:41 AM CEST 
//


package com.centralapi.domain.xml.xml_ftn.users;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.centralapi.domain.xml.xml_ftn.reservation.MessageTable;


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
 *         &lt;element name="roomId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="reservationId" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
    "roomId",
    "reservationId",
    "messageTable"
})
@XmlRootElement(name = "SendMessageDTO")
public class SendMessageDTO {

    protected long roomId;
    protected long reservationId;
    @XmlElement(name = "MessageTable", namespace = "http://www.xml-ftn.xml.domain.centralapi.com/Reservation", required = true)
    protected MessageTable messageTable;

    /**
     * Gets the value of the roomId property.
     * 
     */
    public long getRoomId() {
        return roomId;
    }

    /**
     * Sets the value of the roomId property.
     * 
     */
    public void setRoomId(long value) {
        this.roomId = value;
    }

    /**
     * Gets the value of the reservationId property.
     * 
     */
    public long getReservationId() {
        return reservationId;
    }

    /**
     * Sets the value of the reservationId property.
     * 
     */
    public void setReservationId(long value) {
        this.reservationId = value;
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
