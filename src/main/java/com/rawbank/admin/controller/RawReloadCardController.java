/**
* Copyright 2022 Rawbank. All Rights Reserved.
* 
 * Change Section:
* Programmer               Date
*
* 
 * 
 * Created By:
* @author jacquesk
* @since 8 nov. 2022
* 
 */

package com.rawbank.admin.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rawbank.admin.config.RawAppConfig;
import com.rawbank.admin.model.RawCustomerInfoModel;
import com.rawbank.admin.model.RawEmailObject;
import com.rawbank.admin.model.RawLoginResponse;
import com.rawbank.admin.model.RawReloadDetails;
import com.rawbank.admin.oracle.entity.RawApprovalsOps;
import com.rawbank.admin.oracle.entity.RawCscConfig;
import com.rawbank.admin.oracle.repository.RawApprovalsOpsRepository;
import com.rawbank.admin.oracle.repository.RawCscConfigRepository;
import com.rawbank.admin.service.RawAuthenticateService;
import com.rawbank.admin.service.RawEmailService;
import com.rawbank.admin.service.RawMCPrepaidConfigServiceImpl;
import com.rawbank.admin.utility.RawUtility;

/**
 * @author jacquesk
 *
 */
@Controller
public class RawReloadCardController {

	/**
	 * 
	 */
	@Value("${email.test}")
	private String emailTest;

	private SimpleDateFormat formatter1 = new SimpleDateFormat("yyyyMMdd");
	// format date for the reload api call
	private SimpleDateFormat formatter2 = new SimpleDateFormat("yyMM");

	@Autowired
	private RawApprovalsOpsRepository rawApprovalsOpsRepository;

	@Autowired
	private RawEmailService rawEmailService;

	@Autowired
	private RawAuthenticateService rawAuthenticateService;
	@Autowired
	private RawMCPrepaidConfigServiceImpl rawMCPrepaidConfigServiceImpl;

	@Autowired
	private RawCscConfigRepository rawCscConfigRepository;
	private static final String PENDING = "PENDING";
	private static final String RELOAD = "RELOAD";
	private static final Logger LOGGER = LoggerFactory.getLogger(RawReloadCardController.class);

	@PostMapping(path = "/reloadCard")
	public String reloadCard(@ModelAttribute("reloaddetails") RawReloadDetails rawReloadDetails,
			RedirectAttributes redirectAttrs, Locale locale, BindingResult result,
			@RequestParam("file") MultipartFile file, HttpSession session) throws ParseException, IOException {

		String fileName = file.getOriginalFilename();
		byte[] bytes = file.getBytes();
		Path path = Paths.get(RawAppConfig.getUploadFilePath() + fileName);
		Files.write(path, bytes);

		LOGGER.info("submitting reload form .... ");
		LOGGER.info("{}", rawReloadDetails);

		final Optional<RawCscConfig> confdstAccountOpt = rawCscConfigRepository
				.findByConfkey("csc-trusted-account-usd");
		if (!confdstAccountOpt.isPresent()) {
			throw new NotFoundException(" configuration of destination account not found");
		}

		final Optional<RawCscConfig> confprocCodeOpt = rawCscConfigRepository.findByConfkey("procCode");

		if (!confprocCodeOpt.isPresent()) {
			throw new NotFoundException(" configuration of procCode  not found");
		}

		final Optional<RawCscConfig> conftransactionTypeOpt = rawCscConfigRepository.findByConfkey("transactionType");

		if (!conftransactionTypeOpt.isPresent()) {
			throw new NotFoundException(" configuration of transactionType  not found");
		}

		final RawCscConfig confdstAccount = confdstAccountOpt.get();
		final RawCscConfig confprocCode = confprocCodeOpt.get();
		final RawCscConfig conftransactionType = conftransactionTypeOpt.get();

		RawApprovalsOps approvalsOps = new RawApprovalsOps();
		approvalsOps.setAaDestAccount(confdstAccount.getConfvalue().trim());

		String[] account = rawReloadDetails.getCustAccount().split("-");
		approvalsOps.setAaSrcAccount((account[0] + "" + account[1]).trim());

		approvalsOps.setAaStatus(null);
		approvalsOps.setAaTransId(null);
		approvalsOps.setAcquirerCountry(null);

		// must have 12 digits fill with zero at left
		DecimalFormat df = new DecimalFormat("000000000000");
		approvalsOps.setTransamount(df.format(rawReloadDetails.getPaidAmount() * 100));
		approvalsOps.setApprouver(null);
		approvalsOps.setAuthCode(null);
		approvalsOps.setCardNumber(rawReloadDetails.getCardNumber());
		approvalsOps.setComments(rawReloadDetails.getReloadComment());

		// converting currency string to numeric code USD=> 840
		approvalsOps.setCurrency("" + RawUtility.getCurrencyInstance(rawReloadDetails.getCurrency()).getNumericCode());
		approvalsOps.setFileName(fileName);

		approvalsOps.setInitiator(SecurityContextHolder.getContext().getAuthentication().getName());

		approvalsOps.setInstitutionNumber(RawAppConfig.getCscinstitutionNumber());
		approvalsOps.setOpsType(RELOAD);
		approvalsOps.setProccode(confprocCode.getConfvalue()); //
		approvalsOps.setResponseCode(null);
		approvalsOps.setResponseMessage(null);
		approvalsOps.setRetrievalReference(RawUtility.generateRandomSequence(12));
		approvalsOps.setTransactionType(conftransactionType.getConfvalue());
		approvalsOps.setCscAccountNumber(rawReloadDetails.getCscAccountNumber());
		Date parse = formatter1.parse(rawReloadDetails.getCardExpiredDate());
		approvalsOps.setExpiryDate(formatter2.format(parse));
		approvalsOps.setApprouverStatus(PENDING);
		approvalsOps.setCustomerName(rawReloadDetails.getClientName());
		LOGGER.info("saving apporoval nuplet in db ...");
		rawApprovalsOpsRepository.save(approvalsOps);
		LOGGER.info("{}", approvalsOps);
		LOGGER.info("save  done  ...");
		RawCustomerInfoModel rawCustomerInfoModel = new RawCustomerInfoModel();
		// because customerNumber is required to populate date in customerCardList page
		rawCustomerInfoModel.setCustomerNumber(rawReloadDetails.getClientNumber());
		// send email of notification
		RawEmailObject rawEmailObject = new RawEmailObject();
		rawEmailObject.setRr(approvalsOps.getRetrievalReference());
		rawEmailObject.setUserName("La transaction CSC a été initiée par  : " + approvalsOps.getInitiator());
		rawEmailObject.setSetCC(
				emailTest + "," + rawAuthenticateService.getUserDetails(approvalsOps.getInitiator().trim()).getEmail());
		rawEmailObject.setSetTo(rawAuthenticateService.getListAdminCsc().stream().map(RawLoginResponse::getEmail)
				.collect(Collectors.joining(",")));
		rawEmailObject.setComment("Montant : " + rawReloadDetails.getPaidAmount() + " " + rawReloadDetails.getCurrency()
				+ ", \n \n  Compte de debit du client : " + approvalsOps.getAaSrcAccount()
				+ "\n \n, Numéro de la carte : " + RawUtility.maskString(approvalsOps.getCardNumber(),6, 12, 'X'));
		// emaiil sending
		rawEmailService.sendMail(rawEmailObject);

		redirectAttrs.addFlashAttribute("customerInfo", rawCustomerInfoModel);
		//
		final String reloadMessage = "Transaction  avec ID: " + approvalsOps.getRetrievalReference()
				+ " est initiée  avec succès";
		session.setAttribute("reloadMessage01", reloadMessage);
		return "redirect:/customerCardList";
	}
	
	
	
	
	@PostMapping(path = "/reloadCardMCindividuel")
	public String reloadCardMCindividuel(@ModelAttribute("reloaddetails") RawReloadDetails rawReloadDetails,
			RedirectAttributes redirectAttrs, Locale locale, BindingResult result,
			@RequestParam("file") MultipartFile file, HttpSession session) throws ParseException, IOException {

		String fileName = file.getOriginalFilename();
		byte[] bytes = file.getBytes();
		Path path = Paths.get(RawAppConfig.getUploadFilePath() + fileName);
		Files.write(path, bytes);

		LOGGER.info("submitting reload form .... ");
		LOGGER.info("{}", rawReloadDetails);

		String confdstAccountOpt = rawMCPrepaidConfigServiceImpl.getTrustedPrepaidCreditAccount();
		
		
		
		if (confdstAccountOpt.isEmpty()) {
			throw new NotFoundException(" configuration of destination account not found");
		}

		final Optional<RawCscConfig> confprocCodeOpt = rawCscConfigRepository.findByConfkey("procCode");

		if (!confprocCodeOpt.isPresent()) {
			throw new NotFoundException(" configuration of procCode  not found");
		}

		final Optional<RawCscConfig> conftransactionTypeOpt = rawCscConfigRepository.findByConfkey("transactionType");

		if (!conftransactionTypeOpt.isPresent()) {
			throw new NotFoundException(" configuration of transactionType  not found");
		}

		
		final RawCscConfig confprocCode = confprocCodeOpt.get();
		final RawCscConfig conftransactionType = conftransactionTypeOpt.get();

		RawApprovalsOps approvalsOps = new RawApprovalsOps();
		approvalsOps.setAaDestAccount(confdstAccountOpt.trim());

		approvalsOps.setAaSrcAccount(rawReloadDetails.getCustAccount());

		approvalsOps.setAaStatus(null);
		approvalsOps.setAaTransId(null);
		approvalsOps.setAcquirerCountry(null);

		// must have 12 digits fill with zero at left
		DecimalFormat df = new DecimalFormat("000000000000");
		approvalsOps.setTransamount(df.format(rawReloadDetails.getPaidAmount() * 100));
		approvalsOps.setApprouver(null);
		approvalsOps.setAuthCode(null);
		approvalsOps.setCardNumber(rawReloadDetails.getCardNumber());
		approvalsOps.setComments(RawAppConfig.getMcPrepaidIndividualVal() + rawReloadDetails.getReloadComment());

		// converting currency string to numeric code USD=> 840
		approvalsOps.setCurrency("" + RawUtility.getCurrencyInstance(rawReloadDetails.getCurrency()).getNumericCode());
		approvalsOps.setFileName(fileName);

		approvalsOps.setInitiator(SecurityContextHolder.getContext().getAuthentication().getName());

		approvalsOps.setInstitutionNumber(RawAppConfig.getCscinstitutionNumber());
		approvalsOps.setOpsType(RELOAD);
		approvalsOps.setProccode(confprocCode.getConfvalue()); //
		approvalsOps.setResponseCode(null);
		approvalsOps.setResponseMessage(null);
		approvalsOps.setRetrievalReference(RawUtility.generateRandomSequence(12));
		approvalsOps.setTransactionType(conftransactionType.getConfvalue());
		approvalsOps.setCscAccountNumber(rawReloadDetails.getCscAccountNumber());
		Date parse = formatter1.parse(rawReloadDetails.getCardExpiredDate());
		approvalsOps.setExpiryDate(formatter2.format(parse));
		approvalsOps.setApprouverStatus(PENDING);
		approvalsOps.setCustomerName(rawReloadDetails.getClientName());
		LOGGER.info("saving apporoval nuplet in db ...");
		rawApprovalsOpsRepository.save(approvalsOps);
		LOGGER.info("{}", approvalsOps);
		LOGGER.info("save  done  ...");
		RawCustomerInfoModel rawCustomerInfoModel = new RawCustomerInfoModel();
		// because customerNumber is required to populate date in customerCardList page
		rawCustomerInfoModel.setCustomerNumber(rawReloadDetails.getClientNumber());
		// send email of notification
		RawEmailObject rawEmailObject = new RawEmailObject();
		rawEmailObject.setRr(approvalsOps.getRetrievalReference());
		rawEmailObject.setUserName("La transaction CSC a été initiée par  : " + approvalsOps.getInitiator());
		rawEmailObject.setSetCC(
				emailTest + "," + rawAuthenticateService.getUserDetails(approvalsOps.getInitiator().trim()).getEmail());
		rawEmailObject.setSetTo(rawAuthenticateService.getListAdminCsc().stream().map(RawLoginResponse::getEmail)
				.collect(Collectors.joining(",")));
		rawEmailObject.setComment("Montant : " + rawReloadDetails.getPaidAmount() + " " + rawReloadDetails.getCurrency()
				+ ", \n \n  Compte de debit du client : " + approvalsOps.getAaSrcAccount()
				+ "\n \n, Numéro de la carte : " + RawUtility.maskString(approvalsOps.getCardNumber(),6, 12, 'X'));
		// emaiil sending
		rawEmailService.sendMail(rawEmailObject);

		redirectAttrs.addFlashAttribute("customerInfo", rawCustomerInfoModel);
		//
		final String reloadMessage = "Transaction  avec ID: " + approvalsOps.getRetrievalReference()
				+ " est initiée  avec succès";
		session.setAttribute("reloadMessage01", reloadMessage);
		return "redirect:/customerCardListMCPrepaid";
	}

}
