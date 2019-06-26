package com.centralapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.centralapi.domain.xml.xml_ftn.rooms.AccommodationCategory;

public interface AccommodationCategoriesRepository extends JpaRepository<AccommodationCategory, Long> {

	@Query(value = "SELECT * FROM accommodation_category ac WHERE ac.description =?1 ", nativeQuery = true)
	AccommodationCategory findByDescription(String description);
}
