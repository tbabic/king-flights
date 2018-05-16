package com.king.flights.services.amadeus;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.king.flights.models.FlightInfo;
import com.king.flights.models.FlightQuery;


import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class AmadeusServiceTest {
	
	@InjectMocks
	private AmadeusService amadeusService;
	
	private ResponseBody responseBody;
	
	@Before
	public void init() throws IOException {
	    
	    responseBody = Mockito.mock(ResponseBody.class, Mockito.RETURNS_DEEP_STUBS);
	    Response response = new Response.Builder()
				.body(responseBody)
				.request(new Request.Builder().url("http:\\testUrl.com").build())
				.protocol(Protocol.HTTP_2)
				.code(0)
				.message("test message")
				.build();
	    Call call = Mockito.mock(Call.class, Mockito.RETURNS_DEEP_STUBS);
		Mockito.when(call.execute()).thenReturn(response);
	    OkHttpClient httpClient = Mockito.mock(OkHttpClient.class, Mockito.RETURNS_DEEP_STUBS);
		Mockito.when(httpClient.newCall(Matchers.any())).thenReturn(call);
		
		
	    amadeusService = new AmadeusService("someKey", "http:\\testUrl.com", httpClient);
	}
	
	@Test
	public void testBasicResponse() throws IOException {
		InputStream responseStream = AmadeusServiceTest.class.getClassLoader().getResourceAsStream("BasicResponse.json");
		Mockito.when(responseBody.byteStream()).thenReturn(responseStream);
		
		
		
		List<FlightInfo> flightInfoList = amadeusService.findFlights(new FlightQuery());
		Assert.assertEquals(1, flightInfoList.size());
	}
}
