package com.ticketmaster.artistservice.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ticketmaster.artistservice.model.Artist;
import com.ticketmaster.artistservice.service.ArtistService;

@RestController
public class ArtistController {
	
	private final ArtistService artistService;
	
	public ArtistController(ArtistService artistService) {
		this.artistService = artistService;
	}
	
	@GetMapping("/artist-search/{id}")
	public Artist searchArtist(@PathVariable("id") String artistId) {
		return artistService.getArtist(artistId);
	}

}
