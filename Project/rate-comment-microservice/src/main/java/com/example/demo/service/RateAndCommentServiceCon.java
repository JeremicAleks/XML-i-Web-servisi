package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.RateAndComment;
import com.example.demo.domain.Reservation;
import com.example.demo.domain.Room;
import com.example.demo.domain.dto.AddRateAndCommentDTO;
import com.example.demo.repo.RateAndCommentRepository;
import com.example.demo.repo.ReservationRepository;
import com.example.demo.repo.RoomRepository;

@Service
public class RateAndCommentServiceCon implements RateAndCommentService {

	@Autowired
	RoomRepository roomRepo;
	@Autowired
	RateAndCommentRepository rateRepo;
	@Autowired
	ReservationRepository resRepo;
	
	@Override
	public List<RateAndComment> getRatesForRoom(Long idRoom) {
		
		try {
		Room r  = roomRepo.getOne(idRoom);
		return rateRepo.getRatesForRoom(idRoom);
		}
		catch (Exception e) {
			return null;
		}

	}
	@Override
	public RateAndComment addRateAndComment(AddRateAndCommentDTO rate) {
		
		try {
			Room r  = roomRepo.getOne(rate.getRoomId());
			Reservation res = resRepo.getOne(rate.getReservationId());
			
			
			System.out.println("USAOO");
			RateAndComment rateAndComment = new RateAndComment();
			rateAndComment.setComment(rate.getComment());
			rateAndComment.setIsAllowed(false);
			rateAndComment.setRoom(null);
			rateAndComment.setReservation(null);
			rateAndComment.setRating(rate.getRate());
			rateAndComment.setRegUser(null);
			rateRepo.saveAndFlush(rateAndComment);
			
			return rateAndComment;
			
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public void AllowRateAndComment(Long id) throws Exception {
		
	
		RateAndComment rate = rateRepo.getOne(id);
		rate.setAllowed(true);
		rateRepo.save(rate);
		
		
	}

}
