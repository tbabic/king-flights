package com.king.flights.services;

import java.io.IOException;
import java.util.List;

import com.king.flights.models.FlightInfo;
import com.king.flights.models.FlightQuery;

public interface FlightsSeachService {

	
	public List<FlightInfo> findFlights(FlightQuery flightQuery) throws IOException;
}
