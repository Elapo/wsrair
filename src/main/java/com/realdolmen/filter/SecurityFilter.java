package com.realdolmen.filter;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.realdolmen.controller.BackingBean;
import com.realdolmen.domain.Role;

@WebFilter("/filtered/*")
public class SecurityFilter implements Filter {
	Logger l = LoggerFactory.getLogger(SecurityFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		l.info("init filter");

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		BackingBean b = (BackingBean) req.getSession().getAttribute("backingBean");
		if (b == null || b.getUserRole() == null) {
			String requestURI = req.getRequestURI();
			String queryString = req.getQueryString();
			String encodedURL = URLEncoder.encode(requestURI + "?" + queryString, "UTF-8");
			httpResponse.sendRedirect(req.getContextPath() + "/login.xhtml?originalURL=" + encodedURL);
			// httpResponse.sendRedirect(req.getContextPath() + "/login.xhtml");
			return;
		}

		String reqUri = req.getRequestURI();
		String reqUriSplitted = reqUri.split("/")[3].toLowerCase();
		Boolean hasRights = false;
		switch (reqUriSplitted) {
		case "regular":
			hasRights = b.getUserRole().equals(Role.REGULAR);
			break;
		case "employee":
			hasRights = b.getUserRole().equals(Role.EMPLOYEE);
			break;
		case "partner":
			hasRights = b.getUserRole().equals(Role.PARTNER);
			break;
		default:
			break;
		}
		if (hasRights) {
			chain.doFilter(request, response);
		} else {
			// httpResponse.sendRedirect(req.getContextPath() +
			// "/login.xhtml");+
			String requestURI = req.getRequestURI();
			String queryString = req.getQueryString();
			String encodedURL = URLEncoder.encode(requestURI + "?" + queryString, "UTF-8");
			httpResponse.sendRedirect(req.getContextPath() + "/login.xhtml?originalURL=" + encodedURL);
		}
		// TODO redirect if not authed
	}

	@Override
	public void destroy() {

	}

}
