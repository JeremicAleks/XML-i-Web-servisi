package com.agentapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agentapi.com.centralapi.domain.xml.xml_ftn.reservation.AllowReservationDTO;
import com.agentapi.com.centralapi.domain.xml.xml_ftn.reservation.Reservation;
import com.agentapi.com.centralapi.domain.xml.xml_ftn.reservation.ReservationStateEnum;
import com.agentapi.com.centralapi.domain.xml.xml_ftn.reservation.SendReservationDTO;
import com.agentapi.com.centralapi.domain.xml.xml_ftn.rooms.Room;
import com.agentapi.repo.ReservationRepository;
import com.agentapi.repo.RoomRepository;

@Service
public class ReservationServiceCon implements ReservationService {


	@Autowired
	RoomRepository roomRepo;
	
	@Autowired
	ReservationRepository resRepo;
	
	@Override
	public List<SendReservationDTO> getReservations() {
		
		List<Room> rooms = roomRepo.findAll();
		List<SendReservationDTO> reservations = new ArrayList<SendReservationDTO>();
		for (Room room : rooms) {
			for (Reservation res : room.getReservation()) {
				if(res.getState() == ReservationStateEnum.PENDING)
					reservations.add(new SendReservationDTO(room.getId(), room.getAccommodationType(), room.getLocation(), room.getNumberOfBeds(), res));
			}	
		}
		
		return reservations;
	}
	
	@Override
	public void confirmReservation(AllowReservationDTO allowReservation) throws Exception {
	
		Reservation re = resRepo.getOne(allowReservation.getReservationId());
		if(re == null)
			throw new Exception();
		
		
		re.setState(allowReservation.getState());
		
		resRepo.save(re);
		
	}

}
