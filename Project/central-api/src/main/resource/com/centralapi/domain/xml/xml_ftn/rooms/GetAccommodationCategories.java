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
 *         &lt;element ref="{http://www.xml-ftn.xml.domain.centralapi.com/Rooms}AccommodationCategory"/>
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
    "accommodationCategory"
})
@XmlRootElement(name = "GetAccommodationCategories")
public class GetAccommodationCategories {

    @XmlElement(name = "AccommodationCategory", required = true)
    protected List<AccommodationCategory> accommodationCategory;

    /**
     * Gets the value of the accommodationCategory property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the accommodationCategory property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAccommodationCategory().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AccommodationCategory }
     * 
     * 
     */
    public List<AccommodationCategory> getAccommodationCategory() {
        if (accommodationCategory == null) {
            accommodationCategory = new ArrayList<AccommodationCategory>();
        }
        return this.accommodationCategory;
    }

}
