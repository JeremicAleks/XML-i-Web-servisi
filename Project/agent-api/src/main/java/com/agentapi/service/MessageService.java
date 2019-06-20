package com.agentapi.service;

import java.util.List;

import com.agentapi.com.centralapi.domain.xml.xml_ftn.reservation.MessageDTO;
import com.agentapi.com.centralapi.domain.xml.xml_ftn.users.SendMessageDTO;

public interface MessageService {
	
	public List<MessageDTO> getAllMessages();
	public void addMessageToLocal(SendMessageDTO message) throws Exception;

}
