package com.ticketmaster.artistservice.repository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ticketmaster.artistservice.model.Artist;

@Component
public class ArtistRepository {
	
	public Artist[] getArtistData() {
		
		RestTemplate restTemplate = new RestTemplate();
		
		String artistsUrl
		  = "https://iccp-interview-data.s3-eu-west-1.amazonaws.com/78656681/artists.json";
		
		ResponseEntity<Artist[]> response =
				  restTemplate.getForEntity(
						  artistsUrl,
				  Artist[].class);
		
		return response.getBody();
		
	}

}
