//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.16 at 03:13:49 AM CEST 
//


package com.room.microservice.domain;

import javax.persistence.*;
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
 *       &lt;sequence>
 *         &lt;element name="numberOfBeds" form="qualified">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger">
 *               &lt;maxExclusive value="6"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element ref="{http://www.xml-ftn.xml.domain.centralapi.com/Location}Location"/>
 *         &lt;element name="type" type="{http://www.xml-ftn.xml.domain.centralapi.com/Rooms}TypeEnum"/>
 *         &lt;element name="category" type="{http://www.xml-ftn.xml.domain.centralapi.com/Rooms}CategoryEnum"/>
 *         &lt;sequence maxOccurs="unbounded" minOccurs="0">
 *           &lt;element name="additionalServices" type="{http://www.xml-ftn.xml.domain.centralapi.com/Rooms}RoomAdditionalServiceEnum"/>
 *         &lt;/sequence>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;sequence maxOccurs="10">
 *           &lt;element name="image" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;/sequence>
 *         &lt;sequence maxOccurs="unbounded" minOccurs="0">
 *           &lt;element name="priceList" type="{http://www.xml-ftn.xml.domain.centralapi.com/Rooms}PriceList"/>
 *         &lt;/sequence>
 *         &lt;sequence maxOccurs="unbounded" minOccurs="0">
 *           &lt;element ref="{http://www.xml-ftn.xml.domain.centralapi.com/Reservation}Reservation"/>
 *         &lt;/sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="daysForCancel" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "numberOfBeds",
    "location",
    "type",
    "category",
    "additionalServices",
    "description",
    "image",
    "priceList",
    "reservation",
    "id",
    "daysForCancel"
})
@XmlRootElement(name = "Room")
@Entity
public class Room {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    
    protected int numberOfBeds;
    @XmlElement(name = "Location", namespace = "http://www.xml-ftn.xml.domain.centralapi.com/Location", required = true)
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    protected Location location;
    @XmlElement(required = true)
    @Enumerated
    protected TypeEnum type;
    @XmlElement(required = true)
    @Enumerated
    protected CategoryEnum category;
	@Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = RoomAdditionalServiceEnum.class)
    protected List<RoomAdditionalServiceEnum> additionalServices;
    @XmlElement(required = true)
    protected String description;
    @XmlElement(required = true)
    @ElementCollection
    protected List<String> image;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    protected List<PriceList> priceList;
    @XmlElement(name = "Reservation", namespace = "http://www.xml-ftn.xml.domain.centralapi.com/Reservation")
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    protected List<Reservation> reservation;
    protected int daysForCancel;

    @ManyToOne
    protected  AgentUser agentUser;


    public Room() {
    }

    public AgentUser getAgentUser() {
        return agentUser;
    }

    public void setAgentUser(AgentUser agentUser) {
        this.agentUser = agentUser;
    }

    /**
     * Gets the value of the numberOfBeds property.
     * 
     */
    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    /**
     * Sets the value of the numberOfBeds property.
     * 
     */
    public void setNumberOfBeds(int value) {
        this.numberOfBeds = value;
    }

    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link Location }
     *     
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link Location }
     *     
     */
    public void setLocation(Location value) {
        this.location = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link TypeEnum }
     *     
     */
    public TypeEnum getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeEnum }
     *     
     */
    public void setType(TypeEnum value) {
        this.type = value;
    }

    /**
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link CategoryEnum }
     *     
     */
    public CategoryEnum getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link CategoryEnum }
     *     
     */
    public void setCategory(CategoryEnum value) {
        this.category = value;
    }

    /**
     * Gets the value of the additionalServices property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the additionalServices property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdditionalServices().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RoomAdditionalServiceEnum }
     * 
     * 
     */
    public List<RoomAdditionalServiceEnum> getAdditionalServices() {
        if (additionalServices == null) {
            additionalServices = new ArrayList<RoomAdditionalServiceEnum>();
        }
        return this.additionalServices;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the image property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the image property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getImage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getImage() {
        if (image == null) {
            image = new ArrayList<String>();
        }
        return this.image;
    }

    /**
     * Gets the value of the priceList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the priceList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPriceList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PriceList }
     * 
     * 
     */
    public List<PriceList> getPriceList() {
        if (priceList == null) {
            priceList = new ArrayList<PriceList>();
        }
        return this.priceList;
    }

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
     * Gets the value of the daysForCancel property.
     * 
     */
    public int getDaysForCancel() {
        return daysForCancel;
    }

    /**
     * Sets the value of the daysForCancel property.
     * 
     */
    public void setDaysForCancel(int value) {
        this.daysForCancel = value;
    }

}
