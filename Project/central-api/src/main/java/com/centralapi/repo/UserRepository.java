package com.centralapi.repo;

import com.centralapi.domain.xml.xml_ftn.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query(value = "SELECT * FROM user WHERE user.username =?1 ", nativeQuery = true)
    User findByUsername(String username);

    @Modifying
    @Transactional
    @Query(value = "UPDATE user SET role_id = ?1 WHERE role_id = ?2", nativeQuery = true)
    void updateUserRole(Long newRole,Long oldRole);

    User findByEmail(String email);
}
