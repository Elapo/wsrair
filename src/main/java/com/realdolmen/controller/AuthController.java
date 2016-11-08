package com.realdolmen.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.realdolmen.domain.Role;
import com.realdolmen.domain.User;
import com.realdolmen.service.AuthService;

@ViewScoped
@ManagedBean
public class AuthController implements Serializable {
	@EJB
	AuthService authService;

	@ManagedProperty(value = "#{backingBean}")
	BackingBean backingBean;

	User registerUser;
	String confirmPassword;

	String loginEmail;
	String loginPassword;

	@PostConstruct
	private void init() {
		registerUser = new User();
	}

	public BackingBean getBackingBean() {
		return backingBean;
	}

	public void setBackingBean(BackingBean backingBean) {
		this.backingBean = backingBean;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public User getRegisterUser() {
		return registerUser;
	}

	public void setRegisterUser(User registerUser) {
		this.registerUser = registerUser;
	}

	public String getLoginEmail() {
		return loginEmail;
	}

	public void setLoginEmail(String loginEmail) {
		this.loginEmail = loginEmail;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String register() {
		if (!this.registerUser.getPassword().equals(confirmPassword)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "Passwords do not match"));
			return null;
		}
		try {
			registerUser.setRole(Role.REGULAR);
			User registeredUser = authService.createUser(registerUser);
			backingBean.updateLoggedInUser(registeredUser);
			return "index.xhtml?faces-redirect=true";

		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "This e-mail is already in use."));
		}
		return null;
	}

	public String login() {
		// TODO exception catchen en errormessage tonen
		// TODO email(username) tolowercase
		if (authService.login(this.loginEmail, this.loginPassword)) {
			User u = authService.findUserByUserName(this.loginEmail);
			backingBean.updateLoggedInUser(u);
			return "/index.xhtml?faces-redirect=true";
		}
		return null;

	}

	public String logout() {
		backingBean.logout();
		return "/index.xhtml?faces-redirect=true";
	}

}
