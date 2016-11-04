package com.realdolmen.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.realdolmen.domain.SimCard;

@Stateless
public class SimCardRepository {
	@PersistenceContext
	EntityManager em;

	public SimCard save(SimCard simCard) {
		em.persist(simCard);
		return simCard;
	}

	public SimCard findById(Long id) {
		return em.find(SimCard.class, id);
	}

	public List<SimCard> findAll() {
		return em.createQuery("select s from SimCard s", SimCard.class).getResultList();
	}

	public void remove(Long id) {
		em.remove(em.getReference(SimCard.class, id));
	}
}
