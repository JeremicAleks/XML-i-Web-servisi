package com.room.microservice.repository;

import com.room.microservice.domain.PriceList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceListRepository extends JpaRepository<PriceList,Long> {
}
