package com.realdolmen.comperator;

import java.util.Comparator;

import com.realdolmen.domain.PricingRule;

public class PriceRuleDiscountComperator implements Comparator<PricingRule> {

	@Override
	public int compare(PricingRule o1, PricingRule o2) {
		return o1.getDiscountValue().compareTo(o2.getDiscountValue());
	}
}
