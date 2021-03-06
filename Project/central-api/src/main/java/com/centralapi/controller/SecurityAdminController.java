package com.centralapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.centralapi.domain.dto.ChangeRoleForUserDTO;
import com.centralapi.domain.dto.RoleDTO;
import com.centralapi.domain.dto.UpdateRoleDTO;
import com.centralapi.domain.xml.xml_ftn.users.PrivilegesEnum;
import com.centralapi.domain.xml.xml_ftn.users.Role;
import com.centralapi.exception.ResponseMessage;
import com.centralapi.repo.RoleRepository;
import com.centralapi.service.RoleService;

@RestController
@RequestMapping("/api/role")
public class SecurityAdminController {

	@Autowired
	RoleService roleService;

	@Autowired
	RoleRepository roleRepo;

	@PostMapping(value = "/addRole", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseMessage> addRole(@Valid @RequestBody RoleDTO role) {

		ResponseMessage rm = roleService.addRole(role.getRole());

		return new ResponseEntity<>(rm, HttpStatus.OK);
	}

	@PostMapping(value = "/deleteRole", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseMessage> deleteRole(@Valid @RequestBody RoleDTO role) {

		ResponseMessage rm = roleService.deleteRole(role.getRole());

		return new ResponseEntity<>(rm, HttpStatus.OK);
	}

	@PostMapping(value = "/updateRole", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseMessage> updateRole(@Valid @RequestBody UpdateRoleDTO role) {

		ResponseMessage rm = roleService.updateRole(role.getRole(), role.getPrivileges());

		return new ResponseEntity<>(rm, HttpStatus.OK);
	}

	@PostMapping(value = "/changeRoleForUser", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseMessage> changeRole(@Valid @RequestBody ChangeRoleForUserDTO role) {

		ResponseMessage rm = roleService.changeRoleForUser(role.getRole(), role.getUsername());

		return new ResponseEntity<>(rm, HttpStatus.OK);
	}

	@GetMapping(value = "/getAllRoles", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getRoles() {
		
		List<Role> role = roleRepo.findAll();
		System.out.println(role.get(0).getName());
		System.out.println(role.size());

		return new ResponseEntity<List<Role>>(role, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAllPrivileges", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getPrivileges() {
		
		PrivilegesEnum[] privileges = PrivilegesEnum.class.getEnumConstants();

		return new ResponseEntity<>(privileges, HttpStatus.OK);
	}

}
