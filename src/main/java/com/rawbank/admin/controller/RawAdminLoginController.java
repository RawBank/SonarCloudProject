/**
 * Copyright 2022 Rawbank. All Rights Reserved.
 * 
 * Change Section:
 * Programmer			Date
 * Krishna				Sep 15, 2022
 * Fixed critical SonarQube Issues.
 * 
 * Krishna				6/20/2022
 * CSC admin portal Project.
 * Initial Coding.
 * 
 * Created By:
 * @author krishna
 * @since Jun 20, 2022
 * 
 */
package com.rawbank.admin.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author krishna
 *
 */
@Controller
public class RawAdminLoginController {
	private static final String ERRORMSG = "errorMsg";

	@Autowired
	private MessageSource messageSource;

	@GetMapping("/language")
	public String login(Model model, @RequestParam(name = "lang") String lang) {
		
		return "login";
	}


	
	
	@GetMapping(value = "/login")
	public String login(Model model, String errorCode, String logout, Locale locale) {

		if (errorCode != null) {
			setLocalizedMessage(errorCode, model, locale, null);
		}

		if (logout != null) {
			setLocalizedMessage(errorCode, model, locale, logout);
		}
		
		return "login";
	}

	private void setLocalizedMessage(String errorCode, Model model, Locale locale, String logout) {
		if ("404".equals(errorCode)) {
			model.addAttribute(ERRORMSG, messageSource.getMessage("error.message.invalid", new Object[] {}, locale));
		} else if ("423".equals(errorCode)) {
			model.addAttribute(ERRORMSG, messageSource.getMessage("error.message.locked", new Object[] {}, locale));
		} else if ("403".equals(errorCode)) {
			model.addAttribute(ERRORMSG,
					messageSource.getMessage("error.message.unauthorized", new Object[] {}, locale));
		} else if ("422".equals(errorCode)) {
			model.addAttribute(ERRORMSG, messageSource.getMessage("error.message.pwd.wrong", new Object[] {}, locale));
		} else if ("303".equals(errorCode)) {
			model.addAttribute(ERRORMSG, messageSource.getMessage("error.message.ssl", new Object[] {}, locale));
		}else if ("412".equals(errorCode)) {
			model.addAttribute(ERRORMSG, messageSource.getMessage("error.message.session.expired", new Object[] {}, locale));
		}
		
		
		else if (logout != null) {
			model.addAttribute("logOutMsg", messageSource.getMessage("app.logOutMsg", new Object[] {}, locale));
		}
	}
}