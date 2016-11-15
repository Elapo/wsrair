package com.realdolmen.repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.realdolmen.domain.Flight;
import com.realdolmen.domain.SearchQuery;
import com.realdolmen.domain.TravelCategory;
import com.realdolmen.utilities.JpaPersistenceTest;

public class FlightRepositoryTest extends JpaPersistenceTest {
	private static final long PARTNER_ID = 1000;
	private FlightRepository fr;

	@Before
	public void init() {
		fr = new FlightRepository();
		fr.entityManager = entityManager();
	}

	@Test
	public void idShouldNotBeNull() {
		Flight f = new Flight();
		fr.create(f);
		assertNotNull(f);
		assertNotNull(f.getId());
	}

	@Test
	public void shouldReturnAllFlightsFromAPartner() {
		List<Flight> flights = fr.findAllFlightsByPartnerId(PARTNER_ID);
		assertEquals(2, flights.size());
		assertEquals(1000, flights.get(0).getId().longValue());
		assertEquals(1001, flights.get(1).getId().longValue());
	}

	@Test
	public void searchQueryReturningAllFlightByMatchingDepartureAndArrivalLocation() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2016);
		calendar.set(Calendar.MONTH, 10);
		calendar.set(Calendar.DAY_OF_MONTH, 15);
		Date date = calendar.getTime();
		SearchQuery q = new SearchQuery(1000L, 1001L, null, 1, date, TravelCategory.REGULAR);
		List<Flight> flights = fr.findAllFlightsBySearchCriteria(q);
		assertEquals(1, flights.size());
		assertEquals(1000, flights.get(0).getId().longValue());
	}

	private Date getDate(int day, int month, int year, int hour, int minute) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

}
