package com.realdolmen.service;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

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

}
