package com.king.flights.models;

import javax.persistence.Id;


public class Airport {
	
	
	@Id
	private String iataCode;
	private String name;
	
	public String getIataCode() {
		return iataCode;
	}

	public String getName() {
		return name;
	}

	
	

	
	
	
}
