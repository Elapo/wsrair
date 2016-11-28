package com.realdolmen.domain;

import java.io.Serializable;
import java.util.Date;

public class SearchQuery implements Serializable {
	private Long depId;
	private Long arrId;
	private Long partnerId;
	private Integer tickets;
	private Date depDate;
	private TravelCategory tCategory;

	public SearchQuery() {

	}

	public SearchQuery(Long depId, Long arrId, Long partnerId, Integer tickets, Date depDate,
			TravelCategory tCategory) {
		this.depId = depId;
		this.arrId = arrId;
		this.partnerId = partnerId;
		this.tickets = tickets;
		this.depDate = depDate;
		this.tCategory = tCategory;
	}

	public Long getDepId() {
		return depId;
	}

	public Long getArrId() {
		return arrId;
	}

	public Long getPartnerId() {
		return partnerId;
	}

	public Integer getTickets() {
		return tickets;
	}

	public Date getDepDate() {
		return depDate;
	}

	public TravelCategory gettCategory() {
		return tCategory;
	}

	public void setDepId(Long depId) {
		this.depId = depId;
	}

	public void setArrId(Long arrId) {
		this.arrId = arrId;
	}

	public void setPartnerId(Long partnerId) {
		this.partnerId = partnerId;
	}

	public void setTickets(Integer tickets) {
		this.tickets = tickets;
	}

	public void setDepDate(Date depDate) {
		this.depDate = depDate;
	}

	public void settCategory(TravelCategory tCategory) {
		this.tCategory = tCategory;
	}

}
