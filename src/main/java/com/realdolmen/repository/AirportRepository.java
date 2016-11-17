package com.realdolmen.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.realdolmen.domain.Airport;

@Stateless
public class AirportRepository extends AbstractRepository<Airport> {

	public Airport getFirstAirport() {
		return entityManager.createQuery("SELECT a FROM Airport a", Airport.class).setMaxResults(1).getSingleResult();
	}
}
