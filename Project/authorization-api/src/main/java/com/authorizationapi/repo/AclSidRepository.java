package com.authorizationapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.authorizationapi.domain.acl.AclSid;

public interface AclSidRepository extends JpaRepository<AclSid, Long> {
	
	@Query(value = "SELECT * FROM acl_sid WHERE acl_sid.sid =?1 ", nativeQuery = true)
	AclSid findBySid(String name);

}
