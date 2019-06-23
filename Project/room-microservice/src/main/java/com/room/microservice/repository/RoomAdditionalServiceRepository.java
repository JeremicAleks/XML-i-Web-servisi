package com.room.microservice.repository;

import com.room.microservice.domain.RoomAdditionalService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomAdditionalServiceRepository extends JpaRepository<RoomAdditionalService,Long> {
}
