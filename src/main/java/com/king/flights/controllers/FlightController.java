package com.king.flights.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.king.flights.models.FlightInfo;
import com.king.flights.models.FlightQuery;
import com.king.flights.services.FlightsSeachService;


@RestController
@RequestMapping("/flights")
public class FlightController {

	@Autowired
	private FlightsSeachService flightSearchService;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<FlightInfo> searchFlights(FlightQuery flightQuery) throws IOException {
		flightQuery.validate();
		return flightSearchService.findFlights(flightQuery);
	}
	
	
}
