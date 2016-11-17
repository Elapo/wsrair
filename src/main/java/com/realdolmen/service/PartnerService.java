package com.realdolmen.service;

import java.util.List;

import javax.ejb.Remote;

import com.realdolmen.domain.Partner;

@Remote
public interface PartnerService {

	Partner findById(Long id);

	List<Partner> findAll();
}
