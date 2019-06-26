package com.reservation.microservice.repository;

import com.reservation.microservice.domain.user.AgentUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentUserRepository extends JpaRepository<AgentUser,Long> {
}
