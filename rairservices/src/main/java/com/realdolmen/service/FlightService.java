package com.realdolmen.service;

import java.util.List;

import javax.ejb.Remote;

import com.realdolmen.domain.Flight;
import com.realdolmen.domain.SearchQuery;
import com.realdolmen.exception.ConcurrentUpdateException;

@Remote
public interface FlightService {

	List<Flight> findAll();

	List<Flight> findAllFlightsByPartnerId(Long id);

	List<Flight> findAllFlightsBySearchCriteria(SearchQuery q);

	Flight findById(Long id);

	Flight update(Flight flight) throws ConcurrentUpdateException;

	Flight merge(Flight flight);

	Flight create(Flight flight);
}
