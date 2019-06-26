package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.RegistredUser;

public interface UserRepository extends JpaRepository<RegistredUser, Long> {
	
	
	@Query(value = "SELECT * FROM user LEFT JOIN registred_user ON user.id = registred_user.id WHERE user.username =?1 ", nativeQuery = true)
	RegistredUser findByUsername(String username);


}
