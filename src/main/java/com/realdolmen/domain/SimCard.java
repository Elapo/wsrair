package com.realdolmen.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Length;

@XmlRootElement
@Entity
public class SimCard implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Length(min = 8, max = 8)
	private String number;

	private String owner;

	public SimCard() {

	}

	public SimCard(String number) {
		this.number = number;
	}

	public SimCard(String number, String owner) {
		this.number = number;
		this.owner = owner;
	}

	public SimCard(SimCard copy) {
		this.number = copy.number;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

}
