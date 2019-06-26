package com.authorizationapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.authorizationapi.domain.RegistredUser;
import com.authorizationapi.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query(value = "SELECT * FROM user WHERE user.username =?1 ", nativeQuery = true)
	User findByUsername(String username);

}
