package com.search.microservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.search.microservice.domain.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
	
	@Query(value = "SELECT * FROM location l WHERE l.name LIKE '%?1%'", nativeQuery = true)
	public List<Location> findByName(String destinationName);
}
