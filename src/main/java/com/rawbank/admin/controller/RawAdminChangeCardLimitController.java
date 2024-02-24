/**
 * Copyright 2022 Rawbank. All Rights Reserved.
 * 
 * Change Section: 
 * Programmer 			Date 
 * Krishna 				Aug 8, 2022
 * CSC admin portal Project Initial Coding.
 * 
 * Created By:
 * 
 * @author krishna
 * @since Aug 8, 2022
 * 
 */

package com.rawbank.admin.controller;

import java.io.IOException;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rawbank.admin.model.RawCardInfoModel;
import com.rawbank.admin.model.RawCustomerInfoModel;
import com.rawbank.admin.oracle.entity.RawCardLimitChange;
import com.rawbank.admin.oracle.repository.RawCardLimitChangeRepository;
import com.rawbank.admin.utility.RawUtility;

/**
 * @author krishna
 *
 */








@Controller
public class RawAdminChangeCardLimitController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RawAdminChangeCardLimitController.class);
	
	private static final String MESSAGE = "message";

	@Autowired
	private RawCardLimitChangeRepository rawCardLimitChangeRepository;
	
	@Autowired
	private MessageSource messageSource;

	@PostMapping("/changeCardLimit")
	public String changeCardstatusPage(@ModelAttribute("cardInfo") RawCardInfoModel rawCardInfoModel,
			RedirectAttributes redirectAttrs, Locale locale,
		      BindingResult result) throws IOException {

		LOGGER.info("Change card Limit Screen.. {} ", rawCardInfoModel);
		RawCustomerInfoModel rawCustomerInfoModel = new RawCustomerInfoModel();
		rawCustomerInfoModel.setCustomerNumber(rawCardInfoModel.getCustomerNumber());
		redirectAttrs.addFlashAttribute("name", RawUtility.getLoggedinUserName() + " [" +RawUtility.getLoggedinUserRole() + "]");
		redirectAttrs.addFlashAttribute("role", RawUtility.getLoggedinUserRole());
		RawCardLimitChange rawCardLimitChange = rawCardLimitChangeRepository.findBycscNumber(rawCardInfoModel.getCustCardNumber());
		if (rawCardLimitChange == null) {
			rawCardLimitChange = prepareRawCardLimitEntity(rawCardInfoModel);
			rawCardLimitChangeRepository.save(rawCardLimitChange);
			redirectAttrs.addFlashAttribute("customerInfo", rawCustomerInfoModel);
			return "redirect:/customerCardList"; 
		}else {
			redirectAttrs.addFlashAttribute(MESSAGE,
					messageSource.getMessage("msg.duplicate", new Object[] {}, locale) + ": ");
			return "error"; 
		}
	}

	private RawCardLimitChange prepareRawCardLimitEntity(RawCardInfoModel rawCardInfoModel) throws IOException {
		return new RawCardLimitChange(rawCardInfoModel);
	}

}
