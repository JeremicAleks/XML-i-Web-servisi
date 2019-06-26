package com.room.microservice.repository;

import com.room.microservice.domain.AgentUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AgentUserRepository extends JpaRepository<AgentUser,Long> {

    @Query(value = "SELECT * FROM user u left join agent_user a on u.id=a.id WHERE u.username =?1 ", nativeQuery = true)
    AgentUser findByUsername(String username);
}
