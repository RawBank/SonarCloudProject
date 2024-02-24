package com.rawbank.admin.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.formula.functions.Rows;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.rawbank.admin.bo.RawAccountBo;
import com.rawbank.admin.client.stubs.getAccountList.GetAccountResponse;
import com.rawbank.admin.config.RawAppConfig;
import com.rawbank.admin.exception.RawDetailsNotFindException;
import com.rawbank.admin.model.RawAmountDueRequestModel;
import com.rawbank.admin.model.RawAmountDueResponseModel;
import com.rawbank.admin.model.RawCardBalanceRequestModel;
import com.rawbank.admin.model.RawCardBalanceResponseModel;
import com.rawbank.admin.model.RawCustomerInfoModel;
import com.rawbank.admin.model.RawEmailObject;
import com.rawbank.admin.model.RawGetClientCardResponseModel;
import com.rawbank.admin.model.RawLoginResponse;
import com.rawbank.admin.model.RawReloadDetails;
import com.rawbank.admin.model.RawTokenResponseModel;
import com.rawbank.admin.oracle.entity.RawApprovalsOps;
import com.rawbank.admin.oracle.entity.RawBulkRecharge;
import com.rawbank.admin.oracle.entity.RawCscConfig;
import com.rawbank.admin.oracle.repository.RawApprovalsOpsRepository;
import com.rawbank.admin.oracle.repository.RawBulkRechargeRepository;
import com.rawbank.admin.oracle.repository.RawCardStatusChangeRepository;
import com.rawbank.admin.oracle.repository.RawCardStatusCodeRepository;
import com.rawbank.admin.oracle.repository.RawCscConfigRepository;
import com.rawbank.admin.service.RawAuthenticateService;
import com.rawbank.admin.service.RawConsumeCscApiServices;
import com.rawbank.admin.service.RawEmailService;
import com.rawbank.admin.service.RawGetAccountServiceImpl;
import com.rawbank.admin.service.RawTokenManagement;
import com.rawbank.admin.sqlserver.entity.RawClientCards;
import com.rawbank.admin.sqlserver.entity.RawClientDetails;
import com.rawbank.admin.sqlserver.entity.RawClientFinancials;
import com.rawbank.admin.sqlserver.repository.RawClientDetailsRepository;
import com.rawbank.admin.sqlserver.repository.RawClientFinancialsRepository;
import com.rawbank.admin.sqlserver.repository.RawClientRepository;
import com.rawbank.admin.utility.ModifyExcelFileName;
import com.rawbank.admin.utility.RawAccountTransform;
import com.rawbank.admin.utility.RawGetAllBulkFiles;
import com.rawbank.admin.utility.RawUtility;



@Controller
public class RawFileUploadBulkRechargeController {
	private static final Logger logger = LoggerFactory.getLogger(RawFileUploadBulkRechargeController.class);
	
	@Autowired
	RawBulkRechargeRepository rawBulkRechargeRepository;
	
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
	private RawCscConfigRepository rawCscConfigRepository;
	
	@Autowired
	private RawApprovalsOpsRepository rawApprovalsOpsRepository;
	
	@Autowired
	private RawAuthenticateService rawAuthenticateService;
	
	@Autowired
	private RawBulkRechargeRepository raBulkRechargeRepository;
	
	@Autowired
	private RawEmailService rawEmailService;
	
	private static final String PENDING = "PENDING";
	private static final String RELOAD = "RELOAD";
	private SimpleDateFormat formatter1 = new SimpleDateFormat("yyyyMMdd");
	// format date for the reload api call
	private SimpleDateFormat formatter2 = new SimpleDateFormat("yyMM");
	
	//RawReloadDetails rawReloadDetails = new RawReloadDetails();
	
	String excelFileSuffix = "";
	
	@Value("${email.test}")
	private String emailTest;
	
	//FILE VALIDATIONS
    //Number of files and file size
    //File extension
   //MIME type i.e. Content-Type of a file.

	@PostMapping("/upload_bulk_file")
	public ModelAndView fileUpload(@RequestParam("file") MultipartFile[] files) throws ParseException {

		if (files.length <=0) {
			return new ModelAndView("home_prepaid_cards", "messageError", "SVP veuillez selectionner les fichiers");
		}

		try {
			Long fileSize = files[0].getSize();
			Long fileSize2 = files[1].getSize();
			
			String extension = FilenameUtils.getExtension(files[0].getOriginalFilename());
			String extension2 = FilenameUtils.getExtension(files[1].getOriginalFilename());
			
			String fileName = files[0].getOriginalFilename();
			String fileName2 = files[1].getOriginalFilename();
			
			//Object to check MIMEType of this file
			//Tika tika = new Tika();
			//String mimeType = tika.detect((InputStream) file);
			
			logger.info("File size is " + fileSize);
			logger.info("File extension is " + extension);
			logger.info("File2 size is " + fileSize2);
			logger.info("File2 extension is " + extension2);
			//logger.info("File contentType is " + mimeType);
			
			if(fileSize > 3200000 && fileSize2 > 3200000) {
			    return new ModelAndView("home_prepaid_cards", "messageError", "ECHEC ........ La taille du fichier 1 ne doit pas depasser 3MB " + "("+fileSize+")" + ".....La taille du fichier 2 ne doit pas depasser 3MB  " +"("+fileSize2+")");
			}else if(fileSize > 3200000 && fileSize2 <= 3200000) {
				 return new ModelAndView("home_prepaid_cards", "messageError", "ECHEC ........ La taille du fichier 1 ne doit pas depasser 3MB " +"("+ fileSize+")" );
			}else if(fileSize <= 3200000 && fileSize2 > 3200000) {
				return new ModelAndView("home_prepaid_cards", "messageError", "ECHEC ........ La taille du fichier 2 ne doit pas depasser 3MB " +"("+ fileSize2+")" );
			}else if(!isSupportedExtension(extension) && !isSupportedExtension2(extension2)) {
				return new ModelAndView("home_prepaid_cards", "messageError", "ECHEC ........ Extension du fichier 1 non supporté " + "("+extension+")" + "......... Extension du fichier 2 non supporté"+"("+extension2+")");
			}else if(!isSupportedExtension(extension) && isSupportedExtension2(extension2)) {
				return new ModelAndView("home_prepaid_cards", "messageError", "ECHEC ........ Extension du fichier 1 non supporté " + "("+extension+")");
			}else if(isSupportedExtension(extension) && !isSupportedExtension2(extension2)) {
				return new ModelAndView("home_prepaid_cards", "messageError", "ECHEC ........ Extension du fichier 2 non supporté " + "("+extension2+")");
			}else {
				
				ModifyExcelFileName modifyExcelFileName = new ModifyExcelFileName();
				excelFileSuffix = SecurityContextHolder.getContext().getAuthentication().getName() +
						modifyExcelFileName.excelFileNameModifier();
				
				String fileModifiedName  = excelFileSuffix + fileName;
				String fileModifiedName2  = excelFileSuffix+"_" + fileName2;
				
				
				// read and write the file to the selected path folder
				byte[] bytesJustificatif = files[1].getBytes();
				Path pathJustificatif = Paths.get(RawAppConfig.getUploadFilePath() + fileModifiedName2);
				Files.write(pathJustificatif, bytesJustificatif);
				
				// read and write the file to the selected path folder
				byte[] bytes2 = files[1].getBytes();
				Path path2 = Paths.get(RawAppConfig.getUploadFilePath2() + fileModifiedName2);
				Files.write(path2, bytes2);
				
				
				// read and write the file to the selected path folder
				byte[] bytes = files[0].getBytes();
				Path path = Paths.get(RawAppConfig.getUploadFilePath2() + fileModifiedName);
				Files.write(path, bytes);
				
				logger.info("File was uploaded in the Path " + path);
				
				Path pathOutPutBulk = Paths.get(RawAppConfig.getUploadFilePathOutPutBulk() + fileModifiedName);
				Path pathOutPutBulkPieceJustificative = Paths.get(RawAppConfig.getUploadFilePathOutPutBulk() + fileModifiedName2);
				
				
				//Read exel file and save data in temp table in DB
				try {
					
					Workbook workbook = WorkbookFactory.create(new File(""+path));
					logger.info("workbook has " + workbook.getNumberOfSheets() + " Sheets");
					
					for(Sheet sheet:workbook) {
						
						logger.info("The sheet name is  " + sheet.getSheetName());
						
						boolean isFirst = true;
						
						
						DataFormatter dataFormatter = new  DataFormatter();
						
						for(Row row:sheet) {
							
							//Skip the first row that contains sheet title
							if(!isFirst) {
								
								String numClient = row.getCell(0).getStringCellValue();
								String montant = row.getCell(1).getStringCellValue();
								String comptesrc = row.getCell(2).getStringCellValue();
								String currency = row.getCell(3).getStringCellValue();
								String comments = row.getCell(4).getStringCellValue();
								String cardNumber = row.getCell(5).getStringCellValue();
								
								
								RawBulkRecharge rawBulkRecharge = new RawBulkRecharge();
								rawBulkRecharge.setCardNumber(cardNumber);
								rawBulkRecharge.setComments(comments);
								rawBulkRecharge.setComptesrc(comptesrc);
								rawBulkRecharge.setCurrency(currency);
								rawBulkRecharge.setInitiateur(SecurityContextHolder.getContext().getAuthentication().getName());
								rawBulkRecharge.setMontant(montant);
								rawBulkRecharge.setNomFichier(fileModifiedName);
								rawBulkRecharge.setNumClient(numClient);
								
								
								logger.info("Retrieved data from EXCEL are:  " + rawBulkRecharge);
								
								raBulkRechargeRepository.save(rawBulkRecharge);
								
							}else {
								isFirst = false;
							}
							
							
							
						}
						
					}
					
					
					
					
					
					
				} catch (Exception e) {
					logger.info("erreur du fichier excel => Vérifier les colonnes Excel:  " + e);
					return new ModelAndView("home_prepaid_cards", "messageError", "ECHEC ........ Vérifiez les colonnes du fichier Excel.");
				}
				
				
				
				// get excel db bulk details by nomFichier
				List<RawBulkRecharge> listClientCards = rawBulkRechargeRepository.findByNomFichier(fileModifiedName);
				logger.info("List bulk excel details from DB =  " + listClientCards);
				
				
				
				
				String cardNumber = "";
				String clientNumber ="";
			
				String completeAccountWithKey ="";
				String branchlessAccount = "";
				String currency ="";
				String thisComment ="";
				String srcAccount = "";
				Float rechargeAmount;
				GetAccountResponse filteredAccountObject = null;
				String accountAndKey = "";
				List<String> filteredAccount=new ArrayList<String>();  
				
				
				RawTokenResponseModel rawTokenResponseModel = rawTokenManagement.generateCscToken();
				RawReloadDetails reloadDetails = new RawReloadDetails();
				
				
				List<RawCustomerInfoModel> customerlist = new ArrayList<>();
				
				for(int i = 0; i < listClientCards.size(); i++) {
					
					branchlessAccount = listClientCards.get(i).getComptesrc().substring(4);
					List<GetAccountResponse> accountList = rawGetAccountServiceImpl.getAccountList(listClientCards.get(i).getNumClient());
					clientNumber = listClientCards.get(i).getNumClient();
					cardNumber = listClientCards.get(i).getCardNumber();
					currency = listClientCards.get(i).getCurrency();
					thisComment = listClientCards.get(i).getComments();
					srcAccount = listClientCards.get(i).getComptesrc();
					String filteredAccount2 = null;
					rechargeAmount = Float.parseFloat(listClientCards.get(i).getMontant());
					
					logger.info("============ CSC CLIENT NUMBER  ==== " + clientNumber);
					logger.info("============ CARD NUMBER  ==== " + cardNumber);
					logger.info("============ CURRENCY  ==== " + currency);
					
				

					// get csc client details based on csc client number
					RawGetClientCardResponseModel[] clientCards = consumeCscApiServices
							.getClientCard(rawTokenResponseModel, clientNumber);
				
					for (int v = 0; v < clientCards.length; v++) {
						  System.out.println("CardNumber " + clientCards[v].getCardNumber());
						  System.out.println("clientNumber " + cardNumber);
						  
						if(clientCards[v].getCardNumber().equalsIgnoreCase(cardNumber)) {
						
							  RawAmountDueRequestModel rawAmountDueRequestModel = new RawAmountDueRequestModel(cardNumber,
										String.valueOf(RawUtility.getCurrencyInstance(currency).getNumericCode()));
								RawAmountDueResponseModel amountdue = consumeCscApiServices.getAmountdue(rawTokenResponseModel,
										rawAmountDueRequestModel);  

								RawCardBalanceRequestModel rawCardBalanceRequestModel = new RawCardBalanceRequestModel(cardNumber);
								RawCardBalanceResponseModel balance = consumeCscApiServices.getBalance(rawTokenResponseModel,
										rawCardBalanceRequestModel);
						
								
								
								Float amountDue = Float
										.parseFloat(amountdue.getAmountDue().substring(1, amountdue.getAmountDue().length()));

								reloadDetails.setAmountDue(amountDue / 100);
								reloadDetails.setBalance(Math.abs(Float.valueOf(balance.getAvailableAmount())));
								reloadDetails.setCardExpiredDate(clientCards[v].getExpiryDate());
								reloadDetails.setCardNumber(clientCards[v].getCardNumber());
								reloadDetails.setCardStatus(clientCards[v].getCardStatus().equals("001") ? "Active" : "Temporay Blocked");
								reloadDetails.setClientName(clientCards[v].getEmbossLine1());
								reloadDetails.setClientNumber(clientNumber);
								reloadDetails.setLimitBalance(Math.abs(Float.valueOf(balance.getLimitAmount())));
								reloadDetails.setPendingAmount(Float.parseFloat(balance.getPendingAuthsAmount()));
								reloadDetails.setReloadComment(thisComment);
								reloadDetails.setCurrency(currency);
								reloadDetails.setCscAccountNumber(amountdue.getAccountNumber());
								reloadDetails.setPaidAmount(rechargeAmount);
								reloadDetails.setCustAccount(srcAccount);
								logger.info("reload model : {}", reloadDetails);
								logger.info("============ FIRST PART OF DATA TO BE SENT IN DB  ==== " + reloadDetails);
							
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
								
							
								approvalsOps.setAaSrcAccount(srcAccount);
								
								

								approvalsOps.setAaStatus(null);
								approvalsOps.setAaTransId(null);
								approvalsOps.setAcquirerCountry(null);

								// must have 12 digits fill with zero at left
								DecimalFormat df = new DecimalFormat("000000000000");
								approvalsOps.setTransamount(df.format(reloadDetails.getPaidAmount() * 100));
								approvalsOps.setApprouver(null);
								approvalsOps.setAuthCode(null);
								approvalsOps.setCardNumber(reloadDetails.getCardNumber());
								approvalsOps.setComments(RawAppConfig.getMcPrepaidBulkVal()+reloadDetails.getReloadComment());

								// converting currency string to numeric code USD=> 840
								approvalsOps.setCurrency("" + RawUtility.getCurrencyInstance(reloadDetails.getCurrency()).getNumericCode());
								approvalsOps.setFileName(fileModifiedName2);

								approvalsOps.setInitiator(SecurityContextHolder.getContext().getAuthentication().getName());

								approvalsOps.setInstitutionNumber(RawAppConfig.getCscinstitutionNumber());
								approvalsOps.setOpsType(RELOAD);
								approvalsOps.setProccode(confprocCode.getConfvalue()); //
								approvalsOps.setResponseCode(null);
								approvalsOps.setResponseMessage(null);
								approvalsOps.setRetrievalReference(RawUtility.generateRandomSequence(12));
								approvalsOps.setTransactionType(conftransactionType.getConfvalue());
								approvalsOps.setCscAccountNumber(reloadDetails.getCscAccountNumber());
								Date parse = formatter1.parse(reloadDetails.getCardExpiredDate());
								approvalsOps.setExpiryDate(formatter2.format(parse));
								approvalsOps.setApprouverStatus(PENDING);
								approvalsOps.setCustomerName(reloadDetails.getClientName());
								logger.info("saving apporoval nuplet in db ...");
								rawApprovalsOpsRepository.save(approvalsOps);
								logger.info("{}", approvalsOps);
								logger.info("save  done  ...");
								RawCustomerInfoModel rawCustomerInfoModel = new RawCustomerInfoModel();
								// because customerNumber is required to populate date in customerCardList page
								rawCustomerInfoModel.setCustomerNumber(reloadDetails.getClientNumber());
								
								
								// send email of notification
								RawEmailObject rawEmailObject = new RawEmailObject();
								rawEmailObject.setRr(approvalsOps.getRetrievalReference());
								rawEmailObject.setUserName("La transaction CSC a été initiée par  : " + approvalsOps.getInitiator());

								//Set CC users to mail  =====email.test=josue.kazadi@rawbank.cd and the current connected user/Initiator
								rawEmailObject.setSetCC(
										emailTest + "," + rawAuthenticateService.getUserDetails(approvalsOps.getInitiator().trim()).getEmail());
								
								//Set To mail all users that role is admin in order to send them emails
								rawEmailObject.setSetTo(rawAuthenticateService.getListAdminCsc().stream().map(RawLoginResponse::getEmail)
										.collect(Collectors.joining(",")));

								rawEmailObject.setComment("Montant : " + reloadDetails.getPaidAmount() + " " + reloadDetails.getCurrency()
										+ ", \n \n  Compte de debit du client : " + approvalsOps.getAaSrcAccount()
										+ "\n \n, Numéro de la carte : " + RawUtility.maskString(approvalsOps.getCardNumber(),6, 12, 'X'));
								// emaiil sending
								rawEmailService.sendMail(rawEmailObject);
								
							
							
						}else {
							
							logger.info("============ INCORRECT CARDS. PLEASE CHECK YOUR BULK DATA  ==== " + reloadDetails);
							//return new ModelAndView("home_prepaid_cards", "messageError", "CARTE DU FICHIER EXCEL INVALIDE " + clientCards[v].getCardNumber() +" <===> " + cardNumber);
						}
	
					}
					
				}
				
				
				//Remove this file from inputbulk to outputbulk
				Path sourcepath = path;
				Path destinationepath = pathOutPutBulk;
				logger.info("**********File destination after process is => " + destinationepath);
			    Files.copy(sourcepath, destinationepath, StandardCopyOption.REPLACE_EXISTING);
			    // Delete file from inputbulk after process
			    
			    
			    //Remove this file from inputbulk to outputbulk
				Path sourcepath2 = path2;
				Path destinationepath2 = pathOutPutBulkPieceJustificative;
				logger.info("**********File 2 destination after process is => " + destinationepath2);
			    Files.copy(sourcepath2, destinationepath2, StandardCopyOption.REPLACE_EXISTING);
			    // Delete file from inputbulk after process
			    
			    //pathOutPutBulkPieceJustificative
			    
			    
			    try {
					Files.delete(path);
					Files.delete(path2);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				
				
				return new ModelAndView("home_prepaid_cards", "message", "Fichier " + fileModifiedName + " et le fichier " + fileModifiedName2 + " ont été bien envoyé");
		      
				//return new ModelAndView("home_prepaid_cards", "fichiers", RawGetAllBulkFiles.getAllBulkFiles());
				
			
				//Send one email to notify admins
			
			}
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new ModelAndView("home_prepaid_cards", "message", "Ton fichier a été bien envoyé ");
	}


	private boolean isSupportedExtension(String extension) {
		return extension != null && (extension.equals("xlsx") || extension.equals("xls"));
		
		/*
		return extension != null && (
	              extension.equals("png")
	              || extension.equals("jpg")
	              || extension.equals("jpeg")
	              || extension.equals("pdf"));
		*/
	}
	
	
	private boolean isSupportedExtension2(String extension) {
		return extension != null && (
	              extension.equals("png")
	              || extension.equals("jpg")
	              || extension.equals("jpeg")
	              || extension.equals("pdf"));
		
	}
	
	

}
