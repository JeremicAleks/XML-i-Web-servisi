package com.centralapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.centralapi.domain.xml.xml_ftn.users.GetUserRequest;
import com.centralapi.domain.xml.xml_ftn.users.User;
import com.centralapi.domain.xml.xml_ftn.users.UserLoginDTO;
import com.centralapi.repo.UserRepository;

@Endpoint
public class UserSoapController {
	
	private static final String USER_NAMESPACE_URI = "http://www.xml-ftn.xml.domain.centralapi.com/Users";
	private static final String ROOM_NAMESPACE_URI = "http://www.xml-ftn.xml.domain.centralapi.com/Rooms";
	
	
	@Autowired
	private UserRepository userRepo;
	
    @PayloadRoot(namespace = USER_NAMESPACE_URI, localPart = "getUserDTO")
    @ResponsePayload
    public User getUser(@RequestPayload GetUserRequest  request) {
    	User response = userRepo.findByUsername(request.getUsername());

        return response;
    }
    
    @PayloadRoot(namespace = USER_NAMESPACE_URI, localPart = "userLoginDTO")
    @ResponsePayload
    public UserLoginDTO login(@RequestPayload UserLoginDTO  login) {
    	//vrsi se sinhronizacija sa back-endom
    	//AgentUser poseduje sve
    	
    	System.out.println("lOGIN: " + login.getUsername());
  
    	return login;
        
    }
    
    
    

  
}
