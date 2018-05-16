package com.king.flights.services.amadeus;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.king.flights.models.Currency;
import com.king.flights.models.FlightInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
class FlightResponse {

	@JsonProperty
	private String currency;
	@JsonProperty
	private List<FlightResult> results;
	
	List<FlightInfo> convertToFlightInfos() {
		return results.stream().map(r -> r.convertToFlightInfo(currency)).collect(Collectors.toList());
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	private static class FlightResult {
		@JsonProperty
		private List<Itinerary> itineraries;
		@JsonProperty
		private Fare fare;
		
		private FlightInfo convertToFlightInfo(String currency) {
			String origin = itineraries.get(0).getOrigin();
			String destination = itineraries.get(0).getDestination();
			Date departureDate = itineraries.get(0).getDepartureDate();
			Date returnDate = itineraries.get(0).getReturnDate();
			int departureStops = itineraries.get(0).getDepartureStops();
			int returnStops = itineraries.get(0).getReturnStops();
			String price = fare.price;
			return new FlightInfo(origin, destination, departureDate, returnDate, departureStops, returnStops, Currency.valueOf(currency), price);
		}
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	private static class Itinerary {
		@JsonProperty
		private Bound outbound;
		@JsonProperty
		private Bound inbound;
		
		private String getOrigin() {
			return outbound != null ? outbound.getOrigin() : null;
		}
		
		private String getDestination() {
			return outbound != null ? outbound.getDestination() : null;
		}
		
		private Date getDepartureDate() {
			return outbound != null ? outbound.getDepartureDate() : null;
		}
		
		private Date getReturnDate() {
			return inbound != null ? inbound.getArrivalDate() : null;
		}
		
		private int getDepartureStops() {
			return outbound != null ? outbound.getStops() : 0;
		}
		
		private int getReturnStops() {
			return inbound != null ? inbound.getStops() : 0;
		}
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	private static class Bound {
		@JsonProperty
		private List<Flight> flights;
		
		private String getOrigin() {
			return flights.get(0).origin.iataCode;
		}
		
		private String getDestination() {
			return flights.get(flights.size()-1).destination.iataCode;
		}
		
		private Date getDepartureDate() {
			return flights.get(0).departsAt;
		}
		
		private Date getArrivalDate() {
			return flights.get(flights.size()-1).arrivesAt;
		}
		
		private int getStops() {
			return flights.size()-1;
		}
		
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	private static class Flight {
		@JsonProperty("departs_at")
		@DateTimeFormat(pattern="yyyy-MM-ddTHH:mm")
		private Date departsAt;
		@JsonProperty("arrives_at")
		@DateTimeFormat(pattern="yyyy-MM-ddTHH:mm")
		private Date arrivesAt;
		@JsonProperty
		private AirportWrapper origin;
		@JsonProperty
		private AirportWrapper destination;
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	private static class Fare {
		@JsonProperty("total_price")
		private String price;
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	private static class AirportWrapper {
		@JsonProperty("airport")
		private String iataCode;
	}
}
