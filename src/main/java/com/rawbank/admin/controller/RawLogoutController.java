/**
 * Copyright 2022 Rawbank. All Rights Reserved.
 * 
 * Change Section: 
 * Programmer 			Date
 * Krishna				6/21/2022
 * CSC admin portal Project.
 * Initial Coding.
 * 
 * 
 * Created By:
 * 
 * @author krishna
 * @since Jun 21, 2022
 * 
 */
package com.rawbank.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.rawbank.admin.service.RawAuthenticateService;

@Controller
public class RawLogoutController {
	
	@Autowired
	private RawAuthenticateService rawAuthenticateService;

	@GetMapping("/logOut")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			rawAuthenticateService.logout(authentication.getName());
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		return "redirect:/";
	}
}
