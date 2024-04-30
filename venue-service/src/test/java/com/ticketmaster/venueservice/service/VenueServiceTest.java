package com.ticketmaster.venueservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.ticketmaster.venueservice.model.Venue;
import com.ticketmaster.venueservice.repository.VenueRepository;

@ExtendWith(MockitoExtension.class)
public class VenueServiceTest {
	
	@Mock
	RestTemplate restTemplate;
	
	@Mock
	VenueRepository venueRepository;
	
	@InjectMocks
	VenueService venueService;
	
	@Test
	public void shouldGetVenue() {
		String venueId="41";
		
		Venue venue1 = new Venue();
		venue1.setId("41");
		venue1.setName("O2 Academy Sheffield");
		venue1.setCity("Sheffield");
		
		Venue venue2 = new Venue();
		venue2.setId("42");
		venue2.setName("O2 Institute2 Birmingham");
		venue2.setCity("Birmingham");
		
		Venue[] venues = {venue1, venue2};
		
		when(venueRepository.getVenueData()).thenReturn(venues);
		
		Venue venue = venueService.getVenue(venueId);
		
		assertEquals(venue.getId(), venueId);
		assertEquals(venue.getName(), venue1.getName());
		
	}
	
	@Test
	public void shouldThrowException() {
		
		String venueId="51";
		
		Venue venue1 = new Venue();
		venue1.setId("41");
		venue1.setName("O2 Academy Sheffield");
		venue1.setCity("Sheffield");
		
		Venue venue2 = new Venue();
		venue2.setId("42");
		venue2.setName("O2 Institute2 Birmingham");
		venue2.setCity("Birmingham");
		
		Venue[] venues = {venue1, venue2};
		
		when(venueRepository.getVenueData()).thenReturn(venues);
		
		assertThrows(ResponseStatusException.class, () -> venueService.getVenue(venueId));
	}

}
