package com.realdolmen.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

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

	String originalURL;

	@PostConstruct
	private void init() {
		registerUser = new User();
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		String forwardedRequestURI = (String) externalContext.getRequestMap()
				.get(RequestDispatcher.FORWARD_REQUEST_URI);

		if ((this.originalURL = request.getParameter("originalURL")) != null) {
			// If the user was redirected, retrieve the originalURL from the
			// request's "originalURL" parameter
			return;

		} else if (forwardedRequestURI == null) {
			// If the user logged in directly from the top bar, simply redirect
			// to the originalURL recorded by UserSessionBean
			this.originalURL = backingBean.getOriginalURL();
			if (originalURL == null) {
				// Redirect to home page in case the user didn't surf any pages
				// before logging in
				this.originalURL = request.getContextPath();
			}

		} else {
			System.out.println("forwarded");
			// If the user was forwarded to the login page, re-build the orignal
			// requestURL
			this.originalURL = forwardedRequestURI;
			String originalQuery = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_QUERY_STRING);

			if (originalQuery != null) {
				this.originalURL += "?" + originalQuery;
			}
		}
		// this.originalURL = backingBean.getOriginalURL();
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
		} catch (EJBTransactionRolledbackException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "This e-mail is already in use."));
		}
		return null;
	}

	public String login() throws IOException {
		// TODO exception catchen en errormessage tonen
		User u = authService.login(this.loginEmail.toLowerCase(), this.loginPassword);
		if (u != null) {
			backingBean.updateLoggedInUser(u);
			// return "/index.xhtml?faces-redirect=true";
			FacesContext.getCurrentInstance().getExternalContext().redirect(originalURL);
		}
		return null;

	}

	public String logout() {
		backingBean.logout();
		return "/index.xhtml?faces-redirect=true";
	}

}
