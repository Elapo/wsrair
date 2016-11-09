package com.realdolmen.service;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.realdolmen.domain.Partner;
import com.realdolmen.repository.PartnerRepository;

@Stateless
@LocalBean
public class PartnerServiceImpl implements PartnerService {

	@EJB
	PartnerRepository partnerRepository;
	
	@Override
	public Partner findById(Long id) {
		return partnerRepository.findById(id);
	}

}
