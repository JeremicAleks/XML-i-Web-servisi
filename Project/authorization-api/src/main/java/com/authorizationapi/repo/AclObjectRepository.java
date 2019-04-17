package com.authorizationapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.authorizationapi.domain.acl.AclObjectIdentity;
/**
 * Na cemu sam ja gubio vreme :D:D:D
 * @author Kiriyaga
 *
 */
public interface AclObjectRepository extends JpaRepository<AclObjectIdentity, Long> {
	
	@Query(value = "SELECT * FROM acl_object_identity WHERE acl_object_identity.path =?1 ", nativeQuery = true)
	AclObjectIdentity findEntryForSid(String path);
	

}
