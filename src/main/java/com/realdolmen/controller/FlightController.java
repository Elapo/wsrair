package com.realdolmen.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.realdolmen.domain.Airport;
import com.realdolmen.domain.Flight;
import com.realdolmen.exception.ConcurrentUpdateException;
import com.realdolmen.service.AirportService;
import com.realdolmen.service.FlightService;

@ViewScoped
@ManagedBean
public class FlightController implements Serializable {

	@EJB
	private FlightService flightService;
	@EJB
	private AirportService airportService;

	private List<Flight> flights;
	private List<Airport> airports;
	private Long flightId;
	private Flight editFlight;
	private Airport airport;

	@PostConstruct
	public void init() {
		flights = flightService.findAllFlightsByPartnerId(1000);
	}

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	public void loadData() {
		if (flightId != null) {
			this.editFlight = flightService.findFlightById(flightId);
		} else {
			this.editFlight = new Flight();
		}
		airports = airportService.findAllAirports();
		System.out.println(airports.isEmpty());
	}

	public Long getFlightId() {
		return flightId;
	}

	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}

	public Flight getEditFlight() {
		return editFlight;
	}

	public void setEditFlight(Flight editFlight) {
		this.editFlight = editFlight;
	}

	public String updateFlight() throws ConcurrentUpdateException {
		editFlight = flightService.update(editFlight);
		return "findFlight.xhtml?faces-redirect=true";
	}
	
	public Airport getAirportById(Long id){
		return airportService.findById(id);
	}

	public List<Airport> getAirports() {
		return airports;
	}

	public void setAirports(List<Airport> airports) {
		this.airports = airports;
	}

	public Airport getAirport() {
		return airport;
	}

	public void setAirport(Airport airport) {
		this.airport = airport;
	}
}
