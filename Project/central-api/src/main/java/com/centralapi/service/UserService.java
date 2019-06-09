/*
package com.centralapi.service;

import com.centralapi.domain.xml.xml_ftn.users.User;
import com.centralapi.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByUsername(String username){return userRepository.findByUsername(username);}

    public User findByEmail(String  email){
        return userRepository.findByEmail(email);
    }

    public User findById(Long  id){
        return userRepository.findById(id).get();
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User save(User user){
        return userRepository.save(user);
    }
}
*/
