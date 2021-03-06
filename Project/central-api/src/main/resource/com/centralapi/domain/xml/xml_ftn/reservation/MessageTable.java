//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.29 at 10:27:27 PM CEST 
//


package com.centralapi.domain.xml.xml_ftn.reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="messageString" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fromUser" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="toUser" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "messageString",
    "fromUser",
    "toUser"
})
@XmlRootElement(name = "MessageTable")
public class MessageTable {

    protected long id;
    @XmlElement(required = true)
    protected String messageString;
    @XmlElement(required = true)
    protected String fromUser;
    @XmlElement(required = true, nillable = true)
    protected String toUser;

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
     * Gets the value of the messageString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageString() {
        return messageString;
    }

    /**
     * Sets the value of the messageString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageString(String value) {
        this.messageString = value;
    }

    /**
     * Gets the value of the fromUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromUser() {
        return fromUser;
    }

    /**
     * Sets the value of the fromUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromUser(String value) {
        this.fromUser = value;
    }

    /**
     * Gets the value of the toUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToUser() {
        return toUser;
    }

    /**
     * Sets the value of the toUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToUser(String value) {
        this.toUser = value;
    }

}
