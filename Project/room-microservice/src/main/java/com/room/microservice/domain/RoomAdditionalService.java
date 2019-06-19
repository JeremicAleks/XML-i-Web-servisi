package com.room.microservice.domain;

import javax.persistence.*;

@Entity
public class RoomAdditionalService {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double price;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = RoomAdditionalServiceEnum.class)
    private RoomAdditionalServiceEnum roomAdditionalServiceEnum;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY , optional = false)
    private Room roomAddService;

    public RoomAdditionalService() {
    }

    public RoomAdditionalService(double price, RoomAdditionalServiceEnum roomAdditionalServiceEnum, Room roomAddService) {
        this.price = price;
        this.roomAdditionalServiceEnum = roomAdditionalServiceEnum;
        this.roomAddService = roomAddService;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public RoomAdditionalServiceEnum getRoomAdditionalServiceEnum() {
        return roomAdditionalServiceEnum;
    }

    public void setRoomAdditionalServiceEnum(RoomAdditionalServiceEnum roomAdditionalServiceEnum) {
        this.roomAdditionalServiceEnum = roomAdditionalServiceEnum;
    }

    public Room getRoomAddService() {
        return roomAddService;
    }

    public void setRoomAddService(Room roomAddService) {
        this.roomAddService = roomAddService;
    }
}
