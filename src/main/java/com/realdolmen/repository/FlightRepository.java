package com.realdolmen.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.realdolmen.domain.Flight;

@Stateless
public class FlightRepository {

	@PersistenceContext
	EntityManager entityManager;

	public List<Flight> findAllFlightsByPartnerId(Long id) {
		return entityManager.createQuery("from Flight f where f.partner.id = :id", Flight.class).setParameter("id", id)
				.getResultList();
	}

	public Flight findFlightById(Long id) {
		return entityManager.find(Flight.class, id);
	}

	public Flight update(Flight f) {
		return entityManager.merge(f);
	}
}
