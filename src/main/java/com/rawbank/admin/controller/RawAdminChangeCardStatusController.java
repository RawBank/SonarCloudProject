/**
 * Copyright 2022 Rawbank. All Rights Reserved.
 * 
 * Change Section:
 * Programmer			Date
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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rawbank.admin.bo.RawAccountBo;
import com.rawbank.admin.client.stubs.getAccountList.GetAccountResponse;
import com.rawbank.admin.config.RawAppConfig;
import com.rawbank.admin.model.RawCardInfoModel;
import com.rawbank.admin.model.RawCardInfoModelSecond;
import com.rawbank.admin.model.RawChangeStatusRequestModel;
import com.rawbank.admin.model.RawChangeStatusResponseModel;
import com.rawbank.admin.model.RawClientResponseModel;
import com.rawbank.admin.model.RawCustomerInfoModel;
import com.rawbank.admin.model.RawReloadDetails;
import com.rawbank.admin.model.RawTokenResponseModel;
import com.rawbank.admin.model.RawTransferP2PRequest;
import com.rawbank.admin.model.RawTransferP2PResponse;
import com.rawbank.admin.oracle.entity.RawCardStatusChange;
import com.rawbank.admin.oracle.entity.RawCardStatusCode;
import com.rawbank.admin.oracle.entity.RawChangeStatusCancel;
import com.rawbank.admin.oracle.repository.RawCardStatusChangeRepository;
import com.rawbank.admin.oracle.repository.RawCardStatusCodeRepository;
import com.rawbank.admin.service.RawChangeStatusCancelServiceImpl;
import com.rawbank.admin.service.RawConsumeCscApiServices;
import com.rawbank.admin.service.RawGetAccountServiceImpl;
import com.rawbank.admin.service.RawTokenManagement;
import com.rawbank.admin.service.RawTransfertP2PService;
import com.rawbank.admin.sqlserver.entity.RawClientCards;
import com.rawbank.admin.sqlserver.entity.RawClientDetails;
import com.rawbank.admin.sqlserver.entity.RawClientFinancials;
import com.rawbank.admin.sqlserver.repository.RawClientDetailsRepository;
import com.rawbank.admin.sqlserver.repository.RawClientFinancialsRepository;
import com.rawbank.admin.sqlserver.repository.RawClientRepository;
import com.rawbank.admin.utility.RawAccountTransform;
import com.rawbank.admin.utility.RawUtility;

/**
 * @author krishna
 *
 */
@Controller
public class RawAdminChangeCardStatusController {
	private static final Logger LOGGER = LoggerFactory.getLogger(RawAdminChangeCardStatusController.class);

	private static final String MESSAGE = "message";

	@Autowired
	private RawClientRepository rawClientRepository;

	@Autowired
	private RawGetAccountServiceImpl rawGetAccountServiceImpl;

	@Autowired
	private RawClientFinancialsRepository rawClientFinancialsRepository;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private RawTokenManagement rawTokenManagement;

	@Autowired
	private RawConsumeCscApiServices consumeCscApiServices;

	@Autowired
	private RawCardStatusCodeRepository rawCardStatusCodeRepository;

	@Autowired
	private RawCardStatusChangeRepository rawCardStatusChangeRepository;
	
	@Autowired
	private RawClientDetailsRepository rawClientDetailsRepository;
	
	@Autowired
	RawChangeStatusCancelServiceImpl rawChangeStatusCancelServiceImpl;
	
	@Autowired
	private RawTransfertP2PService rawTransfertP2PService;

	private String searchURLSwitch = "";

	@GetMapping(value = { "/searchCards", "/changeCardLimits", "/changeCardDetails", "/reloadMC" })
	public ModelAndView searchCards(ModelMap model, HttpServletRequest request) {
		LOGGER.info("Search Card Screen..");
		RawCustomerInfoModel rawCustomerInfoModel = new RawCustomerInfoModel();
		model.addAttribute("customerInfo", rawCustomerInfoModel);
		model.put("name", RawUtility.getLoggedinUserName() + " [" + RawUtility.getLoggedinUserRole() + "]");
		model.put("role", RawUtility.getLoggedinUserRole());
		List<RawCardStatusCode> codeCardActives = rawCardStatusCodeRepository.findByStatusIgnoreCase("Y");
		model.addAttribute("codeCardActives", codeCardActives);
		// login to get url for rediect on the customerCardList
		searchURLSwitch = request.getRequestURI();

		LOGGER.info("searchURLSwitch  {} ", searchURLSwitch);
		// modelattributes specialy for reload menu
		model.addAttribute("reloaddetails", new RawReloadDetails());
		return new ModelAndView("searchCards");
	}

	// customerCardList

	// @PostMapping(path = "/customerCardList")
	@RequestMapping(path = "/customerCardList", method = { RequestMethod.GET, RequestMethod.POST })
	public String showCustomerCardList(ModelMap model, @ModelAttribute("customerInfo") RawCustomerInfoModel customer,
			RedirectAttributes redirectAttrs, Locale locale, HttpServletRequest request, HttpSession session)
			throws JsonProcessingException {

		String clientNumber01 = "";
		List<RawCustomerInfoModel> customerlist = new ArrayList<>();
		RawTokenResponseModel rawTokenResponseModel = rawTokenManagement.generateCscToken();
		String customerNumber = customer.getCustomerNumber();
		//String customerNumber = customer.getCscNumber();
		
		LOGGER.info("  *********** customerNumber => " + customerNumber);

		List<GetAccountResponse> accountList = rawGetAccountServiceImpl.getAccountList(customerNumber);
		
		LOGGER.info("  *********** accountList => " + accountList);

		for (GetAccountResponse getAccountResponse : accountList) {
			// get accounts belong to the customer together with it branch

			if (getAccountResponse.getAccount() != null ) {

				String bankAccountNumber = getAccountResponse.getAccount().getAccountNumber()
						.getInternalFormatAccountOurBranch().getAccount() + getAccountResponse.getCheckKey();
           
				
				System.err.println("bankAccountNumber without Key " + getAccountResponse.getAccount().getAccountNumber()
						.getInternalFormatAccountOurBranch().getAccount());
				System.err.println("getCheckKey " + getAccountResponse.getCheckKey());
				
			  System.err.println("bankAccountNumber " + bankAccountNumber);
				final String maskStringBankAccount = RawUtility.maskString(bankAccountNumber, 3, 10, 'X');
				LOGGER.info("Bank Account Number: {}", maskStringBankAccount);
				LOGGER.info("Bank Account Number: {}", bankAccountNumber);

				List<RawClientFinancials> rawClientFinancials = rawClientFinancialsRepository
				 		.findByBankAccountNumber(bankAccountNumber);
				
				/*
				List<RawClientFinancials> rawClientFinancials = rawClientFinancialsRepository
						.findByClientNumber(customerNumber);
				*/
				
				LOGGER.info("  #############*********** customerNumber ****************############# => " + customerNumber);
				
				LOGGER.info("  *********** RawClientFinancials => " + rawClientFinancials);

				if (!rawClientFinancials.isEmpty()) {
					

					for (RawClientFinancials rawClientFinancial : rawClientFinancials) {
						clientNumber01 = rawClientFinancial.getClientNumber();
						
						final String maskStringClientNumber = RawUtility
								.maskString(rawClientFinancial.getClientNumber(), 2, 6, 'X');
						LOGGER.info("Client Number: {}", maskStringClientNumber);

						LOGGER.info("Client Number: {}", rawClientFinancial.getClientNumber());

						Optional<List<RawClientCards>> OptListOfRawClientCards = rawClientRepository
								.findByClientNumberOrParentClientNumber(rawClientFinancial.getClientNumber(),
										rawClientFinancial.getClientNumber());
						
						LOGGER.info("  *********** RawClientCards => " + OptListOfRawClientCards.get());

						if (OptListOfRawClientCards.isPresent()) {
							System.err.println("starting....");
							List<RawClientCards> ListOfRawClientCards = OptListOfRawClientCards.get();
							ListOfRawClientCards.stream().forEach(c -> LOGGER.info( " * " + c.toString()));

							for (RawClientCards rawClientCards : ListOfRawClientCards) {
								customerlist.add(prepareCustomerInfoModel(rawClientCards, rawClientFinancial,
										getAccountResponse));
								System.err.println("before filter");
								customerlist.stream()
										.forEach(rawCustomerInfoModel -> LOGGER.info(rawCustomerInfoModel.toString()));
							}
						}

					}
				}

			}
		}

		// filter only the cards with the status active and Temporary blocked
		List<RawCustomerInfoModel> collectcustomerlist = customerlist.stream()
				.filter(rawCustomerInfoModel -> ("active".equalsIgnoreCase(rawCustomerInfoModel.getCurrentCardStatus())
						|| "Temporary blocked".equalsIgnoreCase(rawCustomerInfoModel.getCurrentCardStatus())
						|| "InActive".equalsIgnoreCase(rawCustomerInfoModel.getCurrentCardStatus())
						|| "Blocked".equalsIgnoreCase(rawCustomerInfoModel.getCurrentCardStatus())
						)
						&& RawUtility.checkCardNotExpired(rawCustomerInfoModel.getExpiryDate()))
				.collect(Collectors.toList());
		System.err.println("after filter");
		collectcustomerlist.stream().forEach(rawCustomerInfoModel -> LOGGER.info(rawCustomerInfoModel.toString()));

		if (!collectcustomerlist.isEmpty()) {

			// https://www.onlinetutorialspoint.com/java8/java-8-how-to-remove-duplicates-from-list.html
			 //  below code is shown how to remove duplicate value in the  list of objects
			TreeSet<RawCustomerInfoModel> collectcustomerlistUnique = collectcustomerlist.stream().collect(Collectors
					.toCollection(() -> new TreeSet<>(Comparator.comparing(RawCustomerInfoModel::getCscNumber))));
			collectcustomerlistUnique.stream().forEach(G -> LOGGER.info("Unique value printed {} " , G.toString()));

			redirectAttrs.addFlashAttribute("customerInfoList", collectcustomerlistUnique);
			// no need to lookup the collect add the customer Number is unique for all
			// records
			if (searchURLSwitch.endsWith("changeCardDetails")) {
				//

				final RawClientResponseModel rawClientResponseModel = consumeCscApiServices
						.getClientDetails(rawTokenResponseModel, clientNumber01);

				redirectAttrs.addFlashAttribute("rawClientResponseModel", rawClientResponseModel);
			}

		} else {
			redirectAttrs.addFlashAttribute(MESSAGE,
					messageSource.getMessage("msg.noCards", new Object[] {}, locale) + ": " + customerNumber);
		}

		String reloadMessage = (String) session.getAttribute("reloadMessage01");
		redirectAttrs.addFlashAttribute("reloadMessage", reloadMessage);
		session.removeAttribute("reloadMessage01");
		return "redirect:/" + searchURLSwitch.split("/")[2].trim();
		
		//return "redirect:/" + searchURLSwitch;
	}

	private RawCustomerInfoModel prepareCustomerInfoModel(RawClientCards rawClientCards,
			RawClientFinancials rawClientFinancial, GetAccountResponse getAccountResponse) {

		RawAccountBo rawAccountBo = RawAccountTransform.getAccountBo(getAccountResponse);
		RawCustomerInfoModel rawCustomerInfoModel = new RawCustomerInfoModel();
		 
		Optional<RawClientDetails> rawClientDetailsOpt = rawClientDetailsRepository.findByClientNumber(rawClientCards.getClientNumber().trim());
		
		
		RawClientDetails rawClientDetails = rawClientDetailsOpt.get();
		rawCustomerInfoModel.setCustomerNumber(rawAccountBo.getCustomerNumber());
		rawCustomerInfoModel.setName(rawClientCards.getCustomerName());
		rawCustomerInfoModel.setCscNumber(rawClientCards.getCardNumber());
		rawCustomerInfoModel.setCscClientNumber(rawClientCards.getClientNumber());
		rawCustomerInfoModel.setCurrency(rawClientCards.getCardCurrency());
		rawCustomerInfoModel.setExpiryDate(rawClientCards.getExpiryDate());
		rawCustomerInfoModel.setBirthDate(rawClientDetails.getBirthDate());
		rawCustomerInfoModel.setPrivateTel(rawClientFinancial.getTelPrivate());
		String st = rawCardStatusChangeRepository
				.findFirstByCardNumberOrderByCardStatusChangeDateDesc(rawClientCards.getCardNumber()) != null
						? rawCardStatusChangeRepository
								.findFirstByCardNumberOrderByCardStatusChangeDateDesc(rawClientCards.getCardNumber())
								.getCardCurrenttStatus()
						: rawClientCards.getCardStatus();

		LOGGER.info("current status of card :  {}", st);

		rawCustomerInfoModel.setCurrentCardStatus(st);

		rawCustomerInfoModel.setCardLimit(rawClientFinancial.getLimit());
		rawCustomerInfoModel.setPhoneNumber(rawClientDetails.getMobileNumber());
		rawCustomerInfoModel.setCardCurrentBalance(rawClientFinancial.getCurrentBalance());
		rawCustomerInfoModel.setBankAccountNumber(rawAccountBo.getAccountNumber());
		rawCustomerInfoModel.setTotalDues(rawClientFinancial.getTotalDues());
		rawCustomerInfoModel.setBillingAccountBalance(rawAccountBo.getBillingAccountBalance());

		return rawCustomerInfoModel;
	}

	@PostMapping("/changeCardStatus")
	public String changeCardstatusPage(@ModelAttribute("cardInfo") RawCardInfoModel rawCardInfoModel,
			@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttrs,Model model) {

		LOGGER.info("File uploaded : {}", file.getOriginalFilename());
		
		//RawChangeStatusResponseModel getChangeCardStatusResp = new RawChangeStatusResponseModel();

		List<? extends GrantedAuthority> roleofUser = RawUtility
				.getRoleofUser(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		GrantedAuthority grantedAuthority = roleofUser.get(0);
		LOGGER.info(" {} ", grantedAuthority);
		try {
			// read and write the file to the selected location-
			String fileName = file.getOriginalFilename();
			byte[] bytes = file.getBytes();
			Path path = Paths.get(RawAppConfig.getUploadFilePath() + fileName);
			Files.write(path, bytes);

			LOGGER.info("Changecard status Screen.. {} ", rawCardInfoModel);
			//
			RawCustomerInfoModel rawCustomerInfoModel = new RawCustomerInfoModel();
			// this field is required to feel informations while redirection is done
			rawCustomerInfoModel.setCustomerNumber(rawCardInfoModel.getCustomerNumber());
			// create an object
			RawCardStatusChange rawCardStatusChange = new RawCardStatusChange();
			String status = "";

			RawTokenResponseModel rawTokenResponseModel = rawTokenManagement.generateCscToken();

			// consuming csc api to change status
			RawChangeStatusRequestModel rawChangeStatusRequestModel = new RawChangeStatusRequestModel(
					rawCardInfoModel.getCustCardNumber(), rawCardInfoModel.getCardStatus(),
					RawAppConfig.getCscinstitutionNumber());

			RawChangeStatusResponseModel rawChangeStatusResponseModel = new RawChangeStatusResponseModel();
			rawChangeStatusResponseModel = consumeCscApiServices.changeStatus(rawTokenResponseModel, rawChangeStatusRequestModel);
		
			LOGGER.info(" ========= CSC RESPONSE OBJECT =========== " + rawChangeStatusResponseModel);
			LOGGER.info(" ========= CSC RESPONSE OBJECT Message =========== " + rawChangeStatusResponseModel.getMessage());
			//

			status = rawCardStatusCodeRepository.findByNumCode(rawChangeStatusRequestModel.getStatus().trim())
					.getDescription();
			
			//Check csc status before update the same in DB
			String s = rawChangeStatusResponseModel.getMessage();
			String[] result = s.split("=>");
			LOGGER.info("===========   CSC splitted Message  ===========  "+ result[0]);
			if(result[0].trim().equalsIgnoreCase("Success")) {
				
				//1. do update of CSC card status in DB	    
				
				
				LOGGER.info("#############  Saving card new change status details in DB ");
				  RawClientCards rawClientCards = rawClientRepository
				  .findByCardNumber(rawCardInfoModel.getCustCardNumber().trim());
				  
				  rawClientCards.setCardStatus(status); // change and save in the db
				  rawCardStatusChange.setCardnewStatus(
				  rawCardStatusCodeRepository.findByNumCode(rawCardInfoModel.getCardStatus()).
				  getDescription());
				  rawCardStatusChange.setCardoldStatus(rawCardInfoModel.getCurrentCardStatus())
				  ; rawCardStatusChange.setCardStatusChangeDate(LocalDateTime.now());
				  rawCardStatusChange.setUserName(SecurityContextHolder.getContext().
				  getAuthentication().getName()); rawCardStatusChange.setValidator(null);
				  rawCardStatusChange.setChangeReason(rawCardInfoModel.getChangeReason());
				  rawCardStatusChange.setCardNumber(rawClientCards.getCardNumber());
				  rawCardStatusChange.setFileName(fileName);
				  rawCardStatusChange.setCardCurrenttStatus(
				  rawCardStatusCodeRepository.findByNumCode(rawCardInfoModel.getCardStatus()).
				  getDescription()); rawCardStatusChangeRepository.save(rawCardStatusChange);
				 
				  LOGGER.info("#############  Card updated change status details saved successfully in DB  ############ " + rawClientCards);
				  

				redirectAttrs.addFlashAttribute("customerInfo", rawCustomerInfoModel);

			}else{
				LOGGER.info("#############  Echec changement status carte " );
				//redirectAttrs.addFlashAttribute("messageError", "CSC =>" + s);
				
				//redirectAttrs.addFlashAttribute("message", "voir les logs");
				
				model.addAttribute("messageCallbackError", "Hello from Spring Boot Controller!");
				// return "redirect:/loadpendingcancelmc";
				
				//redirectAttrs.addFlashAttribute("customerInfo", rawCustomerInfoModel);
				//return "redirect:/customerCardList";
				//return "redirect:/loadpendingcancelmc";
				 
				 return "redirect:/customerCardList";
				
			}

			
			
			

		} catch (IOException e) {
			LOGGER.error("context", e);

		}
		//
		return "redirect:/customerCardList";
	}
	
	
	
	//Record request of card without provision and sent to n + 1
	@PostMapping("/changeCardStatusCancel")
	public String cancelChangeCardstatusPage(@ModelAttribute("cardInfo") RawCardInfoModelSecond rawCardInfoModelSecond,
			@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttrs) {

		LOGGER.info("File uploaded : {}", file.getOriginalFilename());
		
		//RawChangeStatusResponseModel getChangeCardStatusResp = new RawChangeStatusResponseModel();

		List<? extends GrantedAuthority> roleofUser = RawUtility
				.getRoleofUser(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		GrantedAuthority grantedAuthority = roleofUser.get(0);
		LOGGER.info(" {} ", grantedAuthority);
		try {
			// read and write the file to the selected location-
			String fileName = file.getOriginalFilename();
			byte[] bytes = file.getBytes();
			Path path = Paths.get(RawAppConfig.getUploadFilePath() + fileName);
			Files.write(path, bytes);

			LOGGER.info("Changecard status Screen.. {} ", rawCardInfoModelSecond);
			//
			
			RawChangeStatusCancel rawChangeStatusCancel = new RawChangeStatusCancel();
			rawChangeStatusCancel.setCardNumber(rawCardInfoModelSecond.getCustCardNumber());
			rawChangeStatusCancel.setCardnewStatus(rawCardInfoModelSecond.getCardStatus());
			rawChangeStatusCancel.setInstiutionNumber(RawAppConfig.getCscinstitutionNumber());
			rawChangeStatusCancel.setInitiator(SecurityContextHolder.getContext().getAuthentication().getName());
			rawChangeStatusCancel.setDateInitiator(RawUtility.getFormattedCurrentTimeStamp());
			rawChangeStatusCancel.setValidator(null);
			rawChangeStatusCancel.setDateValidation(null);
			rawChangeStatusCancel.setStatusOpCsc(null);
			rawChangeStatusCancel.setCompteCreditA(rawCardInfoModelSecond.getCreditA());
			rawChangeStatusCancel.setCompteDebitA(rawCardInfoModelSecond.getDebitA());
			rawChangeStatusCancel.setMotifAnnulation(rawCardInfoModelSecond.getChangeReason());
			rawChangeStatusCancel.setTxnReferenceCodeBankA(null);
			rawChangeStatusCancel.setTxnReferenceCodeBankB(null);
			rawChangeStatusCancel.setTxnAmountA(rawCardInfoModelSecond.getMontantTxnA());
			rawChangeStatusCancel.setTxnDevise(rawCardInfoModelSecond.getCurrency());
			rawChangeStatusCancel.setCustomerName(rawCardInfoModelSecond.getCustomerName());
			rawChangeStatusCancel.setCancelComment(null);
			rawChangeStatusCancel.setCancelEventStatus("RI");
			rawChangeStatusCancel.setCustomerNumber(rawCardInfoModelSecond.getCustomerNumber());
			rawChangeStatusCancel.setReqCancelId(RawUtility.generateRandomIdForCancelReq());
			rawChangeStatusCancel.setCardOldStatus(rawCardInfoModelSecond.getCardOldStatus());
			rawChangeStatusCancel.setTypeAnnulationCarte("CSP");
			rawChangeStatusCancel.setAgenceCompteCreditA(rawCardInfoModelSecond.getCodeAgenceCreditA());
			rawChangeStatusCancel.setAgenceCompteDebitA(rawCardInfoModelSecond.getCodeAgenceDebitA());
			rawChangeStatusCancel.setStatusOpAmplitudeA(null);
			rawChangeStatusCancel.setStatusOpAmplitudeB(null);
			
			
		
			
			LOGGER.info("rawChangeStatusCancel oject.. {} ", rawChangeStatusCancel);
			
			RawCustomerInfoModel rawCustomerInfoModel = new RawCustomerInfoModel();
			// this field is required to feel informations while redirection is done
			rawCustomerInfoModel.setCustomerNumber(rawCardInfoModelSecond.getCustomerNumber());
	
			rawChangeStatusCancelServiceImpl.saveChangeStatusCancelRecord(rawChangeStatusCancel);
			
			LOGGER.info(" ========= Cancel reauest saved and sent to N + 1 ");

			redirectAttrs.addFlashAttribute("customerInfo", rawCustomerInfoModel);

		} catch (IOException e) {
			LOGGER.error("context", e);

		}
		//
		return "redirect:/loadpendingcancelmcInitiator";
	}
	
	
	//Record request of card with provision and sent to n + 1
	@PostMapping("/changeCardStatusCancelWithProvision")
	public String cancelChangeCardstatusPageWithProvision(@ModelAttribute("cardInfo") RawCardInfoModelSecond rawCardInfoModelSecond,
			@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttrs) {

		LOGGER.info("File uploaded : {}", file.getOriginalFilename());
		
		//RawChangeStatusResponseModel getChangeCardStatusResp = new RawChangeStatusResponseModel();

		List<? extends GrantedAuthority> roleofUser = RawUtility
				.getRoleofUser(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		GrantedAuthority grantedAuthority = roleofUser.get(0);
		LOGGER.info(" {} ", grantedAuthority);
		try {
			// read and write the file to the selected location-
			String fileName = file.getOriginalFilename();
			byte[] bytes = file.getBytes();
			Path path = Paths.get(RawAppConfig.getUploadFilePath() + fileName);
			Files.write(path, bytes);

			LOGGER.info("Changecard status Screen.. {} ", rawCardInfoModelSecond);
			//
			
			RawChangeStatusCancel rawChangeStatusCancel = new RawChangeStatusCancel();
			rawChangeStatusCancel.setCardNumber(rawCardInfoModelSecond.getCustCardNumber());
			rawChangeStatusCancel.setCardnewStatus(rawCardInfoModelSecond.getCardStatus());
			rawChangeStatusCancel.setInstiutionNumber(RawAppConfig.getCscinstitutionNumber());
			rawChangeStatusCancel.setInitiator(SecurityContextHolder.getContext().getAuthentication().getName());
			rawChangeStatusCancel.setDateInitiator(RawUtility.getFormattedCurrentTimeStamp());
			rawChangeStatusCancel.setValidator(null);
			rawChangeStatusCancel.setDateValidation(null);
			rawChangeStatusCancel.setStatusOpCsc(null);
			rawChangeStatusCancel.setCompteCreditA(rawCardInfoModelSecond.getCreditA());
			rawChangeStatusCancel.setCompteDebitA(rawCardInfoModelSecond.getDebitA());
			rawChangeStatusCancel.setCompteDebitB(rawCardInfoModelSecond.getDebitB());
			rawChangeStatusCancel.setCompteCreditB(rawCardInfoModelSecond.getCreditB());
			rawChangeStatusCancel.setMotifAnnulation(rawCardInfoModelSecond.getChangeReason());
			rawChangeStatusCancel.setTxnReferenceCodeBankA(null);
			rawChangeStatusCancel.setTxnReferenceCodeBankB(null);
			rawChangeStatusCancel.setTxnAmountA(rawCardInfoModelSecond.getMontantTxnA());
			rawChangeStatusCancel.setTxnAmountB(rawCardInfoModelSecond.getMontantTxnB());
			rawChangeStatusCancel.setTxnDevise(rawCardInfoModelSecond.getCurrency());
			rawChangeStatusCancel.setCustomerName(rawCardInfoModelSecond.getCustomerName());
			rawChangeStatusCancel.setCancelComment(null);
			rawChangeStatusCancel.setCancelEventStatus("RI");
			rawChangeStatusCancel.setCustomerNumber(rawCardInfoModelSecond.getCustomerNumber());
			rawChangeStatusCancel.setReqCancelId(RawUtility.generateRandomIdForCancelReq());
			rawChangeStatusCancel.setCardOldStatus(rawCardInfoModelSecond.getCardOldStatus());
			rawChangeStatusCancel.setTypeAnnulationCarte("CAP");
			rawChangeStatusCancel.setAgenceCompteCreditA(rawCardInfoModelSecond.getCodeAgenceCreditA());
			rawChangeStatusCancel.setAgenceCompteDebitA(rawCardInfoModelSecond.getCodeAgenceDebitA());
			rawChangeStatusCancel.setAgenceCompteCreditB(rawCardInfoModelSecond.getCodeAgenceCreditB());
			rawChangeStatusCancel.setAgenceCompteDebitB(rawCardInfoModelSecond.getCodeAgenceDebitB());
			rawChangeStatusCancel.setStatusOpAmplitudeA(null);
			rawChangeStatusCancel.setStatusOpAmplitudeB(null);
		
			
			LOGGER.info("rawChangeStatusCancel oject.. {} ", rawChangeStatusCancel);
			
			RawCustomerInfoModel rawCustomerInfoModel = new RawCustomerInfoModel();
			// this field is required to feel informations while redirection is done
			rawCustomerInfoModel.setCustomerNumber(rawCardInfoModelSecond.getCustomerNumber());
	
			rawChangeStatusCancelServiceImpl.saveChangeStatusCancelRecord(rawChangeStatusCancel);
			
			LOGGER.info(" ========= Cancel reauest saved and sent to N + 1 ");

			redirectAttrs.addFlashAttribute("customerInfo", rawCustomerInfoModel);

		} catch (IOException e) {
			LOGGER.error("context", e);

		}
		//
		return "redirect:/loadpendingcancelmcInitiator";
	}
	
	
	//Approve request of card without provision
	@PostMapping("/changeCardStatusCancelApprove")
	public String cancelChangeCardstatusPageProcess(@ModelAttribute("cardCancelInfo") RawCardInfoModelSecond rawCardInfoModelSecond, RedirectAttributes redirectAttrs) {
		

		List<? extends GrantedAuthority> roleofUser = RawUtility
				.getRoleofUser(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		GrantedAuthority grantedAuthority = roleofUser.get(0);
		LOGGER.info(" {} ", grantedAuthority);
		LOGGER.info("Changecard status Screen.. {} ", rawCardInfoModelSecond);
		//
		
		RawCustomerInfoModel rawCustomerInfoModel = new RawCustomerInfoModel();
		// this field is required to feel informations while redirection is done
		rawCustomerInfoModel.setCustomerNumber(rawCardInfoModelSecond.getCustomerNumber());
		// create an object
		RawCardStatusChange rawCardStatusChange = new RawCardStatusChange();
		String status = "";

		RawTokenResponseModel rawTokenResponseModel = rawTokenManagement.generateCscToken();

		// consuming csc api to change status
		RawChangeStatusRequestModel rawChangeStatusRequestModel = new RawChangeStatusRequestModel(
				rawCardInfoModelSecond.getCustCardNumber(), rawCardInfoModelSecond.getCardStatus(),
				RawAppConfig.getCscinstitutionNumber());
		
		
		RawChangeStatusResponseModel rawChangeStatusResponseModel = new RawChangeStatusResponseModel();
		rawChangeStatusResponseModel = consumeCscApiServices.changeStatus(rawTokenResponseModel, rawChangeStatusRequestModel);
		/*
		String messageResponse = ""+consumeCscApiServices.changeStatus(rawTokenResponseModel, rawChangeStatusRequestModel);
		*/
		 LOGGER.info(" ========= CSC RESPONSE OBJECT =========== " + rawChangeStatusResponseModel);
		 LOGGER.info(" ========= CSC RESPONSE OBJECT Message =========== " + rawChangeStatusResponseModel.getMessage());
		//

		status = rawCardStatusCodeRepository.findByNumCode(rawChangeStatusRequestModel.getStatus().trim())
				.getDescription();
		
		
		//Check csc status before update the same in DB
		String s = rawChangeStatusResponseModel.getMessage();
		String[] result = s.split("=>");
		LOGGER.info("===========   CSC splitted Message  ===========  "+ result[0]);
		if(result[0].trim().equalsIgnoreCase("Success")) {
			//1. do update of CSC  card status in DB	    	
			//2. Call amplitude API for bank accounts settlment
			
			
			// update client cards in DB
			  RawClientCards rawClientCards = rawClientRepository
			  .findByCardNumber(rawCardInfoModelSecond.getCustCardNumber().trim());
			  
			  LOGGER.info(" ========= card  status new " + rawCardInfoModelSecond.getCardStatus());
			  LOGGER.info(" ========= card  Old status " + rawCardInfoModelSecond.getCardOldStatus());
			 
			  rawClientCards.setCardStatus(status); // change and save in the db
			  rawCardStatusChange.setCardnewStatus(
			  rawCardStatusCodeRepository.findByNumCode(rawCardInfoModelSecond.getCardStatus()).
			  getDescription());
			  rawCardStatusChange.setCardoldStatus(rawCardInfoModelSecond.getCardOldStatus())
			  ; rawCardStatusChange.setCardStatusChangeDate(LocalDateTime.now());
			  rawCardStatusChange.setUserName(SecurityContextHolder.getContext().
			  getAuthentication().getName()); rawCardStatusChange.setValidator(null);
			  rawCardStatusChange.setChangeReason(rawCardInfoModelSecond.getChangeReason());
			  rawCardStatusChange.setCardNumber(rawClientCards.getCardNumber());
			  rawCardStatusChange.setCardCurrenttStatus(
			  rawCardStatusCodeRepository.findByNumCode(rawCardInfoModelSecond.getCardStatus()).
			  getDescription()); rawCardStatusChangeRepository.save(rawCardStatusChange);
			  
			  
			  LOGGER.info(" ========= Card Status Change details saved ====  " + rawCardStatusChange);
			  
			  /* Update csc status, validator, date validation in case all following amplitude requests fail
		   		but in case amplitude req run then csc op status will be override */
		   		rawChangeStatusCancelServiceImpl.updateCancelRecordCscStatus(
		   			 rawCardInfoModelSecond.getReqCancelId()
					  ,SecurityContextHolder.getContext().getAuthentication().getName(), 
					  RawUtility.getFormattedCurrentTimeStamp(), 
					  "OK",
					  "PAA"
		   				);
			  
			  
			  
			// amplitude API ops//
			  LOGGER.info("starting Transfer P2P...");
			  
			  LOGGER.info(" ========= DEVISE " + rawCardInfoModelSecond.getCurrency());
			  
				RawTransferP2PRequest rawTransferP2PRequest = new RawTransferP2PRequest();
				rawTransferP2PRequest.setSrcAccount(rawCardInfoModelSecond.getCreditA());
				rawTransferP2PRequest.setSrcAgence(rawCardInfoModelSecond.getCodeAgenceCreditA());
				rawTransferP2PRequest.setDevise(rawCardInfoModelSecond.getCurrency());
				rawTransferP2PRequest.setDstAccount(rawCardInfoModelSecond.getDebitA());
				rawTransferP2PRequest.setDstAgence(rawCardInfoModelSecond.getCodeAgenceDebitA());
				rawTransferP2PRequest.setAmount(rawCardInfoModelSecond.getMontantTxnA());
				rawTransferP2PRequest.setMotif("Regularisation des ecritures comptables");
				
				final RawTransferP2PResponse postTransferP2P = rawTransfertP2PService.postTransferP2P(rawTransferP2PRequest);

				if (postTransferP2P == null) {
					
					LOGGER.error("==== Echec Ecriture Amplitude A  ===== => ");
					LOGGER.error(" Request API ecriture comptable A " + rawTransferP2PRequest);
					LOGGER.error(" Response API ecriture comptable A " + postTransferP2P);
					
					LOGGER.info("==== Update ecriture amplitude status A as Fail ===== ");
					//Update amplitude status A in DB as Fail
					  rawChangeStatusCancelServiceImpl.updateCancelRecordAmplitudeEcritureA(
							  rawCardInfoModelSecond.getReqCancelId(), 
							  "ECHEC",
							  "PAA");
					  
					  return "redirect:/loadpendingcancelmc";
					
				}else {
					
					
					if(postTransferP2P.getStatus().equalsIgnoreCase("000")) {
						
						LOGGER.error("============== P2P Transfer API ============== " + postTransferP2P.getStatus());
						LOGGER.error("============== P2P Transfer Bank Reference Number ============== " + postTransferP2P.getBnkrefno());
						LOGGER.error("============== P2P Transfer Msg Desc ============== " + postTransferP2P.getMsgdesc());
						
						LOGGER.info("==== Success  écriture amplitude A ===== " + "Status => "+ postTransferP2P.getStatus() + "Msg =>" + postTransferP2P.getMsgdesc());
						LOGGER.info(" Request ecriture comptable A " + rawTransferP2PRequest);
						LOGGER.info(" Response ecriture comptable A " + postTransferP2P);
		
						
						//Update amplitude status in DB as success
						  //update temp cancel table
						  rawChangeStatusCancelServiceImpl.updateCancelRecord(
								  rawCardInfoModelSecond.getReqCancelId()
								  ,SecurityContextHolder.getContext().getAuthentication().getName(), 
								  RawUtility.getFormattedCurrentTimeStamp(), 
								  "OK", 
								  postTransferP2P.getBnkrefno(), 
								  "OK", 
								  "RP",
								  rawCardInfoModelSecond.getCancelComment());
					
						  redirectAttrs.addFlashAttribute("message", "Annulation carte a été bien approuvé. Statut => " + postTransferP2P.getStatus() + " Msg => " + postTransferP2P.getMsgdesc());
						  return "redirect:/loadpendingcancelmc";
						
					}else if(postTransferP2P.getStatus().equalsIgnoreCase("196")){
						
						LOGGER.error("==== Echec  écriture amplitude A ===== " + "Status => "+ postTransferP2P.getStatus() + "errorMsg =>" + postTransferP2P.getMsgdesc());
						LOGGER.error("====  Request Ecriture Amplitude A ===== => "+ rawTransferP2PRequest);
						LOGGER.error("====  Response Ecriture Amplitude A ===== => "+ postTransferP2P);

						LOGGER.info("==== Update ecriture amplitude status A as Fail ===== ");
						//Update amplitude status A in DB as Fail
						  rawChangeStatusCancelServiceImpl.updateCancelRecordAmplitudeEcritureA(
								  rawCardInfoModelSecond.getReqCancelId(), 
								  "ECHEC",
								  "PAA");
						  
						  redirectAttrs.addFlashAttribute("messageError", "Echec Ecriture Amplitude. Status => " + postTransferP2P.getStatus() + " Msg =>"+  postTransferP2P.getMsgdesc());
						  return "redirect:/loadpendingcancelmc";
					}
					
				}
			  
			
		}else if(result[0].equalsIgnoreCase("Failure")) {
			
		
			 redirectAttrs.addFlashAttribute("messageError", "CSC =>" + s);
			 return "redirect:/loadpendingcancelmc";
		}

			redirectAttrs.addFlashAttribute("customerInfo", rawCustomerInfoModel);
			redirectAttrs.addFlashAttribute("messageError", "CSC =>" + s);
			return "redirect:/loadpendingcancelmc";
	}
	
	
	//Approve request of card with provision
	@PostMapping("/changeCardStatusCancelApproveCardWithProvision")
	public String cancelChangeCardstatusPageProcessCardWithProvision(@ModelAttribute("cardCancelInfo") RawCardInfoModelSecond rawCardInfoModelSecond, 
			RedirectAttributes redirectAttrs) {
	
		List<? extends GrantedAuthority> roleofUser = RawUtility
				.getRoleofUser(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		GrantedAuthority grantedAuthority = roleofUser.get(0);
		LOGGER.info(" {} ", grantedAuthority);
		LOGGER.info("Changecard status Screen.. {} ", rawCardInfoModelSecond);
		//
		
		
		
		RawCustomerInfoModel rawCustomerInfoModel = new RawCustomerInfoModel();
		// this field is required to feel informations while redirection is done
		rawCustomerInfoModel.setCustomerNumber(rawCardInfoModelSecond.getCustomerNumber());
		// create an object
		RawCardStatusChange rawCardStatusChange = new RawCardStatusChange();
		String status = "";

		RawTokenResponseModel rawTokenResponseModel = rawTokenManagement.generateCscToken();

		// consuming csc api to change status
		RawChangeStatusRequestModel rawChangeStatusRequestModel = new RawChangeStatusRequestModel(
				rawCardInfoModelSecond.getCustCardNumber(), rawCardInfoModelSecond.getCardStatus(),
				RawAppConfig.getCscinstitutionNumber());
		
		/*
		consumeCscApiServices.changeStatus(rawTokenResponseModel, rawChangeStatusRequestModel);
		*/
		
		RawChangeStatusResponseModel rawChangeStatusResponseModel = new RawChangeStatusResponseModel();
		rawChangeStatusResponseModel = consumeCscApiServices.changeStatus(rawTokenResponseModel, rawChangeStatusRequestModel);
		
		LOGGER.info(" ========= CSC RESPONSE OBJECT =========== " + rawChangeStatusResponseModel);
		LOGGER.info(" ========= CSC RESPONSE OBJECT Message =========== " + rawChangeStatusResponseModel.getMessage());

		status = rawCardStatusCodeRepository.findByNumCode(rawChangeStatusRequestModel.getStatus().trim())
				.getDescription();
		
		//Check csc status before update the same in DB
		String s = rawChangeStatusResponseModel.getMessage();
		String[] result = s.split("=>");
		LOGGER.info("===========   CSC splitted Message  ===========  "+ result[0]);
		if(result[0].trim().equalsIgnoreCase("Success")) {
			
			//1. do update of CSC card status in DB	    	
			//2. Call amplitude API for bank accounts settlment
			
			
			// update client cards in DB
			RawClientCards rawClientCards = rawClientRepository
				  .findByCardNumber(rawCardInfoModelSecond.getCustCardNumber().trim());
				  
			LOGGER.info(" ========= card  status new " + rawCardInfoModelSecond.getCardStatus());
			LOGGER.info(" ========= card  Old status " + rawCardInfoModelSecond.getCardOldStatus());
				  
			rawClientCards.setCardStatus(status); // change and save in the db
			rawCardStatusChange.setCardnewStatus(
			rawCardStatusCodeRepository.findByNumCode(rawCardInfoModelSecond.getCardStatus()).
			getDescription());
			rawCardStatusChange.setCardoldStatus(rawCardInfoModelSecond.getCardOldStatus())
			; rawCardStatusChange.setCardStatusChangeDate(LocalDateTime.now());
			rawCardStatusChange.setUserName(SecurityContextHolder.getContext().
			getAuthentication().getName()); rawCardStatusChange.setValidator(null);
			rawCardStatusChange.setChangeReason(rawCardInfoModelSecond.getChangeReason());
			rawCardStatusChange.setCardNumber(rawClientCards.getCardNumber());
			rawCardStatusChange.setCardCurrenttStatus(
			rawCardStatusCodeRepository.findByNumCode(rawCardInfoModelSecond.getCardStatus()).
			getDescription()); rawCardStatusChangeRepository.save(rawCardStatusChange);
			
			/* Update csc status, validator, date validation in case all following amplitude requests fail
			but in case amplitude req run then csc op status will be override */
			rawChangeStatusCancelServiceImpl.updateCancelRecordCscStatus(
				 rawCardInfoModelSecond.getReqCancelId()
				  ,SecurityContextHolder.getContext().getAuthentication().getName(), 
				  RawUtility.getFormattedCurrentTimeStamp(), 
				  "OK",
				  "PAAB"
					);
			
			
			//amplitude API ops//
			  LOGGER.info("starting Transfer P2P of first amplitude setlment");
			  
			  LOGGER.info(" ========= DEVISE " + rawCardInfoModelSecond.getCurrency());
			  
			  //First ecriture comptable
				RawTransferP2PRequest rawTransferP2PRequest = new RawTransferP2PRequest();
				rawTransferP2PRequest.setSrcAccount(rawCardInfoModelSecond.getCreditA());
				rawTransferP2PRequest.setSrcAgence(rawCardInfoModelSecond.getCodeAgenceCreditA());
				rawTransferP2PRequest.setDevise(rawCardInfoModelSecond.getCurrency());
				rawTransferP2PRequest.setDstAccount(rawCardInfoModelSecond.getDebitA());
				rawTransferP2PRequest.setDstAgence(rawCardInfoModelSecond.getCodeAgenceDebitA());
				rawTransferP2PRequest.setAmount(rawCardInfoModelSecond.getMontantTxnA());
				rawTransferP2PRequest.setMotif("Regularisation des ecritures comptables");
				final RawTransferP2PResponse postTransferP2P = rawTransfertP2PService.postTransferP2P(rawTransferP2PRequest);
				
				//Second ecriture comptable
				RawTransferP2PRequest rawTransferP2PRequest2 = new RawTransferP2PRequest();
				rawTransferP2PRequest2.setSrcAccount(rawCardInfoModelSecond.getCreditB());
				rawTransferP2PRequest2.setSrcAgence(rawCardInfoModelSecond.getCodeAgenceCreditB());
				rawTransferP2PRequest2.setDevise(rawCardInfoModelSecond.getCurrency());
				rawTransferP2PRequest2.setDstAccount(rawCardInfoModelSecond.getDebitB());
				rawTransferP2PRequest2.setDstAgence(rawCardInfoModelSecond.getCodeAgenceDebitB());
				rawTransferP2PRequest2.setAmount(rawCardInfoModelSecond.getMontantTxnB());
				rawTransferP2PRequest2.setMotif("Regularisation des ecritures comptables");
				final RawTransferP2PResponse postTransferP2P2 = rawTransfertP2PService.postTransferP2P(rawTransferP2PRequest2);

				if (postTransferP2P == null && postTransferP2P2 == null) {
					
					LOGGER.error("==== Echec Ecriture Amplitude AB  ===== => ");
					LOGGER.error(" Request  ecriture comptable A " + rawTransferP2PRequest);
					LOGGER.error(" Request  ecriture comptable B " + rawTransferP2PRequest2);
					LOGGER.error(" Response  ecriture comptable A " + postTransferP2P);
					LOGGER.error(" Response  ecriture comptable B " + postTransferP2P2);
					
					
					LOGGER.info("==== Update ecriture amplitude status A B as Fail ===== ");
					//Update amplitude status A and status B in DB as Fail
					  rawChangeStatusCancelServiceImpl.updateCancelRecordAmplitudeEcritureAetB(
							  rawCardInfoModelSecond.getReqCancelId(),
							  "ECHEC", 
							  "ECHEC",
							  "PAAB");
					
					redirectAttrs.addFlashAttribute("messageError", "Echec Ecriture Amplitude AB lors de la communication avec amplitude pour passation des écritures => Veuiller voir les logs");
					 return "redirect:/loadpendingcancelmc";
					
				}else if(postTransferP2P == null && postTransferP2P2 != null){
					
					//Update amplitude status ecriture B as successfull but amplitude status ecriture A as fail
					if(postTransferP2P2.getStatus().equalsIgnoreCase("000")) {
						LOGGER.info("============== Ecriture B P2P Transfer API ============== " + postTransferP2P2.getStatus());
						LOGGER.info("============== Ecriture B P2P Transfer Bank Reference Number ============== " + postTransferP2P2.getBnkrefno());
						LOGGER.info("============== Ecriture B P2P Transfer Msg Desc ============== " + postTransferP2P2.getMsgdesc());
						LOGGER.info("==== Success  écriture amplitude B ===== " + "Status => "+ postTransferP2P2.getStatus() + "Msg =>" + postTransferP2P2.getMsgdesc());
						LOGGER.info(" Request  ecriture comptable B " + rawTransferP2PRequest2);
						LOGGER.info(" Response  ecriture comptable B " + postTransferP2P2);
						
						
						//Update in DB Amplitude only A failed for cancel approval MC card with provision
						 rawChangeStatusCancelServiceImpl.updateCancelRecordWithProvision(
								  rawCardInfoModelSecond.getReqCancelId()
								  ,SecurityContextHolder.getContext().getAuthentication().getName(), 
								  RawUtility.getFormattedCurrentTimeStamp(), 
								  "OK", 
								  "", 
								  postTransferP2P2.getBnkrefno(),
								  "ECHEC", 
								  "OK", 
								  "PAA",
								  rawCardInfoModelSecond.getCancelComment());
						  
						  redirectAttrs.addFlashAttribute("messagePartResult", "Ecriture amplitude B success mais Ecriture Amplitude A échec. Msg A =>" + " vide " + "\\n Msg B =>" + postTransferP2P2.getMsgdesc());
						  return "redirect:/loadpendingcancelmc";
						
						
					//Update amplitude status ecriture for both A and B as fail
					}else if(postTransferP2P2.getStatus().equalsIgnoreCase("196")) {
						LOGGER.error("==== Echec  écriture amplitude A ===== " + "Status => "+ " vide " + "errorMsg =>" +" vide ");
						LOGGER.error("==== Echec  écriture amplitude B ===== " + "Status => "+ postTransferP2P2.getStatus() + "errorMsg =>" + postTransferP2P2.getMsgdesc());
						
						LOGGER.error("====  Request Ecriture Amplitude A ===== => "+ " vide ");
						LOGGER.error("====  Response Ecriture Amplitude A ===== => "+ " vide ");
						LOGGER.error("====  Request Ecriture Amplitude B ===== => "+ rawTransferP2PRequest2);
						LOGGER.error("====  Response Ecriture Amplitude B ===== => "+ postTransferP2P2);
						
						LOGGER.info("==== Update ecriture amplitude status A B as Fail ===== ");
						//Update amplitude status A and status B in DB as Fail
						  rawChangeStatusCancelServiceImpl.updateCancelRecordAmplitudeEcritureAetB(
								  rawCardInfoModelSecond.getReqCancelId(),
								  "ECHEC", 
								  "ECHEC",
								  "PAAB");
						  
						  
						  redirectAttrs.addFlashAttribute("messageError", "Echec Ecriture Amplitude AB. ErrorMsgMsg Ecriture A =>"+  " vide " + "\\n ErrorMsgMsg Ecriture B "+ postTransferP2P2.getMsgdesc());
						  return "redirect:/loadpendingcancelmc";
						
					}else {
						//Update amplitude status ecriture for both A and B as fail
						//Print Message error on the screen and the rest in logs to track issue
						LOGGER.error("==== Echec  écriture amplitude A ===== " + "Status => "+ " vide " + "errorMsg =>" + " vide ");
						LOGGER.error("==== Echec  écriture amplitude B ===== " + "Status => "+ postTransferP2P2.getStatus() + "errorMsg =>" + postTransferP2P2.getMsgdesc());
						
						LOGGER.error("====  Request Ecriture Amplitude A ===== => "+ " vide ");
						LOGGER.error("====  Response Ecriture Amplitude A ===== => "+ " vide ");
						LOGGER.error("====  Request Ecriture Amplitude B ===== => "+ rawTransferP2PRequest2);
						LOGGER.error("====  Response Ecriture Amplitude B ===== => "+ postTransferP2P2);
						
						LOGGER.info("==== Update ecriture amplitude status A B as Fail ===== ");
						//Update amplitude status A and status B in DB as Fail
						  rawChangeStatusCancelServiceImpl.updateCancelRecordAmplitudeEcritureAetB(
								  rawCardInfoModelSecond.getReqCancelId(),
								  "ECHEC", 
								  "ECHEC",
								  "PAAB");
						  
						  
						  redirectAttrs.addFlashAttribute("messageError", "Echec Ecriture Amplitude AB. ErrorMsgMsg Ecriture A =>"+  " vide " + "\\n ErrorMsgMsg Ecriture B "+ postTransferP2P2.getMsgdesc());
						  return "redirect:/loadpendingcancelmc";
					}
					
				}else if(postTransferP2P != null && postTransferP2P2 == null){
					
					//Update amplitude status ecriture B as successfull but amplitude status ecriture A as fail
					if(postTransferP2P.getStatus().equalsIgnoreCase("000")) {
						LOGGER.info("============== Ecriture A P2P Transfer API ============== " + postTransferP2P.getStatus());
						LOGGER.info("============== Ecriture A P2P Transfer Bank Reference Number ============== " + postTransferP2P.getBnkrefno());
						LOGGER.info("============== Ecriture A P2P Transfer Msg Desc ============== " + postTransferP2P.getMsgdesc());
						LOGGER.info("==== Success  écriture amplitude A ===== " + "Status => "+ postTransferP2P.getStatus() + "Msg =>" + postTransferP2P.getMsgdesc());
						LOGGER.info(" Request  ecriture comptable A " + rawTransferP2PRequest);
						LOGGER.info(" Response  ecriture comptable A " + postTransferP2P);
						
						//Update in DB Amplitude only B failed for cancel approval MC card with provision
						 rawChangeStatusCancelServiceImpl.updateCancelRecordWithProvision(
								  rawCardInfoModelSecond.getReqCancelId()
								  ,SecurityContextHolder.getContext().getAuthentication().getName(), 
								  RawUtility.getFormattedCurrentTimeStamp(), 
								  "OK", 
								  postTransferP2P.getBnkrefno(), 
								  "",
								  "OK", 
								  "ECHEC", 
								  "PAB",
								  rawCardInfoModelSecond.getCancelComment());
						  
						  redirectAttrs.addFlashAttribute("messagePartResult", "Ecriture amplitude A success mais Ecriture Amplitude B échec. Msg A =>" + postTransferP2P.getMsgdesc() + "\\n Msg B =>" + " vide ");
						  return "redirect:/loadpendingcancelmc";
						
						
					//Update amplitude status ecriture for both A and B as fail
					}else if(postTransferP2P.getStatus().equalsIgnoreCase("196")) {
						LOGGER.error("==== Echec  écriture amplitude A ===== " + "Status => "+ postTransferP2P.getStatus() + "errorMsg =>" + postTransferP2P.getMsgdesc());
						LOGGER.error("==== Echec  écriture amplitude B ===== " + "Status => "+ " vide " + "errorMsg =>" + " vide ");
						
						LOGGER.error("====  Request Ecriture Amplitude A ===== => "+ rawTransferP2PRequest);
						LOGGER.error("====  Response Ecriture Amplitude A ===== => "+ postTransferP2P);
						LOGGER.error("====  Request Ecriture Amplitude B ===== => "+ " vide ");
						LOGGER.error("====  Response Ecriture Amplitude B ===== => "+ " vide ");
						
						LOGGER.info("==== Update ecriture amplitude status A B as Fail ===== ");
						//Update amplitude status A and status B in DB as Fail
						  rawChangeStatusCancelServiceImpl.updateCancelRecordAmplitudeEcritureAetB(
								  rawCardInfoModelSecond.getReqCancelId(),
								  "ECHEC", 
								  "ECHEC",
								  "PAAB");
						  
						  
						  redirectAttrs.addFlashAttribute("messageError", "Echec Ecriture Amplitude AB. ErrorMsgMsg Ecriture A =>"+  postTransferP2P.getMsgdesc() + "\\n ErrorMsgMsg Ecriture B "+ " vide ");
						  return "redirect:/loadpendingcancelmc";
						
					}else {
						//Update amplitude status ecriture for both A and B as fail
						//Print Message error on the screen and the rest in logs to track issue
						LOGGER.error("==== Echec  écriture amplitude A ===== " + "Status => "+ postTransferP2P.getStatus() + "errorMsg =>" + postTransferP2P.getMsgdesc());
						LOGGER.error("==== Echec  écriture amplitude B ===== " + "Status => "+ " vide " + "errorMsg =>" + " vide ");
						
						LOGGER.error("====  Request Ecriture Amplitude A ===== => "+ rawTransferP2PRequest);
						LOGGER.error("====  Response Ecriture Amplitude A ===== => "+ postTransferP2P);
						LOGGER.error("====  Request Ecriture Amplitude B ===== => "+ " vide ");
						LOGGER.error("====  Response Ecriture Amplitude B ===== => "+ " vide ");
						
						LOGGER.info("==== Update ecriture amplitude status A B as Fail ===== ");
						//Update amplitude status A and status B in DB as Fail
						  rawChangeStatusCancelServiceImpl.updateCancelRecordAmplitudeEcritureAetB(
								  rawCardInfoModelSecond.getReqCancelId(),
								  "ECHEC", 
								  "ECHEC",
								  "PAAB");
						  
						  
						  redirectAttrs.addFlashAttribute("messageError", "Echec Ecriture Amplitude AB. ErrorMsgMsg Ecriture A =>"+  postTransferP2P.getMsgdesc() + "\\n ErrorMsgMsg Ecriture B "+ " vide ");
						  return "redirect:/loadpendingcancelmc";
					}
					
				}else{
					
					
					if(postTransferP2P.getStatus().equalsIgnoreCase("000") && postTransferP2P2.getStatus().equalsIgnoreCase("000")) {
						
						LOGGER.info("============== Ecriture A P2P Transfer API ============== " + postTransferP2P.getStatus());
						LOGGER.info("============== Ecriture A P2P Transfer Bank Reference Number ============== " + postTransferP2P.getBnkrefno());
						LOGGER.info("============== Ecriture A P2P Transfer Msg Desc ============== " + postTransferP2P.getMsgdesc());
						
						LOGGER.info("============== Ecriture B P2P Transfer API ============== " + postTransferP2P2.getStatus());
						LOGGER.info("============== Ecriture B P2P Transfer Bank Reference Number ============== " + postTransferP2P2.getBnkrefno());
						LOGGER.info("============== Ecriture B P2P Transfer Msg Desc ============== " + postTransferP2P2.getMsgdesc());
						
						LOGGER.info("==== Success  écriture amplitude A ===== " + "Status => "+ postTransferP2P.getStatus() + "Msg =>" + postTransferP2P.getMsgdesc());
						LOGGER.info("==== Success  écriture amplitude B ===== " + "Status => "+ postTransferP2P2.getStatus() + "Msg =>" + postTransferP2P2.getMsgdesc());
						
						LOGGER.info(" Request  ecriture comptable A " + rawTransferP2PRequest);
						LOGGER.info(" Request  ecriture comptable B " + rawTransferP2PRequest2);
						LOGGER.info(" Response  ecriture comptable A " + postTransferP2P);
						LOGGER.info(" Response  ecriture comptable B " + postTransferP2P2);
						
						//Update amplitude status in DB as success
						  rawChangeStatusCancelServiceImpl.updateCancelRecordWithProvision(
								  rawCardInfoModelSecond.getReqCancelId()
								  ,SecurityContextHolder.getContext().getAuthentication().getName(), 
								  RawUtility.getFormattedCurrentTimeStamp(), 
								  "OK", 
								  postTransferP2P.getBnkrefno(), 
								  postTransferP2P2.getBnkrefno(),
								  "OK", 
								  "OK", 
								  "RP",
								  rawCardInfoModelSecond.getCancelComment());
						  
						  redirectAttrs.addFlashAttribute("message", "Ecriture amplitude AB faites avec succès. Msg A =>" + postTransferP2P.getMsgdesc() + "\\n Msg B =>" + postTransferP2P2.getMsgdesc());
						  return "redirect:/loadpendingcancelmc";
					
						
					}else if(postTransferP2P.getStatus().equalsIgnoreCase("196") && postTransferP2P2.getStatus().equalsIgnoreCase("196")){
						LOGGER.error("==== Echec  écriture amplitude A ===== " + "Status => "+ postTransferP2P.getStatus() + "errorMsg =>" + postTransferP2P.getMsgdesc());
						LOGGER.error("==== Echec  écriture amplitude B ===== " + "Status => "+ postTransferP2P2.getStatus() + "errorMsg =>" + postTransferP2P2.getMsgdesc());
						
						LOGGER.error("====  Request Ecriture Amplitude A ===== => "+ rawTransferP2PRequest);
						LOGGER.error("====  Response Ecriture Amplitude A ===== => "+ postTransferP2P);
						LOGGER.error("====  Request Ecriture Amplitude B ===== => "+ rawTransferP2PRequest2);
						LOGGER.error("====  Response Ecriture Amplitude B ===== => "+ postTransferP2P2);
						
						LOGGER.info("==== Update ecriture amplitude status A B as Fail ===== ");
						//Update amplitude status A and status B in DB as Fail
						  rawChangeStatusCancelServiceImpl.updateCancelRecordAmplitudeEcritureAetB(
								  rawCardInfoModelSecond.getReqCancelId(),
								  "ECHEC", 
								  "ECHEC",
								  "PAAB");
						  
						  
						  redirectAttrs.addFlashAttribute("messageError", "Echec Ecriture Amplitude AB. ErrorMsgMsg Ecriture A =>"+  postTransferP2P.getMsgdesc() + "\\n ErrorMsgMsg Ecriture B "+ postTransferP2P2.getMsgdesc());
						  return "redirect:/loadpendingcancelmc";
					}
										
				}
			  
			
		}else if(result[0].equalsIgnoreCase("Failure")) {
			
			redirectAttrs.addFlashAttribute("messageError", "CSC =>" + s);
			
			return "redirect:/loadpendingcancelmc";
			
		}

		
		redirectAttrs.addFlashAttribute("messageError", "CSC =>" + s);
		redirectAttrs.addFlashAttribute("customerInfo", rawCustomerInfoModel);
		//
		return "redirect:/loadpendingcancelmc";
	}
	
	
	
	//Controller retry Amplitude ecriture premiere
	@PostMapping("/retryTxnAmplitudeA")
	public String retryTxnAmplitudeAPageProcess(@ModelAttribute("cardCancelInfo") RawCardInfoModelSecond rawCardInfoModelSecond, RedirectAttributes redirectAttrs) {
		List<? extends GrantedAuthority> roleofUser = RawUtility.getRoleofUser(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		GrantedAuthority grantedAuthority = roleofUser.get(0);
		LOGGER.info(" {} ", grantedAuthority);
		LOGGER.info("Changecard status Screen.. {} ", rawCardInfoModelSecond);
		//
		
		RawCustomerInfoModel rawCustomerInfoModel = new RawCustomerInfoModel();
		// this field is required to feel informations while redirection is done
		rawCustomerInfoModel.setCustomerNumber(rawCardInfoModelSecond.getCustomerNumber());
		
		
			// amplitude API ops//
			  LOGGER.info("starting Transfer RETRY Ecriture Amplitude A P2P...");
			  LOGGER.info(" ========= DEVISE RETRY Ecriture Amplitude A " + rawCardInfoModelSecond.getCurrency());
			  
				RawTransferP2PRequest rawTransferP2PRequest = new RawTransferP2PRequest();
				rawTransferP2PRequest.setSrcAccount(rawCardInfoModelSecond.getCreditA());
				rawTransferP2PRequest.setSrcAgence(rawCardInfoModelSecond.getCodeAgenceCreditA());
				rawTransferP2PRequest.setDevise(rawCardInfoModelSecond.getCurrency());
				rawTransferP2PRequest.setDstAccount(rawCardInfoModelSecond.getDebitA());
				rawTransferP2PRequest.setDstAgence(rawCardInfoModelSecond.getCodeAgenceDebitA());
				rawTransferP2PRequest.setAmount(rawCardInfoModelSecond.getMontantTxnA());
				rawTransferP2PRequest.setMotif("Regularisation des ecritures comptables");
				
				final RawTransferP2PResponse postTransferP2P = rawTransfertP2PService.postTransferP2P(rawTransferP2PRequest);

				if (postTransferP2P == null) {
						LOGGER.error("==== Echec Retry Ecriture Amplitude A  ===== => ");
						LOGGER.error("==== Retry Request Ecriture Amplitude A ===== => "+ rawTransferP2PRequest);
						LOGGER.error("==== Retry Response Ecriture Amplitude A ===== => "+ postTransferP2P);
						
						redirectAttrs.addFlashAttribute("messageError", "Echec Retry Ecriture Amplitude A lors de la communication  avec amplitude pour passation des écritures => Veuiller voir les logs");
						return "redirect:/loadpendingcancelmc";
				}else {
					if(postTransferP2P.getStatus().equalsIgnoreCase("000")) {
						LOGGER.error("==== Success Retry Ecriture Amplitude A  ===== => ");
						LOGGER.info("============== RETRY A P2P Transfer API ============== " + postTransferP2P.getStatus());
						LOGGER.info("============== RETRY A P2P Transfer Bank Reference Number ============== " + postTransferP2P.getBnkrefno());
						LOGGER.info("============== RETRY A P2P Transfer Msg Desc ============== " + postTransferP2P.getMsgdesc());
						LOGGER.info("==== Retry Request Ecriture Amplitude A ===== => "+ rawTransferP2PRequest);
						LOGGER.info("==== Retry Response Ecriture Amplitude A ===== => "+ postTransferP2P);
						
						//Update amplitude status in DB as success//update temp cancel table
						  rawChangeStatusCancelServiceImpl.updateCancelRecordAmplitudeEcritureAetBankRefCodeA(
								  rawCardInfoModelSecond.getReqCancelId(),
								  postTransferP2P.getBnkrefno(),
								  "OK", 
								  "RP");
					
						  redirectAttrs.addFlashAttribute("message", "Retry Ecriture amplitude A faites avec succès " + "Msg =>" + postTransferP2P.getMsgdesc());
						  return "redirect:/loadpendingcancelmc";
						
					}else if(postTransferP2P.getStatus().equalsIgnoreCase("196")){
						
						LOGGER.error("==== Echec Retry écriture amplitude A ===== " + "Status => "+ postTransferP2P.getStatus() + "errorMsg =>" + postTransferP2P.getMsgdesc());
						LOGGER.error("==== Retry Request Ecriture Amplitude A ===== => "+ rawTransferP2PRequest);
						LOGGER.error("==== Retry Response Ecriture Amplitude A ===== => "+ postTransferP2P);
						
						//Update amplitude status A in DB as Fail
						LOGGER.info("==== Update amplitude status A as Fail ===== ");
						  rawChangeStatusCancelServiceImpl.updateCancelRecordAmplitudeEcritureA(
								  rawCardInfoModelSecond.getReqCancelId(), 
								  "ECHEC",
								  "PAA");
						  
						  redirectAttrs.addFlashAttribute("message", "Echec Retry Ecriture amplitude A avec Status => " + postTransferP2P.getStatus() + " ErrorMsg => " + postTransferP2P.getMsgdesc());
						  return "redirect:/loadpendingcancelmc";
					}
				}
			  
		redirectAttrs.addFlashAttribute("customerInfo", rawCustomerInfoModel);
		
		return "redirect:/loadpendingcancelmc";
	}
	
	
	
	//Controller retry Amplitude ecriture deuxieme
		@PostMapping("/retryTxnAmplitudeB")
		public String retryTxnAmplitudeBPageProcess(@ModelAttribute("cardCancelInfo") RawCardInfoModelSecond rawCardInfoModelSecond, RedirectAttributes redirectAttrs) {
			List<? extends GrantedAuthority> roleofUser = RawUtility.getRoleofUser(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
			GrantedAuthority grantedAuthority = roleofUser.get(0);
			LOGGER.info(" {} ", grantedAuthority);
			LOGGER.info("Changecard status Screen.. {} ", rawCardInfoModelSecond);
			//
			
			RawCustomerInfoModel rawCustomerInfoModel = new RawCustomerInfoModel();
			// this field is required to feel informations while redirection is done
			rawCustomerInfoModel.setCustomerNumber(rawCardInfoModelSecond.getCustomerNumber());
			
			
				// amplitude API ops//
				  LOGGER.info("starting Transfer RETRY B P2P...");
				  LOGGER.info(" ========= DEVISE RETRY B " + rawCardInfoModelSecond.getCurrency());
				  
					RawTransferP2PRequest rawTransferP2PRequest2 = new RawTransferP2PRequest();
					rawTransferP2PRequest2.setSrcAccount(rawCardInfoModelSecond.getCreditB());
					rawTransferP2PRequest2.setSrcAgence(rawCardInfoModelSecond.getCodeAgenceCreditB());
					rawTransferP2PRequest2.setDevise(rawCardInfoModelSecond.getCurrency());
					rawTransferP2PRequest2.setDstAccount(rawCardInfoModelSecond.getDebitB());
					rawTransferP2PRequest2.setDstAgence(rawCardInfoModelSecond.getCodeAgenceDebitB());
					rawTransferP2PRequest2.setAmount(rawCardInfoModelSecond.getMontantTxnB());
					rawTransferP2PRequest2.setMotif("Regularisation des ecritures comptables");
					
					final RawTransferP2PResponse postTransferP2P2 = rawTransfertP2PService.postTransferP2P(rawTransferP2PRequest2);

					if (postTransferP2P2 == null) {
						LOGGER.error("==== Echec Retry Ecriture Amplitude B  ===== => ");
						LOGGER.error("RETRY AMPLITUDE ECRITURE B  Request  ...." + rawTransferP2PRequest2);
						LOGGER.error("RETRY AMPLITUDE ECRITURE B  Response  ...." + postTransferP2P2);
						
						redirectAttrs.addFlashAttribute("messageError", "Echec Retry Ecriture Amplitude B lors de la communication  avec amplitude pour passation des écritures => Veuiller voir les logs");
						return "redirect:/loadpendingcancelmc";
						 
					}else {
						if(postTransferP2P2.getStatus().equalsIgnoreCase("000")) {
							LOGGER.info("==== Success Retry Ecriture Amplitude A  ===== => ");
							LOGGER.info("============== RETRY AMPLITUDE ECRITURE B P2P Transfer API ============== " + postTransferP2P2.getStatus());
							LOGGER.info("============== RETRY AMPLITUDE ECRITURE B P2P Transfer Bank Reference Number ============== " + postTransferP2P2.getBnkrefno());
							LOGGER.info("============== RETRY AMPLITUDE ECRITURE B P2P Transfer Msg Desc ============== " + postTransferP2P2.getMsgdesc());
							LOGGER.info("RETRY AMPLITUDE ECRITURE B  Request  ...." + rawTransferP2PRequest2);
							LOGGER.info("RETRY AMPLITUDE ECRITURE B  Response  ...." + postTransferP2P2);
							
							//Update amplitude status in DB as success//update temp cancel table
							  rawChangeStatusCancelServiceImpl.updateCancelRecordAmplitudeEcritureBetBankRefCodeB(
									  rawCardInfoModelSecond.getReqCancelId(),
									  postTransferP2P2.getBnkrefno(),
									  "OK", 
									  "RP");
							  
							  redirectAttrs.addFlashAttribute("message", "Retry Ecriture amplitude B faites avec succès " + "Msg =>" + postTransferP2P2.getMsgdesc());
							  return "redirect:/loadpendingcancelmc";
						
							
						}else if(postTransferP2P2.getStatus().equalsIgnoreCase("196")){
							
							LOGGER.error("==== Echec Retry écriture amplitude B ===== " + "Status => "+ postTransferP2P2.getStatus() + "errorMsg =>" + postTransferP2P2.getMsgdesc());
							LOGGER.error("==== Retry Request Ecriture Amplitude B ===== => "+ rawTransferP2PRequest2);
							LOGGER.error("==== Retry Response Ecriture Amplitude B ===== => "+ postTransferP2P2);
							
							//Update amplitude status A in DB as Fail
							LOGGER.info("==== Update amplitude status B as Fail ===== ");
							  rawChangeStatusCancelServiceImpl.updateCancelRecordAmplitudeEcritureB(
									  rawCardInfoModelSecond.getReqCancelId(), 
									  "ECHEC",
									  "PAB");
							  
							  redirectAttrs.addFlashAttribute("messageError", "Echec Retry Ecriture Amplitude B. Status => " + postTransferP2P2.getStatus() + " ErrorMsg =>"+  postTransferP2P2.getMsgdesc());
							  return "redirect:/loadpendingcancelmc";
						}
					}
				  
			redirectAttrs.addFlashAttribute("customerInfo", rawCustomerInfoModel);
			
			return "redirect:/loadpendingcancelmc";
		}
		
		
		
		
		
		// retryTxnAmplitudeAB
		@PostMapping("/retryTxnAmplitudeAB")
		public String retryTxnAmplitudeABPageProcess(@ModelAttribute("cardCancelInfo") RawCardInfoModelSecond rawCardInfoModelSecond, RedirectAttributes redirectAttrs) {
			
			List<? extends GrantedAuthority> roleofUser = RawUtility
					.getRoleofUser(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
			GrantedAuthority grantedAuthority = roleofUser.get(0);
			LOGGER.info(" {} ", grantedAuthority);
			LOGGER.info("Changecard status Screen.. {} ", rawCardInfoModelSecond);
			//
			
			
			
			RawCustomerInfoModel rawCustomerInfoModel = new RawCustomerInfoModel();
			// this field is required to feel informations while redirection is done
			rawCustomerInfoModel.setCustomerNumber(rawCardInfoModelSecond.getCustomerNumber());
			

				//amplitude API ops//
				  LOGGER.info("starting Transfer P2P of first amplitude setlment");
				  
				  LOGGER.info(" ========= DEVISE " + rawCardInfoModelSecond.getCurrency());
				  
				  //First ecriture comptable
					RawTransferP2PRequest rawTransferP2PRequest = new RawTransferP2PRequest();
					rawTransferP2PRequest.setSrcAccount(rawCardInfoModelSecond.getCreditA());
					rawTransferP2PRequest.setSrcAgence(rawCardInfoModelSecond.getCodeAgenceCreditA());
					rawTransferP2PRequest.setDevise(rawCardInfoModelSecond.getCurrency());
					rawTransferP2PRequest.setDstAccount(rawCardInfoModelSecond.getDebitA());
					rawTransferP2PRequest.setDstAgence(rawCardInfoModelSecond.getCodeAgenceDebitA());
					rawTransferP2PRequest.setAmount(rawCardInfoModelSecond.getMontantTxnA());
					rawTransferP2PRequest.setMotif("Regularisation des ecritures comptables");
					final RawTransferP2PResponse postTransferP2P = rawTransfertP2PService.postTransferP2P(rawTransferP2PRequest);
					
					//Second ecriture comptable
					RawTransferP2PRequest rawTransferP2PRequest2 = new RawTransferP2PRequest();
					rawTransferP2PRequest2.setSrcAccount(rawCardInfoModelSecond.getCreditB());
					rawTransferP2PRequest2.setSrcAgence(rawCardInfoModelSecond.getCodeAgenceCreditB());
					rawTransferP2PRequest2.setDevise(rawCardInfoModelSecond.getCurrency());
					rawTransferP2PRequest2.setDstAccount(rawCardInfoModelSecond.getDebitB());
					rawTransferP2PRequest2.setDstAgence(rawCardInfoModelSecond.getCodeAgenceDebitB());
					rawTransferP2PRequest2.setAmount(rawCardInfoModelSecond.getMontantTxnB());
					rawTransferP2PRequest2.setMotif("Regularisation des ecritures comptables");
					final RawTransferP2PResponse postTransferP2P2 = rawTransfertP2PService.postTransferP2P(rawTransferP2PRequest2);

					if (postTransferP2P == null && postTransferP2P2 == null) {
						LOGGER.error("==== Echec Retry Ecriture Amplitude AB  ===== => ");
						LOGGER.error("Retry Request  ecriture comptable A " + rawTransferP2PRequest);
						LOGGER.error("Retry Request  ecriture comptable B " + rawTransferP2PRequest2);
						LOGGER.error("Retry Response  ecriture comptable A " + postTransferP2P);
						LOGGER.error("Retry Response  ecriture comptable B " + postTransferP2P2);
						
						 redirectAttrs.addFlashAttribute("messageError", "Echec Retry Ecriture Amplitude AB lors de la communication avec amplitude pour passation des écritures => Veuiller voir les logs");
						 return "redirect:/loadpendingcancelmc";
						
						
						
					}else if(postTransferP2P == null && postTransferP2P2 != null){
						
						//Update amplitude status ecriture B as successfull but amplitude status ecriture A as fail
						if(postTransferP2P2.getStatus().equalsIgnoreCase("000")) {
							LOGGER.info("============== Ecriture 2 P2P Transfer API ============== " + postTransferP2P2.getStatus());
							LOGGER.info("============== Ecriture 2 P2P Transfer Bank Reference Number ============== " + postTransferP2P2.getBnkrefno());
							LOGGER.info("============== Ecriture 2 P2P Transfer Msg Desc ============== " + postTransferP2P2.getMsgdesc());
							LOGGER.info("==== Success  écriture amplitude B ===== " + "Status => "+ postTransferP2P2.getStatus() + "Msg =>" + postTransferP2P2.getMsgdesc());
							LOGGER.info(" Request  ecriture comptable B " + rawTransferP2PRequest2);
							LOGGER.info(" Response  ecriture comptable B " + postTransferP2P2);
							
							
							 rawChangeStatusCancelServiceImpl.updateCancelRecordWithProvisionRetry(
									  rawCardInfoModelSecond.getReqCancelId()
									  ,SecurityContextHolder.getContext().getAuthentication().getName(), 
									  RawUtility.getFormattedCurrentTimeStamp(), 
									  "", 
									  postTransferP2P2.getBnkrefno(),
									  "ECHEC", 
									  "OK", 
									  "PAA",
									  rawCardInfoModelSecond.getCancelComment());
							  
							  redirectAttrs.addFlashAttribute("messagePartResult", "Ecriture amplitude B success mais Ecriture Amplitude A échec. Msg A =>" + " vide "+ "\\n Msg B =>" + postTransferP2P2.getMsgdesc());
							  return "redirect:/loadpendingcancelmc";
							
							
						//Update amplitude status ecriture for both A and B as fail
						}else if(postTransferP2P2.getStatus().equalsIgnoreCase("196")) {
							LOGGER.error("==== Echec  écriture amplitude A ===== " + "Status => "+ " vide " + "errorMsg =>" + " vide ");
							LOGGER.error("==== Echec  écriture amplitude B ===== " + "Status => "+ postTransferP2P2.getStatus() + "errorMsg =>" + postTransferP2P2.getMsgdesc());
							
							LOGGER.error("====  Request Ecriture Amplitude A ===== => "+ " vide ");
							LOGGER.error("====  Response Ecriture Amplitude A ===== => "+ " vide ");
							LOGGER.error("====  Request Ecriture Amplitude B ===== => "+ rawTransferP2PRequest2);
							LOGGER.error("====  Response Ecriture Amplitude B ===== => "+ postTransferP2P2);
							
							LOGGER.info("==== Update ecriture amplitude status A B as Fail ===== ");
							//Update amplitude status A and status B in DB as Fail
							  rawChangeStatusCancelServiceImpl.updateCancelRecordAmplitudeEcritureAetB(
									  rawCardInfoModelSecond.getReqCancelId(),
									  "ECHEC", 
									  "ECHEC",
									  "PAAB");
							  
							  
							  redirectAttrs.addFlashAttribute("messageError", "Echec Ecriture Amplitude AB. ErrorMsgMsg Ecriture A =>"+  " vide " + "\\n ErrorMsgMsg Ecriture B "+ postTransferP2P2.getMsgdesc());
							  return "redirect:/loadpendingcancelmc";
							
						}else {
							//Update amplitude status ecriture for both A and B as fail
							//Print Message error on the screen and the rest in logs to track issue
							LOGGER.error("==== Echec  écriture amplitude A ===== " + "Status => "+ " vide " + "errorMsg =>" + " vide ");
							LOGGER.error("==== Echec  écriture amplitude B ===== " + "Status => "+ postTransferP2P2.getStatus() + "errorMsg =>" + postTransferP2P2.getMsgdesc());
							
							LOGGER.error("====  Request Ecriture Amplitude A ===== => "+ rawTransferP2PRequest);
							LOGGER.error("====  Response Ecriture Amplitude A ===== => "+ postTransferP2P);
							LOGGER.error("====  Request Ecriture Amplitude B ===== => "+ rawTransferP2PRequest2);
							LOGGER.error("====  Response Ecriture Amplitude B ===== => "+ postTransferP2P2);
							
							LOGGER.info("==== Update ecriture amplitude status A B as Fail ===== ");
							//Update amplitude status A and status B in DB as Fail
							  rawChangeStatusCancelServiceImpl.updateCancelRecordAmplitudeEcritureAetB(
									  rawCardInfoModelSecond.getReqCancelId(),
									  "ECHEC", 
									  "ECHEC",
									  "PAAB");
							  
							  
							  redirectAttrs.addFlashAttribute("messageError", "Echec Ecriture Amplitude AB. ErrorMsgMsg Ecriture A =>"+  " vide " + "\\n ErrorMsgMsg Ecriture B "+ postTransferP2P2.getMsgdesc());
							  return "redirect:/loadpendingcancelmc";
						}
						
						
						
					}else if(postTransferP2P != null && postTransferP2P2 == null){
						
						//Update amplitude status ecriture B as successfull but amplitude status ecriture A as fail
						if(postTransferP2P.getStatus().equalsIgnoreCase("000")) {
							LOGGER.info("============== Ecriture A P2P Transfer API ============== " + postTransferP2P.getStatus());
							LOGGER.info("============== Ecriture A P2P Transfer Bank Reference Number ============== " + postTransferP2P.getBnkrefno());
							LOGGER.info("============== Ecriture A P2P Transfer Msg Desc ============== " + postTransferP2P.getMsgdesc());
							LOGGER.info("==== Success  écriture amplitude A ===== " + "Status => "+ postTransferP2P.getStatus() + "Msg =>" + postTransferP2P.getMsgdesc());
							LOGGER.info(" Request  ecriture comptable A " + rawTransferP2PRequest);
							LOGGER.info(" Response  ecriture comptable A " + postTransferP2P);
							
							
							 rawChangeStatusCancelServiceImpl.updateCancelRecordWithProvisionRetry(
									  rawCardInfoModelSecond.getReqCancelId()
									  ,SecurityContextHolder.getContext().getAuthentication().getName(), 
									  RawUtility.getFormattedCurrentTimeStamp(),
									  postTransferP2P.getBnkrefno(), 
									  "",
									  "OK", 
									  "ECHEC", 
									  "PAB",
									  rawCardInfoModelSecond.getCancelComment());
							  
							  redirectAttrs.addFlashAttribute("messagePartResult", "Ecriture amplitude A success mais Ecriture Amplitude B échec. Msg A =>" + postTransferP2P.getMsgdesc() + "\\n Msg B =>" + " vide ");
							  return "redirect:/loadpendingcancelmc";
							
							
						//Update amplitude status ecriture for both A and B as fail
						}else if(postTransferP2P.getStatus().equalsIgnoreCase("196")) {
							LOGGER.error("==== Echec  écriture amplitude A ===== " + "Status => "+ postTransferP2P.getStatus() + "errorMsg =>" + postTransferP2P.getMsgdesc());
							LOGGER.error("==== Echec  écriture amplitude B ===== " + "Status => "+ " vide " + "errorMsg =>" + " vide ");
							
							LOGGER.error("====  Request Ecriture Amplitude A ===== => "+ rawTransferP2PRequest);
							LOGGER.error("====  Response Ecriture Amplitude A ===== => "+ postTransferP2P);
							LOGGER.error("====  Request Ecriture Amplitude B ===== => "+ " vide ");
							LOGGER.error("====  Response Ecriture Amplitude B ===== => "+ " vide ");
							
							LOGGER.info("==== Update ecriture amplitude status A B as Fail ===== ");
							//Update amplitude status A and status B in DB as Fail
							  rawChangeStatusCancelServiceImpl.updateCancelRecordAmplitudeEcritureAetB(
									  rawCardInfoModelSecond.getReqCancelId(),
									  "ECHEC", 
									  "ECHEC",
									  "PAAB");
							  
							  
							  redirectAttrs.addFlashAttribute("messageError", "Echec Ecriture Amplitude AB. ErrorMsgMsg Ecriture A =>"+  postTransferP2P.getMsgdesc() + "\\n ErrorMsgMsg Ecriture B "+ " vide ");
							  return "redirect:/loadpendingcancelmc";
							
						}else {
							//Update amplitude status ecriture for both A and B as fail
							//Print Message error on the screen and the rest in logs to track issue
							LOGGER.error("==== Echec  écriture amplitude A ===== " + "Status => "+ postTransferP2P.getStatus() + "errorMsg =>" + postTransferP2P.getMsgdesc());
							LOGGER.error("==== Echec  écriture amplitude B ===== " + "Status => "+ " vide " + "errorMsg =>" + " vide ");
							
							LOGGER.error("====  Request Ecriture Amplitude A ===== => "+ rawTransferP2PRequest);
							LOGGER.error("====  Response Ecriture Amplitude A ===== => "+ postTransferP2P);
							LOGGER.error("====  Request Ecriture Amplitude B ===== => "+ " vide ");
							LOGGER.error("====  Response Ecriture Amplitude B ===== => "+ " vide ");
							
							LOGGER.info("==== Update ecriture amplitude status A B as Fail ===== ");
							//Update amplitude status A and status B in DB as Fail
							  rawChangeStatusCancelServiceImpl.updateCancelRecordAmplitudeEcritureAetB(
									  rawCardInfoModelSecond.getReqCancelId(),
									  "ECHEC", 
									  "ECHEC",
									  "PAAB");
							  
							  
							  redirectAttrs.addFlashAttribute("messageError", "Echec Ecriture Amplitude AB. ErrorMsgMsg Ecriture A =>"+  postTransferP2P.getMsgdesc() + "\\n ErrorMsgMsg Ecriture B "+ " vide ");
							  return "redirect:/loadpendingcancelmc";
						}
						
						
					}else {
						
						
						if(postTransferP2P.getStatus().equalsIgnoreCase("000") && postTransferP2P2.getStatus().equalsIgnoreCase("000")) {
						
							LOGGER.info("==== Success Retry écriture amplitude A ===== " + "Status => "+ postTransferP2P.getStatus() + "Msg =>" + postTransferP2P.getMsgdesc());
							LOGGER.info("==== Success Retry écriture amplitude B ===== " + "Status => "+ postTransferP2P2.getStatus() + "Msg =>" + postTransferP2P2.getMsgdesc());
							
							LOGGER.info("============== Retry Ecriture A P2P Transfer  ============== " + postTransferP2P.getStatus());
							LOGGER.info("============== Retry Ecriture A P2P Transfer Bank Reference Number ============== " + postTransferP2P.getBnkrefno());
							LOGGER.info("============== Retry Ecriture A P2P Transfer Msg Desc ============== " + postTransferP2P.getMsgdesc());
							
							LOGGER.info("============== Retry Ecriture B P2P Transfer  ============== " + postTransferP2P2.getStatus());
							LOGGER.info("============== Retry Ecriture B P2P Transfer Bank Reference Number ============== " + postTransferP2P2.getBnkrefno());
							LOGGER.info("============== Retry Ecriture B P2P Transfer Msg Desc ============== " + postTransferP2P2.getMsgdesc());
							
							LOGGER.info("Retry Request ecriture comptable A " + rawTransferP2PRequest);
							LOGGER.info("Retry Request ecriture comptable B " + rawTransferP2PRequest2);
							LOGGER.info("Retry Response ecriture comptable A " + postTransferP2P);
							LOGGER.info("Retry Response ecriture comptable B " + postTransferP2P2);
							
							//Update amplitude status in DB as success
							  rawChangeStatusCancelServiceImpl.updateCancelRecordWithProvisionRetry(
									  rawCardInfoModelSecond.getReqCancelId()
									  ,SecurityContextHolder.getContext().getAuthentication().getName(), 
									  RawUtility.getFormattedCurrentTimeStamp(),
									  postTransferP2P.getBnkrefno(), 
									  postTransferP2P2.getBnkrefno(),
									  "OK", 
									  "OK", 
									  "RP",
									  rawCardInfoModelSecond.getCancelComment());
						
							  redirectAttrs.addFlashAttribute("message", "Retry Ecriture amplitude AB faites avec succès. Msg A =>" + postTransferP2P.getMsgdesc() + "\\n Msg B =>" + postTransferP2P2.getMsgdesc());
							  return "redirect:/loadpendingcancelmc";
							
						}else if(postTransferP2P.getStatus().equalsIgnoreCase("196") && postTransferP2P2.getStatus().equalsIgnoreCase("196")){
							
							LOGGER.error("==== Echec Retry écriture amplitude AB ===== " + "Status => "+ postTransferP2P.getStatus() + "errorMsg =>" + postTransferP2P.getMsgdesc());
							LOGGER.error("==== Echec Retry écriture amplitude B ===== " + "Status => "+ postTransferP2P2.getStatus() + "errorMsg =>" + postTransferP2P2.getMsgdesc());
							LOGGER.error("==== Retry Request Ecriture Amplitude A ===== => "+ rawTransferP2PRequest);
							LOGGER.error("==== Retry Response Ecriture Amplitude A ===== => "+ postTransferP2P);
							LOGGER.error("==== Retry Request Ecriture Amplitude B ===== => "+ rawTransferP2PRequest2);
							LOGGER.error("==== Retry Response Ecriture Amplitude B ===== => "+ postTransferP2P2);
							//Update amplitude status A and status B in DB as Fail
							LOGGER.info("==== Update amplitude status AB as Fail ===== ");
							  rawChangeStatusCancelServiceImpl.updateCancelRecordAmplitudeEcritureAetB(
									  rawCardInfoModelSecond.getReqCancelId(),
									  "ECHEC", 
									  "ECHEC",
									  "PAAB");
							  
							  redirectAttrs.addFlashAttribute("messageError", "Echec Retry Ecriture Amplitude AB. ErrorMsgMsg Ecriture A =>"+  postTransferP2P.getMsgdesc() + "\\n ErrorMsgMsg Ecriture B "+ postTransferP2P2.getMsgdesc());
							  return "redirect:/loadpendingcancelmc";
						}

					}
				  
			
			redirectAttrs.addFlashAttribute("customerInfo", rawCustomerInfoModel);
			//
			return "redirect:/loadpendingcancelmc";
		}
		
		
		
		
		
		
		
		
		
		//Reject request of card without provision
		@PostMapping("/changeCardStatusCancelReject")
		public String cancelChangeCardstatusPageProcessReject(@ModelAttribute("cardCancelInfo") RawCardInfoModelSecond rawCardInfoModelSecond, 
				RedirectAttributes redirectAttrs) {
			

			List<? extends GrantedAuthority> roleofUser = RawUtility
					.getRoleofUser(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
			GrantedAuthority grantedAuthority = roleofUser.get(0);
			LOGGER.info(" {} ", grantedAuthority);
			LOGGER.info("Changecard status Screen.. {} ", rawCardInfoModelSecond);
			//
	
				  
			/* Update reject on two fields Approvateur / date approbation / status request */
			 rawChangeStatusCancelServiceImpl.updateCancelRecordAsRejected(
			   	 rawCardInfoModelSecond.getReqCancelId()
					,SecurityContextHolder.getContext().getAuthentication().getName(), 
					 RawUtility.getFormattedCurrentTimeStamp(), 
					 rawCardInfoModelSecond.getCancelComment(),
					 "RR"
			   		);
			 
			 LOGGER.info("Rejection of was successful ");
				  
			 return "redirect:/loadpendingcancelmc";
				
		}
		
		
		//Reject request of card with provision
				@PostMapping("/changeCardStatusCancelApproveCardWithProvisionReject")
				public String cancelChangeCardstatusPageProcessWithProvisionReject(@ModelAttribute("cardCancelInfo") RawCardInfoModelSecond rawCardInfoModelSecond, 
						RedirectAttributes redirectAttrs) {
					

					List<? extends GrantedAuthority> roleofUser = RawUtility
							.getRoleofUser(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
					GrantedAuthority grantedAuthority = roleofUser.get(0);
					LOGGER.info(" {} ", grantedAuthority);
					LOGGER.info("Changecard status Screen.. {} ", rawCardInfoModelSecond);
					//
			
						  
					/* Update reject on two fields Approvateur / date approbation / status request */
					 rawChangeStatusCancelServiceImpl.updateCancelRecordAsRejected(
					   	 rawCardInfoModelSecond.getReqCancelId()
							,SecurityContextHolder.getContext().getAuthentication().getName(), 
							 RawUtility.getFormattedCurrentTimeStamp(), 
							 rawCardInfoModelSecond.getCancelComment(),
							 "RR"
					   		);
					 
					 LOGGER.info("Rejection of was successful ");
						  
					 return "redirect:/loadpendingcancelmc";
						
				}
	

}