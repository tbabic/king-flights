package com.king.flights.services.amadeus;

import java.io.IOException;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.king.flights.models.FlightInfo;
import com.king.flights.models.FlightQuery;
import com.king.flights.services.FlightsSeachService;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class AmadeusService implements FlightsSeachService {
	
	private static final Logger logger = LoggerFactory.getLogger(AmadeusService.class);
	
	private String apiKey;
	
	private String apiUrl;
	
	private OkHttpClient httpClient;
	
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	
	
	public AmadeusService(@Value("${amadues.api.key}") String apiKey, 
			@Value("${amadeus.api.url}") String apiUrl, 
			@Autowired OkHttpClient httpClient) {
		this.apiKey = apiKey;
		this.apiUrl = apiUrl;
		this.httpClient = httpClient;
	}


	public List<FlightInfo> findFlights(FlightQuery flightQuery) throws IOException {
		DateFormat df = new SimpleDateFormat(DATE_FORMAT);
		
		HttpUrl url = createUrl(flightQuery, df);
		Request request = new Request.Builder()
				.url(url)
				.build();
		Response response = httpClient.newCall(request).execute();
		
		ObjectMapper mapper = new ObjectMapper();
		StringWriter writer = new StringWriter();
		IOUtils.copy(response.body().byteStream(), writer, "UTF-8");
		String responseString = writer.toString();
		try {
			FlightResponse flightResponse = mapper.readValue(responseString, FlightResponse.class);
			return flightResponse.convertToFlightInfos();
		} catch (JsonParseException | JsonMappingException e) {
			logger.error("request: " + url);
			logger.error("response: " + responseString);
			throw e;
		}
	}


	private HttpUrl createUrl(FlightQuery flightQuery, DateFormat df) {
		HttpUrl.Builder urlBuilder = HttpUrl.parse(apiUrl).newBuilder()
				.addQueryParameter("apikey", apiKey);
		if (StringUtils.isNotBlank(flightQuery.getOrigin())) {
			urlBuilder.addQueryParameter("origin", flightQuery.getOrigin());
		}
		if (StringUtils.isNotBlank(flightQuery.getDestination())) {
			urlBuilder.addQueryParameter("destination", flightQuery.getDestination());
		}
		if (flightQuery.getAdults() > 0) {
			urlBuilder.addQueryParameter("adults", String.valueOf(flightQuery.getAdults()));
		}
		if (flightQuery.getChildren() > 0) {
			urlBuilder.addQueryParameter("children", String.valueOf(flightQuery.getChildren()));
		}
		if (flightQuery.getInfants() > 0) {
			urlBuilder.addQueryParameter("infants", String.valueOf(flightQuery.getInfants()));
		}
		if (flightQuery.getDepartureDate() != null) {
			urlBuilder.addQueryParameter("departure_date", df.format(flightQuery.getDepartureDate()));
		}
		if (flightQuery.getReturnDate() != null) {
			urlBuilder.addQueryParameter("return_date", df.format(flightQuery.getReturnDate()));
		}
		if (flightQuery.getCurrency() != null) {
			urlBuilder.addQueryParameter("currency", flightQuery.getCurrency().toString());
		}
		HttpUrl url = urlBuilder.build();
		return url;
	}

	
}
