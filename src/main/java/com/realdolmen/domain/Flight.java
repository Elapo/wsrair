package com.realdolmen.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Flight implements Serializable {

	private static final int MAX_AMOUNT_PRICINGRULES = 3;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "depAirportId")
	private Airport departureLocation;

	@Temporal(TemporalType.TIMESTAMP)
	private Date departureDateTime;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "arrAirportId")
	private Airport arrivalLocation;

	@Temporal(TemporalType.TIMESTAMP)
	private Date arrivalDateTime;

	@OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
	private List<PricingRule> priceRules;

	@ManyToOne
	@JoinColumn(name = "partnerId")
	private Partner partner;

	@OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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

	public Date getDepartureDateTime() {
		return departureDateTime;
	}

	public void setDepartureDateTime(Date departureDateTime) {
		this.departureDateTime = departureDateTime;
	}

	public Airport getArrivalLocation() {
		return arrivalLocation;
	}

	public void setArrivalLocation(Airport arrivalLocation) {
		this.arrivalLocation = arrivalLocation;
	}

	public Date getArrivalDateTime() {
		return arrivalDateTime;
	}

	public void setArrivalDateTime(Date arrivalDateTime) {
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
		List<FlightTravelCategory> flightTravelCategories = new ArrayList<FlightTravelCategory>();

		for (TravelCategory travelCategory : TravelCategory.values()) {
			FlightTravelCategory ftg = new FlightTravelCategory();
			ftg.setMaximumSeats(0);
			ftg.setOpenSeats(0);
			ftg.setSeatPrice(0.0);
			ftg.setTravelCategory(travelCategory);
			ftg.setFlight(this);
			flightTravelCategories.add(ftg);
		}

		this.flightTravelCategory = flightTravelCategories;

		List<PricingRule> pricingRules = new ArrayList<PricingRule>();

		for (int i = 0; i < MAX_AMOUNT_PRICINGRULES; i++) {
			PricingRule pricingRule = new PricingRule();
			pricingRule.setDiscountValue(0.0);
			pricingRule.setVolume(0);
			pricingRule.setFlight(this);

			pricingRules.add(pricingRule);
		}

		this.priceRules = pricingRules;
	}
}
