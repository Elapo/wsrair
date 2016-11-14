package com.realdolmen.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.realdolmen.domain.Booking;
import com.realdolmen.domain.User;
import com.realdolmen.service.AuthService;
import com.realdolmen.service.BookingService;

@ViewScoped
@ManagedBean
public class UserController implements Serializable {
	@EJB
	AuthService authService;

	@EJB
	BookingService bookingService;

	@ManagedProperty(value = "#{backingBean}")
	BackingBean backingBean;

	User loggedInUser;
	List<Booking> bookings;

	@PostConstruct
	private void init() {
		loggedInUser = authService.findUserByUserName(backingBean.getUserName());
		bookings = bookingService.findAllBookingsByUserId(loggedInUser.getId());
	}

	public BackingBean getBackingBean() {
		return backingBean;
	}

	public void setBackingBean(BackingBean backingBean) {
		this.backingBean = backingBean;
	}

	public User getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(User loggedInUser) {
		this.loggedInUser = loggedInUser;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public void updateUser() {
		System.out.println("update");
		authService.merge(this.loggedInUser);
	}

}
