package com.agentapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agentapi.com.centralapi.domain.xml.xml_ftn.users.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
