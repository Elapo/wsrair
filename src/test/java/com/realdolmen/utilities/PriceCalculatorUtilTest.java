package com.realdolmen.utilities;

import org.junit.Test;

import com.realdolmen.util.PriceCalculatorUtil;

public class PriceCalculatorUtilTest {

	@Test
	public void roundTwoDecimalsTest() {
		System.out.println(PriceCalculatorUtil.minimumCommissionPercentage());
		System.out.println(PriceCalculatorUtil.minimumBaseIncrease(50.0));
		System.out.println(PriceCalculatorUtil.minimumBaseIncreaseWithMargin(50.0));
	}
}
