/**
 * Copyright 2022 Rawbank. All Rights Reserved.
 * 
 * Change Section:
 * Programmer			Date
 * Krishna				7/1/2022
 * CSC admin portal Project.
 * Initial Coding.
 * 
 * Created By:
 * @author krishna
 * @since July 1, 2022
 * 
 */
package com.rawbank.admin.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;


@Component
public class RawCustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {

	@Autowired
	private RawAuthenticateService rawAuthenticateService;

	private static final Logger LOGGER = LoggerFactory.getLogger(RawCustomLogoutSuccessHandler.class);
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		if (authentication != null && authentication.getDetails() != null) {
			try {
				LOGGER.info(" RawCustomLogoutSuccessHandler  {} " , authentication.getName());
				
				rawAuthenticateService.logout(authentication.getName().split("\\s+")[0]);
				
				LOGGER.info(" User Successfully Logout ");
				
				request.getSession().invalidate();

				String url = "/adminPortal/login?logout";

				response.setStatus(HttpStatus.OK.value());
				response.sendRedirect(url);
				response.getWriter().flush();
				
			   SecurityContextHolder.clearContext();
			} catch (Exception e) {
				LOGGER.error("", e);
			}
		}
		super.onLogoutSuccess(request, response, authentication);
	}
}
