package com.realdolmen.service;

import java.util.List;

import javax.ejb.Remote;

import com.realdolmen.domain.Booking;
import com.realdolmen.domain.BookingStatus;
import com.realdolmen.domain.TravelCategory;
import com.realdolmen.exception.ConcurrentUpdateException;

@Remote
public interface BookingService {

	Booking create(Booking booking);
	
	Booking update(Booking booking) throws ConcurrentUpdateException;

	Booking find(Long id);

	List<Booking> findAllBookingsByFlightId(Long flightId);

	List<Booking> findAllBookingsByUserId(Long userId);
	
	List<Booking> findBookingsByUserIdWithoutStatusType(Long userId, BookingStatus bStatus);

	Integer countBookingsByFlightId(Long flightId);

	Integer countBookingByFlightIdAndCategory(Long flightId, TravelCategory category);
}
