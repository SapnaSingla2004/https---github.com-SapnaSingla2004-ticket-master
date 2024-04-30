package com.ticketmaster.eventservice.repository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ticketmaster.eventservice.model.Event;

@Component
public class EventRepository {
	
	private final RestTemplate restTemplate;
	
	public EventRepository(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public Event[] getEventsData() {
		String eventsUrl
		  = "https://iccp-interview-data.s3-eu-west-1.amazonaws.com/78656681/events.json";
		
		ResponseEntity<Event[]> response =
				  restTemplate.getForEntity(
						  eventsUrl,
						  Event[].class);
		
		return response.getBody();
	}

}
