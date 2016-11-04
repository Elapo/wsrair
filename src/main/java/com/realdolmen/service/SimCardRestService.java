package com.realdolmen.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.realdolmen.domain.SimCard;
import com.realdolmen.repository.SimCardRepository;

@Path("simcard")
@Stateless
public class SimCardRestService {
	@EJB
	SimCardRepository simCardRepository;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{simId}")
	public SimCard findSimCardById(@PathParam("simId") Long simId) {
		return simCardRepository.findById(simId);
	}

	@GET
	@Path("all")
	public List<SimCard> findAllSimCards() {
		return simCardRepository.findAll();
	}

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public SimCard createSimCard(SimCard simCard) {
		return simCardRepository.save(simCard);
	}

	@DELETE
	@Path("{simCardId}")
	public void deleteBook(@PathParam("simCardId") Long simCardId) {
		simCardRepository.remove(simCardId);
	}

}
