package com.centralapi.domain.dto;

import javax.validation.constraints.NotNull;

public class ClientSendMessageDTO {
	
	@NotNull(message = "Reservation id is required")
    private Long idReservation;
	@NotNull(message = "Username is required")
    private String username;
	@NotNull(message = "Message is required")
    private String message;

    public Long getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Long idReservation) {
        this.idReservation = idReservation;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
