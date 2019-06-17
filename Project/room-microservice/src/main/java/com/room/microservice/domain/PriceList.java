package com.room.microservice.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class PriceList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Date month;

    private double price;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Room roomPriceList;

    public PriceList() {
    }

    public PriceList(Date month, double price, Room roomPriceList) {
        this.month = month;
        this.price = price;
        this.roomPriceList = roomPriceList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getMonth() {
        return month;
    }

    public void setMonth(Date month) {
        this.month = month;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Room getRoomPriceList() {
        return roomPriceList;
    }

    public void setRoomPriceList(Room roomPriceList) {
        this.roomPriceList = roomPriceList;
    }
}
