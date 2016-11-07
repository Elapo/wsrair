package com.realdolmen.domain;

import org.junit.Test;

import com.realdolmen.utilities.JpaPersistenceTest;

public class AirportTest extends JpaPersistenceTest {

	@Test
	public void canCreateAirportTest() throws Exception {
		Region region = new Region("Belgie");
		Airport a = new Airport("Zaventem", region);
		entityManager().persist(a);
		assertNotNull(a.getId());

	}

}
