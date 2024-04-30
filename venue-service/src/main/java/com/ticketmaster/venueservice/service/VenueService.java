package com.ticketmaster.venueservice.service;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ticketmaster.venueservice.model.Venue;
import com.ticketmaster.venueservice.repository.VenueRepository;

@Service
public class VenueService {
	
	private VenueRepository venueRepository;
	
	public VenueService(VenueRepository venueRepository) {
		this.venueRepository = venueRepository;
	}
	
	public Venue getVenue(String venueId) {
		Venue[] venues = venueRepository.getVenueData();
		
		Optional<Venue> venueOptional =  Arrays.stream(venues).filter(a-> a.getId().equals(venueId)).findFirst();
		
		//throw exception if venue doesn't exist
		if(venueOptional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "venue doesnt exist");
		}
		return venueOptional.get();
	}

}
