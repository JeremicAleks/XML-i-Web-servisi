package com.agentapi.service;

import java.util.List;

import com.agentapi.com.centralapi.domain.xml.xml_ftn.reservation.AllowReservationDTO;
import com.agentapi.com.centralapi.domain.xml.xml_ftn.reservation.SendReservationDTO;

public interface ReservationService {
	
	public List<SendReservationDTO> getReservations();
	
	public void confirmReservation(AllowReservationDTO allowReservation) throws Exception;

}
