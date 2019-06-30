package com.reservation.microservice.repository;

import com.reservation.microservice.domain.user.AgentUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AgentUserRepository extends JpaRepository<AgentUser,Long> {

    @Query(value = "select agent_user_id from agent_user_room where room_id=?1",nativeQuery = true)
    Long agentId(Long roomId);
}
