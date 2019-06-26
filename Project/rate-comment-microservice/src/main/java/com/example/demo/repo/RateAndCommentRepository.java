package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.RateAndComment;

public interface RateAndCommentRepository extends JpaRepository<RateAndComment, Long> {
	
	@Query(value = "SELECT * FROM rate_and_comment WHERE room_id =?1", nativeQuery = true)
	List<RateAndComment> getRatesForRoom(Long id);

}
