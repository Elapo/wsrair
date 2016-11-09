package com.realdolmen.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.realdolmen.domain.Airport;
import com.realdolmen.domain.Flight;
import com.realdolmen.domain.FlightTravelCategory;
import com.realdolmen.domain.PricingRule;
import com.realdolmen.domain.TravelCategory;
import com.realdolmen.exception.ConcurrentUpdateException;
import com.realdolmen.service.AirportService;
import com.realdolmen.service.BookingService;
import com.realdolmen.service.FlightService;
import com.realdolmen.service.FlightTravelCategoryService;
import com.realdolmen.service.PartnerService;

@ViewScoped
@ManagedBean
public class FlightController implements Serializable {

	@EJB
	private FlightService flightService;
	@EJB
	private AirportService airportService;
	@EJB
	private FlightTravelCategoryService flightTravelCategoryService;
	@EJB
	private PartnerService partnerService;
	@EJB
	private BookingService bookingService;

	private List<Flight> flights;
	private List<Airport> airports;
	private Long flightId;
	private Flight editFlight;
	private Date currentDate = new Date();
	private List<TravelCategory> travelCategories;

	@PostConstruct
	public void init() {
		// UPDATE WITH USER PARTNERID
		flights = flightService.findAllFlightsByPartnerId(1000L);
	}

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	public Long getFlightId() {
		return flightId;
	}

	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}

	public Flight getEditFlight() {
		return editFlight;
	}

	public void setEditFlight(Flight editFlight) {
		this.editFlight = editFlight;
	}

	public List<Airport> getAirports() {
		return airports;
	}

	public void setAirports(List<Airport> airports) {
		this.airports = airports;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public List<TravelCategory> getTravelCategories() {
		return travelCategories;
	}

	public void setTravelCategories(List<TravelCategory> travelCategories) {
		this.travelCategories = travelCategories;
	}

	public String persistFlight() throws ConcurrentUpdateException {

		int newTotalSeats = 0;

		if (this.editFlight.getArrivalLocation().equals(this.editFlight.getDepartureLocation())) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
					"The destination and arrival location should be different!"));
			return null;
		}

		for (FlightTravelCategory ftg : this.editFlight.getFlightTravelCategory()) {
			newTotalSeats += ftg.getMaximumSeats();
		}

		for (PricingRule pr : editFlight.getPriceRules()) {
			if (pr.getVolume() > newTotalSeats) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Error!",
						"The volume of tickets for a price rule should not exceed the total amount of seats possible! "));
				return null;
			}
		}

		if (editFlight.getId() == null) {
			return createFlight();
		} else {
			return updateFlight();
		}
	}

	public String createFlight() {
		flightService.create(editFlight);
		return "findFlight.xhtml?faces-redirect=true";
	}

	public String updateFlight() throws ConcurrentUpdateException {

		for (FlightTravelCategory ftg : this.editFlight.getFlightTravelCategory()) {
			int amountOfBookings = bookingService.countBookingByFlightIdAndCategory(editFlight.getId(), ftg.getTravelCategory());
			if (ftg.getMaximumSeats() < amountOfBookings ) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
								"The new value for maximum number of seats cannot be lower than amount of bookings already made for category "
										+ ftg.getTravelCategory().toString() + "!"));
				return null;
			} else {
				ftg.setOpenSeats(ftg.getMaximumSeats() - amountOfBookings);
			}
		}

		flightService.merge(editFlight);
		return "findFlight.xhtml?faces-redirect=true";
	}

	public void loadData() {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			if (flightId != null) {
				this.editFlight = flightService.findById(flightId);
				System.out.println(editFlight.getPriceRules().size());
				System.out.println(editFlight.getFlightTravelCategory().size());
			} else {
				Airport dummyAirport = airportService.getFirstAirport();
				this.editFlight = new Flight();
				editFlight.setDepartureLocation(dummyAirport);
				editFlight.setArrivalLocation(dummyAirport);
				editFlight.setDepartureDateTime(new Date());
				editFlight.setArrivalDateTime(new Date());
				// UPDATE WITH USER AGENTID
				editFlight.setPartner(partnerService.findById(1000L));
			}
			airports = airportService.findAllAirports();
			travelCategories = Arrays.asList(TravelCategory.values());
		}
	}

	public FlightTravelCategory getFlightTravelCategoryByTravelCategory(TravelCategory category) {
		FlightTravelCategory matchedFTG = new FlightTravelCategory();
		matchedFTG.setMaximumSeats(0);
		matchedFTG.setSeatPrice(0.0);
		matchedFTG.setFlight(editFlight);

		List<FlightTravelCategory> ftgList = this.editFlight.getFlightTravelCategory();

		for (FlightTravelCategory ftg : ftgList) {
			if (ftg.getTravelCategory().equals(category)) {
				matchedFTG = ftg;
			}
		}
		return matchedFTG;
	}

	public Airport getAirportById(Long id) {
		return airportService.findById(id);
	}

	public int determineMaxSeatsLowerBoundPerCategory(Long id) {
		int totalAvailable = 0;

		for (FlightTravelCategory ftg : this.editFlight.getFlightTravelCategory()) {
			if (ftg.getId().equals(id)) {
				totalAvailable = ftg.getMaximumSeats() - ftg.getOpenSeats();
			}
		}

		return totalAvailable;
	}

	public int getAmountOfBookingsPerCategory(TravelCategory travelCategory) {
		return bookingService.countBookingByFlightIdAndCategory(editFlight.getId(), travelCategory);
	}

	public int determineTotalAmountOfSeats() {
		int totalAmount = 0;
		for (FlightTravelCategory ftg : this.editFlight.getFlightTravelCategory()) {
			totalAmount += ftg.getMaximumSeats();
		}
		return totalAmount;
	}
}
