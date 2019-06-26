package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.RateAndComment;
import com.example.demo.domain.RegistredUser;
import com.example.demo.domain.Reservation;
import com.example.demo.domain.Room;
import com.example.demo.domain.User;
import com.example.demo.domain.dto.AddRateAndCommentDTO;
import com.example.demo.repo.RateAndCommentRepository;
import com.example.demo.repo.ReservationRepository;
import com.example.demo.repo.RoomRepository;
import com.example.demo.repo.UserRepository;

@Service
public class RateAndCommentServiceCon implements RateAndCommentService {

	@Autowired
	RoomRepository roomRepo;
	@Autowired
	RateAndCommentRepository rateRepo;
	@Autowired
	ReservationRepository resRepo;
	@Autowired
	UserRepository regUserRepo;
	
	@Override
	public List<RateAndComment> getRatesForRoom(Long idRoom) {
		
		try {
		Room r  = roomRepo.getOne(idRoom);
		List<RateAndComment> rates = rateRepo.getRatesForRoom(idRoom);

		return rates;
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
			RegistredUser reg = regUserRepo.findByUsername(rate.getUsername());

			RateAndComment rateAndComment = new RateAndComment();
			rateAndComment.setComment(rate.getComment());
			rateAndComment.setIsAllowed(false);
			rateAndComment.setRoom(r);
			rateAndComment.setReservation(res);
			rateAndComment.setRating(rate.getRate());
			rateAndComment.setRegUser(reg);
			rateRepo.save(rateAndComment);
			
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
