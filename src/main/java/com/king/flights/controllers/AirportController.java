package com.king.flights.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.king.flights.models.Airport;
import com.king.flights.repositories.AirportRepository;

@RequestMapping("/airports")
@RestController
public class AirportController {

	@Autowired
	private AirportRepository airportRepository;
	
	@RequestMapping(method=RequestMethod.GET)
	public Collection<Airport> getAllAirports() {
		return (Collection<Airport>) airportRepository.findAll();
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public void addAirport(@RequestBody Airport airport) {
		airportRepository.save(airport);
	}
}
