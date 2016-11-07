package com.realdolmen.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.realdolmen.domain.Flight;
import com.realdolmen.service.FlightService;

@ViewScoped
@ManagedBean
public class FlightController implements Serializable {

	@EJB
	private FlightService flightService;

	private List<Flight> flights;
	private Long flightId;
	private Flight editFlight;

	@PostConstruct
	public void init() {
		flights = flightService.findAllFlightsByPartnerId(1000);
		System.out.println("init flightcontroller");
	}

	public List<Flight> getFlights() {
		System.out.println("getflight");
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

	public String updateFlight() {
		System.out.println("updating flight" + editFlight.getArrivalLocation().getName());
		editFlight = flightService.update(editFlight);
		System.out.println("part 2" + editFlight.getArrivalLocation().getName());
		return "findFlight.xhtml?faces-redirect=true";
	}

}
