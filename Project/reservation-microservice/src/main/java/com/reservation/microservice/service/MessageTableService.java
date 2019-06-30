package com.reservation.microservice.service;

import com.reservation.microservice.domain.dto.ClientSendMessageDTO;
import com.reservation.microservice.domain.reservation.MessageTable;
import com.reservation.microservice.domain.reservation.Reservation;
import com.reservation.microservice.domain.room.Room;
import com.reservation.microservice.domain.user.AgentUser;
import com.reservation.microservice.domain.user.RegistredUser;
import com.reservation.microservice.domain.user.SendMessageDTO;
import com.reservation.microservice.repository.MessageTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageTableService {

    @Autowired
    private MessageTableRepository messageTableRepository;
    @Autowired
    private RegisteredUserService registeredUserService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private RoomService roomService;

    @Autowired
    private AgentUserService agentUserService;

    public MessageTable findById(Long id){return messageTableRepository.getOne(id);}

    public List<MessageTable> getAll(){return messageTableRepository.findAll();}

    public MessageTable save(MessageTable messageTable){return messageTableRepository.save(messageTable);}

    public List<MessageTable> getMessageForUser(String username) {
        List<MessageTable> messageTables = new ArrayList<>();
        RegistredUser registredUser = registeredUserService.findByUsername(username);
        List<Reservation> reservations = registredUser.getReservation();

        for(Reservation reservation: reservations)
            messageTables.addAll(reservation.getMessageTable());

        return messageTables;
    }

    public Reservation sendMessage(SendMessageDTO sendMessageDTO) {
        MessageTable messageTable = sendMessageDTO.getMessageTable();
        Room room = roomService.findById(sendMessageDTO.getRoomId());
        Reservation reservation =reservationService.findById(sendMessageDTO.getRoomId());

        messageTable = save(messageTable);

        reservation.getMessageTable().add(messageTable);
        reservation = reservationService.save(reservation);

        return reservation;

    }

    public Reservation sendMessageClient(ClientSendMessageDTO clientSendMessageDTO) {
        Reservation reservation = reservationService.findById(clientSendMessageDTO.getIdReservation());
        MessageTable messageTable = new MessageTable();
        messageTable.setFromUser(clientSendMessageDTO.getUsername());
        messageTable.setMessageString(clientSendMessageDTO.getMessage());

        Long roomId = roomService.room_id(clientSendMessageDTO.getIdReservation());
        Long agentId = agentUserService.findId(roomId);

        AgentUser agentUser = agentUserService.findById(agentId);
        messageTable.setToUser(agentUser.getUsername());

        reservation.getMessageTable().add(messageTable);
        reservation = reservationService.save(reservation);

        return reservation;
    }
}
