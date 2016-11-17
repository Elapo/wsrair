package com.realdolmen.repository;

import javax.ejb.Stateless;

import com.realdolmen.domain.User;

@Stateless
public class UserRepository extends AbstractRepository<User> {
	/*
	 * @PersistenceContext EntityManager entityManager;
	 */

	public User findUserByUserName(String userName) {
		return entityManager.createQuery("from User u where u.userName = :userName", User.class)
				.setParameter("userName", userName).getSingleResult();
	}
}
