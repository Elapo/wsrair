package com.realdolmen.service;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.realdolmen.domain.FlightTravelCategory;
import com.realdolmen.exception.ConcurrentUpdateException;
import com.realdolmen.repository.FlightTravelCategoryRepository;

@Stateless
@LocalBean
public class FlightTravelCategoryServiceImpl implements FlightTravelCategoryService{

	@EJB
	private FlightTravelCategoryRepository flightTravelCategoryServiceRepository;
	
	@Override
	public Integer savedMaxSeatsByTravelCategoryId(Long id) {
		return flightTravelCategoryServiceRepository.savedMaxSeatsByTravelCategoryId(id);
	}

	@Override
	public FlightTravelCategory update(FlightTravelCategory ftg) throws ConcurrentUpdateException {
		return flightTravelCategoryServiceRepository.update(ftg);
	}

	@Override
	public Integer availableSeatsLeftByFlightTravelCategory(Long id) {
		return flightTravelCategoryServiceRepository.availableSeatsLeftByFlightTravelCategory(id);
	}

	@Override
	public FlightTravelCategory find(Long id) {
		return flightTravelCategoryServiceRepository.findById(id);
	}

}
