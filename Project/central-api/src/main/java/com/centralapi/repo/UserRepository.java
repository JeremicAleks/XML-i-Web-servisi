package com.centralapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centralapi.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
