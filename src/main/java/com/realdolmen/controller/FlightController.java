package com.realdolmen.controller;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;

import com.realdolmen.domain.Flight;
import com.realdolmen.service.FlightService;

@ManagedBean
@ViewScoped
public class FlightController {

	@EJB
	private FlightService flightService;
	
	private List<Flight> flights;
	
	
	@PostConstruct
	public void init(){
		flights = flightService.findAllFlightsByPartnerId(1000);
	}


	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}
	
	
}
