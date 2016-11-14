package com.realdolmen.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.realdolmen.domain.Booking;
import com.realdolmen.domain.Partner;
import com.realdolmen.domain.Role;
import com.realdolmen.domain.User;

@SessionScoped
@ManagedBean
public class BackingBean implements Serializable {
	private String userName;
	private Role userRole;
	private Partner partner;
	private List<Booking> bookingsToBeConfirmed;

	private String originalURL;

	public void recordOriginalURL(String originalURL) {
		this.originalURL = originalURL;
		System.out.println("URI" + originalURL);
	}

	@PostConstruct
	private void init() {
		System.out.println("backingbean init");
		bookingsToBeConfirmed = new ArrayList<>();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Role getUserRole() {
		return userRole;
	}

	public void setUserRole(Role userRole) {
		this.userRole = userRole;
	}

	public Partner getPartner() {
		return partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

	public String getOriginalURL() {
		return originalURL;
	}

	public void setOriginalURL(String originalURL) {
		this.originalURL = originalURL;
	}

	public List<Booking> getBookingsToBeConfirmed() {
		return bookingsToBeConfirmed;
	}

	public void setBookingsToBeConfirmed(List<Booking> bookingsToBeConfirmed) {
		this.bookingsToBeConfirmed = bookingsToBeConfirmed;
	}

	public void updateLoggedInUser(User u) {
		this.resetUser();
		this.userName = u.getUserName();
		this.userRole = u.getRole();
		if (this.userRole.equals(Role.PARTNER)) {
			this.partner = u.getPartner();
		}
	}

	public void logout() {
		this.resetUser();
	}

	private void resetUser() {
		this.userName = null;
		this.userRole = null;
		this.partner = null;
		this.bookingsToBeConfirmed.clear();
	}

}
