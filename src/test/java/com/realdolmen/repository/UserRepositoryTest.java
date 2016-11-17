package com.realdolmen.repository;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

import org.junit.Before;
import org.junit.Test;

import com.realdolmen.domain.Role;
import com.realdolmen.domain.User;
import com.realdolmen.utilities.JpaPersistenceTest;

public class UserRepositoryTest extends JpaPersistenceTest {
	private UserRepository ur;
	private User user1;

	@Before
	public void init() {
		ur = new UserRepository();
		ur.entityManager = entityManager();

		// Create a valid user object for persistence
		final String uniqueUserName = "unique@email.com";
		user1 = new User();
		user1.setUserName(uniqueUserName);
		user1.setPassword("pwd");
		user1.setRole(Role.REGULAR);
	}

	@Test
	public void userIdShouldNotBeNull() {
		assertNotNull(ur.create(user1).getId());
	}

	@Test(expected = PersistenceException.class)
	public void userNameShouldBeUnique() throws Exception {
		User user2 = new User();
		user2.setUserName(user1.getUserName());
		user2.setPassword("pwd");
		user2.setRole(Role.REGULAR);
		ur.create(user1);
		ur.create(user2);
	}

	@Test(expected = ConstraintViolationException.class)
	public void userNameShouldBeAValidEmail() throws Exception {
		user1.setUserName("ThisIsNotAnEmail");
		ur.create(user1);
	}

	@Test(expected = ConstraintViolationException.class)
	public void userNameCanNotBeBlank() throws Exception {
		user1.setUserName("");
		ur.create(user1);
	}

	@Test(expected = ConstraintViolationException.class)
	public void userNameCanNotBeNull() throws Exception {
		user1.setUserName(null);
		ur.create(user1);
	}

	@Test(expected = ConstraintViolationException.class)
	public void passwordCanNotBeBlank() throws Exception {
		user1.setPassword("");
		ur.create(user1);
	}

	@Test(expected = ConstraintViolationException.class)
	public void passwordCanNotBeNull() throws Exception {
		user1.setPassword(null);
		ur.create(user1);
	}

	@Test(expected = ConstraintViolationException.class)
	public void roleCanNotBeNull() throws Exception {
		user1.setRole(null);
		ur.create(user1);
	}

}
