package com.realdolmen.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.realdolmen.domain.Booking;
import com.realdolmen.domain.Flight;
import com.realdolmen.domain.FlightTravelCategory;
import com.realdolmen.domain.PaymentType;
import com.realdolmen.domain.PricingRule;
import com.realdolmen.domain.TravelCategory;
import com.realdolmen.exception.ConcurrentUpdateException;
import com.realdolmen.service.AuthService;
import com.realdolmen.service.BookingService;
import com.realdolmen.service.FlightService;
import com.realdolmen.service.FlightTravelCategoryService;
import com.realdolmen.util.PriceCalculatorUtil;

@ViewScoped
@ManagedBean
public class BookingController implements Serializable {
	@EJB
	private FlightService flightService;
	@EJB
	private BookingService bookingService;
	@EJB
	private FlightTravelCategoryService flightTravelCategoryService;
	@EJB
	private AuthService authService;
	@ManagedProperty(value = "#{backingBean}")
	private BackingBean backingBean;

	private Long flightId;
	private Flight bookFlight;
	private HashMap<TravelCategory, Integer> amountPerCategory = new HashMap<>();
	private HashMap<TravelCategory, Double> displayPricePerCategory = new HashMap<>();
	private HashMap<TravelCategory, Double> finalPricePerCategory = new HashMap<>();
	private List<PaymentType> paymentTypes;
	private PaymentType paymentType;
	private Double preferredPaymentDiscount = PriceCalculatorUtil.getPreferredPaymentDiscount();
	private PaymentType preferredPaymentType = PriceCalculatorUtil.getPreferredPaymentType();

	public Long getFlightId() {
		return flightId;
	}

	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}

	public Flight getBookFlight() {
		return bookFlight;
	}

	public void setBookFlight(Flight bookFlight) {
		this.bookFlight = bookFlight;
	}

	public HashMap<TravelCategory, Integer> getAmountPerCategory() {
		return amountPerCategory;
	}

	public void setAmountPerCategory(HashMap<TravelCategory, Integer> amountPerCategory) {
		this.amountPerCategory = amountPerCategory;
	}

	public List<PaymentType> getPaymentTypes() {
		return paymentTypes;
	}

	public void setPaymentTypes(List<PaymentType> paymentTypes) {
		this.paymentTypes = paymentTypes;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public Double getPreferredPaymentDiscount() {
		return preferredPaymentDiscount;
	}

	public void setPreferredPaymentDiscount(Double preferredPaymentDiscount) {
		this.preferredPaymentDiscount = preferredPaymentDiscount;
	}

	public HashMap<TravelCategory, Double> getDisplayPricePerCategory() {
		return displayPricePerCategory;
	}

	public void setDisplayPricePerCategory(HashMap<TravelCategory, Double> displayPricePerCategory) {
		this.displayPricePerCategory = displayPricePerCategory;
	}

	public HashMap<TravelCategory, Double> getFinalPricePerCategory() {
		return finalPricePerCategory;
	}

	public void setFinalPricePerCategory(HashMap<TravelCategory, Double> finalPricePerCategory) {
		this.finalPricePerCategory = finalPricePerCategory;
	}

	public PaymentType getPreferredPaymentType() {
		return preferredPaymentType;
	}

	public void setPreferredPaymentType(PaymentType preferredPaymentType) {
		this.preferredPaymentType = preferredPaymentType;
	}

	public BackingBean getBackingBean() {
		return backingBean;
	}

	public void setBackingBean(BackingBean backingBean) {
		this.backingBean = backingBean;
	}

	public void loadData() {
		this.bookFlight = flightService.findById(flightId);
		if (!FacesContext.getCurrentInstance().isPostback()) {
			this.paymentTypes = Arrays.asList(PaymentType.values());
			this.paymentType = PaymentType.CREDIT_CARD;

			for (FlightTravelCategory ftg : bookFlight.getFlightTravelCategory()) {
				this.displayPricePerCategory.put(ftg.getTravelCategory(), PriceCalculatorUtil.getDisplayPrice(ftg));
				this.amountPerCategory.put(ftg.getTravelCategory(), 0);
				this.finalPricePerCategory.put(ftg.getTravelCategory(),
						PriceCalculatorUtil.getIndividualPrice(ftg, bookFlight.getPriceRules(), 0, this.paymentType));
			}
		}
	}

	public PricingRule pricingRuleToApply(Integer amountOfSeats) {
		return PriceCalculatorUtil.pricingRuleToApply(amountOfSeats, bookFlight.getPriceRules());
	}

	public Double getCombinedPrice(FlightTravelCategory ftg, Integer amount) {
		return PriceCalculatorUtil.getCombinedPrice(ftg, this.bookFlight.getPriceRules(),
				this.amountPerCategory.get(ftg.getTravelCategory()), this.paymentType);
	}

	public String confirmBooking() {
		List<Booking> bookingsToConfirm = new ArrayList<Booking>();

		for (FlightTravelCategory ftg : bookFlight.getFlightTravelCategory()) {
			Integer amountOfCategory = this.amountPerCategory.get(ftg.getTravelCategory());

			if (amountOfCategory > flightTravelCategoryService.availableSeatsLeftByFlightTravelCategory(ftg.getId())) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Warning!", "The request amount of tickets is no longer available. Please try again."));
				return null;
			}

			for (Integer i = 0; i < amountOfCategory; i++) {
				Booking booking = new Booking();
				booking.setFinalPrice(PriceCalculatorUtil.getIndividualPrice(ftg, bookFlight.getPriceRules(),
						amountOfCategory, this.paymentType));
				booking.setFlight(bookFlight);
				booking.setPaymentType(this.paymentType);
				booking.setTravelCategory(ftg.getTravelCategory());
				booking.setBookingDateTime(new Date());

				bookingsToConfirm.add(booking);
			}
		}
		backingBean.setBookingsToBeConfirmed(bookingsToConfirm);
		return "/filtered/regular/confirm.xhtml?faces-redirect=true";
	}

	public String persistBooking() throws ConcurrentUpdateException {
		try {

			Boolean updateFlag = false;
			
			// Init amount
			HashMap<TravelCategory, Integer> ticketsPerCategory = new HashMap<>();
			for (TravelCategory tc : TravelCategory.values()) {
				ticketsPerCategory.put(tc, 0);
			}
			// Fill amount
			for (Booking booking : backingBean.getBookingsToBeConfirmed()) {
				ticketsPerCategory.put(booking.getTravelCategory(),
						ticketsPerCategory.get(booking.getTravelCategory()) + 1);
			}
			// Check persistence possiblity via checksum
			for( FlightTravelCategory ftg : backingBean.getBookingsToBeConfirmed().get(0).getFlight().getFlightTravelCategory()) {
				
				if (ticketsPerCategory.get(ftg.getTravelCategory()) <= flightTravelCategoryService
						.availableSeatsLeftByFlightTravelCategory(ftg.getId())) {
					updateFlag = true;
				} else {
					updateFlag = false;
				}
				
			}
			
			// Update seat count
			if (updateFlag) {
				for( FlightTravelCategory ftg : backingBean.getBookingsToBeConfirmed().get(0).getFlight().getFlightTravelCategory()) {
					if (ticketsPerCategory.get(ftg.getTravelCategory()) <= flightTravelCategoryService
							.availableSeatsLeftByFlightTravelCategory(ftg.getId())) {
						ftg.setOpenSeats(ftg.getOpenSeats() - ticketsPerCategory.get(ftg.getTravelCategory()));
						flightTravelCategoryService.update(ftg);
					} else {
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!",
								"The request amount of tickets is no longer available! Please try again."));
						return "/bookFlight.xhtml?faces-redirect=true";
					}
					
				}
			}
			
			// Persist
			for (Booking booking : backingBean.getBookingsToBeConfirmed()) {
				booking.setUser(authService.findUserByUserName(backingBean.getUserName()));
				bookingService.create(booking);
			}
		} catch (ConcurrentUpdateException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!",
					"Something went wrong! Please try again."));
			return "/bookFlight.xhtml?faces-redirect=true";
		}
		return "/filtered/regular/thankYou.xhtml?faces-redirect=true";
	}

}
