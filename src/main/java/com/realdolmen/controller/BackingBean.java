package com.realdolmen.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.realdolmen.domain.Partner;
import com.realdolmen.domain.Role;
import com.realdolmen.domain.User;

@SessionScoped
@ManagedBean
public class BackingBean implements Serializable {
	private String userName;
	private Role userRole;
	private Partner partner;

	@PostConstruct
	private void init() {
		System.out.println("backingbean init");
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

	public void updateLoggedInUser(User u) {
		this.resetUser();
		this.userName = u.getUserName();
		this.userRole = u.getRole();
		if (this.userRole.equals(Role.PARTNER)) {
			this.partner = u.getPartner();
		}
	}

	public void logout() {
		this.resetUser();
	}

	private void resetUser() {
		this.userName = null;
		this.userRole = null;
		this.partner = null;
	}

}
