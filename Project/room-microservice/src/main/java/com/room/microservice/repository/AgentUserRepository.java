package com.room.microservice.repository;

import com.room.microservice.domain.AgentUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentUserRepository extends JpaRepository<AgentUser,Long> {

    AgentUser findByUsername(String username);
}
