package com.realdolmen.service;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.realdolmen.domain.User;
import com.realdolmen.repository.UserRepository;

@Stateless
@LocalBean
public class AuthServiceImpl implements AuthService {

	@EJB
	UserRepository userRepository;

	@Override
	public User createUser(User u) {
		u.setPassword(this.encrypt(u.getPassword()));
		return userRepository.create(u);
	}

	public User login(String userName, String password) {
		User u = userRepository.findUserByUserName(userName);
		return u != null && u.getPassword().equals(encrypt(password)) ? u : null;
	}

	/**
	 * Just a easy/simple encrypted, just to showcase passwords are not stored
	 * unencrypted
	 * 
	 * @param String
	 * @return Encrypted String
	 */
	private String encrypt(String password) {
		char[] chars = password.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];

			if (c >= 'a' && c <= 'z')
				chars[i] = (char) ('z' - c + 'a');

			if (c >= 'A' && c <= 'Z')
				chars[i] = (char) ('Z' - c + 'A');
		}
		return String.valueOf(chars);
	}

	@Override
	public User findUserByUserName(String userName) {
		return userRepository.findUserByUserName(userName);
	}

}
