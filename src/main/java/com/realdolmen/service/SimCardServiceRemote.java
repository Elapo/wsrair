package com.realdolmen.service;

import java.util.List;

import javax.ejb.Remote;

import com.realdolmen.domain.SimCard;

@Remote
public interface SimCardServiceRemote {
	SimCard save(SimCard simCard);

	SimCard findById(Long id);

	List<SimCard> findAll();

	void remove(Long id);
}
