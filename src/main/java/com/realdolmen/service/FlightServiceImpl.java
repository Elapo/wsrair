package com.realdolmen.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.realdolmen.domain.Flight;
import com.realdolmen.domain.FlightTravelCategory;
import com.realdolmen.domain.PricingRule;
import com.realdolmen.exception.ConcurrentUpdateException;
import com.realdolmen.repository.FlightRepository;
import com.realdolmen.repository.FlightTravelCategoryRepository;
import com.realdolmen.repository.PricingRuleRepository;

@Stateless
@LocalBean
public class FlightServiceImpl implements FlightService {

	@EJB
	private FlightRepository flightRepository;
	
	@EJB
	private PricingRuleRepository pricingRuleRepository;
	
	@EJB
	private FlightTravelCategoryRepository flightTravelCategoryRepository;

	@Override
	public List<Flight> findAllFlightsByPartnerId(Long id) {
		return flightRepository.findAllFlightsByPartnerId(id);
	}

	@Override
	public Flight findById(Long id) {
		Flight matchingFlight = flightRepository.findById(id);
		List<PricingRule> priceRules = pricingRuleRepository.findPricingRulesByFlightId(id);
		matchingFlight.setPriceRules(priceRules);
		List<FlightTravelCategory> flightTravelCategories = flightTravelCategoryRepository.findFlightTravelCategoriesByFlightId(id);
		matchingFlight.setFlightTravelCategory(flightTravelCategories);
		return matchingFlight;
	}

	@Override
	public Flight update(Flight flight) throws ConcurrentUpdateException {
		return flightRepository.update(flight);
	}

	@Override
	public Flight create(Flight flight) {
		return flightRepository.create(flight);
	}

	@Override
	public Flight merge(Flight flight) {
		return flightRepository.attach(flight);
	}

	@Override
	public List<Flight> findAll() {
		return flightRepository.findAll();
	}

}
