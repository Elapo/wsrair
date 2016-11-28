package com.realdolmen.repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.realdolmen.domain.Flight;
import com.realdolmen.domain.SearchQuery;

@Stateless
public class FlightRepository extends AbstractRepository<Flight> {

	public List<Flight> findAllFlightsByPartnerId(Long id) {
		return entityManager.createQuery("from Flight f where f.partner.id = :id", Flight.class).setParameter("id", id)
				.getResultList();
	}

	public List<Flight> findAllFlightsBySearchCriteria(SearchQuery s) {
		/* @formatter:off */
		Calendar c = Calendar.getInstance(); 
		c.setTime(s.getDepDate()); 
		c.add(Calendar.DATE, -1);
		Date depDateMinOneDay = c.getTime();
		
		c = Calendar.getInstance(); 
		c.setTime(s.getDepDate()); 
		c.add(Calendar.DATE, 1);
		Date depDatePlusOneDay = c.getTime();
		
		Date today = new Date();
		
		String query = "SELECT f FROM Flight f INNER JOIN f.flightTravelCategory ftc"
				+ " WHERE f.departureDateTime BETWEEN :depDateMinusOne AND :depDatePlusOne"
				+ " AND ftc.travelCategory = :tC"
				+ " AND ftc.openSeats >= :tickets"
				+ " AND f.departureDateTime >= :today";
		if (s.getPartnerId() != null) {
			query += " AND f.partner.id = :partnerId";
		}
		if (s.getDepId() != null) {
			query += " AND f.departureLocation.id = :depId";
		}
		if (s.getArrId() != null) {
			query += " AND f.arrivalLocation.id = :arrId";
		}
		
		TypedQuery<Flight> tq = entityManager.createQuery(query,
				Flight.class)
				.setParameter("tC", s.gettCategory())
				.setParameter("tickets", s.getTickets())
				.setParameter("depDateMinusOne", depDateMinOneDay)
				.setParameter("depDatePlusOne", depDatePlusOneDay)
				.setParameter("today", today);

		
		
		if (s.getPartnerId() != null) {
			tq.setParameter("partnerId", s.getPartnerId());
		}
		if (s.getDepId() != null) {
			tq.setParameter("depId", s.getDepId());
		}
		if (s.getArrId() != null) {
			tq.setParameter("arrId", s.getArrId());
		}
		
		return tq.getResultList();
		
		/* @formatter:on */
	}
	// TODO refactor this monster
}
