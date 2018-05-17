package com.king.flights.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.ValidationException;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Airport {
	
	
	@Id
	@JsonProperty
	private String iataCode;
	@JsonProperty
	private String name;
	
	
	
	public Airport(String iataCode, String name) {
		this.iataCode = iataCode;
		this.name = name;
	}
	
	

	public Airport() {
	}



	public String getIataCode() {
		return iataCode;
	}

	public String getName() {
		return name;
	}

	
	public void validate() {
		if (StringUtils.isEmpty(iataCode)) {
			throw new ValidationException("IATA code must be three uppercase characters");
		}
		
		if (iataCode.length() != 3) {
			throw new ValidationException("IATA code must be three uppercase characters");
		}
		if (!iataCode.equals(iataCode.toUpperCase())) {
			throw new ValidationException("IATA code must be three uppercase characters");
		}
		if (StringUtils.isEmpty(name)) {
			throw new ValidationException("Airport name must not be empty");
		}
	}
	
	

	
	
	
}
