package com.realdolmen.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

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
		BackingBean b = (BackingBean) req.getSession().getAttribute("backingBean");
		if (b.getUserRole().equals(Role.EMPLOYEE)) {
			l.info("authed");
			chain.doFilter(request, response);
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
