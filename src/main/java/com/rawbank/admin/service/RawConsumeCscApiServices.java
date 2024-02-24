/**
 * © Copyright 2021 Rawbank. All Rights Reserved.
 * 
 * Change Section:
 * Programmer			Date
 * Krishna				Sep 15, 2022
 * Fixed critical SonarQube Issues.
 * 
 * Krishna 				Sep 13, 2022
 * Added the API's - getClientDetails and getClientDetails
 * 
 * 
 * Created By:
 * @author Jacques
 * @since Sep 13, 2022
 * 
 */
package com.rawbank.admin.service;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rawbank.admin.config.RawAppConfig;
import com.rawbank.admin.model.RawAmountDueRequestModel;
import com.rawbank.admin.model.RawAmountDueResponseModel;
import com.rawbank.admin.model.RawCardActivityRequestModel;
import com.rawbank.admin.model.RawCardActivityResponseModel;
import com.rawbank.admin.model.RawCardBalanceRequestModel;
import com.rawbank.admin.model.RawCardBalanceResponseModel;
import com.rawbank.admin.model.RawChangeStatusRequestModel;
import com.rawbank.admin.model.RawChangeStatusResponseModel;
import com.rawbank.admin.model.RawClientRequestModel;
import com.rawbank.admin.model.RawClientResponseModel;
import com.rawbank.admin.model.RawGetClientCardResponseModel;
import com.rawbank.admin.model.RawTokenResponseModel;
import com.rawbank.admin.model.RawTransactionCsCRequestModel;
import com.rawbank.admin.model.RawTransactionCsCResponseModel;

@Service
public class RawConsumeCscApiServices {

	
	private static final Logger LOG = LoggerFactory.getLogger(RawConsumeCscApiServices.class);
	private static final String ERROR_MSG = " ERROR {}";
	private static final String URL_CHANGESTATUS_API = "/cards/status";
	private static final String URL_AMOUNT_DUE = "/cards/amount/due";
	private static final String URL_TRANSACTIONS = "/transactions";
	private static final String URL_BALANCE = "/cards/balance";
	private static final String URL_GETCLIENT_CARDS = "/clients/cards";
	private static final String URL_ACTIVITYCARD_API = "/cards/activity";
	private static final String URL_GETCLIENT_DETAILS = "/clients";

	private static final String AUTHORIZATION = "Authorization";
	private static final String BEARER = "Bearer ";

	
	
	public RawConsumeCscApiServices() {
		super();
	}

	public RawChangeStatusResponseModel changeStatus(RawTokenResponseModel rawTokenResponseModel,
			RawChangeStatusRequestModel rawChangeStatusRequestModel) {
		
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		LOG.info(" changeStatus method");

		String url = RawAppConfig.getCscEndpointBase() + URL_CHANGESTATUS_API;
		String message = "";
		
		RawChangeStatusResponseModel rawChangeStatusResponseModel01 = new RawChangeStatusResponseModel();
		

		try {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			httpHeaders.add(AUTHORIZATION, BEARER + rawTokenResponseModel.getAccessToken());

			HttpEntity<RawChangeStatusRequestModel> requestEntity = new HttpEntity<>(rawChangeStatusRequestModel,
					httpHeaders);
			LOG.info("change status url = " +  url);
			LOG.info("change status request = {}", requestEntity);

			ResponseEntity<RawChangeStatusResponseModel> response = restTemplate.exchange(url, HttpMethod.PATCH,
					requestEntity, RawChangeStatusResponseModel.class);

			RawChangeStatusResponseModel rawChangeStatusResponseModel = response.getBody();
			//RawChangeStatusResponseModel rawChangeStatusResponseModel01 = new RawChangeStatusResponseModel();

			if (response.getStatusCode().is2xxSuccessful()) {
				message = "Success =>" + rawChangeStatusRequestModel.getStatus();
				rawChangeStatusResponseModel01.setMessage(message);
				LOG.info(message);
				
				return rawChangeStatusResponseModel01;

			} else {
				
				message = "Failure =>" + rawChangeStatusRequestModel.getStatus();
				rawChangeStatusResponseModel01.setMessage(message);
				LOG.info(message);
				
				
				/*
				message = "Failure =>";
				rawChangeStatusResponseModel01.setMessage(message);
				if (rawChangeStatusResponseModel != null) {
					rawChangeStatusResponseModel01 = new RawChangeStatusResponseModel(message,
							rawChangeStatusResponseModel.getError(),
							rawChangeStatusResponseModel.getErrorDescription());
					
					
				}
				*/
				return rawChangeStatusResponseModel01;
				
				
			}
			//LOG.info("RawChangeStatusResponseModel : {}", rawChangeStatusResponseModel01);
			
			
			//return rawChangeStatusResponseModel;
		} catch (RestClientException e) {
			LOG.error(ERROR_MSG, e.getMessage());
		
			message = "Failure =>" + e.getMessage();
			
			LOG.info("******************** CSC Response from SERVICE************  " + message);
			rawChangeStatusResponseModel01.setMessage(message);
			
			
			
		}
		
		
		
		return rawChangeStatusResponseModel01;
		//return null;
	}
	

	public RawCardActivityResponseModel getTransactionsperCard(RawTokenResponseModel rawTokenResponseModel,
			RawCardActivityRequestModel rawCardActivityRequestModel) {
		RestTemplate restTemplate = new RestTemplate();
		LOG.info(" get Transaction by CardNumber");

		try {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			httpHeaders.add(AUTHORIZATION, BEARER + rawTokenResponseModel.getAccessToken());

			HttpEntity<RawCardActivityRequestModel> requestEntity = new HttpEntity<>(rawCardActivityRequestModel,
					httpHeaders);

			LOG.info("get Transactions  request = {}", requestEntity);

			ResponseEntity<RawCardActivityResponseModel> response = restTemplate.exchange(
					RawAppConfig.getCscEndpointBase() + URL_ACTIVITYCARD_API, HttpMethod.POST, requestEntity,
					RawCardActivityResponseModel.class);

			RawCardActivityResponseModel rawCardActivityResponseModel = response.getBody();

			if (response.getStatusCode().is2xxSuccessful()) {
				LOG.info(" successful retrieve  transactions during the period mentionned");

				return rawCardActivityResponseModel;

			} else {
				return null;
			}

		} catch (RestClientException e) {
			LOG.error(ERROR_MSG, e.getMessage());
		}

		return null;
	}

	// amount due
	public RawAmountDueResponseModel getAmountdue(RawTokenResponseModel rawTokenResponseModel,
			RawAmountDueRequestModel rawAmountDueRequestModel) throws NullPointerException {
		RestTemplate restTemplate = new RestTemplate();

		LOG.info(" get AmountDue  by CardNumber");

		try {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			httpHeaders.add(AUTHORIZATION, BEARER + rawTokenResponseModel.getAccessToken());

			HttpEntity<RawAmountDueRequestModel> requestEntity = new HttpEntity<>(rawAmountDueRequestModel,
					httpHeaders);

			LOG.info("get amount  request {} ", requestEntity);
			ResponseEntity<RawAmountDueResponseModel[]> response = restTemplate.exchange(
					RawAppConfig.getCscEndpointBase() + URL_AMOUNT_DUE, HttpMethod.POST, requestEntity,
					RawAmountDueResponseModel[].class);

			if (response.getStatusCode().is2xxSuccessful()) {
				
				final RawAmountDueResponseModel[] body = response.getBody();
				if (body != null) {
					RawAmountDueResponseModel rawAmountDueResponseModel = body [0];
					LOG.info("response of success getAmoundue :  {}", rawAmountDueResponseModel);

					return rawAmountDueResponseModel;
				}
				return null;
			} else {
				return null;
			}

		} catch (RestClientException e) {
			LOG.error(ERROR_MSG, e.getMessage());
		}

		return null;
	}

	// balance

	public RawCardBalanceResponseModel getBalance(RawTokenResponseModel rawTokenResponseModel,
			RawCardBalanceRequestModel rawCardBalanceRequestModel) {
		RestTemplate restTemplate = new RestTemplate();

		LOG.info("get balance by CardNumber");

		try {

			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			httpHeaders.add(AUTHORIZATION, BEARER + rawTokenResponseModel.getAccessToken());

			HttpEntity<RawCardBalanceRequestModel> requestEntity = new HttpEntity<>(rawCardBalanceRequestModel,
					httpHeaders);

			LOG.info("get card balance  request = {}", requestEntity);

			ResponseEntity<RawCardBalanceResponseModel[]> response = restTemplate.exchange(
					RawAppConfig.getCscEndpointBase() + URL_BALANCE, HttpMethod.POST, requestEntity,
					RawCardBalanceResponseModel[].class);

			
			if (response.getStatusCode().is2xxSuccessful()) {
				
				final RawCardBalanceResponseModel[] body = response.getBody();
				if (body != null) {
					RawCardBalanceResponseModel rawCardBalanceResponseModel = body[0];
					
					LOG.info("rawCardBalanceResponseModel {}", rawCardBalanceResponseModel);
	  
					return rawCardBalanceResponseModel;
				}
				 return null;

			} else {
				return null;
			}

		} catch (RestClientException e) {
			LOG.error(ERROR_MSG, e.getMessage());
		}

		return null;
	}

	// getClientDetails

	public RawGetClientCardResponseModel[] getClientCard(RawTokenResponseModel rawTokenResponseModel,
			String clientNumber) {
		RestTemplate restTemplate = new RestTemplate();

		LOG.info("get client card ");
		try {

			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add(AUTHORIZATION, BEARER + rawTokenResponseModel.getAccessToken());

			HttpEntity<?> requestEntity = new HttpEntity<>(null, httpHeaders);

			final ResponseEntity<RawGetClientCardResponseModel[]> response = restTemplate.exchange(
					RawAppConfig.getCscEndpointBase() + URL_GETCLIENT_CARDS + "?institutionNumber="
							+ RawAppConfig.getCscinstitutionNumber() + "&clientNumber=" + clientNumber,
					HttpMethod.GET, requestEntity, RawGetClientCardResponseModel[].class);

			if (response.getStatusCode().is2xxSuccessful()) {
				final RawGetClientCardResponseModel[] rawGetClientCardResponseModels = response.getBody();
				if (rawGetClientCardResponseModels != null) {
					Arrays.asList(rawGetClientCardResponseModels).stream()
							.forEach(clientCardDetail -> LOG.info(" {}", clientCardDetail));
				}

				return rawGetClientCardResponseModels;

			} else {
				return new RawGetClientCardResponseModel[0];
			}

		} catch (RestClientException e) {
			LOG.error(ERROR_MSG, e.getMessage());
		}

		return new RawGetClientCardResponseModel[0];
	}
	// transactionge

	public RawTransactionCsCResponseModel doTransaction(RawTransactionCsCRequestModel rawTransactionCsCRequestModel,
			RawTokenResponseModel rawTokenResponseModel) {
		RestTemplate restTemplate = new RestTemplate();
		LOG.info("doing  csc Transaction");

		try {

			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			httpHeaders.add(AUTHORIZATION, BEARER + rawTokenResponseModel.getAccessToken());

			HttpEntity<RawTransactionCsCRequestModel> requestEntity = new HttpEntity<>(rawTransactionCsCRequestModel,
					httpHeaders);

			LOG.info("transaction  request = {}", requestEntity);

			ResponseEntity<RawTransactionCsCResponseModel> response = restTemplate.exchange(
					RawAppConfig.getCscEndpointBase() + URL_TRANSACTIONS, HttpMethod.POST, requestEntity,
					RawTransactionCsCResponseModel.class);

			RawTransactionCsCResponseModel rawTransactionCsCResponseModel = response.getBody();
			if (response.getStatusCode().is2xxSuccessful()) {
				LOG.info("RawTransactionCsCResponseModel {}", rawTransactionCsCResponseModel);

				return rawTransactionCsCResponseModel;

			} else {
				return null;
			}

		} catch (RestClientException e) {
			LOG.error(ERROR_MSG, e.getMessage());
		}

		return null;
	}

	public RawClientResponseModel getClientDetails(final RawTokenResponseModel rawTokenResponseModel,
			final String clientNumber) throws JsonProcessingException {
		RestTemplate restTemplate = new RestTemplate();
		LOG.info("GetClientDetails : ");
		try {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add(AUTHORIZATION, BEARER + rawTokenResponseModel.getAccessToken());
			HttpEntity<?> requestEntity = new HttpEntity<>(null, httpHeaders);

			ResponseEntity<String> response = restTemplate.exchange(
					RawAppConfig.getCscEndpointBase() + URL_GETCLIENT_DETAILS + "?institutionNumber="
							+ RawAppConfig.getCscinstitutionNumber() + "&clientNumber=" + clientNumber,
					HttpMethod.GET, requestEntity, String.class);

			if (response.getStatusCode().is2xxSuccessful()) {
				ObjectMapper mapper = new ObjectMapper();

				final RawClientResponseModel rawGetClientResponseModel = mapper.readValue(response.getBody(),
						RawClientResponseModel.class);

				LOG.info("RawGetClientCardResponseModel {}", rawGetClientResponseModel);
				return rawGetClientResponseModel;

			} else if (response.getStatusCode().is4xxClientError()) {
				return null;
			}
		} catch (RestClientException e) {
			LOG.error(ERROR_MSG, e.getMessage());
		}
		return null;
	}

	public void updateClientDetails(RawTokenResponseModel rawTokenResponseModel,
			RawClientRequestModel rawClientRequestModel) {
		RestTemplate restTemplate = new RestTemplate();
		LOG.info("UpdateClientDetails: ");
		try {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			httpHeaders.add(AUTHORIZATION, BEARER + rawTokenResponseModel.getAccessToken());

			HttpEntity<?> requestEntity = new HttpEntity<>(rawClientRequestModel, httpHeaders);

			LOG.info("UpdateClientDetails Request = {}", requestEntity);

			ResponseEntity<String> response = restTemplate.exchange(
					RawAppConfig.getCscEndpointBase() + URL_GETCLIENT_DETAILS, HttpMethod.PATCH, requestEntity,
					String.class);

			if (response.getStatusCode().is2xxSuccessful()) {
				LOG.info("UpdateClientDetails {}", "Success");
			}

		} catch (RestClientException e) {
			LOG.error(ERROR_MSG, e.getMessage());
		}
	}
}
