package com.centralapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.centralapi.domain.xml.xml_ftn.rooms.AccommodationType;

public interface AccommodationTypeRepository extends JpaRepository<AccommodationType, Long> {

	@Query(value = "SELECT * FROM accommodation_type at WHERE at.description =?1 ", nativeQuery = true)
	AccommodationType findByDescription(String description);
}
