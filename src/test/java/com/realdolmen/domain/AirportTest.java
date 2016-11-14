package com.realdolmen.domain;

import org.junit.Test;

import com.realdolmen.utilities.JpaPersistenceTest;

public class AirportTest extends JpaPersistenceTest {

	@Test
	public void canCreateAirportTest() throws Exception {
		Airport a = new Airport("Zaventem", Region.EUROPE);
		entityManager().persist(a);
		assertNotNull(a.getId());
	}

}
