package com.king.flights.models;

import java.util.Date;

import javax.validation.ValidationException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class FlightQuery {

	
	private String origin;
	private String destination;
	@DateTimeFormat(iso = ISO.DATE)
	private Date departureDate;
	@DateTimeFormat(iso = ISO.DATE)
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
	
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public void setAdults(int adults) {
		this.adults = adults;
	}
	public void setChildren(int children) {
		this.children = children;
	}
	public void setInfants(int infants) {
		this.infants = infants;
	}
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	
	
	public void validate() throws ValidationException {
		if (StringUtils.isBlank(origin)) {
			throw new ValidationException("Origin must be set");
		}
		if (StringUtils.isBlank(destination)) {
			throw new ValidationException("Destination must be set");
		}
		if (StringUtils.equals(origin, destination)) {
			throw new ValidationException("Origin and destination must be different");
		}
		if (departureDate == null) {
			throw new ValidationException("Departure date must be set");
		}
		if (returnDate != null && returnDate.before(departureDate)) {
			throw new ValidationException("Return date must be after departure date");
		}
		if (adults == 0 && children == 0) {
			throw new ValidationException("There must be some travelers (adults or children)");
		}
		if (infants > adults) {
			throw new ValidationException("There can't be more infants than adults");
		}
	}
}
