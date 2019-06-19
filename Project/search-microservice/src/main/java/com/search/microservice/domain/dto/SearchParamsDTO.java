package com.search.microservice.domain.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.search.microservice.domain.AccommodationCategory;
import com.search.microservice.domain.AccommodationType;
import com.search.microservice.domain.RoomAdditionalService;

public class SearchParamsDTO {
	
	@NotNull(message = "Destination is required!")
	private String destination;
	@NotNull(message = "Check in date is required!")
	private Date checkIn;
	@NotNull(message = "Check out date is required!")
	private Date checkOut;
	@NotNull(message = "Number of people is required!")
	private int numOfPeople;
	
	private AccommodationType accType; 
	private AccommodationCategory accCategory;
	private double distanceFromDestionation;
	private List<RoomAdditionalService> roomServices;
	private boolean freeCancellation;
	
	public SearchParamsDTO() {
		super();
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
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

	public int getNumOfPeople() {
		return numOfPeople;
	}

	public void setNumOfPople(int numOfPeople) {
		this.numOfPeople = numOfPeople;
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

	public double getDistanceFromDestionation() {
		return distanceFromDestionation;
	}

	public void setDistanceFromDestionation(double distanceFromDestionation) {
		this.distanceFromDestionation = distanceFromDestionation;
	}

	public List<RoomAdditionalService> getRoomServices() {
		return roomServices;
	}

	public void setRoomServices(List<RoomAdditionalService> roomServices) {
		this.roomServices = roomServices;
	}

	public boolean isFreeCancellation() {
		return freeCancellation;
	}

	public void setFreeCancellation(boolean freeCancellation) {
		this.freeCancellation = freeCancellation;
	}
	
}
