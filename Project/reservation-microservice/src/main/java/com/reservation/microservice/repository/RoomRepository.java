package com.reservation.microservice.repository;

import com.reservation.microservice.domain.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoomRepository extends JpaRepository<Room,Long> {

    @Query(value = "select room_id from room_reservation where reservation_id=?1",nativeQuery = true)
    Long roomid(Long id);

}
