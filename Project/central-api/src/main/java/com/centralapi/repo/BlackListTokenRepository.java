package com.centralapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.centralapi.domain.BlackListToken;

public interface BlackListTokenRepository extends JpaRepository<BlackListToken, Long> {
	
	@Query(value = "SELECT * FROM black_list WHERE black_list.token =?1 ", nativeQuery = true)
	BlackListToken findByToken(String token);
}
