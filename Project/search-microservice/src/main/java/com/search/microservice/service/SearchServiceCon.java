package com.search.microservice.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.search.microservice.domain.GetRooms;
import com.search.microservice.domain.Location;
import com.search.microservice.domain.Room;
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

		Date date1 = new Date();
		System.out.println("Date1: " + x.format(date1));
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		Date date2 = new Date();
		System.out.println("Date2: " + x.format(date2));
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);

		if (spDTO.getDestination().isEmpty() || spDTO.getNumOfPeople() == 0) {
			System.out.println("nije unesena destinacija ili broj ljudi u pretrazi");
			System.out.println("ne znam sta treba da vratim");
			return null;
		}

		for (Room r : roomRepository.findByNumberOfBeds(spDTO.getNumOfPeople())) {
			if (spDTO.getDistanceFromDestionation() == 0) {
				if (!r.getLocation().getName().contains(spDTO.getDestination()))
					continue;
			} else {
				if (!isInDestinationRange(spDTO.getDestination(), r.getLocation(), spDTO.getDistanceFromDestionation()))
					continue;

			}
			
			searchResults.add(r);
		}

		GetRooms foundRooms = new GetRooms();
		foundRooms.setRoom(searchResults);

		return foundRooms;
	}

	public boolean isInDestinationRange(String destinationName, Location roomLocation, double range) {
		boolean ret = false;

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

		return ret;
	}

	public static double distance(double lat1, double lon1, double lat2, double lon2) {

		final int R = 6371; // Radius of the earth

		double latDistance = Math.toRadians(lat2 - lat1);
		double lonDistance = Math.toRadians(lon2 - lon1);
		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = R * c;

		return distance;
	}

}
