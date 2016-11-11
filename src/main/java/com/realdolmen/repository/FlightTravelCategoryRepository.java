package com.realdolmen.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.realdolmen.domain.FlightTravelCategory;

@Stateless
public class FlightTravelCategoryRepository extends AbstractRepository<FlightTravelCategory> {

	public List<FlightTravelCategory> findFlightTravelCategoriesByFlightId(Long flightId) {
		return entityManager
				.createQuery("FROM FlightTravelCategory ftg WHERE ftg.flight.id = :id", FlightTravelCategory.class)
				.setParameter("id", flightId).getResultList();
	}

	public Integer savedMaxSeatsByTravelCategoryId(Long id) {
		return entityManager
				.createQuery("SELECT ftg.maximumSeats FROM FlightTravelCategory ftg WHERE ftg.id = :id", Integer.class)
				.setParameter("id", id).getSingleResult();
	}
}
