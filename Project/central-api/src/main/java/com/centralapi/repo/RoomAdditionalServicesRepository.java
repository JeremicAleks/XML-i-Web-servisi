package com.centralapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.centralapi.domain.xml.xml_ftn.rooms.RoomAdditionalService;

public interface RoomAdditionalServicesRepository extends JpaRepository<RoomAdditionalService, Long> {

	@Query(value = "SELECT * FROM room_additional_service ras WHERE ras.description =?1 ", nativeQuery = true)
	RoomAdditionalService findByDescription(String description);
	
}
