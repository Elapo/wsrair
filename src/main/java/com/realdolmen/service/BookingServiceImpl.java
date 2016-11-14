package com.realdolmen.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.realdolmen.domain.Booking;
import com.realdolmen.domain.TravelCategory;
import com.realdolmen.repository.BookingRepository;

@Stateless
@LocalBean
public class BookingServiceImpl implements BookingService {

	@EJB
	BookingRepository bookingRepository;

	@Override
	public List<Booking> findAllBookingsByFlightId(Long flightId) {
		return bookingRepository.findAllBookingsByFlightId(flightId);
	}

	@Override
	public Integer countBookingsByFlightId(Long flightId) {
		return findAllBookingsByFlightId(flightId).size();
	}

	@Override
	public Integer countBookingByFlightIdAndCategory(Long flightId, TravelCategory category) {
		List<Booking> bookings = findAllBookingsByFlightId(flightId);
		int matchCount = 0;

		for (Booking booking : bookings) {
			if (booking.getTravelCategory().name().equals(category.name())) {
				matchCount++;
			}
		}
		return matchCount;
	}

	@Override
	public List<Booking> findAllBookingsByUserId(Long userId) {
		return bookingRepository.findAllBookingsByUserId(userId);
	}

	@Override	
	public Booking create(Booking booking) {
		return bookingRepository.create(booking);
	}
}
