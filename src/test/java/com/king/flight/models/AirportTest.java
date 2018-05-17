package com.king.flight.models;

import javax.validation.ValidationException;

import org.junit.Test;

import com.king.flights.models.Airport;

public class AirportTest {

	@Test
	public void airportValidation_ok() {
		Airport airport = new Airport("IAT","name");
		airport.validate();
	}
	
	@Test(expected = ValidationException.class)
	public void airportValidation_missingIata_Exception() {
		Airport airport = new Airport(null,"name");
		airport.validate();
	}
	
	@Test(expected = ValidationException.class)
	public void airportValidation_emptyIata_Exception() {
		Airport airport = new Airport("","name");
		airport.validate();
	}
	
	@Test(expected = ValidationException.class)
	public void airportValidation_tooShortIata_Exception() {
		Airport airport = new Airport("IA","name");
		airport.validate();
	}
	
	@Test(expected = ValidationException.class)
	public void airportValidation_lowerCaseIata_Exception() {
		Airport airport = new Airport("iat","name");
		airport.validate();
	}
	
	@Test(expected = ValidationException.class)
	public void airportValidation_tooLongIata_Exception() {
		Airport airport = new Airport("IATA","name");
		airport.validate();
	}
	
	@Test(expected = ValidationException.class)
	public void airportValidation_missingName_Exception() {
		Airport airport = new Airport("IAT","");
		airport.validate();
	}
}
