package com.king.flights.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.king.flights.repositories.AirportRepository;


@RestController
public class FlightController {

	@Autowired
	private AirportRepository airportRepository;
	
	@RequestMapping(path={"/", "/index"}, method=RequestMethod.GET)
	public ModelAndView getIndex() {
		ModelAndView mav = new ModelAndView("index.html");
		mav.addObject("airports", airportRepository.findAll());
		return mav;
	}
	
	@RequestMapping(path={"/flights"}, method=RequestMethod.POST)
	public String search() {
		return "index.html";
	}
	
}
