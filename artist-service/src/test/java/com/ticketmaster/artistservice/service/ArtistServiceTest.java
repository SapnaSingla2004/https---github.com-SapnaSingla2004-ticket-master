package com.ticketmaster.artistservice.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.ticketmaster.artistservice.model.Artist;
import com.ticketmaster.artistservice.model.Event;
import com.ticketmaster.artistservice.model.EventResponse;
import com.ticketmaster.artistservice.model.Venue;
import com.ticketmaster.artistservice.repository.ArtistRepository;

@ExtendWith(MockitoExtension.class)
public class ArtistServiceTest {
	
	@Mock
	RestTemplate restTemplate;
	
	@Mock
	ArtistRepository artistRepository;
	
	@InjectMocks
	ArtistService artistService;
	
	@Test
	public void shouldGetArtist() {
		String artistId = "21";
		
		Event event = new Event();
		event.setId("1");
		event.setTitle("Fusion Prog");
		event.setDateStatus("singleDate");
		Venue venue = new Venue();
		venue.setId("41");
		event.setVenue(venue);
		
		
		when(restTemplate.getForObject("http://event-service/events-search/21", EventResponse.class)).thenReturn(new EventResponse(List.of(event)));
		venue.setName("O2 Academy Sheffield");
		venue.setCity("Sheffield");
		when(restTemplate.getForObject("http://venue-service/venue-search/41", Venue.class)).thenReturn(venue);
		
		Artist artistData = new Artist();
		artistData.setId("21");
		artistData.setName("HRH Prog");
		
		Artist[] artists = {artistData};
		
		when(artistRepository.getArtistData()).thenReturn(artists);
		
		Artist artist = artistService.getArtist(artistId);
		
		assertNotNull(artist);
		assertEquals(artist.getName(), artistData.getName());
		assertEquals(artist.getEvents().get(0).getId(), event.getId());
		assertEquals(artist.getEvents().get(0).getVenue().getName(), venue.getName());
	}
	
	@Test
	public void shouldThrowException() {
		String artistId = "41";
		
		Artist artistData = new Artist();
		artistData.setId("21");
		artistData.setName("HRH Prog");
		
		Artist[] artists = {artistData};
		
		when(artistRepository.getArtistData()).thenReturn(artists);
		
		assertThrows(ResponseStatusException.class, () -> artistService.getArtist(artistId));
	}

}
