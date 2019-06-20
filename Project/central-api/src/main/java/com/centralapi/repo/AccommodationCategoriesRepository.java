package com.centralapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centralapi.domain.xml.xml_ftn.rooms.AccommodationCategory;

public interface AccommodationCategoriesRepository extends JpaRepository<AccommodationCategory, Long> {

}
