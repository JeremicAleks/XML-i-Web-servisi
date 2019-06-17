package com.centralapi.service;

import java.util.List;

import com.centralapi.domain.xml.xml_ftn.users.PrivilegesEnum;
import com.centralapi.exception.ResponseMessage;

public interface RoleService {
	
	ResponseMessage addRole(String role);
	
	ResponseMessage deleteRole(String role);
	
	ResponseMessage updateRole(String role,List<PrivilegesEnum> privileges);
	
	ResponseMessage changeRoleForUser(String role,String username);

}
