package com.room.microservice.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    protected int numberOfBeds;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,optional = false)
    protected Location location;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = TypeEnum.class)
    protected TypeEnum type;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = CategoryEnum.class)
    protected CategoryEnum category;

    @OneToMany(mappedBy = "roomAddService")
    protected List<RoomAdditionalService> additionalServices;

    protected String description;
    protected List<byte[]> image;

    @OneToMany(mappedBy = "roomRateAndComment")
    protected List<RateAndComment> ratesAndComments;

    @OneToMany(mappedBy = "roomPriceList")
    protected List<PriceList> priceList;

    @OneToMany(mappedBy = "reservedRoom")
    protected List<Reservation> reservation;


    protected int floor;
    protected int number;


    public Room() {
    }

    public Room(int numberOfBeds, Location location, TypeEnum type, CategoryEnum category, List<RoomAdditionalService> additionalServices, String description, List<byte[]> image, List<RateAndComment> ratesAndComments, List<PriceList> priceList, List<Reservation> reservation, int floor, int number) {

        this.numberOfBeds = numberOfBeds;
        this.location = location;
        this.type = type;
        this.category = category;
        this.additionalServices = additionalServices;
        this.description = description;
        this.image = image;
        this.ratesAndComments = ratesAndComments;
        this.priceList = priceList;
        this.reservation = reservation;
        this.floor = floor;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    public List<RoomAdditionalService> getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(List<RoomAdditionalService> additionalServices) {
        this.additionalServices = additionalServices;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<byte[]> getImage() {
        return image;
    }

    public void setImage(List<byte[]> image) {
        this.image = image;
    }

    public List<RateAndComment> getRatesAndComments() {
        return ratesAndComments;
    }

    public void setRatesAndComments(List<RateAndComment> ratesAndComments) {
        this.ratesAndComments = ratesAndComments;
    }

    public List<PriceList> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<PriceList> priceList) {
        this.priceList = priceList;
    }

    public List<Reservation> getReservation() {
        return reservation;
    }

    public void setReservation(List<Reservation> reservation) {
        this.reservation = reservation;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}

