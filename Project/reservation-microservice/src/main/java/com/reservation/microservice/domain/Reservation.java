package com.reservation.microservice.domain;

import javax.persistence.Entity;
        import javax.persistence.GeneratedValue;
        import javax.persistence.GenerationType;
        import javax.persistence.Id;
        import java.util.Date;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date checkIn;
    private Date checkOut;
    private ReservationStateEnum state;

    public Reservation() {
    }

    public Reservation(Date checkIn, Date checkOut, ReservationStateEnum state) {
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

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public ReservationStateEnum getState() {
        return state;
    }

    public void setState(ReservationStateEnum state) {
        this.state = state;
    }
}
