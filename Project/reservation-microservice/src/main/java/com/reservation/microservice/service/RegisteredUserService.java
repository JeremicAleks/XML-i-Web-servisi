package com.reservation.microservice.service;

import com.reservation.microservice.domain.user.RegistredUser;
import com.reservation.microservice.repository.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisteredUserService {

    @Autowired
    private RegisteredUserRepository registredUserRepository;

    public RegistredUser findById(Long id){ return  registredUserRepository.getOne(id);}

    public RegistredUser findByUsername(String username){return  registredUserRepository.findByUsername(username);}

    public RegistredUser save(RegistredUser registredUser){return  registredUserRepository.save(registredUser);}


}
