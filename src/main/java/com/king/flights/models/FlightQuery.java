package com.king.flights.models;

import java.util.Date;

public class FlightQuery {

	private String origin;
	private String destination;
	private Date departureDate; 
	private Date returnDate; 
	private int adults; 
	private int children; 
	private int infants;
	private Currency currency;
	
	public String getOrigin() {
		return origin;
	}
	public String getDestination() {
		return destination;
	}
	public Date getDepartureDate() {
		return departureDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public int getAdults() {
		return adults;
	}
	public int getChildren() {
		return children;
	}
	public int getInfants() {
		return infants;
	}
	public Currency getCurrency() {
		return currency;
	}

	
	
}
