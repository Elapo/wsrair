package com.realdolmen.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.realdolmen.domain.Booking;
import com.realdolmen.domain.BookingStatus;
import com.realdolmen.domain.FlightTravelCategory;
import com.realdolmen.domain.User;
import com.realdolmen.exception.ConcurrentUpdateException;
import com.realdolmen.service.AuthService;
import com.realdolmen.service.BookingService;
import com.realdolmen.service.FlightTravelCategoryService;

@ViewScoped
@ManagedBean
public class UserController implements Serializable {
	@EJB
	AuthService authService;
	@EJB
	BookingService bookingService;
	@EJB
	FlightTravelCategoryService flightTravelCategoryService;

	@ManagedProperty(value = "#{backingBean}")
	BackingBean backingBean;

	User loggedInUser;
	List<Booking> bookings;

	Long bookingId;
	Booking bookingToPrint;

	@PostConstruct
	private void init() {
		loggedInUser = authService.findUserByUserName(backingBean.getUserName());
		bookings = bookingService.findBookingsByUserIdWithoutStatusType(loggedInUser.getId(), BookingStatus.INACTIVE);
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

	public Booking getBookingToPrint() {
		return bookingToPrint;
	}

	public void setBookingToPrint(Booking bookingToPrint) {
		this.bookingToPrint = bookingToPrint;
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public void updateUser() {
		System.out.println("update");
		authService.merge(this.loggedInUser);
	}

	public void loadBooking() throws IOException {
		if (bookingId == null) {
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			ec.redirect(ec.getRequestContextPath() + "/filtered/regular/manageaccount.xhtml");
			return;
		}
		Booking b = bookingService.find(bookingId);
		if (b != null && b.getUser() != null && b.getUser().getUserName().equals(backingBean.getUserName())) {
			if (loggedInUser != null && loggedInUser.requiredFieldsFilledIn()) {
				bookingToPrint = b;
				return;
			}
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			ec.redirect(ec.getRequestContextPath() + "/filtered/regular/manageaccount.xhtml");
			return;
		}
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.redirect(ec.getRequestContextPath() + "/index.xhtml");

	}

	public String cancelBooking(Long bookingId) throws ConcurrentUpdateException {
		Booking booking = bookingService.find(bookingId);
		booking.setBookingStatus(BookingStatus.INACTIVE);
		bookingService.update(booking);
		bookings = bookingService.findBookingsByUserIdWithoutStatusType(loggedInUser.getId(), BookingStatus.INACTIVE);
		
		List<FlightTravelCategory> ftcs = booking.getFlight().getFlightTravelCategory();
		Long ftcId = 0l;
		for (FlightTravelCategory ftc : ftcs) {
			if (ftc.getTravelCategory().equals(booking.getTravelCategory())) {
				ftcId = ftc.getId();
			}
		}
		FlightTravelCategory flightTravelCategory = flightTravelCategoryService.find(ftcId);
		flightTravelCategory.setOpenSeats(flightTravelCategory.getOpenSeats() + 1);
		flightTravelCategoryService.update(flightTravelCategory);
		
		return null;
	}
}
