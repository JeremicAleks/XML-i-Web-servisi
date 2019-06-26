package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
