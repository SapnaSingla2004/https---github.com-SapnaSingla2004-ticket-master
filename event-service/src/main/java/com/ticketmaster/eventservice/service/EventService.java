package com.ticketmaster.eventservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ticketmaster.eventservice.model.Event;
import com.ticketmaster.eventservice.repository.EventRepository;

@Service
public class EventService {
	
	private final EventRepository eventRepository;
	
	public EventService(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}
	
	private List<Event> filterEventsForArtist(String artistId, Event[] events){
		
		return Arrays.stream(events).filter( event -> event.getArtists().stream().filter(a -> a.getId().equals(artistId)).count() > 0).collect(Collectors.toList());
				
	}
	
	public List<Event> getEventsForArtist(String artistId){
		Event[] events = eventRepository.getEventsData();
		
		return filterEventsForArtist(artistId, events);
	}

}
