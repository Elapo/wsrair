package com.realdolmen.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "depAirportId")
	private Airport departureLocation;

	private LocalDateTime departureDateTime;

	@ManyToOne
	@JoinColumn(name = "arrAirportId")
	private Airport arrivalLocation;

	private LocalDateTime arrivalDateTime;

	@OneToMany
	private List<PricingRule> priceRules;

	@ManyToOne
	@JoinColumn(name = "partnerId")
	private Partner partner;

	@OneToMany
	private List<FlightTravelCategory> flightTravelCategory;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Airport getDepartureLocation() {
		return departureLocation;
	}

	public void setDepartureLocation(Airport departureLocation) {
		this.departureLocation = departureLocation;
	}

	public LocalDateTime getDepartureDateTime() {
		return departureDateTime;
	}

	public void setDepartureDateTime(LocalDateTime departureDateTime) {
		this.departureDateTime = departureDateTime;
	}

	public Airport getArrivalLocation() {
		return arrivalLocation;
	}

	public void setArrivalLocation(Airport arrivalLocation) {
		this.arrivalLocation = arrivalLocation;
	}

	public LocalDateTime getArrivalDateTime() {
		return arrivalDateTime;
	}

	public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
		this.arrivalDateTime = arrivalDateTime;
	}

	public List<PricingRule> getPriceRules() {
		return priceRules;
	}

	public void setPriceRules(List<PricingRule> priceRules) {
		this.priceRules = priceRules;
	}

	public Partner getPartner() {
		return partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

	public List<FlightTravelCategory> getFlightTravelCategory() {
		return flightTravelCategory;
	}

	public void setFlightTravelCategory(List<FlightTravelCategory> flightTravelCategory) {
		this.flightTravelCategory = flightTravelCategory;
	}

	public Flight() {
	}

}
