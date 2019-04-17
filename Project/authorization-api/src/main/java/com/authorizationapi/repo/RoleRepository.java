package com.authorizationapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.authorizationapi.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	@Query(value = "SELECT * FROM role WHERE role.name =?1 ", nativeQuery = true)
	Role findByName(String name);


}
