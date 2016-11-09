package com.realdolmen.domain;

import org.junit.Test;

import com.realdolmen.utilities.JpaPersistenceTest;

public class FlightTest extends JpaPersistenceTest{
	
	@Test
	public void canCreateFlightTest() throws Exception {
		Flight f = new Flight();
		entityManager().persist(f);
		assertNotNull(f.getId());
	}

}
