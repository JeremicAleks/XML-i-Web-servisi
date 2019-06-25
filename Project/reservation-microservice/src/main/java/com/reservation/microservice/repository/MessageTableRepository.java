package com.reservation.microservice.repository;

import com.reservation.microservice.domain.reservation.MessageTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageTableRepository extends JpaRepository<MessageTable,Long> {

}
