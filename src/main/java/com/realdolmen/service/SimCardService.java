package com.realdolmen.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.realdolmen.domain.SimCard;
import com.realdolmen.repository.SimCardRepository;

@Stateless
@LocalBean
public class SimCardService implements SimCardServiceRemote {
	@EJB
	SimCardRepository simCardRepository;

	@Override
	public SimCard save(SimCard simCard) {
		return simCardRepository.save(simCard);
	}

	@Override
	public SimCard findById(Long id) {
		return simCardRepository.findById(id);
	}

	@Override
	public List<SimCard> findAll() {
		return simCardRepository.findAll();
	}

	@Override
	public void remove(Long id) {
		simCardRepository.remove(id);
	}

}
