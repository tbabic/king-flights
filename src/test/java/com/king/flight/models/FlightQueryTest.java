package com.king.flight.models;

import java.util.Date;

import javax.validation.ValidationException;

import org.junit.Test;

import com.king.flights.models.FlightQuery;

public class FlightQueryTest {

	@Test
	public void queryValidation_minimalData_Ok() {
		FlightQuery query = new FlightQuery();
		query.setAdults(1);
		query.setOrigin("ABC");
		query.setDestination("123");
		query.setDepartureDate(new Date());
		query.validate();
	}
	
	@Test
	public void queryValidation_minimalDataChildren_Ok() {
		FlightQuery query = new FlightQuery();
		query.setChildren(1);
		query.setOrigin("ABC");
		query.setDestination("123");
		query.setDepartureDate(new Date());
		query.validate();
	}
	
	@Test(expected = ValidationException.class)
	public void queryValidation_returnAfterDeparture_Ok() {
		FlightQuery query = new FlightQuery();
		query.setAdults(1);
		query.setOrigin("ABC");
		query.setDestination("ABC");
		query.setDepartureDate(new Date());
		query.setReturnDate(new Date());
		query.validate();
	}
	
	@Test(expected = ValidationException.class)
	public void queryValidation_sameOriginDestination_Exception() {
		FlightQuery query = new FlightQuery();
		query.setAdults(1);
		query.setOrigin("ABC");
		query.setDestination("ABC");
		query.setDepartureDate(new Date());
		query.validate();
	}
	
	@Test(expected = ValidationException.class)
	public void queryValidation_missingDestination_Exception() {
		FlightQuery query = new FlightQuery();
		query.setAdults(1);
		query.setOrigin("ABC");
		query.setDepartureDate(new Date());
		query.validate();
	}
	
	@Test(expected = ValidationException.class)
	public void queryValidation_missingOrigin_Exception() {
		FlightQuery query = new FlightQuery();
		query.setAdults(1);
		query.setOrigin("ABC");
		query.setDestination("ABC");
		query.setDepartureDate(new Date());
		query.validate();
	}
	
	@Test(expected = ValidationException.class)
	public void queryValidation_returnBeforeDeparture_Exception() {
		FlightQuery query = new FlightQuery();
		query.setAdults(1);
		query.setOrigin("ABC");
		query.setDestination("ABC");
		query.setReturnDate(new Date());
		query.setDepartureDate(new Date());
		query.validate();
	}
	
	@Test(expected = ValidationException.class)
	public void queryValidation_noPassengers_Exception() {
		FlightQuery query = new FlightQuery();
		query.setOrigin("ABC");
		query.setDestination("ABC");
		query.setDepartureDate(new Date());
		query.validate();
	}
	
	@Test(expected = ValidationException.class)
	public void queryValidation_onlyInfants_Exception() {
		FlightQuery query = new FlightQuery();
		query.setInfants(1);
		query.setOrigin("ABC");
		query.setDestination("ABC");
		query.setDepartureDate(new Date());
		query.validate();
	}
	
	@Test(expected = ValidationException.class)
	public void queryValidation_childrenAndInfants_Exception() {
		FlightQuery query = new FlightQuery();
		query.setInfants(1);
		query.setChildren(1);
		query.setOrigin("ABC");
		query.setDestination("ABC");
		query.setDepartureDate(new Date());
		query.validate();
	}
	
	@Test(expected = ValidationException.class)
	public void queryValidation_adultsMoreThanInfants_Ok() {
		FlightQuery query = new FlightQuery();
		query.setInfants(1);
		query.setAdults(1);
		query.setOrigin("ABC");
		query.setDestination("ABC");
		query.setDepartureDate(new Date());
		query.validate();
	}
	
	@Test(expected = ValidationException.class)
	public void queryValidation_adultsLessThanInfants_Exception() {
		FlightQuery query = new FlightQuery();
		query.setInfants(2);
		query.setAdults(1);
		query.setOrigin("ABC");
		query.setDestination("ABC");
		query.setDepartureDate(new Date());
		query.validate();
	}
}
