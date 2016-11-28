package com.realdolmen.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.realdolmen.domain.Airport;
import com.realdolmen.repository.AirportRepository;

@Stateless
@LocalBean
public class AirportServiceImpl implements AirportService {
	
	@EJB
	AirportRepository airportRepository;
	
	public List<Airport> findAllAirports() {
		return airportRepository.findAll();
	}

	@Override
	public Airport findById(Long id) {
		return airportRepository.findById(id);
	}

	@Override
	public Airport getFirstAirport() {
		return airportRepository.getFirstAirport();
	}	
}
