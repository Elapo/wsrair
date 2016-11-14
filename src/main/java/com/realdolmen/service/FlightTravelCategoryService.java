package com.realdolmen.service;

import javax.ejb.Remote;

import com.realdolmen.domain.FlightTravelCategory;
import com.realdolmen.exception.ConcurrentUpdateException;

@Remote
public interface FlightTravelCategoryService {
	Integer savedMaxSeatsByTravelCategoryId(Long id);
	Integer availableSeatsLeftByFlightTravelCategory(Long id);
	FlightTravelCategory update(FlightTravelCategory ftg) throws ConcurrentUpdateException;

}
