package com.centralapi.domain.dto;

import com.centralapi.domain.xml.xml_ftn.reservation.MessageTable;
import com.centralapi.domain.xml.xml_ftn.reservation.Reservation;
import com.centralapi.domain.xml.xml_ftn.rooms.Room;

import java.util.List;

public class ShowMessageForUserDTO {
    private List<MessageTable> messageTable;
    private Room room;
    private Reservation reservation;

    public List<MessageTable> getMessageTable() {
        return messageTable;
    }

    public void setMessageTable(List<MessageTable> messageTable) {
        this.messageTable = messageTable;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
