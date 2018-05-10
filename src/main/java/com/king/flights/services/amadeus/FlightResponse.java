package com.king.flights.services.amadeus;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.king.flights.models.Currency;
import com.king.flights.models.FlightInfo;

class FlightResponse {

	private Currency currency;
	private List<FlightResult> results;
	
	List<FlightInfo> convertToFlightInfos() {
		return results.stream().map(r -> r.convertToFlightInfo(currency)).collect(Collectors.toList());
	}
	
	private static class FlightResult {
		private List<Itinerary> itineraries;
		private Map<String, Object> fare;
		
		private FlightInfo convertToFlightInfo(Currency currency) {
			
			return null;
		}
	}
	
	private static class Itinerary {
		private Bound outbound;
		private Bound inbound;
	}
	
	private static class Bound {
		private List<Flight> flights;
	}
	
	private static class Flight {
		@JsonProperty("departs_at")
		private String departsAt;
		@JsonProperty("arrives_at")
		private String arrivesAt;
		private String origin;
		private String destination;
	}
	
	private static class Fare {
		
	}
	
}
