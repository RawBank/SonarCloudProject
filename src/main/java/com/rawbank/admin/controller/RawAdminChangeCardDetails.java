package com.rawbank.admin.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RawAdminChangeCardDetails {

	private static final Logger LOGGER = LoggerFactory.getLogger(RawAdminChangeCardDetails.class);
	
	@PostMapping(path = "/changeCardDetail")
	public String changeCardDetails( RedirectAttributes redirectAttrs, Locale locale,
		      BindingResult result) {
//		
		
		LOGGER.info(" change card details ");
//		
	return  "redirect:/customerCardList";
	}

}
