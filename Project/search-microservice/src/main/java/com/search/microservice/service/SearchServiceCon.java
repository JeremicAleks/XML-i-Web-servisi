package com.search.microservice.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.search.microservice.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.search.microservice.domain.dto.SearchParamsDTO;
import com.search.microservice.repository.LocationRepository;
import com.search.microservice.repository.RoomRepository;

@Service
public class SearchServiceCon implements SearchService {

	@Autowired
	RoomRepository roomRepository;

	@Autowired
	LocationRepository locationRepository;

	@Override
	public GetRooms search(SearchParamsDTO spDTO) {
		System.out.println("search service con");
		List<Room> searchResults = new ArrayList<Room>();

		SimpleDateFormat x = new SimpleDateFormat("yyyy.MM.dd.");
		
		System.out.println(spDTO.getCheckIn());

		Date date1 = spDTO.getCheckIn();
		System.out.println("CheckIn: " + x.format(date1));
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		Date date2 = spDTO.getCheckOut();
		System.out.println("CheckOut: " + x.format(date2));
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);

		if (spDTO.getDestination().isEmpty()) {
			System.out.println("nije unesena destinacija");
			System.out.println("ne znam sta treba da vratim");
			return null;
		}
		
		if (spDTO.getNumOfPeople() == 0) {
			System.out.println("nije unesen broj ljudi u pretrazi");
			System.out.println("ne znam sta treba da vratim");
			return null;
		}
		
		if (cal2.before(cal1) || areDatesEqual(date1, date2)) {
			System.out.println("CheckOut je pre CheckIn-a ili su isti datumi");
			return null;
		}

		for (Room r : roomRepository.findByNumberOfBeds(spDTO.getNumOfPeople())) {
			if (spDTO.getDistanceFromDestionation() == 0) {
				if (!r.getLocation().getName().contains(spDTO.getDestination()))
					continue;
			} else {
				if (!isInDestinationRange(spDTO.getDestination(), r.getLocation(), spDTO.getDistanceFromDestionation()))
					continue;
				
				if (!isFreeInSelectedTime(spDTO.getCheckIn(), spDTO.getCheckOut(), r.getReservation()))
					continue;
				
				if (spDTO.getAccCategory() != null && !r.getAccommodationCategory().equals(spDTO.getAccCategory())) {
					continue;
				}
				
				if (spDTO.getAccType() != null && !r.getAccommodationType().equals(spDTO.getAccType())) {
					continue;
				}
				
				if (!spDTO.getRoomServices().isEmpty()) {
					boolean shouldContinue = false;
					for (RoomAdditionalService ras : spDTO.getRoomServices()) {
						if (!r.getRoomAdditionalService().contains(ras))
							shouldContinue = true;
							break;
					}
					
					if (shouldContinue)
						continue;
				}
			}
			
			searchResults.add(r);
		}

		GetRooms foundRooms = new GetRooms();
		foundRooms.setRoom(searchResults);

		return foundRooms;
	}
	
	public boolean isFreeInSelectedTime(Date checkIn, Date checkOut, List<Reservation> roomReservations) {
		boolean ret = true;
		System.out.println("isFreeInSelectedTime");
		
		for (Reservation r : roomReservations) {
			if (!isDate1AfterDate2(checkIn, r.getCheckOut()))
				return false;
			
			if (!isDate1BeforeDate2(checkOut, r.getCheckIn()))
				return false;
		}
	
		return ret;
	}
	
	public boolean isDate1AfterDate2(Date date1, Date date2) {
		boolean ret = true;
		System.out.println("isDate1AfterDate2");
		
		SimpleDateFormat x = new SimpleDateFormat("yyyy.MM.dd.");
		String date1String[] = x.format(date1).split(".");
		String date2String[] = x.format(date2).split(".");
		
		int date1year = Integer.parseInt(date1String[0]);
		int date2year = Integer.parseInt(date2String[0]);
		
		if (date1year <= date2year)
			return false;
		
		int date1month = Integer.parseInt(date1String[1]);
		int date2month = Integer.parseInt(date2String[1]);
		
		if (date1month <= date2month)
			return false;
		
		int date1day = Integer.parseInt(date1String[2]);
		int date2day = Integer.parseInt(date2String[2]);
		
		if (date1day <= date2day)
			return false;
		
		return ret;
	}
	
	public boolean isDate1BeforeDate2(Date date1, Date date2) {
		boolean ret = true;
		System.out.println("isDate1BeforeDate2");
		SimpleDateFormat x = new SimpleDateFormat("yyyy.MM.dd.");
		String date1String[] = x.format(date1).split(".");
		String date2String[] = x.format(date2).split(".");
		
		int date1year = Integer.parseInt(date1String[0]);
		int date2year = Integer.parseInt(date2String[0]);
		
		if (date1year >= date2year)
			return false;
		
		int date1month = Integer.parseInt(date1String[1]);
		int date2month = Integer.parseInt(date2String[1]);
		
		if (date1month >= date2month)
			return false;
		
		int date1day = Integer.parseInt(date1String[2]);
		int date2day = Integer.parseInt(date2String[2]);
		
		if (date1day >= date2day)
			return false;
		
		return ret;
	}
	
	public boolean areDatesEqual(Date date1, Date date2) {
		boolean ret = true;
		System.out.println("areDatesEqual");
		SimpleDateFormat x = new SimpleDateFormat("yyyy.MM.dd.");
		String date1String[] = x.format(date1).split(".");
		String date2String[] = x.format(date2).split(".");
		
		int date1year = Integer.parseInt(date1String[0]);
		int date2year = Integer.parseInt(date2String[0]);
		
		if (date1year != date2year)
			return false;
		
		int date1month = Integer.parseInt(date1String[1]);
		int date2month = Integer.parseInt(date2String[1]);
		
		if (date1month != date2month)
			return false;
		
		int date1day = Integer.parseInt(date1String[2]);
		int date2day = Integer.parseInt(date2String[2]);
		
		if (date1day != date2day)
			return false;
		
		return ret;
	}

	public boolean isInDestinationRange(String destinationName, Location roomLocation, double range) {
		boolean ret = false;
		System.out.println("isInDestinationRange");
		/*
		 * List<Location> foundLocations =
		 * locationRepository.findByName(destinationName);
		 * 
		 * if (foundLocations.isEmpty()) return ret;
		 * 
		 */

		for (Location l : locationRepository.findByName(destinationName)) {
			if(distance(l.getLat(), l.getLng(), roomLocation.getLat(), roomLocation.getLng()) <= range) {
				ret = true;
				break;
			}
		}
		
		System.out.println("ret: " + ret);
		return ret;
	}

	public static double distance(double lat1, double lon1, double lat2, double lon2) {
		System.out.println("distance");
		final int R = 6371; // Radius of the earth

		double latDistance = Math.toRadians(lat2 - lat1);
		double lonDistance = Math.toRadians(lon2 - lon1);
		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = R * c;
		
		System.out.println("distance: " + distance);
		return distance;
	}

}
