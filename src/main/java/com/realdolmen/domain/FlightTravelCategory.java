package com.realdolmen.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FlightTravelCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private TravelCategory travelCategory;
	
	private Double seatPrice;
	private Integer maximumSeats;
	private Integer openSeats;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TravelCategory getTravelCategory() {
		return travelCategory;
	}

	public void setTravelCategory(TravelCategory travelCategory) {
		this.travelCategory = travelCategory;
	}

	public Double getSeatPrice() {
		return seatPrice;
	}

	public void setSeatPrice(Double seatPrice) {
		this.seatPrice = seatPrice;
	}

	public Integer getMaximumSeats() {
		return maximumSeats;
	}

	public void setMaximumSeats(Integer maximumSeats) {
		this.maximumSeats = maximumSeats;
	}
	
	public Integer getOpenSeats() {
		return openSeats;
	}

	public void setOpenSeats(Integer openSeats) {
		this.openSeats = openSeats;
	}

	public FlightTravelCategory() {
	}


	
}
