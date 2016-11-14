package com.realdolmen.repository;

import java.util.List;

import javax.ejb.Stateless;

import com.realdolmen.domain.Booking;

@Stateless
public class BookingRepository extends AbstractRepository<Booking> {

	public List<Booking> findAllBookingsByFlightId(Long flightId) {
		return entityManager.createQuery("from Booking b where b.flight.id = :id", Booking.class)
				.setParameter("id", flightId).getResultList();
	}

	public List<Booking> findAllBookingsByUserId(Long userId) {
		return entityManager.createQuery("from Booking b where b.user.id = :id", Booking.class)
				.setParameter("id", userId).getResultList();
	}
}