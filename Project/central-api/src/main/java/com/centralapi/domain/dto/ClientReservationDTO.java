package com.centralapi.domain.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ClientReservationDTO {

	@NotNull(message = "Room id is required")
    private Long roomId;
	@NotNull(message = "Username is required")
    private String username;
	@NotNull(message = "Check in is required")
    private Date checkIn;
	@NotNull(message = "Check out is required")
    private Date checkOut;

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
