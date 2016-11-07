package com.realdolmen.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.realdolmen.domain.Flight;
import com.realdolmen.service.FlightService;

@ManagedBean
@ViewScoped
public class FlightController implements Serializable {

	@EJB
	private FlightService flightService;

	private List<Flight> flights;

	@PostConstruct
	public void init() {
		flights = flightService.findAllFlightsByPartnerId(1000);
		System.out.println("init");
	}

	public List<Flight> getFlights() {
		System.out.println("getflight");
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

}
