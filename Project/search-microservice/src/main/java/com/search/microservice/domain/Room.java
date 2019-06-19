package com.search.microservice.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

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
public class Room {

    protected int numberOfBeds;
    @XmlElement(name = "Location", namespace = "http://www.xml-ftn.xml.domain.centralapi.com/Location", required = true)
    protected Location location;
    @XmlElement(required = true)
    protected AccommodationType accType;
    @XmlElement(required = true)
    protected AccommodationCategory accCategory;
    protected List<RoomAdditionalService> roomServices;
    @XmlElement(required = true)
    protected String description;
    @XmlElement(required = true)
    protected List<String> image;
    protected List<PriceList> priceList;
    @XmlElement(name = "Reservation", namespace = "http://www.xml-ftn.xml.domain.centralapi.com/Reservation")
    protected List<Reservation> reservation;
    protected long id;
    protected int daysForCancel;

    public Room() {
    	
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

	public AccommodationType getAccType() {
		return accType;
	}

	public void setAccType(AccommodationType accType) {
		this.accType = accType;
	}

	public AccommodationCategory getAccCategory() {
		return accCategory;
	}

	public void setAccCategory(AccommodationCategory accCategory) {
		this.accCategory = accCategory;
	}

	public List<RoomAdditionalService> getRoomServices() {
		if (roomServices == null)
			return new ArrayList<RoomAdditionalService>();
		return roomServices;
	}

	public void setRoomServices(List<RoomAdditionalService> roomServices) {
		this.roomServices = roomServices;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getImage() {
		if (image == null) 
			return new ArrayList<String>();
		
		return image;
	}

	public void setImage(List<String> image) {
		this.image = image;
	}

	public List<PriceList> getPriceList() {
		if (priceList == null)
			return new ArrayList<PriceList>();
		
		return priceList;
	}

	public void setPriceList(List<PriceList> priceList) {
		this.priceList = priceList;
	}

	public List<Reservation> getReservation() {
		if (reservation == null)
			return new ArrayList<Reservation>();
					
		return reservation;
	}

	public void setReservation(List<Reservation> reservation) {
		this.reservation = reservation;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getDaysForCancel() {
		return daysForCancel;
	}

	public void setDaysForCancel(int daysForCancel) {
		this.daysForCancel = daysForCancel;
	}

}
