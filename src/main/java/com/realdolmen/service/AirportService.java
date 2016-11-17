package com.realdolmen.service;

import java.util.List;

import javax.ejb.Remote;

import com.realdolmen.domain.Airport;

@Remote
public interface AirportService {
	
	List<Airport> findAllAirports();

	Airport findById(Long id);
	
	Airport getFirstAirport();
}
