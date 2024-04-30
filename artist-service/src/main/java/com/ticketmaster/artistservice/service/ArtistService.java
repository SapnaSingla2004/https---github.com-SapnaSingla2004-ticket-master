package com.ticketmaster.artistservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.ticketmaster.artistservice.model.Artist;
import com.ticketmaster.artistservice.model.Event;
import com.ticketmaster.artistservice.model.EventResponse;
import com.ticketmaster.artistservice.model.Venue;
import com.ticketmaster.artistservice.repository.ArtistRepository;

@Service
public class ArtistService {
	
	private final ArtistRepository artistRepository;
	
	private final RestTemplate restTemplate;
	
	public ArtistService(ArtistRepository artistRepository, RestTemplate restTemplate) {
		this.artistRepository = artistRepository;
		this.restTemplate = restTemplate;
	}
	
	public Artist getArtist(String artistId) {
		Artist[] artistArray = artistRepository.getArtistData();
		
		Optional<Artist> artistOptional =  Arrays.stream(artistArray).filter(a-> a.getId().equals(artistId)).findFirst();
		
		//throw exception if author doesn't exist
		if(artistOptional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "author doesnt exist");
		}
		
		Artist artist = artistOptional.get();
		
		artist.setEvents(this.getEventsForArtist(artistId));
		
		for (Event event : artist.getEvents()) {
			
			event.setVenue(getVenueForEvent(event.getVenue().getId()));
			
		}
		
		return artist;
	}
	
	private List<Event> getEventsForArtist(String artistId) {
		
		String eventsUrl = "http://event-service/events-search/" + artistId;
		
		try {
			EventResponse eventResponse = restTemplate.getForObject(eventsUrl, EventResponse.class);
			
			return eventResponse.events();
		}catch (IllegalStateException e) {
			throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "EventService is not available");
		}
		
	}
	
	private Venue getVenueForEvent(String venueId) {
		String eventsUrl = "http://venue-service/venue-search/" + venueId;
		
		try {
			return restTemplate.getForObject(eventsUrl, Venue.class);
		}catch (IllegalStateException e) {
			throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "VenueService is not available");
		}
		
	}

}
