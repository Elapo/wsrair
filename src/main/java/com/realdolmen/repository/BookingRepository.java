package com.realdolmen.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.realdolmen.domain.Booking;

@Stateless
public class BookingRepository extends AbstractRepository<Booking>{

	public List<Booking> findAllBookingsByFlightId(Long flightId) {
		return entityManager.createQuery("from Booking b where b.flight.id = :id", Booking.class).setParameter("id", flightId)
				.getResultList();
	}
}