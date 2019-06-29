package com.centralapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centralapi.domain.xml.xml_ftn.users.AgentUser;

public interface AgentUserRepository extends JpaRepository<AgentUser, Long> {

}
