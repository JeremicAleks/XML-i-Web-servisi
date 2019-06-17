package com.room.microservice.repository;

import com.room.microservice.domain.RateAndComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateAndCommentRepository extends JpaRepository<RateAndComment,Long> {
}
