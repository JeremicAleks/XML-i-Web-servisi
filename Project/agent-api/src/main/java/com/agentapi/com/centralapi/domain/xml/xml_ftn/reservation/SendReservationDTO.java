package com.agentapi.com.centralapi.domain.xml.xml_ftn.reservation;

import com.agentapi.com.centralapi.domain.xml.xml_ftn.location.Location;
import com.agentapi.com.centralapi.domain.xml.xml_ftn.rooms.AccommodationType;

public class SendReservationDTO {
	
	private Long roomId;
	private AccommodationType roomType;
	private Location location;
	private int numberOfBeds;
	private Reservation reservation;
	
	public SendReservationDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public SendReservationDTO(Long roomId, AccommodationType roomType, Location location, int numberOfBeds,
			Reservation reservation) {
		super();
		this.roomId = roomId;
		this.roomType = roomType;
		this.location = location;
		this.numberOfBeds = numberOfBeds;
		this.reservation = reservation;
	}


	public Long getRoomId() {
		return roomId;
	}
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
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
	public Reservation getReservation() {
		return reservation;
	}
	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	
	

}
