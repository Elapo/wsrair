package com.realdolmen.service;

import javax.ejb.Remote;

@Remote
public interface FlightTravelCategoryService {
	Integer savedMaxSeatsByTravelCategoryId(Long id);

}
