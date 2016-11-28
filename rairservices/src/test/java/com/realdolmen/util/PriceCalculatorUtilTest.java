package com.realdolmen.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.realdolmen.domain.FlightTravelCategory;
import com.realdolmen.domain.PaymentType;
import com.realdolmen.domain.PricingRule;
import com.realdolmen.util.PriceCalculatorUtil;

public class PriceCalculatorUtilTest {

	@Test
	public void roundTwoDecimalsTest() {
		System.out.println(PriceCalculatorUtil.minimumCommissionPercentage());
		System.out.println(PriceCalculatorUtil.minimumCommissionPercentageWithMargin());
		
		System.out.println(PriceCalculatorUtil.minimumBaseIncrease(50.0));
		System.out.println(PriceCalculatorUtil.minimumBaseIncreaseWithMargin(100.0));
		
		
		List<PricingRule> allRules = new ArrayList<PricingRule>();
		for (int i = 0; i < 3; i++){
			PricingRule pr = new PricingRule();
			pr.setDiscountValue(0.0);
			pr.setVolume(0);
			allRules.add(i, pr);
		}
		
		FlightTravelCategory ftgReg = new FlightTravelCategory();
		ftgReg.setCommission(PriceCalculatorUtil.minimumCommissionPercentage());
		ftgReg.setOverruledPrice(0.0);
		ftgReg.setSeatPrice(100.0);
		
		System.out.println(PriceCalculatorUtil.getIndividualPrice(ftgReg, allRules, 1, PaymentType.CREDIT_CARD));
		
		FlightTravelCategory ftgProfit = new FlightTravelCategory();
		ftgProfit.setCommission(PriceCalculatorUtil.minimumCommissionPercentageWithMargin());
		ftgProfit.setOverruledPrice(0.0);
		ftgProfit.setSeatPrice(100.0);
		
		System.out.println(PriceCalculatorUtil.getIndividualPrice(ftgProfit, allRules, 1, PaymentType.CREDIT_CARD));
	}
}
