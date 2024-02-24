/**
 * Copyright 2022 Rawbank. All Rights Reserved.
 * 
 * Change Section:
 * Programmer			Date
 * Krishna				6/28/2022
 * CSC admin portal Project.
 * Initial Coding.
 * 
 * Created By:
 * @author krishna
 * @since Jun 28, 2022
 * 
 */
package com.rawbank.admin.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class RawCustomAuthenticationHandler extends SimpleUrlAuthenticationFailureHandler
		implements AuthenticationFailureHandler {

	private static final Logger LOG = LoggerFactory.getLogger(RawCustomAuthenticationHandler.class);
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String errorCode = "";
		if (exception.getClass().isAssignableFrom(BadCredentialsException.class)) {
			errorCode = "404";
		} else if (exception instanceof CredentialsExpiredException) {
			errorCode = "422";
		} else if (exception instanceof LockedException) {
			errorCode = "423";
		} else if (exception instanceof InternalAuthenticationServiceException) {
			errorCode = "303";
		} 
		String redirectUrl = "/adminPortal/login?errorCode="+errorCode;
		LOG.info("RedirectUrl:  {} ", redirectUrl);
		request.setAttribute("errorCode", errorCode);
		response.sendRedirect(redirectUrl);
		response.getWriter().flush();
	}

}
