package com.centralapi.domain.dto;

import com.centralapi.domain.xml.xml_ftn.rooms.PriceList;
import com.centralapi.domain.xml.xml_ftn.rooms.RateAndComment;
import com.centralapi.domain.xml.xml_ftn.rooms.Room;

public class ShowRoomDTO {

	private Room room;
	private PriceList priceList;
	private RateAndComment ratesAndComments;
	private String totalDays;
	
	
	
	public String getTotalDays() {
		return totalDays;
	}
	public void setTotalDays(String totalDays) {
		this.totalDays = totalDays;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public PriceList getPriceList() {
		return priceList;
	}
	public void setPriceList(PriceList priceList) {
		this.priceList = priceList;
	}
	public RateAndComment getRatesAndComments() {
		return ratesAndComments;
	}
	public void setRatesAndComments(RateAndComment ratesAndComments) {
		this.ratesAndComments = ratesAndComments;
	}
	
}