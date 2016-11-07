package com.realdolmen.service;

import java.util.List;

import javax.ejb.Remote;

import com.realdolmen.domain.Flight;

@Remote
public interface FlightService {

		List<Flight> findAllFlightsByPartnerId(long id);
	
}
