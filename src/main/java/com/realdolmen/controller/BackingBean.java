package com.realdolmen.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.realdolmen.domain.Partner;
import com.realdolmen.domain.Role;

@SessionScoped
@ManagedBean
public class BackingBean implements Serializable {
	private String userName;
	private Role userRole;
	private Partner partner;

	@PostConstruct
	private void init() {
		userRole = Role.EMPLOYEE;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Role getUserRole() {
		return userRole;
	}

	public void setUserRole(Role userRole) {
		this.userRole = userRole;
	}

	public Partner getPartner() {
		return partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

}
