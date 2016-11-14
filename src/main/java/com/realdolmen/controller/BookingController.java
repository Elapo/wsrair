package com.realdolmen.controller;

import java.io.Serializable;
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

	public String persistBooking() throws ConcurrentUpdateException {

		try {
			for (FlightTravelCategory ftg : bookFlight.getFlightTravelCategory()) {
				if (amountPerCategory.get(ftg.getTravelCategory()) <= flightTravelCategoryService
						.availableSeatsLeftByFlightTravelCategory(ftg.getId())) {
					ftg.setOpenSeats(ftg.getOpenSeats() - this.amountPerCategory.get(ftg.getTravelCategory()));
					flightTravelCategoryService.update(ftg);
				} else {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Warning!", "The request amount of tickets is no longer available. Please try again."));
					return null;
				}
			}
			for (FlightTravelCategory ftg : bookFlight.getFlightTravelCategory()) {
				Integer amountOfCategory = this.amountPerCategory.get(ftg.getTravelCategory());
				for (Integer i = 0; i < amountOfCategory; i++) {
					Booking booking = new Booking();
					booking.setFinalPrice(PriceCalculatorUtil.getIndividualPrice(ftg, bookFlight.getPriceRules(),
							amountOfCategory, paymentType));
					booking.setFlight(bookFlight);
					booking.setPaymentType(paymentType);
					booking.setTravelCategory(ftg.getTravelCategory());
					booking.setBookingDateTime(new Date());
					// TODO: Implement User
					// booking.setUser(backingBean.getUser);

					bookingService.create(booking);
				}
			}
		} catch (ConcurrentUpdateException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!",
					"Something went wrong! Please try again."));
			return null;
		}
		return "/findFlight.xhtml?faces-redirect=true";
	}

}
