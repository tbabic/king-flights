package com.king.flights.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.king.flights.models.Airport;

public interface AirportRepository extends CrudRepository<Airport, String> {

}
