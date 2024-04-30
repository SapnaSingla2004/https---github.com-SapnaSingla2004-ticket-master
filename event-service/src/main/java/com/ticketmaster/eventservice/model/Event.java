package com.ticketmaster.eventservice.model;

import java.time.LocalDate;
import java.util.List;

public class Event {
	
	private String title;
	
	private String id;
	
	private String dateStatus;
	
	private String timeZone;
	
	private LocalDate startDate;
	
	List<Artist> artists;
	
	Venue venue;
	
	boolean hiddenFromSearch;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDateStatus() {
		return dateStatus;
	}

	public void setDateStatus(String dateStatus) {
		this.dateStatus = dateStatus;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public List<Artist> getArtists() {
		return artists;
	}

	public void setArtists(List<Artist> artists) {
		this.artists = artists;
	}

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}

	public boolean isHiddenFromSearch() {
		return hiddenFromSearch;
	}

	public void setHiddenFromSearch(boolean hiddenFromSearch) {
		this.hiddenFromSearch = hiddenFromSearch;
	}
	
	

}
