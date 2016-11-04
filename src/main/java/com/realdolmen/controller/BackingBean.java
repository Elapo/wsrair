package com.realdolmen.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.realdolmen.domain.SimCard;
import com.realdolmen.service.SimCardService;

@Named
@ApplicationScoped
public class BackingBean implements Serializable {

	@Inject
	private SimCardService service;

	private SimCard simcard;

	@PostConstruct
	private void init() {
		System.out.println("init new simcard");
		simcard = new SimCard("12345678");
	}

	public List<SimCard> allSimCard() {
		return service.findAll();
	}

	public void addSimCard() {
		System.out.println("add new simcard" + simcard.getNumber());
		service.save(new SimCard(simcard));
	}

	public SimCard getSimcard() {
		return simcard;
	}

	public void setSimcard(SimCard simcard) {
		this.simcard = simcard;
	}

}
