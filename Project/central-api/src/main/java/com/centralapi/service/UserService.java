
package com.centralapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centralapi.domain.xml.xml_ftn.users.AgentDTO;
import com.centralapi.domain.xml.xml_ftn.users.AgentUser;
import com.centralapi.domain.xml.xml_ftn.users.Role;
import com.centralapi.domain.xml.xml_ftn.users.User;
import com.centralapi.domain.xml.xml_ftn.users.UserStatusEnum;
import com.centralapi.exception.ResponseMessage;
import com.centralapi.repo.AgentUserRepository;
import com.centralapi.repo.RoleRepository;
import com.centralapi.repo.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AgentUserRepository agentUserRepository;

	@Autowired
	private RoleRepository roleRepository;

	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public User findById(Long id) {
		return userRepository.findById(id).get();
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public ResponseMessage promoteAgent(AgentDTO newAgent) {
		
		System.out.println(newAgent.getUserToPromote());
		System.out.println(newAgent.getAddress());
		System.out.println(newAgent.getBusinessRegNumber());
		
		User foundUser = userRepository.findByUsername(newAgent.getUserToPromote());
		if (foundUser == null)
			return new ResponseMessage("There is no user with sent username!");
			
		System.out.println("ovdee");
		
		Role agentRole = roleRepository.findByName("AGENT_USER");
		System.out.println(agentRole.getName());
		
		if (agentRole == null)
			return new ResponseMessage("You can only promote user to agent!");
		
		AgentUser agent = new AgentUser(foundUser, newAgent.getBusinessRegNumber());
		agent.setRole(agentRole);
		
		foundUser.setUsername("old-"+ foundUser.getUsername());
		foundUser.setUserStatus(UserStatusEnum.DELETED);
		userRepository.saveAndFlush(foundUser);

		agentUserRepository.save(agent);
		
		return new ResponseMessage("User successfully promoted to agent!");
	}

	public ResponseMessage blockUser(String username) {
		
		User foundUser = userRepository.findByUsername(username);
		if (foundUser == null)
			return new ResponseMessage("There is no user with sent username!");
		
		if (foundUser.getUserStatus() == UserStatusEnum.BLOCK)
			return new ResponseMessage("You can't block user who is already blocked!");
		
		foundUser.setUserStatus(UserStatusEnum.BLOCK);
		userRepository.save(foundUser);
		
		return new ResponseMessage("User has been successfully blocked!");
	}

	public ResponseMessage activateUser(String username) {

		User foundUser = userRepository.findByUsername(username);
		if (foundUser == null)
			return new ResponseMessage("There is no user with sent username!");
		
		if (foundUser.getUserStatus() == UserStatusEnum.ACTIVE)
			return new ResponseMessage("You can't activate user who is already activated!");
		
		foundUser.setUserStatus(UserStatusEnum.ACTIVE);
		userRepository.save(foundUser);
		
		return new ResponseMessage("User has been successfully activated!");
	}
}
