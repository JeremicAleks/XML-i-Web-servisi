package com.centralapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.centralapi.domain.xml.xml_ftn.users.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	@Query(value = "SELECT * FROM role WHERE role.name =?1 ", nativeQuery = true)
	Role findByName(String name);

	
}
