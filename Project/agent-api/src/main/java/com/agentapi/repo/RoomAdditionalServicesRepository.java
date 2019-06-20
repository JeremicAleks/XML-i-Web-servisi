package com.agentapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agentapi.com.centralapi.domain.xml.xml_ftn.rooms.RoomAdditionalService;

public interface RoomAdditionalServicesRepository extends JpaRepository<RoomAdditionalService, Long> {

}
