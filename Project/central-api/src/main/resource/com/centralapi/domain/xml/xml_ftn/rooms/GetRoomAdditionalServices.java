//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.20 at 01:15:30 AM CEST 
//


package com.centralapi.domain.xml.xml_ftn.rooms;

import java.util.ArrayList;
import java.util.List;
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
 *       &lt;sequence maxOccurs="unbounded">
 *         &lt;element ref="{http://www.xml-ftn.xml.domain.centralapi.com/Rooms}RoomAdditionalService"/>
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
    "roomAdditionalService"
})
@XmlRootElement(name = "GetRoomAdditionalServices")
public class GetRoomAdditionalServices {

    @XmlElement(name = "RoomAdditionalService", required = true)
    protected List<RoomAdditionalService> roomAdditionalService;

    /**
     * Gets the value of the roomAdditionalService property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the roomAdditionalService property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRoomAdditionalService().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RoomAdditionalService }
     * 
     * 
     */
    public List<RoomAdditionalService> getRoomAdditionalService() {
        if (roomAdditionalService == null) {
            roomAdditionalService = new ArrayList<RoomAdditionalService>();
        }
        return this.roomAdditionalService;
    }

}
