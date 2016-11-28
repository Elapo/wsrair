package com.realdolmen.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import com.realdolmen.domain.Airport;
import com.realdolmen.domain.Booking;
import com.realdolmen.domain.Flight;
import com.realdolmen.domain.FlightTravelCategory;
import com.realdolmen.domain.PricingRule;
import com.realdolmen.domain.Role;
import com.realdolmen.domain.TravelCategory;
import com.realdolmen.exception.ConcurrentUpdateException;
import com.realdolmen.service.AirportService;
import com.realdolmen.service.BookingService;
import com.realdolmen.service.FlightService;
import com.realdolmen.service.FlightTravelCategoryService;
import com.realdolmen.service.PartnerService;
import com.realdolmen.util.PriceCalculatorUtil;

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
	@ManagedProperty(value = "#{backingBean}")
	private BackingBean backingBean;

	private List<Flight> flights;
	private List<Flight> filteredFlights;
	private List<Airport> airports;
	private Long flightId;
	private Flight editFlight;
	private Date currentDate = new Date();
	private List<TravelCategory> travelCategories;
	private LineChartModel lineModelPrices;

	@PostConstruct
	public void init() {
		if (backingBean.getPartner() != null) {
			flights = flightService.findAllFlightsByPartnerId(backingBean.getPartner().getId());
			filteredFlights = flights;
		} else {
			flights = flightService.findAll();
			filteredFlights = flights;
		}
		createLineModels(filteredFlights);
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

	public List<Flight> getFilteredFlights() {
		return filteredFlights;
	}

	public void setFilteredFlights(List<Flight> filteredFlights) {
		if (filteredFlights != null) {
			this.filteredFlights = filteredFlights;
			createLineModels(filteredFlights);
		}
	}

	public BackingBean getBackingBean() {
		return backingBean;
	}

	public void setBackingBean(BackingBean backingBean) {
		this.backingBean = backingBean;
	}

	public LineChartModel getLineModelPrices() {
		return lineModelPrices;
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
		for (FlightTravelCategory ftc : editFlight.getFlightTravelCategory()) {
			ftc.setOpenSeats(ftc.getMaximumSeats());
		}
		flightService.create(editFlight);
		return "/findFlight.xhtml?faces-redirect=true";
	}

	public String updateFlight() throws ConcurrentUpdateException {

		for (FlightTravelCategory ftg : this.editFlight.getFlightTravelCategory()) {
			int amountOfBookings = bookingService.countBookingByFlightIdAndCategory(editFlight.getId(),
					ftg.getTravelCategory());
			if (ftg.getMaximumSeats() < amountOfBookings) {
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
		return "/findFlight.xhtml?faces-redirect=true";
	}

	public void loadData() throws IOException {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			if (flightId != null) {
				this.editFlight = flightService.findById(flightId);
				if (!(backingBean.getPartner() == null)
						&& !backingBean.getPartner().getId().equals(editFlight.getPartner().getId())) {

					FacesContext.getCurrentInstance().getExternalContext()
							.redirect("/rair/findFlight.xhtml?faces-redirect=true");
				}
			} else {
				Airport dummyAirport = airportService.getFirstAirport();
				this.editFlight = new Flight();
				editFlight.setDepartureLocation(dummyAirport);
				editFlight.setArrivalLocation(dummyAirport);
				editFlight.setDepartureDateTime(new Date());
				editFlight.setArrivalDateTime(new Date());
				editFlight.setPartner(partnerService.findById(backingBean.getPartner().getId()));
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

	public Double minimumCommissionPercentage() {
		return PriceCalculatorUtil.minimumCommissionPercentage();
	}

	public Double minimumCommissionPercentageWithMargin() {
		return PriceCalculatorUtil.minimumCommissionPercentageWithMargin();
	}

	public Double minimumBaseIncrease(Double partnerSeatPrice) {
		return PriceCalculatorUtil.minimumBaseIncrease(partnerSeatPrice);
	}

	public Double minimumBaseIncreaseWithMargin(Double partnerSeatPrice) {
		return PriceCalculatorUtil.minimumBaseIncreaseWithMargin(partnerSeatPrice);
	}

	public Double getMinimumMargin() {
		return PriceCalculatorUtil.getMinimumMargin();
	}

	private void createLineModels(List<Flight> filteredFlights) {
		lineModelPrices = initCategoryModel(filteredFlights);
		lineModelPrices.setTitle("Booking Prices");
		lineModelPrices.setLegendPosition("e");
		lineModelPrices.setAnimate(true);
		lineModelPrices.setShowPointLabels(false);
		lineModelPrices.setShowDatatip(false);
		lineModelPrices.setResetAxesOnResize(true);
		lineModelPrices.getAxes().put(AxisType.X, new CategoryAxis("Flight"));
		Axis yAxis = lineModelPrices.getAxis(AxisType.Y);
		yAxis.setMin(0);
		yAxis.setLabel("Price (EUR)");
	}

	private LineChartModel initCategoryModel(List<Flight> filteredFlights) {
		LineChartModel model = new LineChartModel();
		int minIndex = -1, maxIndex = -1;
		List<Booking> bookings = new ArrayList<>();

		LineChartSeries bookingMinPrice = new LineChartSeries();
		bookingMinPrice.setLabel("Mininimum Price");

		LineChartSeries bookingMaxPrice = new LineChartSeries();
		bookingMaxPrice.setLabel("Maximum Price");

		LineChartSeries bookingAvgPrice = new LineChartSeries();
		bookingAvgPrice.setLabel("Average Price");

		BarChartSeries bookingAvgMargin = new BarChartSeries();
		bookingAvgMargin.setLabel("Average Margin");

		for (Flight flight : filteredFlights) {
			Double minFinalPrice = 0.0;
			Double maxFinalPrice = 0.0;
			Double avgFinalPrice = 0.0;
			Double avgMargin = 0.0;

			bookings = bookingService.findAllBookingsByFlightId(flight.getId());

			if (!bookings.isEmpty()) {
				Double totalPriceSum = 0.0, totalMarginSum = 0.0;

				final ListIterator<Booking> itr = bookings.listIterator();
				Booking min = itr.next(); // first element as the current
											// minimum
				Booking max = min;
				minIndex = itr.previousIndex();
				maxIndex = minIndex;
				totalPriceSum += min.getFinalPrice();
				totalMarginSum += min.getFinalPrice() - min.getPurchasePrice();

				while (itr.hasNext()) {
					final Booking curr = itr.next();
					if (curr.getFinalPrice() < min.getFinalPrice()) {
						min = curr;
						minIndex = itr.previousIndex();
					}
					if (curr.getFinalPrice() > max.getFinalPrice()) {
						max = curr;
						maxIndex = itr.previousIndex();
					}
					totalPriceSum += curr.getFinalPrice();
					totalMarginSum += curr.getFinalPrice() - curr.getPurchasePrice();
				}

				minFinalPrice = bookings.get(minIndex).getFinalPrice();
				maxFinalPrice = bookings.get(maxIndex).getFinalPrice();
				avgFinalPrice = totalPriceSum / bookings.size();
				avgMargin = totalMarginSum / bookings.size();

			}
			bookingMinPrice.set(flight.getArrivalLocation().getAirportCode() + "-" + flight.getId(),
					Double.valueOf(minFinalPrice));
			bookingMaxPrice.set(flight.getArrivalLocation().getAirportCode() + "-" + flight.getId(), maxFinalPrice);
			bookingAvgPrice.set(flight.getArrivalLocation().getAirportCode() + "-" + flight.getId(), avgFinalPrice);
			bookingAvgMargin.set(flight.getArrivalLocation().getAirportCode() + "-" + flight.getId(), avgMargin);
		}

		model.addSeries(bookingMaxPrice);
		model.addSeries(bookingAvgPrice);
		model.addSeries(bookingMinPrice);
		if (backingBean.getUserRole() != null && Role.EMPLOYEE.equals(backingBean.getUserRole())) {
			model.addSeries(bookingAvgMargin);
		}

		return model;
	}
}
