package com.centralapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.centralapi.domain.xml.xml_ftn.users.AgentUser;

public interface AgentUserRepository extends JpaRepository<AgentUser, Long> {
	
	@Query(value = "SELECT * FROM agent_user WHERE agent_user.username =?1 ", nativeQuery = true)
	AgentUser findByUsername(String username);
}
