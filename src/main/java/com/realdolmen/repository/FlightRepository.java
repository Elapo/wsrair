package com.realdolmen.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.realdolmen.domain.Flight;
import com.realdolmen.exception.ConcurrentUpdateException;

@Stateless
public class FlightRepository extends AbstractRepository<Flight> {
	
	public List<Flight> findAllFlightsByPartnerId(Long id) {
		return entityManager.createQuery("from Flight f where f.partner.id = :id", Flight.class).setParameter("id", id)
				.getResultList();
	}


}
