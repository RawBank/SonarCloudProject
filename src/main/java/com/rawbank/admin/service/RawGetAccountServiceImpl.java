/**
 * © Copyright 2021 Rawbank. All Rights Reserved.
 * 
 * Change Section:
 * Programmer			Date
 * Krishna				7/15/2022
 * Initial Coding.
 * 
 * 
 * Created By:
 * @author krishna
 * @since Jul 15, 2022
 * 
 */
package com.rawbank.admin.service;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rawbank.admin.client.stubs.getAccountList.AccountFile;
import com.rawbank.admin.client.stubs.getAccountList.GetAccountList;
import com.rawbank.admin.client.stubs.getAccountList.GetAccountListPortType;
import com.rawbank.admin.client.stubs.getAccountList.GetAccountListRequest;
import com.rawbank.admin.client.stubs.getAccountList.GetAccountListRequestFlow;
import com.rawbank.admin.client.stubs.getAccountList.GetAccountListResponse;
import com.rawbank.admin.client.stubs.getAccountList.GetAccountListResponseFlow;
import com.rawbank.admin.client.stubs.getAccountList.GetAccountResponse;
import com.rawbank.admin.client.stubs.getAccountList.PopulationFile;
import com.rawbank.admin.client.stubs.getAccountList.RequestHeader;
import com.rawbank.admin.client.stubs.getAccountList.RestrictedCustomer;
import com.rawbank.admin.config.RawAdminPortalConfiguration;
import com.rawbank.admin.utility.RawHostNameSslVerifier;

/**
 * @author krishna
 *
 */
@Service
public class RawGetAccountServiceImpl {
	

	private static final Logger LOGGER = LoggerFactory.getLogger(RawGetAccountServiceImpl.class);

	@Autowired
	 private RawAdminPortalConfiguration adminConfig;

	public List<GetAccountResponse> getAccountList(String customerCode) {
		try {
			RawHostNameSslVerifier.disableSslVerification();
	
			String restLoginURL = adminConfig.getSopraURL()+ "/getAccountList?wsdl";
			List<GetAccountResponse> accountList = new ArrayList<>();
			URL urlService = new URL(restLoginURL);
			
			LOGGER.info("************** urlService =>  " + urlService);
			
			GetAccountList getAccountList = new GetAccountList(urlService);
			
			LOGGER.info("************** getAccountList =>  " + getAccountList);
			
			GetAccountListPortType servicePortType = getAccountList.getGetAccountListPortType();
			
			LOGGER.info("************** servicePortType =>  " + servicePortType);

			GregorianCalendar gregorianCalendar = new GregorianCalendar();
			gregorianCalendar.setTime(new Date());

			RequestHeader requestheader = new RequestHeader();
			requestheader.setRequestId("1");
			requestheader.setServiceName("getAccountList");
			requestheader.setTimestamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar));
			requestheader.setUserCode("AUTO");
			
			LOGGER.info("************** requestheader =>  " + requestheader);

			GetAccountListRequest accountRequest = new GetAccountListRequest();
			RestrictedCustomer restrictedCustomer = new RestrictedCustomer();
			restrictedCustomer.setCustomerNumber(customerCode);
			
			LOGGER.info("************** restrictedCustomer =>  " + restrictedCustomer);

			PopulationFile populationFile = new PopulationFile();
			populationFile.setCustomer(restrictedCustomer);
			
			LOGGER.info("************** populationFile =>  " + populationFile);

			AccountFile accountFile = new AccountFile();
			accountFile.setCustomer(populationFile);
			
			LOGGER.info("************** accountFile =>  " + accountFile);

			accountRequest.setAccount(accountFile);
			
			LOGGER.info("************** accountRequest =>  " + accountRequest);

			GetAccountListRequestFlow parameters = new GetAccountListRequestFlow();

			parameters.setRequestHeader(requestheader);
			parameters.setGetAccountListRequest(accountRequest);
			
			LOGGER.info("************** parameters =>  " + parameters);

			GetAccountListResponseFlow response = servicePortType.getAccountList(parameters);
			LOGGER.info("************** GetAccountListResponseFlow response =>  " + response);
			
			GetAccountListResponse responseDetail = response.getGetAccountListResponse();
			LOGGER.info("************** GetAccountListResponse responseDetail =>  " + responseDetail);

			if (response.getResponseStatus().getStatusCode().equals("0")) {
				LOGGER.info("GetAccountList Successfull: {}" , response.getResponseStatus().getStatusCode());
			} else {
				LOGGER.info("GetAccountList Account Not found: {} " , response.getResponseStatus().getStatusCode());
			}

			if(responseDetail != null) {				
				//LOGGER.info("Account found count: {}" , accountList.size());
				for (int i = 0; i < responseDetail.getAccount().size(); i++) {
					/*
					if (!responseDetail.getAccount().get(i).getAccount().getAccountClass().getValue().startsWith("33")) {
						continue;
					}
					*/
					accountList.add(responseDetail.getAccount().get(i));
				}
			}
			
			LOGGER.info("Account found count: {}" , accountList.size());
			
			return accountList;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new ArrayList<>();
		}
	}
}
