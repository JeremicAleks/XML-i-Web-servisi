package com.reservation.microservice.service;

import com.reservation.microservice.domain.user.AgentUser;
import com.reservation.microservice.repository.AgentUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgentUserService {

    @Autowired
    private AgentUserRepository agentUserRepository;


    public AgentUser findById(Long id){return agentUserRepository.getOne(id);}

    public Long findId(Long roomId){return agentUserRepository.agentId(roomId);}


}
