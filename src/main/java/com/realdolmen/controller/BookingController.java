package com.realdolmen.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.realdolmen.domain.Booking;
import com.realdolmen.domain.Flight;
import com.realdolmen.domain.FlightTravelCategory;
import com.realdolmen.domain.PaymentType;
import com.realdolmen.domain.PricingRule;
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
	private HashMap<FlightTravelCategory, Integer> amountPerCategory = new HashMap<>();
	private HashMap<FlightTravelCategory, Double> displayPricePerCategory = new HashMap<>();
	private HashMap<FlightTravelCategory, Double> finalPricePerCategory = new HashMap<>();
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

	public HashMap<FlightTravelCategory, Integer> getAmountPerCategory() {
		return amountPerCategory;
	}

	public void setAmountPerCategory(HashMap<FlightTravelCategory, Integer> amountPerCategory) {
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

	public HashMap<FlightTravelCategory, Double> getDisplayPricePerCategory() {
		return displayPricePerCategory;
	}

	public void setDisplayPricePerCategory(HashMap<FlightTravelCategory, Double> displayPricePerCategory) {
		this.displayPricePerCategory = displayPricePerCategory;
	}

	public HashMap<FlightTravelCategory, Double> getFinalPricePerCategory() {
		return finalPricePerCategory;
	}

	public void setFinalPricePerCategory(HashMap<FlightTravelCategory, Double> finalPricePerCategory) {
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
		if (!FacesContext.getCurrentInstance().isPostback()) {
			this.bookFlight = flightService.findById(flightId);
			this.paymentTypes = Arrays.asList(PaymentType.values());
			this.paymentType = PaymentType.CREDIT_CARD;

			for (FlightTravelCategory ftg : bookFlight.getFlightTravelCategory()) {
				this.displayPricePerCategory.put(ftg, PriceCalculatorUtil.getDisplayPrice(ftg));
				this.amountPerCategory.put(ftg, 0);
				this.finalPricePerCategory.put(ftg, PriceCalculatorUtil.getIndividualPrice(ftg,
						bookFlight.getPriceRules(), 0, PriceCalculatorUtil.getDisplayPrice(ftg), this.paymentType));
			}
		}
	}

	public PricingRule pricingRuleToApply(Integer amountOfSeats) {
		return PriceCalculatorUtil.pricingRuleToApply(amountOfSeats, bookFlight.getPriceRules());
	}

	public Double getCombinedPrice(FlightTravelCategory ftg, Integer amount) {
		return PriceCalculatorUtil.getCombinedPrice(ftg, bookFlight.getPriceRules(), amount,
				PriceCalculatorUtil.getDisplayPrice(ftg), this.paymentType);
	}

	public String persistBooking() throws ConcurrentUpdateException {
		for (FlightTravelCategory ftg : bookFlight.getFlightTravelCategory()) {
			Integer amountOfCategory = this.amountPerCategory.get(ftg);
			for (Integer i = 0; i < amountOfCategory; i++) {
				Booking booking = new Booking();
				booking.setFinalPrice(PriceCalculatorUtil.getIndividualPrice(ftg, bookFlight.getPriceRules(), amountOfCategory, ftg.getSeatPrice(), paymentType));
				booking.setFlight(bookFlight);
				booking.setPaymentType(paymentType);
				booking.setTravelCategory(ftg.getTravelCategory());
				booking.setBookingDateTime(new Date());
				//TODO: Implement User
				// booking.setUser(backingBean.getUser);
				
				bookingService.create(booking);
				
				ftg.setOpenSeats(ftg.getOpenSeats() - 1);
				flightTravelCategoryService.update(ftg);
			}
		}
		return "/findFlight.xhtml?faces-redirect=true";
	}

}
