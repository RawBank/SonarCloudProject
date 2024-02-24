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
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Arrays;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.data.repository.query.Param;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.rawbank.admin.model.RawCardInfoModel;
import com.rawbank.admin.model.RawCustomerInfoModel;
import com.rawbank.admin.model.RawEmailObject;
import com.rawbank.admin.model.RawLoginResponse;
import com.rawbank.admin.model.RawPendingCardsModel;
import com.rawbank.admin.model.RawPrepaidBulkApprovalParams;
import com.rawbank.admin.model.RawTokenResponseModel;
import com.rawbank.admin.model.RawTransactionCsCRequestModel;
import com.rawbank.admin.model.RawTransactionCsCResponseModel;
import com.rawbank.admin.model.RawTransferP2PRequest;
import com.rawbank.admin.model.RawTransferP2PResponse;
import com.rawbank.admin.oracle.entity.RawApprovalsOps;
import com.rawbank.admin.oracle.entity.RawCardLimitChange;
import com.rawbank.admin.oracle.entity.RawCardStatusChange;
import com.rawbank.admin.oracle.repository.RawApprovalsOpsRepository;
import com.rawbank.admin.oracle.repository.RawCardLimitChangeRepository;
import com.rawbank.admin.oracle.repository.RawCardStatusChangeRepository;
import com.rawbank.admin.service.RawAuthenticateService;
import com.rawbank.admin.service.RawConsumeCscApiServices;
import com.rawbank.admin.service.RawEmailService;
import com.rawbank.admin.service.RawTokenManagement;
import com.rawbank.admin.service.RawTransfertP2PService;
import com.rawbank.admin.utility.RawUtility;

//https://www.baeldung.com/spring-form-multiple-submit-buttons
/**
 * @author krishna
 *
 */
@Controller
public class RawAdminApproverController {

	// constante declaration to avoid duplication
	private static final String RELOAD_MESSAGE = "reloadMessage";
	private static final String TRANSACTION_AVEC_ID = "Transaction  avec ID: ";
	private static final String PENDING_CARD_INFO = "pendingCardInfo";
	private static final String REDIRECT_APPROVE_CARDS = "redirect:/approveCards";
	private static final String REDIRECT_APPROVE_CARDS_PP_BLK = "redirect:/approveCardsMcPrepaidBulk";
	private static final String REDIRECT_APPROVE_CARDS_PP_IND = "redirect:/approveCardsMcPrepaidIndividual";
	

	private static final Logger LOGGER = LoggerFactory.getLogger(RawAdminApproverController.class);
	private static final String MESSAGE = "message";
	private static final String PENDING = "PENDING";
	
	//private List<String> approuverStatusList = List.of("PENDING", "ECHEC_CSC");
	
	private List<String> approuverStatusList = Arrays.stream(new String[]{"PENDING", "ECHEC_CSC"})
            .collect(Collectors.toList());
	
	private static final String COMPLETED = "COMPLETED";
	private static final String REJECTED = "REJECTED";
	
	SimpleDateFormat sdfMMYY = new SimpleDateFormat("MM/yy");

	// among the code the comments in french is built to be populated on GUI for the
	// customer understanding

	@Value("${email.test}")
	private String emailTest;

	@Autowired
	private RawCardStatusChangeRepository rawCardStatusChangeRepository;

	@Autowired
	private RawCardLimitChangeRepository rawCardLimitChangeRepository;

	@Autowired
	private RawApprovalsOpsRepository rawApprovalsOpsRepository;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private RawTransfertP2PService rawTransfertP2PService;

	@Autowired
	private RawConsumeCscApiServices rawConsumeCscApiServices;
	@Autowired
	private RawTokenManagement rawTokenManagement;

	@Autowired
	private RawAuthenticateService rawAuthenticateService;

	@Autowired
	private RawEmailService rawEmailService;

	private String reloadMessage = "";

	@GetMapping("/approveCards")
	public ModelAndView approveCardsPage(@ModelAttribute("cardInfo") RawCardInfoModel rawCardInfoModel,
			RedirectAttributes redirectAttrs, Locale locale, BindingResult result,
			@ModelAttribute(PENDING_CARD_INFO) RawPendingCardsModel rawPendingCardsModel) {
		LOGGER.info("ApproveCardsPage.. {} ", rawCardInfoModel);
		RawCustomerInfoModel rawCustomerInfoModel = new RawCustomerInfoModel();
		rawCustomerInfoModel.setCustomerNumber(rawCardInfoModel.getCustomerNumber());

		// card change limit
		List<RawCardLimitChange> rawCardLimitChangeList = rawCardLimitChangeRepository
				.findCardsByApproverStatusLikeCaseInsensitive(PENDING);
		
				/*
				// card reload old repo req
				final Optional<List<RawApprovalsOps>> listRawApprovalsOpsOptional = rawApprovalsOpsRepository
						.findByApprouverStatusContainingIgnoreCase(PENDING);
				*/
				//Working fine to filter MC credit card records only with action button
				final Optional<List<RawApprovalsOps>> listRawApprovalsOpsOptional = rawApprovalsOpsRepository
						.findByapprouverStatusAndCommentsNotLikeAndCommentsNotLike(PENDING,"IND_____%","BLK_____%");
				
		

		if (!listRawApprovalsOpsOptional.isPresent()) {
			throw new NotFoundException("List of  reload is not present");
		}

		final List<RawApprovalsOps> listRawApprovalsOps = listRawApprovalsOpsOptional.get();

		ModelAndView map = new ModelAndView("pendingApproveCards");
		map.addObject("name", RawUtility.getLoggedinUserName() + " [" +RawUtility.getLoggedinUserRole() + "]");
		map.addObject("role", " [" + RawUtility.getLoggedinUserRole() + "]");
		if (rawCardLimitChangeList.isEmpty() && listRawApprovalsOps.isEmpty()) {
			map.addObject(MESSAGE, messageSource.getMessage("info.noPendingCards", new Object[] {}, locale));
		} else {
			map.addObject("pendingApproveCardsList",
					preparePendingCardsList(/* rawCardStatusChangeList , */ rawCardLimitChangeList));

			map.addObject("pendingApproveReloadList", preparePendingReloadList(listRawApprovalsOps));

			// pendingCardInfo
			map.addObject(PENDING_CARD_INFO, new RawPendingCardsModel());

			LOGGER.info("Pending cards size: {}", rawCardLimitChangeList.size());
		}

		return map;
	}
	
	
	
	
	@GetMapping("/approveCardsMcPrepaidIndividual")
	public ModelAndView approveCardsPageMcPrepaidIndividual(@ModelAttribute("cardInfo") RawCardInfoModel rawCardInfoModel,
			RedirectAttributes redirectAttrs, Locale locale, BindingResult result,
			@ModelAttribute(PENDING_CARD_INFO) RawPendingCardsModel rawPendingCardsModel) {
		LOGGER.info("ApproveCardsPage.. {} ", rawCardInfoModel);
		RawCustomerInfoModel rawCustomerInfoModel = new RawCustomerInfoModel();
		rawCustomerInfoModel.setCustomerNumber(rawCardInfoModel.getCustomerNumber());

		// card change limit
		List<RawCardLimitChange> rawCardLimitChangeList = rawCardLimitChangeRepository
				.findCardsByApproverStatusLikeCaseInsensitive(PENDING);
		
		
				// card reload new repo req with filter
				 //Working fine to filter only IND. This is called here in the tab of IND table that contains action button
				final Optional<List<RawApprovalsOps>> listRawApprovalsOpsOptional = rawApprovalsOpsRepository
						//.findByapprouverStatusAndCommentsStartingWith(PENDING,"IND_____");
				.findByapprouverStatusInAndCommentsStartingWith(approuverStatusList, "IND_____");
				
		if (!listRawApprovalsOpsOptional.isPresent()) {
			throw new NotFoundException("List of  reload is not present");
		}

		final List<RawApprovalsOps> listRawApprovalsOps = listRawApprovalsOpsOptional.get();

		ModelAndView map = new ModelAndView("pendingApproveCards");
		map.addObject("name", RawUtility.getLoggedinUserName() + " [" +RawUtility.getLoggedinUserRole() + "]");
		map.addObject("role", " [" + RawUtility.getLoggedinUserRole() + "]");
		if (rawCardLimitChangeList.isEmpty() && listRawApprovalsOps.isEmpty()) {
			map.addObject(MESSAGE, messageSource.getMessage("info.noPendingCards", new Object[] {}, locale));
		} else {
			map.addObject("pendingApproveCardsList",
					preparePendingCardsList(/* rawCardStatusChangeList , */ rawCardLimitChangeList));

			map.addObject("pendingApproveReloadList", preparePendingReloadList(listRawApprovalsOps));

			// pendingCardInfo
			map.addObject(PENDING_CARD_INFO, new RawPendingCardsModel());

			LOGGER.info("Pending cards size: {}", rawCardLimitChangeList.size());
		}

		return map;
	}
	
	
	
	@GetMapping("/approveCardsMcPrepaidBulk")
	public ModelAndView approveCardsPageMcPrepaidBulk(@ModelAttribute("cardInfo") RawCardInfoModel rawCardInfoModel,
			RedirectAttributes redirectAttrs, Locale locale, BindingResult result,
			@ModelAttribute(PENDING_CARD_INFO) RawPendingCardsModel rawPendingCardsModel) {
		LOGGER.info("ApproveCardsPage.. {} ", rawCardInfoModel);
		RawCustomerInfoModel rawCustomerInfoModel = new RawCustomerInfoModel();
		rawCustomerInfoModel.setCustomerNumber(rawCardInfoModel.getCustomerNumber());

		// card change limit
		List<RawCardLimitChange> rawCardLimitChangeList = rawCardLimitChangeRepository
				.findCardsByApproverStatusLikeCaseInsensitive(PENDING);
		
		
	
				// card reload new repo req with filter
				//Working fine to filter only BLK. This is to be called in the tab of Bulk table that contains action button
				final Optional<List<RawApprovalsOps>> listRawApprovalsOpsOptional = rawApprovalsOpsRepository
						//.findByapprouverStatusAndCommentsStartingWith(PENDING,"BLK_____");
				.findByapprouverStatusInAndCommentsStartingWith(approuverStatusList, "BLK_____");
				

		if (!listRawApprovalsOpsOptional.isPresent()) {
			throw new NotFoundException("List of  reload is not present");
		}

		final List<RawApprovalsOps> listRawApprovalsOps = listRawApprovalsOpsOptional.get();

		ModelAndView map = new ModelAndView("pendingApproveCardsPpBulk");
		map.addObject("name", RawUtility.getLoggedinUserName() + " [" +RawUtility.getLoggedinUserRole() + "]");
		map.addObject("role", " [" + RawUtility.getLoggedinUserRole() + "]");
		if (rawCardLimitChangeList.isEmpty() && listRawApprovalsOps.isEmpty()) {
			map.addObject(MESSAGE, messageSource.getMessage("info.noPendingCards", new Object[] {}, locale));
		} else {
			map.addObject("pendingApproveCardsList",
					preparePendingCardsList(/* rawCardStatusChangeList , */ rawCardLimitChangeList));

			map.addObject("pendingApproveReloadList", preparePendingReloadList(listRawApprovalsOps));

			// pendingCardInfo
			map.addObject(PENDING_CARD_INFO, new RawPendingCardsModel());

			LOGGER.info("Pending cards size: {}", rawCardLimitChangeList.size());
		}

		return map;
	}
	
	
	
	
	

	@PostMapping(value = "/approveCardOperation", params = "submit")
	public String approvePendingCard(@ModelAttribute(PENDING_CARD_INFO) RawPendingCardsModel rawPendingCardsModel,
			RedirectAttributes redirectAttrs, Locale locale, BindingResult result) throws JsonProcessingException  {

		redirectAttrs.addFlashAttribute(PENDING_CARD_INFO, new RawPendingCardsModel());
		// Initiatilization
		LOGGER.info("approvePendingCard.. {} ", rawPendingCardsModel);
		RawCustomerInfoModel rawCustomerInfoModel = new RawCustomerInfoModel();
		rawCustomerInfoModel.setCustomerNumber(rawPendingCardsModel.getCardNumber());
		final String rr = rawPendingCardsModel.getRetrievalReference();
		LOGGER.info("Retrieval Reference is ... " + rr);
		
		//Optional<RawApprovalsOps> rawApprovalsOpsOpt = rawApprovalsOpsRepository.findByRetrievalReference(rr);
		Optional<RawApprovalsOps> rawApprovalsOpsOpt = rawApprovalsOpsRepository.findByRetrievalReferenceAndApprouverStatus(rr,"PENDING");
		LOGGER.info("Req to be approved details based on Retrieval Reference is ... " + rawApprovalsOpsOpt);
		
		if (!rawApprovalsOpsOpt.isPresent()) {
			//throw new NotFoundException("Rapproval by retrievalReference " + rr + " does not exist");
			LOGGER.info("Rapproval by retrievalReference " + rr + " does not exist");
			reloadMessage = "<b style='color:red;background:yellow;'>Transaction bloquée coté CSC mais déjà passée coté Core Banking</b>";
			redirectAttrs.addFlashAttribute(RELOAD_MESSAGE, reloadMessage);
			return REDIRECT_APPROVE_CARDS;
		}
		RawApprovalsOps rawApprovalsOps = rawApprovalsOpsOpt.get();

		LOGGER.info("starting Transfer P2P...");

		// this part of transfer will change at every request
		RawTransferP2PRequest rawTransferP2PRequest = new RawTransferP2PRequest();
		rawTransferP2PRequest.setSrcAccount(RawUtility.getAgenceCompte(rawPendingCardsModel.getAaSrcAccount(), "end"));
		rawTransferP2PRequest.setSrcAgence(RawUtility.getAgenceCompte(rawPendingCardsModel.getAaSrcAccount(), "begin"));
		rawTransferP2PRequest.setDevise(
				RawUtility.getCurrencyInstance(Integer.valueOf(rawPendingCardsModel.getCurrency())).getCurrencyCode());
		rawTransferP2PRequest.setDstAccount(RawUtility.getAgenceCompte(rawPendingCardsModel.getAaDestAccount(), "end"));
		rawTransferP2PRequest
				.setDstAgence(RawUtility.getAgenceCompte(rawPendingCardsModel.getAaDestAccount(), "begin"));
		rawTransferP2PRequest.setAmount(Float.valueOf(rawPendingCardsModel.getAmount()) / 100);
		rawTransferP2PRequest.setMotif(String.format("%s", "MASTER " + sdfMMYY.format(new Date())+  "-" + rawApprovalsOps.getCscAccountNumber()));

   //MASTER 12/22-00014621001
		
		final RawTransferP2PResponse postTransferP2P = rawTransfertP2PService.postTransferP2P(rawTransferP2PRequest);

		if (postTransferP2P == null) {

			// insertion in the DB with the failure transfer P2P
			rawApprovalsOps.setAaStatus("10");
			rawApprovalsOps.setResponseMessage("Erreur -Transfert");
			rawApprovalsOps.setApprouver(RawUtility.getLoggedinUserName());
			rawApprovalsOps.setDateheuretransApproval(LocalDateTime.now());
			rawApprovalsOps.setCommentsApprov(rawPendingCardsModel.getCommentApprov());
			// time of approval
			rawApprovalsOpsRepository.save(rawApprovalsOps);

			reloadMessage = TRANSACTION_AVEC_ID + rawApprovalsOps.getRetrievalReference()
					+ "  a  connu une erreur, le transfert a échoué";
			redirectAttrs.addFlashAttribute(RELOAD_MESSAGE, reloadMessage);

			LOGGER.error("Transfer error");
			return REDIRECT_APPROVE_CARDS;
		}
		LOGGER.info("End  Transfer P2P...");
		LOGGER.info("Process  reload card...");

		RawTransactionCsCRequestModel rawTransactionCsCRequestModel = new RawTransactionCsCRequestModel();
		rawTransactionCsCRequestModel.setAmount(rawPendingCardsModel.getAmount());
		rawTransactionCsCRequestModel.setCardNumber(rawPendingCardsModel.getCardNumber());
		rawTransactionCsCRequestModel.setCurrency(Integer.valueOf(rawPendingCardsModel.getCurrency()));
		rawTransactionCsCRequestModel.setExpiryDate(rawPendingCardsModel.getExpiryDate());
		rawTransactionCsCRequestModel.setInstitutionNumber(rawPendingCardsModel.getInstitutionNumber());
		rawTransactionCsCRequestModel.setRetrievalReference(rawPendingCardsModel.getRetrievalReference());
		rawTransactionCsCRequestModel.setProcCode(rawPendingCardsModel.getProccode());
		rawTransactionCsCRequestModel.setTransactionType(rawPendingCardsModel.getTransactionType());

		RawTokenResponseModel rawTokenResponseModel = rawTokenManagement.generateCscToken();
		RawTransactionCsCResponseModel rawTransactionCsCResponseModel = rawConsumeCscApiServices
				.doTransaction(rawTransactionCsCRequestModel, rawTokenResponseModel);

		rawApprovalsOps.setAaStatus(postTransferP2P.getStatus());
		rawApprovalsOps.setApprouver(RawUtility.getLoggedinUserName());
		rawApprovalsOps.setDateheuretransApproval(LocalDateTime.now());
		rawApprovalsOps.setAaTransId(postTransferP2P.getReferenceid());
		rawApprovalsOps.setCommentsApprov(rawPendingCardsModel.getCommentApprov());

		if (rawTransactionCsCResponseModel == null) {
			LOGGER.error("csc reload error");
			rawApprovalsOps.setResponseCode("01");
			rawApprovalsOps.setResponseMessage("Erreur csc reload");
			rawApprovalsOps.setApprouverStatus("ECHEC_CSC");
			rawApprovalsOpsRepository.save(rawApprovalsOps);

			reloadMessage = TRANSACTION_AVEC_ID + rawApprovalsOps.getRetrievalReference()
					+ "  a  connu une erreur, l'appel de CSC n'a pas bien fonctionné";
			redirectAttrs.addFlashAttribute(RELOAD_MESSAGE, reloadMessage);
			return REDIRECT_APPROVE_CARDS;
		}

		if (rawTransactionCsCResponseModel.getResponseCode().equals("00")) {
			rawApprovalsOps.setResponseCode(rawTransactionCsCResponseModel.getResponseCode());
			rawApprovalsOps.setResponseMessage(rawTransactionCsCResponseModel.getResponseDescription());
			rawApprovalsOps.setAuthCode(rawTransactionCsCResponseModel.getAuthCode());
			rawApprovalsOps.setApprouverStatus(COMPLETED);

			rawApprovalsOpsRepository.save(rawApprovalsOps);

			RawEmailObject rawEmailObject = new RawEmailObject();
			rawEmailObject.setUserName("Transaction approuvée par : " + rawApprovalsOps.getApprouver());
			rawEmailObject.setSetTo(emailTest + ","
					+ rawAuthenticateService.getUserDetails(rawApprovalsOps.getInitiator().trim()).getEmail());
			rawEmailObject.setSetCC(rawAuthenticateService.getListAdminCsc().stream().map(RawLoginResponse::getEmail)
					.collect(Collectors.joining(",")));
			rawEmailObject.setRr(rawApprovalsOps.getRetrievalReference());
			rawEmailObject.setComment("Code d'autorisation " + rawApprovalsOps.getAuthCode());

			rawEmailService.sendMail(rawEmailObject);
			reloadMessage = TRANSACTION_AVEC_ID + rawApprovalsOps.getRetrievalReference()
					+ " est a été approuvée  avec succès";
			redirectAttrs.addFlashAttribute(RELOAD_MESSAGE, reloadMessage);
		}


		return REDIRECT_APPROVE_CARDS;

	}
	
	
	

	//PrepaidBulk approval btn
	@PostMapping(value = "/approveCardOperationPrepaidBulk")
	public String approvePendingCardPrepaidBulk(@ModelAttribute(PENDING_CARD_INFO) RawPrepaidBulkApprovalParams rawPrepaidBulkApprovalParams,
			RedirectAttributes redirectAttrs, Locale locale, BindingResult result) throws JsonProcessingException  {

		redirectAttrs.addFlashAttribute(PENDING_CARD_INFO, new RawPendingCardsModel());
		
		
		
		//Get details using params from DB
		Optional<List<RawApprovalsOps>> listRawApprovalsPrepaidBulkOptional = rawApprovalsOpsRepository
				.findByapprouverStatusAndCommentsStartingWith("PENDING","BLK_____");
		
		if (listRawApprovalsPrepaidBulkOptional.isPresent()) {
		    List<RawApprovalsOps> listRawApprovalsPrepaidBulk = listRawApprovalsPrepaidBulkOptional.get();
		    
		    LOGGER.info("======***** List of Bulk rea ======****** " + listRawApprovalsPrepaidBulk);
		    
		    for(int i = 0; i < listRawApprovalsPrepaidBulk.size(); i++) {
		    	
		    	LOGGER.info(" Counter ===  " + i);
		    	
		    	
		    	
		    	RawPendingCardsModel rawPendingCardsModel= new RawPendingCardsModel();
		        rawPendingCardsModel.setCardNumber(listRawApprovalsPrepaidBulk.get(i).getCardNumber());
		        rawPendingCardsModel.setCustomerName(listRawApprovalsPrepaidBulk.get(i).getCustomerName());
		        rawPendingCardsModel.setRetrievalReference(listRawApprovalsPrepaidBulk.get(i).getRetrievalReference());
		        rawPendingCardsModel.setAmount(listRawApprovalsPrepaidBulk.get(i).getTransamount());
		        rawPendingCardsModel.setCurrency(listRawApprovalsPrepaidBulk.get(i).getCurrency());
		        rawPendingCardsModel.setAaSrcAccount(listRawApprovalsPrepaidBulk.get(i).getAaSrcAccount());
		        rawPendingCardsModel.setInstitutionNumber(listRawApprovalsPrepaidBulk.get(i).getInstitutionNumber());
		        rawPendingCardsModel.setProccode(listRawApprovalsPrepaidBulk.get(i).getProccode());
		        rawPendingCardsModel.setTransactionType(listRawApprovalsPrepaidBulk.get(i).getTransactionType());
		        rawPendingCardsModel.setExpiryDate(listRawApprovalsPrepaidBulk.get(i).getExpiryDate());
		        rawPendingCardsModel.setCommentApprov(listRawApprovalsPrepaidBulk.get(i).getCommentsApprov());
		        rawPendingCardsModel.setAaDestAccount(listRawApprovalsPrepaidBulk.get(i).getAaDestAccount());
		        
		        
		        
		        
		        
		        
				
				// Initiatilization
				LOGGER.info("approvePendingCard.. {} ", rawPendingCardsModel);
				RawCustomerInfoModel rawCustomerInfoModel = new RawCustomerInfoModel();
				rawCustomerInfoModel.setCustomerNumber(rawPendingCardsModel.getCardNumber());
				final String rr = rawPendingCardsModel.getRetrievalReference();
				Optional<RawApprovalsOps> rawApprovalsOpsOpt = rawApprovalsOpsRepository.findByRetrievalReferenceAndApprouverStatus(rr,"PENDING");
				if (!rawApprovalsOpsOpt.isPresent()) {
					//throw new NotFoundException("Rapproval by retrievalReference " + rr + " does not exist");
					LOGGER.info("Rapproval by retrievalReference " + rr + " does not exist");
					reloadMessage = "<b style='color:red;background:yellow;'>Transaction bloquée coté CSC mais déjà passée coté Core Banking</b>";
					redirectAttrs.addFlashAttribute(RELOAD_MESSAGE, reloadMessage);
					return REDIRECT_APPROVE_CARDS;
				}
				RawApprovalsOps rawApprovalsOps = rawApprovalsOpsOpt.get();

				LOGGER.info("starting Transfer P2P...");

				// this part of transfer will change at every request
				RawTransferP2PRequest rawTransferP2PRequest = new RawTransferP2PRequest();
				rawTransferP2PRequest.setSrcAccount(RawUtility.getAgenceCompte(rawPendingCardsModel.getAaSrcAccount(), "end"));
				rawTransferP2PRequest.setSrcAgence(RawUtility.getAgenceCompte(rawPendingCardsModel.getAaSrcAccount(), "begin"));
				rawTransferP2PRequest.setDevise(
						RawUtility.getCurrencyInstance(Integer.valueOf(rawPendingCardsModel.getCurrency())).getCurrencyCode());
				rawTransferP2PRequest.setDstAccount(RawUtility.getAgenceCompte(rawPendingCardsModel.getAaDestAccount(), "end"));
				rawTransferP2PRequest
						.setDstAgence(RawUtility.getAgenceCompte(rawPendingCardsModel.getAaDestAccount(), "begin"));
				rawTransferP2PRequest.setAmount(Float.valueOf(rawPendingCardsModel.getAmount()) / 100);
				rawTransferP2PRequest.setMotif(String.format("%s", "MASTER " + sdfMMYY.format(new Date())+  "-" + rawApprovalsOps.getCscAccountNumber()));

		   //MASTER 12/22-00014621001
				
				final RawTransferP2PResponse postTransferP2P = rawTransfertP2PService.postTransferP2P(rawTransferP2PRequest);

				if (postTransferP2P == null) {

					// insertion in the DB with the failure transfer P2P
					rawApprovalsOps.setAaStatus("10");
					rawApprovalsOps.setResponseMessage("Erreur -Transfert");
					rawApprovalsOps.setApprouver(RawUtility.getLoggedinUserName());
					rawApprovalsOps.setDateheuretransApproval(LocalDateTime.now());
					rawApprovalsOps.setCommentsApprov(rawPendingCardsModel.getCommentApprov());
					// time of approval
					rawApprovalsOpsRepository.save(rawApprovalsOps);

					reloadMessage = TRANSACTION_AVEC_ID + rawApprovalsOps.getRetrievalReference()
							+ "  a  connu une erreur, le transfert a échoué";
					redirectAttrs.addFlashAttribute(RELOAD_MESSAGE, reloadMessage);

					LOGGER.error("Transfer error");
					return REDIRECT_APPROVE_CARDS_PP_BLK;
				}
				LOGGER.info("End  Transfer P2P...");
				LOGGER.info("Process  reload card...");

				RawTransactionCsCRequestModel rawTransactionCsCRequestModel = new RawTransactionCsCRequestModel();
				rawTransactionCsCRequestModel.setAmount(rawPendingCardsModel.getAmount());
				rawTransactionCsCRequestModel.setCardNumber(rawPendingCardsModel.getCardNumber());
				rawTransactionCsCRequestModel.setCurrency(Integer.valueOf(rawPendingCardsModel.getCurrency()));
				rawTransactionCsCRequestModel.setExpiryDate(rawPendingCardsModel.getExpiryDate());
				rawTransactionCsCRequestModel.setInstitutionNumber(rawPendingCardsModel.getInstitutionNumber());
				rawTransactionCsCRequestModel.setRetrievalReference(rawPendingCardsModel.getRetrievalReference());
				rawTransactionCsCRequestModel.setProcCode(rawPendingCardsModel.getProccode());
				rawTransactionCsCRequestModel.setTransactionType(rawPendingCardsModel.getTransactionType());

				RawTokenResponseModel rawTokenResponseModel = rawTokenManagement.generateCscToken();
				RawTransactionCsCResponseModel rawTransactionCsCResponseModel = rawConsumeCscApiServices
						.doTransaction(rawTransactionCsCRequestModel, rawTokenResponseModel);

				rawApprovalsOps.setAaStatus(postTransferP2P.getStatus());
				rawApprovalsOps.setApprouver(RawUtility.getLoggedinUserName());
				rawApprovalsOps.setDateheuretransApproval(LocalDateTime.now());
				rawApprovalsOps.setAaTransId(postTransferP2P.getReferenceid());
				rawApprovalsOps.setCommentsApprov(rawPendingCardsModel.getCommentApprov());

				if (rawTransactionCsCResponseModel == null) {
					LOGGER.error("csc reload error due to csc API response on this particular card number that has probably some issue");
					rawApprovalsOps.setResponseCode("01");
					rawApprovalsOps.setResponseMessage("Erreur csc reload");
					rawApprovalsOps.setApprouverStatus("ECHEC_CSC");
					rawApprovalsOpsRepository.save(rawApprovalsOps);

					reloadMessage = TRANSACTION_AVEC_ID + rawApprovalsOps.getRetrievalReference()
							+ "  a  connu une erreur, l'appel de CSC n'a pas bien fonctionné";
					redirectAttrs.addFlashAttribute(RELOAD_MESSAGE, reloadMessage);
					return REDIRECT_APPROVE_CARDS_PP_BLK;
				}

				if (rawTransactionCsCResponseModel.getResponseCode().equals("00")) {
					rawApprovalsOps.setResponseCode(rawTransactionCsCResponseModel.getResponseCode());
					rawApprovalsOps.setResponseMessage(rawTransactionCsCResponseModel.getResponseDescription());
					rawApprovalsOps.setAuthCode(rawTransactionCsCResponseModel.getAuthCode());
					rawApprovalsOps.setApprouverStatus(COMPLETED);

					rawApprovalsOpsRepository.save(rawApprovalsOps);

					RawEmailObject rawEmailObject = new RawEmailObject();
					rawEmailObject.setUserName("Transaction approuvée par : " + rawApprovalsOps.getApprouver());
					rawEmailObject.setSetTo(emailTest + ","
							+ rawAuthenticateService.getUserDetails(rawApprovalsOps.getInitiator().trim()).getEmail());
					rawEmailObject.setSetCC(rawAuthenticateService.getListAdminCsc().stream().map(RawLoginResponse::getEmail)
							.collect(Collectors.joining(",")));
					rawEmailObject.setRr(rawApprovalsOps.getRetrievalReference());
					rawEmailObject.setComment("Code d'autorisation " + rawApprovalsOps.getAuthCode());

					rawEmailService.sendMail(rawEmailObject);
					reloadMessage = TRANSACTION_AVEC_ID + rawApprovalsOps.getRetrievalReference()
							+ " est a été approuvée  avec succès";
					redirectAttrs.addFlashAttribute(RELOAD_MESSAGE, reloadMessage);
				}
				
		    	
		    	
		    	
		    }
		    
		    /*
		    for (RawApprovalsOps rawApprovalsOpsBlk : listRawApprovalsPrepaidBulk) {
		        // Process each element in the list
		        System.out.println(rawApprovalsOpsBlk); // or any other processing
		        
		        RawPendingCardsModel rawPendingCardsModel= new RawPendingCardsModel();
		        rawPendingCardsModel.setCardNumber(rawApprovalsOpsBlk.getCardNumber());
		        rawPendingCardsModel.setCustomerName(rawApprovalsOpsBlk.getCustomerName());
		        rawPendingCardsModel.setRetrievalReference(rawApprovalsOpsBlk.getRetrievalReference());
		        rawPendingCardsModel.setAmount(rawApprovalsOpsBlk.getTransamount());
		        rawPendingCardsModel.setCurrency(rawApprovalsOpsBlk.getCurrency());
		        rawPendingCardsModel.setAaSrcAccount(rawApprovalsOpsBlk.getAaSrcAccount());
		        rawPendingCardsModel.setInstitutionNumber(rawApprovalsOpsBlk.getInstitutionNumber());
		        rawPendingCardsModel.setProccode(rawApprovalsOpsBlk.getProccode());
		        rawPendingCardsModel.setTransactionType(rawApprovalsOpsBlk.getTransactionType());
		        rawPendingCardsModel.setExpiryDate(rawApprovalsOpsBlk.getExpiryDate());
		        rawPendingCardsModel.setCommentApprov(rawApprovalsOpsBlk.getCommentsApprov());
		        rawPendingCardsModel.setAaDestAccount(rawApprovalsOpsBlk.getAaDestAccount());
		        
		        
		        
		        
		        
		        
				
				// Initiatilization
				LOGGER.info("approvePendingCard.. {} ", rawPendingCardsModel);
				RawCustomerInfoModel rawCustomerInfoModel = new RawCustomerInfoModel();
				rawCustomerInfoModel.setCustomerNumber(rawPendingCardsModel.getCardNumber());
				final String rr = rawPendingCardsModel.getRetrievalReference();
				Optional<RawApprovalsOps> rawApprovalsOpsOpt = rawApprovalsOpsRepository.findByRetrievalReference(rr);
				if (!rawApprovalsOpsOpt.isPresent()) {
					throw new NotFoundException("Rapproval by retrievalReference " + rr + " does not exist");
				}
				RawApprovalsOps rawApprovalsOps = rawApprovalsOpsOpt.get();

				LOGGER.info("starting Transfer P2P...");

				// this part of transfer will change at every request
				RawTransferP2PRequest rawTransferP2PRequest = new RawTransferP2PRequest();
				rawTransferP2PRequest.setSrcAccount(RawUtility.getAgenceCompte(rawPendingCardsModel.getAaSrcAccount(), "end"));
				rawTransferP2PRequest.setSrcAgence(RawUtility.getAgenceCompte(rawPendingCardsModel.getAaSrcAccount(), "begin"));
				rawTransferP2PRequest.setDevise(
						RawUtility.getCurrencyInstance(Integer.valueOf(rawPendingCardsModel.getCurrency())).getCurrencyCode());
				rawTransferP2PRequest.setDstAccount(RawUtility.getAgenceCompte(rawPendingCardsModel.getAaDestAccount(), "end"));
				rawTransferP2PRequest
						.setDstAgence(RawUtility.getAgenceCompte(rawPendingCardsModel.getAaDestAccount(), "begin"));
				rawTransferP2PRequest.setAmount(Float.valueOf(rawPendingCardsModel.getAmount()) / 100);
				rawTransferP2PRequest.setMotif(String.format("%s", "MASTER " + sdfMMYY.format(new Date())+  "-" + rawApprovalsOps.getCscAccountNumber()));

		   //MASTER 12/22-00014621001
				
				final RawTransferP2PResponse postTransferP2P = rawTransfertP2PService.postTransferP2P(rawTransferP2PRequest);

				if (postTransferP2P == null) {

					// insertion in the DB with the failure transfer P2P
					rawApprovalsOps.setAaStatus("10");
					rawApprovalsOps.setResponseMessage("Erreur -Transfert");
					rawApprovalsOps.setApprouver(RawUtility.getLoggedinUserName());
					rawApprovalsOps.setDateheuretransApproval(LocalDateTime.now());
					rawApprovalsOps.setCommentsApprov(rawPendingCardsModel.getCommentApprov());
					// time of approval
					rawApprovalsOpsRepository.save(rawApprovalsOps);

					reloadMessage = TRANSACTION_AVEC_ID + rawApprovalsOps.getRetrievalReference()
							+ "  a  connu une erreur, le transfert a échoué";
					redirectAttrs.addFlashAttribute(RELOAD_MESSAGE, reloadMessage);

					LOGGER.error("Transfer error");
					return REDIRECT_APPROVE_CARDS;
				}
				LOGGER.info("End  Transfer P2P...");
				LOGGER.info("Process  reload card...");

				RawTransactionCsCRequestModel rawTransactionCsCRequestModel = new RawTransactionCsCRequestModel();
				rawTransactionCsCRequestModel.setAmount(rawPendingCardsModel.getAmount());
				rawTransactionCsCRequestModel.setCardNumber(rawPendingCardsModel.getCardNumber());
				rawTransactionCsCRequestModel.setCurrency(Integer.valueOf(rawPendingCardsModel.getCurrency()));
				rawTransactionCsCRequestModel.setExpiryDate(rawPendingCardsModel.getExpiryDate());
				rawTransactionCsCRequestModel.setInstitutionNumber(rawPendingCardsModel.getInstitutionNumber());
				rawTransactionCsCRequestModel.setRetrievalReference(rawPendingCardsModel.getRetrievalReference());
				rawTransactionCsCRequestModel.setProcCode(rawPendingCardsModel.getProccode());
				rawTransactionCsCRequestModel.setTransactionType(rawPendingCardsModel.getTransactionType());

				RawTokenResponseModel rawTokenResponseModel = rawTokenManagement.generateCscToken();
				RawTransactionCsCResponseModel rawTransactionCsCResponseModel = rawConsumeCscApiServices
						.doTransaction(rawTransactionCsCRequestModel, rawTokenResponseModel);

				rawApprovalsOps.setAaStatus(postTransferP2P.getStatus());
				rawApprovalsOps.setApprouver(RawUtility.getLoggedinUserName());
				rawApprovalsOps.setDateheuretransApproval(LocalDateTime.now());
				rawApprovalsOps.setAaTransId(postTransferP2P.getReferenceid());
				rawApprovalsOps.setCommentsApprov(rawPendingCardsModel.getCommentApprov());

				if (rawTransactionCsCResponseModel == null) {
					LOGGER.error("csc reload error");
					rawApprovalsOps.setResponseCode("01");
					rawApprovalsOps.setResponseMessage("Erreur csc reload");
					rawApprovalsOpsRepository.save(rawApprovalsOps);

					reloadMessage = TRANSACTION_AVEC_ID + rawApprovalsOps.getRetrievalReference()
							+ "  a  connu une erreur, l'appel de CSC n'a pas bien fonctionné";
					redirectAttrs.addFlashAttribute(RELOAD_MESSAGE, reloadMessage);
					return REDIRECT_APPROVE_CARDS;
				}

				if (rawTransactionCsCResponseModel.getResponseCode().equals("00")) {
					rawApprovalsOps.setResponseCode(rawTransactionCsCResponseModel.getResponseCode());
					rawApprovalsOps.setResponseMessage(rawTransactionCsCResponseModel.getResponseDescription());
					rawApprovalsOps.setAuthCode(rawTransactionCsCResponseModel.getAuthCode());
					rawApprovalsOps.setApprouverStatus(COMPLETED);

					rawApprovalsOpsRepository.save(rawApprovalsOps);

					RawEmailObject rawEmailObject = new RawEmailObject();
					rawEmailObject.setUserName("Transaction approuvée par : " + rawApprovalsOps.getApprouver());
					rawEmailObject.setSetTo(emailTest + ","
							+ rawAuthenticateService.getUserDetails(rawApprovalsOps.getInitiator().trim()).getEmail());
					rawEmailObject.setSetCC(rawAuthenticateService.getListAdminCsc().stream().map(RawLoginResponse::getEmail)
							.collect(Collectors.joining(",")));
					rawEmailObject.setRr(rawApprovalsOps.getRetrievalReference());
					rawEmailObject.setComment("Code d'autorisation " + rawApprovalsOps.getAuthCode());

					rawEmailService.sendMail(rawEmailObject);
					reloadMessage = TRANSACTION_AVEC_ID + rawApprovalsOps.getRetrievalReference()
							+ " est a été approuvée  avec succès";
					redirectAttrs.addFlashAttribute(RELOAD_MESSAGE, reloadMessage);
				}
			
		        
		    }
		    */
		    
		    
		} else {
		    System.out.println("No elements found in the list");
		}
		
		
		//LOGGER.info("Prepaid bulk approval params ********* "+  rawPrepaidBulkApprovalParams.getApprouverStatusBlk() + " ======= " + rawPrepaidBulkApprovalParams.getCommentsLike());
		//LOGGER.info("List all BLK pending req ********* "+  listRawApprovalsPrepaidBulk);
		
		// Init RawPendingCardsModel rawPendingCardsModel
		//RawPendingCardsModel rawPendingCardsModel= new RawPendingCardsModel();
		
		
		
		


		return REDIRECT_APPROVE_CARDS_PP_BLK;

	}


	@PostMapping(value = "/approveCardOperation", params = "reject")
	public String rejectPendingCards(@ModelAttribute(PENDING_CARD_INFO) RawPendingCardsModel rawPendingCardsModel,
			RedirectAttributes redirectAttrs, Locale locale, BindingResult result)
			throws  JsonProcessingException {
		LOGGER.info("rejection  screen.. {} ", rawPendingCardsModel);
		redirectAttrs.addFlashAttribute(PENDING_CARD_INFO, new RawPendingCardsModel());
		// retrievalReference
		String rr = rawPendingCardsModel.getRetrievalReference();
		Optional<RawApprovalsOps> rawApprovalsOpsOpt = rawApprovalsOpsRepository.findByRetrievalReferenceAndApprouverStatus(rr,"PENDING");
		if (!rawApprovalsOpsOpt.isPresent()) {
			//throw new NotFoundException("rejection : approval recorded not available " + rr);
			LOGGER.info("Rapproval by retrievalReference " + rr + " does not exist");
			reloadMessage = "<b style='color:red;background:yellow;'>On ne peut pas rejéter une transaction partiellement approuvée. Core Banking=>OK   CSC=> ECHEC</b>";
			redirectAttrs.addFlashAttribute(RELOAD_MESSAGE, reloadMessage);
			return REDIRECT_APPROVE_CARDS;
		}
		RawApprovalsOps rawApprovalsOps = rawApprovalsOpsOpt.get();
		rawApprovalsOps.setApprouverStatus(REJECTED);
		rawApprovalsOps.setApprouver(RawUtility.getLoggedinUserName());
		rawApprovalsOps.setCommentsApprov(rawPendingCardsModel.getCommentApprov());
		rawApprovalsOps.setDateheuretransApproval(LocalDateTime.now());

		rawApprovalsOpsRepository.save(rawApprovalsOps);

		RawEmailObject rawEmailObject = new RawEmailObject();
		rawEmailObject.setUserName("Transaction rejétéé  par : " + rawApprovalsOps.getApprouver());
		rawEmailObject.setSetTo(emailTest + ","
				+ rawAuthenticateService.getUserDetails(rawApprovalsOps.getInitiator().trim()).getEmail());
		rawEmailObject.setSetCC(rawAuthenticateService.getListAdminCsc().stream().map(RawLoginResponse::getEmail)
				.collect(Collectors.joining(",")));
		rawEmailObject.setRr(rawApprovalsOps.getRetrievalReference());
		rawEmailObject.setComment("Raison de rejet " + rawApprovalsOps.getCommentsApprov());

		rawEmailService.sendMail(rawEmailObject);

		reloadMessage = TRANSACTION_AVEC_ID + rawApprovalsOps.getRetrievalReference()
				+ " est a été réjétée  avec succès";

		redirectAttrs.addFlashAttribute(RELOAD_MESSAGE, reloadMessage);
		return REDIRECT_APPROVE_CARDS;
	}

	@GetMapping(value = "/downloadFile")
	public void downloadFile(@Param("id") Long id, @Param("action") String action, HttpServletResponse response,
			Locale locale) throws IOException {
		LOGGER.info("downloadFile.. {} ", id);
		if (id != null) {
			if ("changeStatus".equalsIgnoreCase(action)) {
				Optional<RawCardStatusChange> rawCardStatusChange = rawCardStatusChangeRepository.findById(id);
				  if(rawCardStatusChange.isPresent()) {
					  final RawCardStatusChange rawCardStatusChange2 = rawCardStatusChange.get();
					LOGGER.info(" {} " , rawCardStatusChange2);
				  }
			} else if ("changeLimit".equalsIgnoreCase(action)) {
				Optional<RawCardLimitChange> rawCardLimitChange = rawCardLimitChangeRepository.findById(id);
				if (rawCardLimitChange.isPresent()) {
					prepareFileResponse(rawCardLimitChange.get().getFileName(), rawCardLimitChange.get().getFileData(),
							response);
				}
			}
		}
	}

	private void prepareFileResponse(String string, byte[] bs, HttpServletResponse response) throws IOException {
		response.setContentType("application/oct-stream");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=" + string;
		response.setHeader(headerKey, headerValue);
		ServletOutputStream outStream = response.getOutputStream();
		outStream.write(bs);
		outStream.close();
	}

	private List<RawPendingCardsModel> preparePendingCardsList(
			/* List<RawCardStatusChange> rawCardStatusChangeList, */List<RawCardLimitChange> rawCardLimitChangeList) {
		List<RawPendingCardsModel> pendingCardsList = new ArrayList<>();
		pendingCardsList.clear();

		for (RawCardLimitChange rawCardStatusChange : rawCardLimitChangeList) {
			pendingCardsList.add(new RawPendingCardsModel(rawCardStatusChange));
		}
		LOGGER.info("Pending Cards List.. {} ", pendingCardsList);
		return pendingCardsList;
	}

	private List<RawPendingCardsModel> preparePendingReloadList(List<RawApprovalsOps> listRawApprovalsOps) {
		List<RawPendingCardsModel> pendingCardsList = new ArrayList<>();
		pendingCardsList.clear();

		for (RawApprovalsOps approvalsOps : listRawApprovalsOps) {
			approvalsOps.setCurrency(approvalsOps.getCurrency() + "-"
					+ RawUtility.getCurrencyInstance(Integer.parseInt(approvalsOps.getCurrency())).getCurrencyCode());
			pendingCardsList.add(new RawPendingCardsModel(approvalsOps));
		}
		LOGGER.info("Pending Cards List.. {} ", pendingCardsList);
		return pendingCardsList;
	}

}
