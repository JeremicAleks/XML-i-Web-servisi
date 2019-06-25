package com.reservation.microservice.domain.reservation;

import com.reservation.microservice.domain.room.AccommodationType;
import com.reservation.microservice.domain.room.Location;

public class MessageDTO {
	
	private AccommodationType roomType;
	private Location location;
	private int numberOfBeds;
	private MessageTable messageTable;

	public MessageDTO(AccommodationType roomType, Location location, int numberOfBeds, MessageTable messageTable) {
		super();
		this.roomType = roomType;
		this.location = location;
		this.numberOfBeds = numberOfBeds;
		this.messageTable = messageTable;
	}

	public AccommodationType getRoomType() {
		return roomType;
	}

	public void setRoomType(AccommodationType roomType) {
		this.roomType = roomType;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public int getNumberOfBeds() {
		return numberOfBeds;
	}

	public void setNumberOfBeds(int numberOfBeds) {
		this.numberOfBeds = numberOfBeds;
	}

	public MessageTable getMessageTable() {
		return messageTable;
	}

	public void setMessageTable(MessageTable messageTable) {
		this.messageTable = messageTable;
	}
	
	

}
