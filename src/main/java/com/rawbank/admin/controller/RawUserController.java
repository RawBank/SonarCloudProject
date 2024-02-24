/**
 * © Copyright 2021 Rawbank. All Rights Reserved.
 * 
 * Change Section:
 * Programmer			Date
 * Krishna				6/20/2022
 * CSC admin portal Project.
 * Initial Coding.
 * 
 * 
 * Created By: 
 * @author krishna
 * @since Jun 29, 2022
 * 
 */
package com.rawbank.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.rawbank.admin.model.RawLoginResponse;
import com.rawbank.admin.service.RawAuthenticateService;
import com.rawbank.admin.utility.RawUtility;

/**
 * @author krishna
 *
 */
@Controller
public class RawUserController {
	@Autowired
	private RawAuthenticateService rawAuthenticateService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RawUserController.class);
	@GetMapping("/userDetails")
	public ModelAndView userDetailsPage(ModelMap model) {
		LOGGER.info("User Details Screen..");
		Object principal = RawUtility.getLoggedinUserName();		
		if(principal != null) {
			RawLoginResponse rawLoginResponse = rawAuthenticateService.getUserDetails(principal);
			model.addAttribute("loginResponse", rawLoginResponse);
			model.put("name", RawUtility.getLoggedinUserName());
			model.put("role"," [" + RawUtility.getLoggedinUserRole() + "]");
		}
		return new ModelAndView("userProfile");
	}
}
