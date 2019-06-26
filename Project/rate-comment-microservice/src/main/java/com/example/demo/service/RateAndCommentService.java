package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.RateAndComment;
import com.example.demo.domain.dto.AddRateAndCommentDTO;

public interface RateAndCommentService {
	
	public List<RateAndComment> getRatesForRoom(Long idRoom);
	
	public RateAndComment addRateAndComment(AddRateAndCommentDTO rate);
	
	public void AllowRateAndComment(Long id) throws Exception;

}
