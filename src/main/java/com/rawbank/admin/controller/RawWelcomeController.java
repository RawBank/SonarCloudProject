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

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import com.rawbank.admin.utility.RawUtility;

@Controller
public class RawWelcomeController {

	HttpSession httpSession = null;
	private static final Logger LOGGER = LoggerFactory.getLogger(RawWelcomeController.class);

	@GetMapping(value = { "/", "/home" })
	public String showWelcomePage(ModelMap model, HttpServletRequest request) {
		model.put("name", RawUtility.getLoggedinUserName() + " [" + RawUtility.getLoggedinUserRole() + "]");
        model.put("role", RawUtility.getLoggedinUserRole());
		// once connectivity is established , generate the session with the list of
		// countries
		//<input id='countries' type='hidden' value='${sessionScope.countries}'/>
		httpSession = request.getSession(true);

		String languageChoosen = LocaleContextHolder.getLocale().toString();

		LOGGER.info(" Loading countries based on  language choosen : {}", languageChoosen);
		final List<String> countries = RawUtility.getCountries(languageChoosen);
				 
		LOGGER.info("size of countries loaded in  the session attribute  countries : {}", countries.size());
		
		httpSession.setAttribute("countries", countries);

		return "/welcome";
	}
}
