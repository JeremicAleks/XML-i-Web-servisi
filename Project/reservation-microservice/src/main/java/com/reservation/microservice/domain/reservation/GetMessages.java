//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.19 at 12:01:06 AM CEST 
//


package com.reservation.microservice.domain.reservation;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence maxOccurs="unbounded">
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
    "messageTable"
})
@XmlRootElement(name = "GetMessages")
public class GetMessages {

    @XmlElement(name = "MessageTable", required = true)
    protected List<MessageTable> messageTable;

    /**
     * Gets the value of the messageTable property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the messageTable property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMessageTable().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MessageTable }
     * 
     * 
     */
    public List<MessageTable> getMessageTable() {
        if (messageTable == null) {
            messageTable = new ArrayList<MessageTable>();
        }
        return this.messageTable;
    }

}