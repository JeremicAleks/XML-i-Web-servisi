package com.authorizationapi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.authorizationapi.domain.acl.AclEntry;
import com.authorizationapi.domain.acl.AclPrivilegeEnum;
/**
 * Na cemu sam ja gubio vreme :D:D:D
 * @author Kiriyaga
 *
 */
public interface AclEntryRepository extends JpaRepository<AclEntry, Long> {
	
	@Query(value = "SELECT * FROM acl_entry WHERE acl_entry.sid_id =?1 ", nativeQuery = true)
	List<AclEntry> findEntryForSid(Long sid);
	
	@Query(value = "SELECT * FROM acl_entry WHERE acl_entry.object_id=?1 ", nativeQuery = true)
	List<AclEntry> findEntryForObject(Long object);
	
	@Query(value = "SELECT privilege FROM acl_entry WHERE acl_entry.object_id=?1 and acl_entry.sid_id=?2 ", nativeQuery = true)
	List<AclPrivilegeEnum> findPrivileges(Long object,Long sidO);
	
}
