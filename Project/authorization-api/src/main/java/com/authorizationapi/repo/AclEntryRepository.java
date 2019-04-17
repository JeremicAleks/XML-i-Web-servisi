package com.authorizationapi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.authorizationapi.domain.acl.AclEntry;

public interface AclEntryRepository extends JpaRepository<AclEntry, Long> {
	
	@Query(value = "SELECT * FROM acl_entry WHERE acl_entry.sid_id =?1 ", nativeQuery = true)
	List<AclEntry> findEntryForSid(Long sid);
	
}
