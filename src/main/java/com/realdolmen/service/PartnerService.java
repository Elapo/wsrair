package com.realdolmen.service;

import javax.ejb.Remote;

import com.realdolmen.domain.Partner;

@Remote
public interface PartnerService {

	Partner findById(Long id);
}
