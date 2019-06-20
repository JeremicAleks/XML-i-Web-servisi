package com.agentapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agentapi.com.centralapi.domain.xml.xml_ftn.rooms.AccommodationType;

public interface AccommodationTypeRepository extends JpaRepository<AccommodationType, Long> {

}
