package com.authorizationapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.authorizationapi.domain.RegistredUser;

public interface RegUserRepository extends JpaRepository<RegistredUser, Long> {

}
