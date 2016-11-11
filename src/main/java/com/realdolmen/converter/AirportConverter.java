package com.realdolmen.converter;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.realdolmen.domain.Airport;
import com.realdolmen.service.AirportService;

@FacesConverter("airportConverter")
public class AirportConverter implements Converter {

	@EJB
	private AirportService airportService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return airportService.findById(Long.parseLong(value));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		Airport airport = (Airport) value;
		return airport.getId() != null ? String.valueOf(airport.getId()) : null;
	}

}
