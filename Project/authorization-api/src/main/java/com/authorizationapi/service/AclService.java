package com.authorizationapi.service;

import java.nio.file.Path;
import java.util.List;

import com.authorizationapi.domain.User;

public interface AclService {
	
	List<Path> findFiles(User user);


}
