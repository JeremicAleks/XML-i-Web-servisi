package com.agentapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agentapi.com.centralapi.domain.xml.xml_ftn.users.UserInfo;

public interface UserInfoRepo extends JpaRepository<UserInfo, String> {

}
