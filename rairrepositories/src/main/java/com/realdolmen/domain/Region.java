package com.realdolmen.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public enum Region {

	AFRICA, NORTH_AMERICA, SOUTH_AMERICA, ASIA, EUROPE, OCEANIA, POLAR
}
