package com.ticketmaster.venueservice.api;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ticketmaster.venueservice.model.Venue;
import com.ticketmaster.venueservice.service.VenueService;


@RestController
public class VenueController {
	
	private VenueService venueService;
	
	public VenueController(VenueService venueService) {
		this.venueService = venueService;
	}
	
	@GetMapping("/venue-search/{id}")
	public Venue searchArtist(@PathVariable("id") String venueId) {
		return venueService.getVenue(venueId);
	}

}
