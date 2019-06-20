package com.centralapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centralapi.domain.xml.xml_ftn.rooms.RoomAdditionalService;

public interface RoomAdditionalServicesRepository extends JpaRepository<RoomAdditionalService, Long> {

}
