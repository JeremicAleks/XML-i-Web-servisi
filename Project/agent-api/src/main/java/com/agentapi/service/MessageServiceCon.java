package com.agentapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agentapi.com.centralapi.domain.xml.xml_ftn.reservation.MessageDTO;
import com.agentapi.com.centralapi.domain.xml.xml_ftn.reservation.MessageTable;
import com.agentapi.com.centralapi.domain.xml.xml_ftn.reservation.Reservation;
import com.agentapi.com.centralapi.domain.xml.xml_ftn.rooms.Room;
import com.agentapi.com.centralapi.domain.xml.xml_ftn.users.SendMessageDTO;
import com.agentapi.repo.ReservationRepository;
import com.agentapi.repo.RoomRepository;
import com.agentapi.repo.UserInfoRepo;

@Service
public class MessageServiceCon implements MessageService {

	@Autowired
	RoomRepository roomRepo;
	@Autowired
	ReservationRepository resRepo;
	@Autowired
	UserInfoRepo userInfoRepo;
	
	@Override
	public List<MessageDTO> getAllMessages() {
		
		List<MessageDTO> msgDTO =new ArrayList<MessageDTO>();
		
		List<Room> rooms = roomRepo.findAll();
		
		for (Room room : rooms) {
			for (Reservation res : room.getReservation()) {
				for (MessageTable msg : res.getMessageTable()) {
					if(msg.getToUser().equals(userInfoRepo.findAll().get(0).getUsername()))
						msgDTO.add(new MessageDTO(room.getId(),res.getId(),room.getAccommodationType(),room.getLocation(),room.getNumberOfBeds(),res.getCheckIn(),res.getCheckOut(),msg));
				}
			}
		}
		
		return msgDTO;
	}
	@Override
	public void addMessageToLocal(SendMessageDTO message) throws Exception{
		Reservation res = resRepo.getOne(message.getReservationId());
		if(res==null)
			throw new Exception("Reservation doesnt exist!");
		res.getMessageTable().add(message.getMessageTable());
		resRepo.saveAndFlush(res);
		
	}

}
