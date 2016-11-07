package com.realdolmen.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.realdolmen.domain.Flight;
import com.realdolmen.repository.FlightRepository;

@Stateless
@LocalBean
public class FlightServiceImpl implements FlightService {

	@EJB
	private FlightRepository flightRepository;
	
	@Override
	public List<Flight> findAllFlightsByPartnerId(long id) {
		return flightRepository.findAllFlightsByPartnerId(id);
	}

}
