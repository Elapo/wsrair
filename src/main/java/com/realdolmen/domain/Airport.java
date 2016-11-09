package com.realdolmen.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Airport implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String name;

	private String airportCode;

	private String country;

	@Enumerated(EnumType.STRING)
	private Region region;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public String getAirportCode() {
		return airportCode;
	}

	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Airport() {
	}

	public Airport(String name, Region region) {
		this.name = name;
		this.region = region;
	}
	
	@Override
    public String toString() {
        return name;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((airportCode == null) ? 0 : airportCode.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Airport other = (Airport) obj;
		if (airportCode == null) {
			if (other.airportCode != null)
				return false;
		} else if (!airportCode.equals(other.airportCode))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/*@Override
	public boolean equals(Object other) {
		if (other == null) return false;
		if (other == this) return true;
		if (!(other instanceof Airport)) return false;
		
		Airport otherAsAirport = (Airport) other;
		
		if (otherAsAirport.getAirportCode() == this.getAirportCode()) return true;
		return false;
	}*/
	
	
}
