package com.ticketmaster.eventservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ticketmaster.eventservice.model.Artist;
import com.ticketmaster.eventservice.model.Event;
import com.ticketmaster.eventservice.model.Venue;
import com.ticketmaster.eventservice.repository.EventRepository;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

	
	@Mock
	EventRepository eventRepository;
	
	@InjectMocks
	EventService eventService;
	
	@Test
	public void shouldGetEventsForArtist() {
		String artistId = "21";
		
		Artist artist1 = new Artist();
		artist1.setId("21");
		
		Artist artist2 = new Artist();
		artist2.setId("23");
		
		Artist artist3 = new Artist();
		artist3.setId("26");
		
		Venue venue = new Venue();
		venue.setId("41");
		
		Event event1 = new Event();
		event1.setId("1");
		event1.setTitle("Fusion Prog");
		event1.setDateStatus("singleDate");
		
		event1.setVenue(venue);
		event1.setArtists(List.of(artist1, artist2));
		
		Event event2 = new Event();
		event2.setId("1");
		event2.setTitle("Fusion Prog");
		event2.setDateStatus("singleDate");
		
		event2.setVenue(venue);
		event2.setArtists(List.of(artist2, artist3));
		
		Event[] events = {event1, event2};
		
		when(eventRepository.getEventsData()).thenReturn(events);
		
		List<Event> eventList = eventService.getEventsForArtist(artistId);
		
		assertEquals(eventList.size(), 1);
		assertEquals(eventList.get(0).getId(), event1.getId());
		assertEquals(eventList.get(0).getTitle(), event1.getTitle());
		
	}

}
