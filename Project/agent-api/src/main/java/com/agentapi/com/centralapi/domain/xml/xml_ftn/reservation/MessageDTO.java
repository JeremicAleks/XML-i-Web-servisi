package com.agentapi.com.centralapi.domain.xml.xml_ftn.reservation;

import java.util.Date;

import com.agentapi.com.centralapi.domain.xml.xml_ftn.location.Location;
import com.agentapi.com.centralapi.domain.xml.xml_ftn.rooms.AccommodationType;

public class MessageDTO {
	
	private Long roomId;
	private Long resId;
	private AccommodationType roomType;
	private Location location;
	private int numberOfBeds;
	private Date checkIn;
	private Date checkOut;
	
	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public Long getResId() {
		return resId;
	}

	public void setResId(Long resId) {
		this.resId = resId;
	}



	public MessageDTO(Long roomId, Long resId, AccommodationType roomType, Location location, int numberOfBeds, Date checkIn,
			Date checkOut, MessageTable messageTable) {
		super();
		this.roomId = roomId;
		this.resId = resId;
		this.roomType = roomType;
		this.location = location;
		this.numberOfBeds = numberOfBeds;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.messageTable = messageTable;
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

	private MessageTable messageTable;

	

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
