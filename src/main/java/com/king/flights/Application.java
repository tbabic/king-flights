package com.king.flights;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.king.flights.models.Airport;
import com.king.flights.repositories.AirportRepository;

import okhttp3.OkHttpClient;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	
	@Bean
	public OkHttpClient httpClient() {
		return new OkHttpClient();
	}
	
//	@Bean
//	public AuthenticationManager authenticationManager() {
//		return new OAuth2AuthenticationManager();
//	}
	
	@Bean
	public CommandLineRunner demo(AirportRepository repository) {
		return (args) -> {
			repository.save(new Airport("ZAG", "Zagreb"));
			repository.save(new Airport("LHR", "London - Heathrow"));
			repository.save(new Airport("TXL", "Berlin - Tegel"));
		};
	}
	
	@Bean
	public static PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

}
