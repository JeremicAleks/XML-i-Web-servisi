//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.08 at 01:12:03 AM CEST 
//


package com.centralapi.domain.xml.xml_ftn.users;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.centralapi.domain.xml.xml_ftn.reservation.Reservation;


/**
 * <p>Java class for RegistredUser complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RegistredUser">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.xml-ftn.xml.domain.centralapi.com/Users}User">
 *       &lt;sequence maxOccurs="unbounded" minOccurs="0">
 *         &lt;element ref="{http://www.xml-ftn.xml.domain.centralapi.com/Reservation}Reservation"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegistredUser", propOrder = {
    "reservation"
})
public class RegistredUser
    extends User
{

    @XmlElement(name = "Reservation", namespace = "http://www.xml-ftn.xml.domain.centralapi.com/Reservation")
    protected List<Reservation> reservation;

    /**
     * Gets the value of the reservation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reservation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReservation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Reservation }
     * 
     * 
     */
    public List<Reservation> getReservation() {
        if (reservation == null) {
            reservation = new ArrayList<Reservation>();
        }
        return this.reservation;
    }

}
