package com.ticketmaster.venueservice.repository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.ticketmaster.venueservice.model.Venue;

@Repository
public class VenueRepository {
	
	private final RestTemplate restTemplate;
	
	public VenueRepository(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public Venue[] getVenueData() {
		
		String artistsUrl
		  = "https://iccp-interview-data.s3-eu-west-1.amazonaws.com/78656681/venues.json";
		
		ResponseEntity<Venue[]> response =
				  restTemplate.getForEntity(
						  artistsUrl,
						  Venue[].class);
		
		return response.getBody();
	}

}
