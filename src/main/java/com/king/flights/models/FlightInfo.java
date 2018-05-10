package com.king.flights.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FlightInfo {

	@JsonProperty
	private Airport origin;
	@JsonProperty
	private Airport destination;
	@JsonProperty
	private Date departureDate;
	@JsonProperty
	private Date returnDate;
	@JsonProperty
	private int departureStops;
	@JsonProperty
	private int returnStops;
	@JsonProperty
	private Currency currency;
	@JsonProperty
	private double price;
	
	public FlightInfo(Airport origin, Airport destination, Date departureDate, Date returnDate, int departureStops,
			int returnStops, Currency currency, double price) {
		this.origin = origin;
		this.destination = destination;
		this.departureDate = departureDate;
		this.returnDate = returnDate;
		this.departureStops = departureStops;
		this.returnStops = returnStops;
		this.currency = currency;
		this.price = price;
	}

	
	
	
	
	
	
}
