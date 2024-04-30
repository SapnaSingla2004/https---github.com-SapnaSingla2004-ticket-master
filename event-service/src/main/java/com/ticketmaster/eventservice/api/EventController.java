package com.ticketmaster.eventservice.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ticketmaster.eventservice.model.Event;
import com.ticketmaster.eventservice.model.EventResponse;
import com.ticketmaster.eventservice.service.EventService;



@RestController
public class EventController {
	
	private final EventService eventService;
	
	public EventController(EventService eventService) {
		this.eventService = eventService;
	}
	
	@GetMapping("/events-search/{artistId}")
	public EventResponse searchArtist(@PathVariable("artistId") String artistId) {
		
		
		List<Event> eventsForArtist = eventService.getEventsForArtist(artistId);
		
		return new EventResponse(eventsForArtist);
		
		
	}

}
