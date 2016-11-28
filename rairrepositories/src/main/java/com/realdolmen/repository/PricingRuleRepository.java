package com.realdolmen.repository;

import java.util.List;

import javax.ejb.Stateless;

import com.realdolmen.domain.PricingRule;

@Stateless
public class PricingRuleRepository extends AbstractRepository<PricingRule> {

	public List<PricingRule> findPricingRulesByFlightId(long id){
		return entityManager.createQuery("from PricingRule pr where pr.flight.id = :id", PricingRule.class).setParameter("id", id)
				.getResultList();
	}
}
