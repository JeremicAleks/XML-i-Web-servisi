package com.reservation.microservice.repository;

import com.reservation.microservice.domain.user.RegistredUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RegisteredUserRepository extends JpaRepository<RegistredUser,Long> {

    @Query(value = "SELECT * FROM user u left join register_user r on u.id=r.id WHERE u.username =?1 ", nativeQuery = true)
    RegistredUser findByUsername(String username);

}
