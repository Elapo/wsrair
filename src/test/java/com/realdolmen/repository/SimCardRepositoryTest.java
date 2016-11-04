package com.realdolmen.repository;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.realdolmen.domain.SimCard;
import com.realdolmen.utilities.JpaPersistenceTest;

public class SimCardRepositoryTest extends JpaPersistenceTest {
	private SimCardRepository simCardRepository;

	private static final long TEST_SIMCARD_ID = 1000;

	@Before
	public void initializeRepository() {
		simCardRepository = new SimCardRepository();
		simCardRepository.em = entityManager();
	}

	@Test
	public void shouldSaveAPerson() {
		SimCard s = new SimCard("12345678");
		simCardRepository.save(s);
		assertNotNull("Person ID is not supposed to be null after saving", s.getId());
	}

	@Test
	public void shouldReturnSimCard() {
		SimCard s = simCardRepository.findById(TEST_SIMCARD_ID);
		assertNotNull(s);
		assertEquals("01020304", s.getNumber());
	}

	@Test
	public void shouldRemoveSimCard() {
		int sizeBefore = simCardRepository.findAll().size();
		simCardRepository.remove(TEST_SIMCARD_ID);
		int sizeAfter = simCardRepository.findAll().size();
		assertEquals(sizeBefore - 1, sizeAfter);

	}

	@Test
	public void shouldReturnAllSimCards() {
		List<SimCard> cards = simCardRepository.findAll();
		assertNotNull(cards);
		assertNotEquals(0, cards.size());
	}

}
