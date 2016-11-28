package com.realdolmen.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class UserTest {
	@Test
	public void userInitHasIdNull() {
		assertNull(new User().getId());
	}

	@Test
	public void usernameShouldAlwaysBeLowercase() {
		final String username = "ShEnNo.w@gMaiL.cOm";
		final String usernameLowercase = "shenno.w@gmail.com";
		User user = new User();
		user.setUserName(username);
		assertEquals(usernameLowercase, user.getUserName());
	}

}
