package com.room.microservice.repository;

import com.room.microservice.domain.AccommodationCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationCategoriesRepository extends JpaRepository<AccommodationCategory, Long> {

}
