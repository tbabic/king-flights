package com.king.flights.services.amadeus;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.king.flights.models.Currency;
import com.king.flights.models.FlightInfo;
import com.king.flights.models.FlightQuery;
import com.king.flights.services.FlightsSeachService;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AmadeusService implements FlightsSeachService {
	
	@Value("${amadeus.api.key}")
	private String apiKey;
	
	@Value("${amadeus.api.url}")
	private String apiUrl;
	
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	
	public List<FlightInfo> findFlights(String fromAirport, String toAirport, Date departureDate, Date returnDate, int adults, int children, int infants, Currency currency) throws IOException {
		DateFormat df = new SimpleDateFormat(DATE_FORMAT);
		
		OkHttpClient client = new OkHttpClient();
		HttpUrl url = new HttpUrl.Builder()
				.addQueryParameter("apikey", apiKey)
				.addQueryParameter("origin", fromAirport)
				.addQueryParameter("destination", toAirport)
				.addQueryParameter("departure_date", df.format(departureDate))
				.addQueryParameter("return_date", df.format(returnDate))
				.build();
		Request request = new Request.Builder()
				.url(url)
				.build();
		Response response = client.newCall(request).execute();
		ObjectMapper mapper = new ObjectMapper();
		FlightResponse flightResponse = mapper.readValue(response.body().toString(), FlightResponse.class);
		
		return flightResponse.convertToFlightInfos();
	}
	
	public List<FlightInfo> findFlights(FlightQuery flightQuery) throws IOException {
		DateFormat df = new SimpleDateFormat(DATE_FORMAT);
		
		OkHttpClient client = new OkHttpClient();
		HttpUrl url = new HttpUrl.Builder()
				.addQueryParameter("apikey", apiKey)
				.addQueryParameter("origin", flightQuery.getOrigin())
				.addQueryParameter("destination", flightQuery.getDestination())
				.addQueryParameter("departure_date", df.format(flightQuery.getDepartureDate()))
				.addQueryParameter("return_date", df.format(flightQuery.getReturnDate()))
				.build();
		Request request = new Request.Builder()
				.url(url)
				.build();
		Response response = client.newCall(request).execute();
		
		ObjectMapper mapper = new ObjectMapper();
		FlightResponse flightResponse = mapper.readValue(response.body().toString(), FlightResponse.class);
		
		return flightResponse.convertToFlightInfos();
	}
	
}