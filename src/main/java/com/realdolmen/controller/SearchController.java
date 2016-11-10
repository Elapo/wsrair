package com.realdolmen.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.realdolmen.domain.Airport;
import com.realdolmen.domain.Flight;
import com.realdolmen.domain.FlightTravelCategory;
import com.realdolmen.domain.Partner;
import com.realdolmen.domain.SearchQuery;
import com.realdolmen.domain.TravelCategory;
import com.realdolmen.service.AirportService;
import com.realdolmen.service.FlightService;
import com.realdolmen.service.PartnerService;

@ViewScoped
@ManagedBean
public class SearchController implements Serializable {
	@EJB
	private AirportService airportService;
	@EJB
	private PartnerService partnerService;

	@EJB
	private FlightService flightService;

	List<Airport> airports;
	List<TravelCategory> categories;
	List<Partner> partners;
	List<Flight> foundFlights;

	SearchQuery searchQuery;
	Date today;

	@PostConstruct
	private void init() {
		resetSearchValues();
		airports = airportService.findAllAirports();
		categories = Arrays.asList(TravelCategory.values());
		partners = partnerService.findAll();
	}

	public List<Airport> getAirports() {
		return airports;
	}

	public void setAirports(List<Airport> airports) {
		this.airports = airports;
	}

	public List<TravelCategory> getCategories() {
		return categories;
	}

	public void setCategories(List<TravelCategory> categories) {
		this.categories = categories;
	}

	public List<Partner> getPartners() {
		return partners;
	}

	public void setPartners(List<Partner> partners) {
		this.partners = partners;
	}

	public Date getToday() {
		return today;
	}

	public void setToday(Date today) {
		this.today = today;
	}

	public SearchQuery getSearchQuery() {
		return searchQuery;
	}

	public void setSearchQuery(SearchQuery searchQuery) {
		this.searchQuery = searchQuery;
	}

	public List<Flight> getFoundFlights() {
		return foundFlights;
	}

	public void setFoundFlights(List<Flight> foundFlights) {
		this.foundFlights = foundFlights;
	}

	public Boolean checkResults() {
		return this.foundFlights != null && this.foundFlights.size() > 0;
	}

	private void resetSearchValues() {
		this.searchQuery = new SearchQuery(null, null, null, 1, new Date(), TravelCategory.REGULAR);
		this.today = new Date();
		this.foundFlights = null;
	}

	public void query() {
		List<Flight> flights = flightService.findAllFlightsBySearchCriteria(searchQuery);
		for (Flight flight : flights) {
			System.out.println(flight.getId());
		}
		this.foundFlights = flights;
	}

	public Double basePrice(Flight f) {
		List<FlightTravelCategory> ftcList = f.getFlightTravelCategory();
		for (FlightTravelCategory ftc : ftcList) {
			if (ftc.getTravelCategory().equals(searchQuery.gettCategory())) {
				return ftc.getSeatPrice();
			}
		}
		return null;
	}

}
