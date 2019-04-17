package com.authorizationapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.authorizationapi.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
