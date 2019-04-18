package com.centralapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centralapi.domain.PrivilegeEnum;
import com.centralapi.domain.Role;
import com.centralapi.domain.User;
import com.centralapi.exception.GlobalException;
import com.centralapi.exception.ResponseMessage;
import com.centralapi.repo.RoleRepository;
import com.centralapi.repo.UserRepository;

@Service
public class RoleServiceCon implements RoleService {

	@Autowired
	RoleRepository roleRepo;

	@Autowired
	UserRepository userRepo;

	@Override
	public ResponseMessage addRole(String role) {

		if (roleRepo.findByName(role) != null) {
			throw new GlobalException("Role alredy exist!");
		}

		roleRepo.save(new Role(role, null));

		return new ResponseMessage("Successfully add role!");

	}

	@Override
	public ResponseMessage deleteRole(String role) {
		
		Role ro = roleRepo.findByName(role);
		if ( ro == null) {
			throw new GlobalException("Role doesnt exist!");
		}
		if (role.equals("DefaultRole")) {
			throw new GlobalException("Cant remove this role!");
		}

		Role r = roleRepo.findByName("DefaultRole");
		userRepo.updateUserRole(r.getId(), ro.getId());

		roleRepo.delete(ro);

		return new ResponseMessage("Successfully deleted role!");

	}

	@Override
	public ResponseMessage updateRole(String role, List<PrivilegeEnum> privileges) {

		Role ro = roleRepo.findByName(role);
		if ( ro == null) {
			throw new GlobalException("Role doesnt exist!");
		}
		if (role.equals("DefaultRole")) {
			throw new GlobalException("Cant update this role!");
		}
		ro.getPrivileges().clear();
		ro.getPrivileges().addAll(privileges);
		roleRepo.saveAndFlush(ro);
		
		return new ResponseMessage("Successfully updated role!");

	}
	
	@Override
	public ResponseMessage changeRoleForUser(String role, String username) {
		Role ro = roleRepo.findByName(role);
		if ( ro == null) {
			throw new GlobalException("Role doesnt exist!");
		}
		if (role.equals("DefaultRole")) {
			throw new GlobalException("Cant update this role!");
		}
		User u = userRepo.findByUsername(username);
		
		if(u == null) {
			throw new GlobalException("User doesnt exist!");
		}
		
		u.setRole(ro);
		userRepo.saveAndFlush(u);
		
		return new ResponseMessage("Successfully changed role for user!");
	}
}
