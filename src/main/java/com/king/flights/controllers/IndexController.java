package com.king.flights.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	
	@RequestMapping(path= {"*","/", "/home", "/index"})
	public String root() {
		return "flightSearch.html";
	}
	
	@RequestMapping(path= {"/admin"})
	public String admin() {
		return "admin.html";
	}
}
