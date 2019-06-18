package com.agentapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agentapi.com.centralapi.domain.xml.xml_ftn.reservation.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
