package com.centralapi.service;

import java.util.List;

import com.centralapi.domain.PrivilegeEnum;
import com.centralapi.exception.ResponseMessage;

public interface RoleService {
	
	ResponseMessage addRole(String role);
	
	ResponseMessage deleteRole(String role);
	
	ResponseMessage updateRole(String role,List<PrivilegeEnum> privileges);
	
	ResponseMessage changeRoleForUser(String role,String username);

}
