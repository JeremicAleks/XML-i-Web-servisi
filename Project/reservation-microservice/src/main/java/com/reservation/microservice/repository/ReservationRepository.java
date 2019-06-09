package com.reservation.microservice.repository;

import com.reservation.microservice.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    @Query(value = "")
    List<Reservation> findByCheckInBetween(XMLGregorianCalendar checkIn,XMLGregorianCalendar checkOut);

}
