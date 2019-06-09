package com.reservation.microservice.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.datatype.XMLGregorianCalendar;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private XMLGregorianCalendar checkIn;
    private XMLGregorianCalendar checkOut;
    private ReservationStateEnum state;

    public Reservation() {
    }

    public Reservation(XMLGregorianCalendar checkIn, XMLGregorianCalendar checkOut, ReservationStateEnum state) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public XMLGregorianCalendar getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(XMLGregorianCalendar checkIn) {
        this.checkIn = checkIn;
    }

    public XMLGregorianCalendar getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(XMLGregorianCalendar checkOut) {
        this.checkOut = checkOut;
    }

    public ReservationStateEnum getState() {
        return state;
    }

    public void setState(ReservationStateEnum state) {
        this.state = state;
    }
}
