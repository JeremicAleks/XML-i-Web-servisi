package com.room.microservice.service;

import com.room.microservice.domain.AgentUser;
import com.room.microservice.repository.AgentUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgentUserService {

    @Autowired
    private AgentUserRepository agentUserRepository;

    public AgentUser findById(Long id){return agentUserRepository.findById(id).get();}

    public AgentUser findByUsername(String username){return agentUserRepository.findByUsername(username);}

    public AgentUser save(AgentUser agentUser){return agentUserRepository.save(agentUser);}


}
