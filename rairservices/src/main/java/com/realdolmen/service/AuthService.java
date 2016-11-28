package com.realdolmen.service;

import javax.ejb.Remote;

import com.realdolmen.domain.User;

@Remote
public interface AuthService {

	User createUser(User u);

	User findUserByUserName(String userName);

	User login(String userName, String password);

	User merge(User u);

}
