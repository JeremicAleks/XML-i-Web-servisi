package com.authorizationapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.authorizationapi.domain.RegistredUser;

public interface RegUserRepository extends JpaRepository<RegistredUser, Long> {
	
	 @Query(value = "SELECT * FROM user u left join registred_user r on u.id=r.id WHERE u.username =?1 ", nativeQuery = true)
	    RegistredUser findByUsername(String username);


}
