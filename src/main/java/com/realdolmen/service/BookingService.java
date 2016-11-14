package com.realdolmen.service;

import java.util.List;

import javax.ejb.Remote;

import com.realdolmen.domain.Booking;
import com.realdolmen.domain.TravelCategory;

@Remote
public interface BookingService {

	Booking create(Booking booking);

	Booking find(Long id);

	List<Booking> findAllBookingsByFlightId(Long flightId);

	List<Booking> findAllBookingsByUserId(Long userId);

	Integer countBookingsByFlightId(Long flightId);

	Integer countBookingByFlightIdAndCategory(Long flightId, TravelCategory category);
}
