//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.24 at 06:58:16 PM CEST 
//

package com.example.demo.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;




@Entity
public class RateAndComment {


    protected String comment;
    protected int rating;
    protected boolean isAllowed;
    @ManyToOne(fetch = FetchType.EAGER)
    protected Room room;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
  
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    protected Reservation reservation;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    protected RegistredUser regUser;

    public boolean isAllowed() {
  		return isAllowed;
  	}

  	public void setAllowed(boolean isAllowed) {
  		this.isAllowed = isAllowed;
  	}

  	public RegistredUser getRegUser() {
  		return regUser;
  	}

  	public void setRegUser(RegistredUser regUser) {
  		this.regUser = regUser;
  	}

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
