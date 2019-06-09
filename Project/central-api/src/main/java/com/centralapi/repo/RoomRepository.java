package com.centralapi.repo;

import com.centralapi.domain.xml.xml_ftn.rooms.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room,Long> {



}
