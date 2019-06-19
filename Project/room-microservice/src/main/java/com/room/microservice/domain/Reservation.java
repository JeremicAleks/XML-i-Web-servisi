package com.room.microservice.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Date checkIn;
    @Column(nullable = false)
    private Date checkOut;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = ReservationStateEnum.class)
    private ReservationStateEnum state;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Room reservedRoom;


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

