package com.king.flights.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FlightInfo {

	@JsonProperty
	private String origin;
	@JsonProperty
	private String destination;
	@JsonProperty
	@JsonFormat(pattern = "dd.MM.yyyy hh:mm")
	private Date departureDate;
	@JsonProperty
	@JsonFormat(pattern = "dd.MM.yyyy hh:mm")
	private Date returnDate;
	@JsonProperty
	private int departureStops;
	@JsonProperty
	private int returnStops;
	@JsonProperty
	private Currency currency;
	@JsonProperty
	private String price;
	
	public FlightInfo(String origin, String destination, Date departureDate, Date returnDate, int departureStops,
			int returnStops, Currency currency, String price) {
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
