package com.realdolmen.repository;

import java.util.List;

import javax.ejb.Stateless;

import com.realdolmen.domain.Booking;
import com.realdolmen.domain.BookingStatus;
import com.realdolmen.domain.PaymentType;

@Stateless
public class BookingRepository extends AbstractRepository<Booking> {

	public List<Booking> findAllBookingsByFlightId(Long flightId) {
		return entityManager.createQuery("from Booking b where b.flight.id = :id", Booking.class)
				.setParameter("id", flightId).getResultList();
	}

	public List<Booking> findAllBookingsByUserId(Long userId) {
		return entityManager.createQuery("FROM Booking b WHERE b.user.id = :id", Booking.class)
				.setParameter("id", userId).getResultList();
	}
	
	public List<Booking> findBookingsByUserIdWithoutStatusType(Long userId, BookingStatus bStatus) {
		return entityManager.createQuery("FROM Booking b WHERE b.user.id = :id AND b.bookingStatus != :category", Booking.class)
				.setParameter("id", userId).setParameter("category", bStatus).getResultList();
	}
}