package com.centralapi.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.centralapi.domain.xml.xml_ftn.users.User;


public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query(value = "SELECT * FROM user WHERE user.username =?1 ", nativeQuery = true)
	User findByUsername(String username);

	@Modifying
	@Transactional
	@Query(value = "UPDATE user SET role_id = ?1 WHERE role_id = ?2", nativeQuery = true)
	void updateUserRole(Long newRole,Long oldRole); 

	@Modifying
	@Transactional
	@Query(value = "UPDATE user SET role_id = ?1 WHERE id=?2", nativeQuery = true)
	void updateUserRoleForUser(Long newRole,Long user); 


	@Modifying
	@Transactional
	@Query(value = "UPDATE user SET user = 'AGENT' WHERE id = ?1", nativeQuery = true)
	void updateUserUserForUser(Long user); 
	



}
