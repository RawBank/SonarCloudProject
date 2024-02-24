package com.rawbank.admin.controller;


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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rawbank.admin.bo.RawAccountBo;
import com.rawbank.admin.client.stubs.getAccountList.GetAccountResponse;
import com.rawbank.admin.model.RawClientResponseModel;
import com.rawbank.admin.model.RawCustomerInfoModel;
import com.rawbank.admin.model.RawReloadDetails;
import com.rawbank.admin.model.RawTokenResponseModel;
import com.rawbank.admin.oracle.entity.RawCardStatusCode;
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

@Controller
public class RawHomePrepaidCardsIndividuel {
	
	private static final Logger logger = LoggerFactory.getLogger(RawHomePrepaidCardsIndividuel.class);
	

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
	
	
	@GetMapping(value = { "/homePrepaidCardIndividuel"})
	public ModelAndView searchCards(ModelMap model, HttpServletRequest request) {
		logger.info("Search Card Screen..");
		RawCustomerInfoModel rawCustomerInfoModel = new RawCustomerInfoModel();
		model.addAttribute("customerInfo", rawCustomerInfoModel);
		model.put("name", RawUtility.getLoggedinUserName() + " [" + RawUtility.getLoggedinUserRole() + "]");
		model.put("role", RawUtility.getLoggedinUserRole());
		List<RawCardStatusCode> codeCardActives = rawCardStatusCodeRepository.findByStatusIgnoreCase("Y");
		model.addAttribute("codeCardActives", codeCardActives);
		// login to get url for rediect on the customerCardList
		searchURLSwitch = request.getRequestURI();

		logger.info("searchURLSwitch  {} ", searchURLSwitch);
		// modelattributes specialy for reload menu
		model.addAttribute("reloaddetails", new RawReloadDetails());
	
		return new ModelAndView("home_prepaid_cards_individuel");
	}
	
	
	
	
	@RequestMapping(path = "/customerCardListMCPrepaid", method = { RequestMethod.GET, RequestMethod.POST })
	public String showCustomerCardList(ModelMap model, @ModelAttribute("customerInfo") RawCustomerInfoModel customer,
			RedirectAttributes redirectAttrs, Locale locale, HttpServletRequest request, HttpSession session)
			throws JsonProcessingException {

		String clientNumber01 = "";
		List<RawCustomerInfoModel> customerlist = new ArrayList<>();
		RawTokenResponseModel rawTokenResponseModel = rawTokenManagement.generateCscToken();
		String cscCustomerNumber = customer.getCustomerNumber();
		//String customerNumber = customer.getCscNumber();
		
		logger.info("  *********** customerNumber => " + cscCustomerNumber);

		List<GetAccountResponse> accountList = rawGetAccountServiceImpl.getAccountList(cscCustomerNumber);
		
		logger.info("  *********** accountList => " + accountList);

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
				logger.info("Bank Account Number: {}", maskStringBankAccount);
				logger.info("Bank Account Number: {}", bankAccountNumber);
				
				/*
				//Load details using client number
				List<RawClientFinancials> rawClientFinancials = rawClientFinancialsRepository
				 		.findByBankAccountNumber(bankAccountNumber);
				*/
				
				//Load details using csc number
				List<RawClientFinancials> rawClientFinancials = rawClientFinancialsRepository
						.findByClientNumber(cscCustomerNumber);
				
				
				logger.info("  #############*********** customerNumber ****************############# => " + cscCustomerNumber);
				
				logger.info("  *********** RawClientFinancials => " + rawClientFinancials);

				if (!rawClientFinancials.isEmpty()) {
					

					for (RawClientFinancials rawClientFinancial : rawClientFinancials) {
						clientNumber01 = rawClientFinancial.getClientNumber();
						
						final String maskStringClientNumber = RawUtility
								.maskString(rawClientFinancial.getClientNumber(), 2, 6, 'X');
						logger.info("Client Number: {}", maskStringClientNumber);

						logger.info("Client Number: {}", rawClientFinancial.getClientNumber());

						Optional<List<RawClientCards>> OptListOfRawClientCards = rawClientRepository
								.findByClientNumberOrParentClientNumber(rawClientFinancial.getClientNumber(),
										rawClientFinancial.getClientNumber());
						
						logger.info("  *********** RawClientCards => " + OptListOfRawClientCards.get());

						if (OptListOfRawClientCards.isPresent()) {
							System.err.println("starting....");
							List<RawClientCards> ListOfRawClientCards = OptListOfRawClientCards.get();
							ListOfRawClientCards.stream().forEach(c -> logger.info( " * " + c.toString()));

							for (RawClientCards rawClientCards : ListOfRawClientCards) {
								customerlist.add(prepareCustomerInfoModel(rawClientCards, rawClientFinancial,
										getAccountResponse));
								System.err.println("before filter");
								customerlist.stream()
										.forEach(rawCustomerInfoModel -> logger.info(rawCustomerInfoModel.toString()));
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
		collectcustomerlist.stream().forEach(rawCustomerInfoModel -> logger.info(rawCustomerInfoModel.toString()));

		if (!collectcustomerlist.isEmpty()) {

			// https://www.onlinetutorialspoint.com/java8/java-8-how-to-remove-duplicates-from-list.html
			 //  below code is shown how to remove duplicate value in the  list of objects
			TreeSet<RawCustomerInfoModel> collectcustomerlistUnique = collectcustomerlist.stream().collect(Collectors
					.toCollection(() -> new TreeSet<>(Comparator.comparing(RawCustomerInfoModel::getCscNumber))));
			collectcustomerlistUnique.stream().forEach(G -> logger.info("Unique value printed {} " , G.toString()));

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
					messageSource.getMessage("msg.noCards", new Object[] {}, locale) + ": " + cscCustomerNumber);
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
	
			logger.info("current status of card :  {}", st);
	
			rawCustomerInfoModel.setCurrentCardStatus(st);
	
			rawCustomerInfoModel.setCardLimit(rawClientFinancial.getLimit());
			rawCustomerInfoModel.setPhoneNumber(rawClientDetails.getMobileNumber());
			rawCustomerInfoModel.setCardCurrentBalance(rawClientFinancial.getCurrentBalance());
			rawCustomerInfoModel.setBankAccountNumber(rawAccountBo.getAccountNumber());
			rawCustomerInfoModel.setTotalDues(rawClientFinancial.getTotalDues());
			rawCustomerInfoModel.setBillingAccountBalance(rawAccountBo.getBillingAccountBalance());
	
			return rawCustomerInfoModel;
	}
	


}
