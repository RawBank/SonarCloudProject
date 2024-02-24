/**
 * © Copyright 2021 Rawbank. All Rights Reserved.
 * 
 * Change Section:
 * Programmer			Date
 * Krishna				Sep 15, 2022
 * Fixed critical SonarQube Issues.
 * 
 * Jacques				6/28/2022
 * CSC admin portal Project.
 * Initial Coding.
 * 
 * 
 * Created By:
 * 
 * @author Jacques
 * @since Jun 28, 2022
 * 
 */
package com.rawbank.admin.restcontroller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rawbank.admin.client.stubs.getAccountList.GetAccountResponse;
import com.rawbank.admin.config.RawAppConfig;
import com.rawbank.admin.exception.RawDetailsNotFindException;
import com.rawbank.admin.model.RawAmountDueRequestModel;
import com.rawbank.admin.model.RawAmountDueResponseModel;
import com.rawbank.admin.model.RawCardActivityRequestModel;
import com.rawbank.admin.model.RawCardActivityResponseModel;
import com.rawbank.admin.model.RawCardBalanceRequestModel;
import com.rawbank.admin.model.RawCardBalanceResponseModel;
import com.rawbank.admin.model.RawGetClientCardResponseModel;
import com.rawbank.admin.model.RawReloadDetails;
import com.rawbank.admin.model.RawTokenResponseModel;
import com.rawbank.admin.model.Transactions;
import com.rawbank.admin.oracle.entity.RawApprovalsOps;
import com.rawbank.admin.oracle.entity.RawCardStatusChange;
import com.rawbank.admin.oracle.repository.RawApprovalsOpsRepository;
import com.rawbank.admin.oracle.repository.RawCardStatusChangeRepository;
import com.rawbank.admin.service.RawConsumeCscApiServices;
import com.rawbank.admin.service.RawGetAccountServiceImpl;
import com.rawbank.admin.service.RawTokenManagement;
import com.rawbank.admin.utility.RawUtility;

@RestController
@RequestMapping("/api")
public class RawMiddlewareController {

	@Autowired
	private RawConsumeCscApiServices consumeCscApiServices;
	@Autowired
	private RawTokenManagement rawTokenManagement;

	@Autowired
	private RawGetAccountServiceImpl rawGetAccountServiceImpl;

	@Autowired
	private RawCardStatusChangeRepository rawCardStatusChangeRepository;

	@Autowired
	private RawApprovalsOpsRepository rawApprovalsOpsRepository;

	private static final String STATEMENTDIR = (new StringBuilder(String.valueOf(System.getProperty("user.home"))))
			.append("/rawb-applog/activity.cscadmin/statement/").toString();

	private static final Logger LOG = LoggerFactory.getLogger(RawMiddlewareController.class);

	private static final String ERROR_MSG = " ERROR {}";

	private static final String PENDING = "PENDING";



	@PostMapping(value = "/getTransactionsList", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RawCardActivityResponseModel> getTransactionsList(@RequestParam("dateFrom") String dateFrom,
			@RequestParam("dateTo") String dateTo, @RequestParam("card") String card)  {
		dateFrom = dateFrom.replace("-", "");
		dateTo = dateTo.replace("-", "");
		LOG.info("Date From  : {} {} {} {} {}", dateFrom, "  Date To: ", dateTo, " for the card : ", card);
		try {
			RawCardActivityRequestModel rawCardActivityRequestModel = new RawCardActivityRequestModel(card, dateFrom,
					dateTo, RawAppConfig.getCscinstitutionNumber());

			RawTokenResponseModel rawTokenResponseModel = rawTokenManagement.generateCscToken();

			RawCardActivityResponseModel transactionsperCard = consumeCscApiServices
					.getTransactionsperCard(rawTokenResponseModel, rawCardActivityRequestModel);
			LOG.info("Size of Transaction List  cleared : {} {} {}",
					transactionsperCard.getClearedTransactions().size(), " pending : ",
					transactionsperCard.getPendingTransactions().size());

			RawCardActivityResponseModel transactionsperCardFiltered = new RawCardActivityResponseModel();

			List<Transactions> transactionsclearedFiltered = new ArrayList<>();
			List<Transactions> transactionspendingFiltered = new ArrayList<>();

			if (!transactionsperCard.getClearedTransactions().isEmpty()) {
				transactionsclearedFiltered = transactionsperCard.getClearedTransactions().stream().map(transaction -> {
					transaction.setIssuingCurrency(RawUtility
							.getCurrencyInstance(Integer.valueOf(transaction.getIssuingCurrency())).toString());
					transaction.setIncr(transactionsperCard.getClearedTransactions().indexOf(transaction) + 1);
					return transaction;
				}).collect(Collectors.toList());
			}

			if (!transactionsperCard.getPendingTransactions().isEmpty()) {
				transactionspendingFiltered = transactionsperCard.getPendingTransactions().stream().map(transaction -> {
					transaction.setIssuingCurrency(RawUtility
							.getCurrencyInstance(Integer.valueOf(transaction.getIssuingCurrency())).toString());
					transaction.setIncr(transactionsperCard.getClearedTransactions().indexOf(transaction) + 1);
					return transaction;
				}).collect(Collectors.toList());

			}
			transactionsperCardFiltered.setClearedTransactions(transactionsclearedFiltered);
			transactionsperCardFiltered.setPendingTransactions(transactionspendingFiltered);

			return ResponseEntity.status(HttpStatus.OK).body(transactionsperCardFiltered);

		} catch (Exception e) {
			LOG.error(ERROR_MSG, e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@PostMapping(value = "/getChangeStatusReports", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RawCardStatusChange>> getChangeStatusReports() {
		LOG.info("Print  change status report ...{}", SecurityContextHolder.getContext().getAuthentication().getName());
		try {
			List<RawCardStatusChange> rawCardStatusChanges = rawCardStatusChangeRepository
					.findAllByUserNameOrderByCardStatusChangeDateDesc(
							SecurityContextHolder.getContext().getAuthentication().getName());
		
			
			 if  (!rawCardStatusChanges.isEmpty()) {
				  List<RawCardStatusChange> rawCardStatusChangesMask = rawCardStatusChanges.stream().map(cstc->  {
						cstc.setCardNumber(RawUtility.maskString(cstc.getCardNumber(), 6, 12, 'X'));
						 return cstc;
					}).collect(Collectors.toList());
				  return ResponseEntity.status(HttpStatus.OK).body(rawCardStatusChangesMask);
			 }
			 return ResponseEntity.status(HttpStatus.OK).body(null);
		} catch (Exception e) {
			LOG.error(ERROR_MSG, e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	//

	@PostMapping(value = "/getreloaddetails/{cardNumber}/{currency}/{cscClientNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RawReloadDetails> getReloadDetails(@PathVariable("cardNumber") String cardNumber,
			@PathVariable("currency") String currency, @PathVariable("cscClientNumber") String cscClientNumber) {
		LOG.info(" reload api call {}, {} , {}", cardNumber, currency, cscClientNumber);

		try {

			RawReloadDetails reloadDetails = new RawReloadDetails();
			// generate a token for call csc api
			RawTokenResponseModel rawTokenResponseModel = rawTokenManagement.generateCscToken();

			// get csc client details based on csc client number
			final RawGetClientCardResponseModel[] clientCards = consumeCscApiServices
					.getClientCard(rawTokenResponseModel, cscClientNumber);
			// as we can have more than one card per client, let us filt based on the card
			// number to be chosen on the UI
			final Optional<RawGetClientCardResponseModel> clientCardOptional = Arrays.asList(clientCards).stream()
					.filter(clientCard -> clientCard.getCardNumber().equals(cardNumber)).findFirst();

			if (!clientCardOptional.isPresent()) {
				throw new RawDetailsNotFindException("details of card not find  for the card" + cardNumber);
			}
			RawGetClientCardResponseModel clientCard = clientCardOptional.get();

			RawAmountDueRequestModel rawAmountDueRequestModel = new RawAmountDueRequestModel(cardNumber,
					String.valueOf(RawUtility.getCurrencyInstance(currency).getNumericCode()));

			RawAmountDueResponseModel amountdue = consumeCscApiServices.getAmountdue(rawTokenResponseModel,
					rawAmountDueRequestModel);  

			RawCardBalanceRequestModel rawCardBalanceRequestModel = new RawCardBalanceRequestModel(cardNumber);
			RawCardBalanceResponseModel balance = consumeCscApiServices.getBalance(rawTokenResponseModel,
					rawCardBalanceRequestModel);

			// amountdue.getAmountDue() is given the string value like C000000005800 it
			// equals 58,00 `currency`
			// have to split and retrieve the float value

			Float amountDue = Float
					.parseFloat(amountdue.getAmountDue().substring(1, amountdue.getAmountDue().length()));

			reloadDetails.setAmountDue(amountDue / 100);
			reloadDetails.setBalance(Math.abs(Float.valueOf(balance.getAvailableAmount())));
			reloadDetails.setCardExpiredDate(clientCard.getExpiryDate());
			reloadDetails.setCardNumber(clientCard.getCardNumber());
			reloadDetails.setCardStatus(clientCard.getCardStatus().equals("001") ? "Active" : "Temporay Blocked");
			reloadDetails.setClientName(clientCard.getEmbossLine1());
			reloadDetails.setClientNumber(null);
			reloadDetails.setLimitBalance(Math.abs(Float.valueOf(balance.getLimitAmount())));
			reloadDetails.setPendingAmount(Float.parseFloat(balance.getPendingAuthsAmount()));
			reloadDetails.setReloadComment(null);
			reloadDetails.setCurrency(currency);
			reloadDetails.setCscAccountNumber(amountdue.getAccountNumber());
			LOG.info("reload model : {}", reloadDetails);
			return ResponseEntity.status(HttpStatus.OK).body(reloadDetails);
		} catch (Exception e) {

			LOG.error(ERROR_MSG, e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

	}

	// customerNumber === clientNumber
	@PostMapping(value = "/getAccounts/{customerNumber}/{currency}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Set<String>> getAccountList(@PathVariable("customerNumber") String customerNumber,
			@PathVariable("currency") String currency) {
		LOG.info(" get account list of client  :  {} and currency {}", customerNumber, currency);
		try {

			String isocurrency = String.valueOf(RawUtility.getCurrencyInstance(currency.trim()).getNumericCode());
			LOG.info("currency transformation for letter to digits {} ", isocurrency);
			Set<String> accounts = new HashSet<>();
			accounts.clear();
			List<GetAccountResponse> accountList = rawGetAccountServiceImpl.getAccountList(customerNumber);

			accounts = accountList.stream()
					.filter(account -> account.getAccount().getAccountNumber().getInternalFormatAccountOurBranch()
							.getCurrency().getNumericCode().equals(isocurrency))
					.collect(Collectors.toList()).stream()
					.map(fc -> fc.getBranch().getCode() + "-"
							+ fc.getAccount().getAccountNumber().getInternalFormatAccountOurBranch().getAccount()
							+ "- BAL:" + fc.getIndicativeBalance())
					.collect(Collectors.toSet());

			accounts.stream().forEach(LOG::info);
			return ResponseEntity.ok(accounts);
		} catch (Exception e) {

			LOG.error("", e);

			return ResponseEntity.internalServerError().body(null);
		}
	}

	@PostMapping(value = "/getInitiatorActivities/{initiator}/{opsType}/{cardType}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RawApprovalsOps>> getInitiatorActivities(@PathVariable("initiator") String initiator,
			@PathVariable("opsType") String opsType, @PathVariable("cardType") String cardType) {

		LOG.info(" initiator =>  :  {} and opsType => {}", initiator, opsType);
		LOG.info(" Reload Card Type " + cardType);

		try {
			/*
			Optional<List<RawApprovalsOps>> approvalsOpsInitiatorOpt = rawApprovalsOpsRepository
					.findByInitiatorAndOpsType(initiator, opsType);
			*/
			
			/*
			Optional<List<RawApprovalsOps>> approvalsOpsInitiatorOpt = rawApprovalsOpsRepository
					.findByCommentsLike(cardType.toUpperCase() + "____%");
			*/
			
			if(cardType.equalsIgnoreCase("IND")) {
				LOG.info(" Reload Card Type " + cardType);
				
				Optional<List<RawApprovalsOps>> approvalsOpsInitiatorOpt = rawApprovalsOpsRepository
						.findByInitiatorAndOpsTypeAndCommentsLike(initiator, opsType, cardType.toUpperCase() + "_____");
				
				if (!approvalsOpsInitiatorOpt.isPresent()) {
					return ResponseEntity.internalServerError().body(null);
				}
				
				if(approvalsOpsInitiatorOpt.isPresent()){
					List<RawApprovalsOps> approvalsOpsInitiator = approvalsOpsInitiatorOpt.get().stream().map(aOps -> {
						aOps.setCurrency(RawUtility.getCurrencyInstance(Integer.valueOf(aOps.getCurrency())).getCurrencyCode());
						 aOps.setCardNumber(RawUtility.maskString(aOps.getCardNumber(), 6, 12, 'X'));
						return aOps;
					}).collect(Collectors.toList());
					return ResponseEntity.ok(approvalsOpsInitiator);
				}else {
					return null;
				}
				
			}else if(cardType.equalsIgnoreCase("BLK")) {
				LOG.info(" Reload Card Type " + cardType);
				
				Optional<List<RawApprovalsOps>> approvalsOpsInitiatorOpt = rawApprovalsOpsRepository
						.findByInitiatorAndOpsTypeAndCommentsLike(initiator, opsType, cardType.toUpperCase() + "_____");
				
				if (!approvalsOpsInitiatorOpt.isPresent()) {
					return ResponseEntity.internalServerError().body(null);
				}
				
				if(approvalsOpsInitiatorOpt.isPresent()){
					List<RawApprovalsOps> approvalsOpsInitiator = approvalsOpsInitiatorOpt.get().stream().map(aOps -> {
						aOps.setCurrency(RawUtility.getCurrencyInstance(Integer.valueOf(aOps.getCurrency())).getCurrencyCode());
						 aOps.setCardNumber(RawUtility.maskString(aOps.getCardNumber(), 6, 12, 'X'));
						return aOps;
					}).collect(Collectors.toList());

					return ResponseEntity.ok(approvalsOpsInitiator);
					
				}else {
					return null;
				}
				
			}else if(cardType.equalsIgnoreCase("PSP")) {
				LOG.info(" Reload Card Type " + cardType);
				
				Optional<List<RawApprovalsOps>> approvalsOpsInitiatorOpt = rawApprovalsOpsRepository
						.findByInitiatorAndOpsTypeAndCommentsLike2(initiator, opsType,"_____");
				
				if (!approvalsOpsInitiatorOpt.isPresent()) {
					return ResponseEntity.internalServerError().body(null);
				}
				
				if(approvalsOpsInitiatorOpt.isPresent()){
					List<RawApprovalsOps> approvalsOpsInitiator = approvalsOpsInitiatorOpt.get().stream().map(aOps -> {
						aOps.setCurrency(RawUtility.getCurrencyInstance(Integer.valueOf(aOps.getCurrency())).getCurrencyCode());
						 aOps.setCardNumber(RawUtility.maskString(aOps.getCardNumber(), 6, 12, 'X'));
						return aOps;
					}).collect(Collectors.toList());

					return ResponseEntity.ok(approvalsOpsInitiator);
					
				}else {
					return null;
				}
				
			}
			
		
			
			
	
		} catch (Exception e) {

			LOG.error("", e);

			return ResponseEntity.internalServerError().body(null);
		}
		return null;
		

	}

	@PostMapping(value = "/getapprouverops/{approuver}/{cardType}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RawApprovalsOps>> getapprouverops(@PathVariable("approuver") String approuver, @PathVariable("cardType") String cardType) {
		// , @PathVariable("opsType") String opsType
		LOG.info(" initiator =>  :  {}  and  status {} :  and  Card type {}", approuver, PENDING,cardType);

		try {
			 // retrieve non pending transactions 
			
			/*
			Optional<List<RawApprovalsOps>> approvalsOpsApprouverOpt = rawApprovalsOpsRepository
					.findByApprouverAndApprouverStatusIsNot(approuver, PENDING);
			if (!approvalsOpsApprouverOpt.isPresent())
				return ResponseEntity.internalServerError().body(null);
			List<RawApprovalsOps> approvalsOpsApprouve = approvalsOpsApprouverOpt.get().stream().map(aOps -> {
				aOps.setCurrency(RawUtility.getCurrencyInstance(Integer.valueOf(aOps.getCurrency())).getCurrencyCode());
                aOps.setCardNumber(RawUtility.maskString(aOps.getCardNumber(), 6, 12, 'X'));
				return aOps;
			}).collect(Collectors.toList());


			return ResponseEntity.ok(approvalsOpsApprouve);
			
			*/
			
			
			if(cardType.equalsIgnoreCase("IND")) {
				LOG.info(" Reload Card Type " + cardType);
				
				Optional<List<RawApprovalsOps>> approvalsOpsInitiatorOpt = rawApprovalsOpsRepository
						.findByApprouverAndApprouverStatusIsNotAndCommentsIsLike(approuver,PENDING,cardType.toUpperCase() + "_____");
				
				if (!approvalsOpsInitiatorOpt.isPresent()) {
					return ResponseEntity.internalServerError().body(null);
				}
				
				if(approvalsOpsInitiatorOpt.isPresent()){
					List<RawApprovalsOps> approvalsOpsInitiator = approvalsOpsInitiatorOpt.get().stream().map(aOps -> {
						aOps.setCurrency(RawUtility.getCurrencyInstance(Integer.valueOf(aOps.getCurrency())).getCurrencyCode());
						 aOps.setCardNumber(RawUtility.maskString(aOps.getCardNumber(), 6, 12, 'X'));
						return aOps;
					}).collect(Collectors.toList());
					return ResponseEntity.ok(approvalsOpsInitiator);
				}else {
					return null;
				}
				
			}else if(cardType.equalsIgnoreCase("BLK")) {
				LOG.info(" Reload Card Type " + cardType);
				
				Optional<List<RawApprovalsOps>> approvalsOpsInitiatorOpt = rawApprovalsOpsRepository
						.findByApprouverAndApprouverStatusIsNotAndCommentsIsLike(approuver, PENDING, cardType.toUpperCase() + "_____");
				
				if (!approvalsOpsInitiatorOpt.isPresent()) {
					return ResponseEntity.internalServerError().body(null);
				}
				
				if(approvalsOpsInitiatorOpt.isPresent()){
					List<RawApprovalsOps> approvalsOpsInitiator = approvalsOpsInitiatorOpt.get().stream().map(aOps -> {
						aOps.setCurrency(RawUtility.getCurrencyInstance(Integer.valueOf(aOps.getCurrency())).getCurrencyCode());
						 aOps.setCardNumber(RawUtility.maskString(aOps.getCardNumber(), 6, 12, 'X'));
						return aOps;
					}).collect(Collectors.toList());

					return ResponseEntity.ok(approvalsOpsInitiator);
					
				}else {
					return null;
				}
				
			}else if(cardType.equalsIgnoreCase("PSP")) {
				LOG.info(" Reload Card Type " + cardType);
				
				Optional<List<RawApprovalsOps>> approvalsOpsInitiatorOpt = rawApprovalsOpsRepository
						//.findByapprouverStatusAndCommentsNotLikeAndCommentsNotLike(PENDING,"IND_____%","BLK_____%");
						.findByApprouverAndApprouverStatusIsNotAndCommentsIsNotLike(approuver, PENDING ,"_____");
						//.findByapprouverAndApprouverStatusAndCommentsNotLikeAndCommentsNotLike(approuver,PENDING,"IND_____%","BLK_____%");
				
				if (!approvalsOpsInitiatorOpt.isPresent()) {
					return ResponseEntity.internalServerError().body(null);
				}
				
				if(approvalsOpsInitiatorOpt.isPresent()){
					List<RawApprovalsOps> approvalsOpsInitiator = approvalsOpsInitiatorOpt.get().stream().map(aOps -> {
						aOps.setCurrency(RawUtility.getCurrencyInstance(Integer.valueOf(aOps.getCurrency())).getCurrencyCode());
						 aOps.setCardNumber(RawUtility.maskString(aOps.getCardNumber(), 6, 12, 'X'));
						return aOps;
					}).collect(Collectors.toList());

					return ResponseEntity.ok(approvalsOpsInitiator);
					
				}else {
					return null;
				}
				
			}
			
			
			
			
			
		} catch (Exception e) {

			LOG.error("", e);

			return ResponseEntity.internalServerError().body(null);
		}
		return null;

	}

	@GetMapping(value = "/downloadFile/pdf/{filename:.+}")
	public void downloadPDFResource(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("filename") String fileName, @RequestHeader String referer) throws IOException {

		LOG.info(" statementDir:  {} ", STATEMENTDIR);
		if (referer == null || referer.isEmpty()) {
			throw new RawDetailsNotFindException(" not allow  to download the file");

			// RawNotAuthorizationException
		}
		// RGO
		File file = new File(STATEMENTDIR + fileName);

		LOG.info("path  : {}", file.getAbsolutePath());
		if (file.exists()) {

			// get the mimetype
			String mimeType = URLConnection.guessContentTypeFromName(file.getName());
			if (mimeType == null) {
				// unknown mimetype so set the mimetype to application/octet-stream
				mimeType = "application/octet-stream";
			}

			response.setContentType(mimeType);

			/**
			 * In a regular HTTP response, the Content-Disposition response header is a
			 * header indicating if the content is expected to be displayed inline in the
			 * browser, that is, as a Web page or as part of a Web page, or as an
			 * attachment, that is downloaded and saved locally.
			 * 
			 */

			/**
			 * Here we have mentioned it to show inline
			 */
		

			response.addHeader("Content-Disposition", "attachment; filename=" + fileName);

			response.setContentLength((int) file.length());

			try (InputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
				FileCopyUtils.copy(inputStream, response.getOutputStream());
			} catch (Exception e) {
				LOG.error("", e);
			}
		}

	}
}
