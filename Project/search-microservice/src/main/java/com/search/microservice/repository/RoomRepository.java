package com.search.microservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.search.microservice.domain.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {

	@Query(value = "SELECT * FROM room r WHERE r.number_of_beds >= ?1", nativeQuery = true)
	public List<Room> findByNumberOfBeds(int numberOfBeds);
}
