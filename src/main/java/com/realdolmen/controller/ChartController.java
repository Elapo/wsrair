package com.realdolmen.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

import com.realdolmen.domain.Booking;
import com.realdolmen.domain.Flight;
import com.realdolmen.domain.TravelCategory;
import com.realdolmen.service.BookingService;

@ViewScoped
@ManagedBean
public class ChartController {

	@EJB
	private BookingService bookingService;

	private HashMap<TravelCategory, BarChartModel> lineChartByLCM = new HashMap<>();
	
	private BarChartModel lineChartPremium;

	public HashMap<TravelCategory, BarChartModel> getLineChartByLCM() {
		return lineChartByLCM;
	}
	
	public BarChartModel  getLineChartPremium() {
		return lineChartPremium;
	}
	
	public void setLineChartByLCM(HashMap<TravelCategory, BarChartModel> lineChartByLCM) {
		this.lineChartByLCM = lineChartByLCM;
	}

	public void setLineChartPremium(BarChartModel lineChartPremium) {
		this.lineChartPremium = lineChartPremium;
	}

	@PostConstruct
	public void init(List<Flight> filteredFlights) {
		createLineModels(filteredFlights);
		System.out.println(lineChartByLCM.size());
	}

	public void createLineModels(List<Flight> filteredFlights) {
		for (TravelCategory tc : TravelCategory.values()) {
			BarChartModel lineChartModel = initCategoryModel(filteredFlights, tc);
			lineChartModel.setTitle(tc.name());
			lineChartModel.setLegendPosition("e");
			lineChartModel.getAxes().put(AxisType.X, new CategoryAxis("Flight"));
			Axis yAxis = lineChartModel.getAxis(AxisType.Y);
			yAxis.setLabel("Price");
			yAxis.setMin(0);
			yAxis.setMax(200);

			lineChartByLCM.put(tc, lineChartModel);
		}
	}
	
	public void createPremiumModels(List<Flight> filteredFlights) {
		lineChartPremium = initCategoryModel(filteredFlights, TravelCategory.PREMIUM);
		lineChartPremium.setTitle("Premium Chart");
		lineChartPremium.setLegendPosition("e");
        Axis yAxis = lineChartPremium.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);
	}

	private BarChartModel initCategoryModel(List<Flight> filteredFlights, TravelCategory tc) {
		BarChartModel model = new BarChartModel();
		ChartSeries serieMin = new ChartSeries();
		ChartSeries serieMax = new ChartSeries();
		ChartSeries serieAvg = new ChartSeries();
		List<Booking> bookings = new ArrayList<>();
		int minIndex = -1, maxIndex = -1;
		Double totalPriceSum = 0.0;

		for (Flight flight : filteredFlights) {
			bookings.addAll(bookingService.findAllBookingsByFlightId(flight.getId()));

			if (!bookings.isEmpty()) {
				final ListIterator<Booking> itr = bookings.listIterator();
				Booking min = itr.next(); // first element as the current
											// minimum
				Booking max = min;
				minIndex = itr.previousIndex();
				totalPriceSum += min.getFinalPrice();
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
				}

			}

			Double minPrice = bookings.get(minIndex).getFinalPrice();
			Double maxPrice = bookings.get(maxIndex).getFinalPrice();
			Double avgPrice = totalPriceSum / bookings.size();

			serieMin.set(flight.getId(), minPrice);
			serieMax.set(flight.getId(), maxPrice);
			serieAvg.set(flight.getId(), avgPrice);

		}

		serieMin.setLabel("Minimum price");
		model.addSeries(serieMin);

		serieMax.setLabel("Maximum price");
		model.addSeries(serieMax);

		serieAvg.setLabel("Average price");
		model.addSeries(serieAvg);

		return model;
	}

}
