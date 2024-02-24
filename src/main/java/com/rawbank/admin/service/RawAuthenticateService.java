/**
 * © Copyright 2021 Rawbank. All Rights Reserved.
 * 
 * Change Section:
 * Programmer			Date
 * Krishna				Sep 15, 2022
 * Fixed critical SonarQube Issues.
 * 
 * Krishna				6/21/2022
 * CSC admin portal Project.
 * Initial Coding.
 * 
 * 
 * Created By:
 * @author krishna
 * @since Jun 27, 2022
 * 
 */
package com.rawbank.admin.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rawbank.admin.config.RawAdminPortalConfiguration;
import com.rawbank.admin.exception.RawSSLHandShakeException;
import com.rawbank.admin.exception.RawUnauthorizedException;
import com.rawbank.admin.exception.RawUserLockedException;
import com.rawbank.admin.model.RawLoginResponse;
import com.rawbank.admin.model.RawRoles;
import com.rawbank.admin.utility.RawDisablesslcheck;
import com.rawbank.admin.utility.RawHostNameSslVerifier;

/**
 * @author krishna
 *
 */
@Service
public class RawAuthenticateService {
	private static final Logger LOGGER = LoggerFactory.getLogger(RawAuthenticateService.class);
	private static final String ACTIONCODE = "ActionCode";

	
	@Autowired
	private RawAdminPortalConfiguration adminConfig;

	public Map<Integer, RawLoginResponse> validateLoginCredentials(final String userName, final String password){
		RawDisablesslcheck.disableSslVerification();
		
		RestTemplate restTemplate = new RestTemplate();
		
		RawLoginResponse rawLoginResponse = null;
		Map<Integer, RawLoginResponse> hashMap = new HashMap<>();
		try {
			String restLoginURL = adminConfig.getBaseAuthRestURL() + "/rawbank/auth/v1/user/login";
			HttpHeaders header = new HttpHeaders();
			header.add("Authorization", getAuthorizationCode(userName, password));
			header.add(ACTIONCODE, "07XXX");
			header.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> entity = new HttpEntity<>(header);
			ResponseEntity<String> responseEntity = restTemplate.exchange(restLoginURL, HttpMethod.POST, entity, String.class);
			if (responseEntity.getStatusCodeValue() != 200) {
				throw new RawUnauthorizedException("Invalid Credentials..");
			} else {
				rawLoginResponse = new ObjectMapper().readValue(responseEntity.getBody(), RawLoginResponse.class);
				rawLoginResponse.setPassword(Base64.getEncoder().encodeToString(password.getBytes()));
				hashMap.put(200, rawLoginResponse);
			}
			LOGGER.info("Status : {} ", responseEntity.getStatusCodeValue());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			if (e instanceof ResourceAccessException) {
				throw new RawSSLHandShakeException("SSL Handshake error..");
			} else if(e instanceof HttpClientErrorException) {
				String responseBodyAsString = ((HttpClientErrorException) e).getResponseBodyAsString();
				LOGGER.error("Client Error: {}", responseBodyAsString);
				hashMap.put(((HttpClientErrorException) e).getRawStatusCode(), null);
				return hashMap;
			}else if(e instanceof HttpServerErrorException) {
				String responseBodyAsString = ((HttpServerErrorException) e).getResponseBodyAsString();
				LOGGER.error("Server Error: {}", responseBodyAsString);
				hashMap.put(((HttpServerErrorException) e).getRawStatusCode(), null);
				return hashMap;
			}
		}
		return hashMap;
	}

	public void logout(String username) {
		RestTemplate restTemplate = new RestTemplate();
		String urlLogout = adminConfig.getBaseAuthRestURL() + "/rawbank/auth/v1/user/logout?userName=" + username;
		ResponseEntity<String> responseEntity = null;
		try {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add(ACTIONCODE, "010XXX");
			HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
			responseEntity = restTemplate.exchange(urlLogout, HttpMethod.GET, entity, String.class);
			if (responseEntity.getStatusCodeValue() != 200) {
                  LOGGER.info(" logout is done successfully !!!");
			}
			LOGGER.info("response : {}" , responseEntity);
		} catch (RestClientException e) {
			LOGGER.error(e.getMessage());
		}
	}
	
	public List<RawLoginResponse> getListAdminCsc() throws JsonProcessingException {
		RestTemplate restTemplate = new RestTemplate();
		 //RawHostNameSslVerifier.disableSslVerification();
		String urlAllusers = adminConfig.getBaseAuthRestURL() + "/rawbank/auth/v1/user/all";
		ResponseEntity<String> responseEntity = null;
		List<RawLoginResponse> rawLoginResponsesSelected = new ArrayList<>();
		rawLoginResponsesSelected.clear();
		try {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add(ACTIONCODE, "01X");
			HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
			responseEntity = restTemplate.exchange(urlAllusers, HttpMethod.GET, entity, String.class);

			if (responseEntity.getStatusCodeValue() != 200) {

				LOGGER.error(" Error while calling api to print all users Admin with the error  {}",
						responseEntity.getStatusCodeValue());
				return Collections.emptyList();
			} else {
				// awRoles
				final RawLoginResponse[] allUsers = new ObjectMapper().readValue(responseEntity.getBody(),
						RawLoginResponse[].class);
				if (allUsers.length > 0) {
					for (RawLoginResponse rawLoginResponse : allUsers) {
						for (RawRoles rawRoles : rawLoginResponse.getRoles()) {
							if (rawRoles.getRoleName().trim().equalsIgnoreCase("csc_admin_administrator")) {
								rawLoginResponsesSelected.add(rawLoginResponse);
								LOGGER.info("csc admn user : {}", rawLoginResponse);
							}
						}
					}

					return rawLoginResponsesSelected;
				}

				return Collections.emptyList();
			}
		} catch (RestClientException e) {
			LOGGER.error(e.getMessage());
			return Collections.emptyList();
		}

	}

	private String getAuthorizationCode(final String userName, final String password) {
		Encoder encoder = Base64.getEncoder();
		String encodeFinal = encoder.encodeToString((encoder.encodeToString(userName.getBytes()) + ":" + encoder.encodeToString(password.getBytes())).getBytes());
		return "Basic " + encodeFinal;
	}

	public RawLoginResponse getUserDetails(Object principal) {
		RestTemplate restTemplate = new RestTemplate();
	 //	RawHostNameSslVerifier.disableSslVerification();
		RawLoginResponse rawLoginResponse = null;
		try {
			String restLoginURL = adminConfig.getBaseAuthRestURL() + "/rawbank/auth/v1/user/details?userName="+ principal.toString();
			HttpHeaders header = new HttpHeaders();
			header.add(ACTIONCODE, "02X");
			header.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> entity = new HttpEntity<>(header);
			ResponseEntity<String> responseEntity = restTemplate.exchange(restLoginURL, HttpMethod.GET, entity,
					String.class);
			if (responseEntity.getStatusCodeValue() != 200) {
				return null;
			} else {
				rawLoginResponse = new ObjectMapper().readValue(responseEntity.getBody(), RawLoginResponse.class);
			}
		} catch (ResourceAccessException e) {
			throw new RawSSLHandShakeException("Invalid Credentials..{}");
		} catch (Exception e) {
			LOGGER.error("Error in getUserDetails : {}", e.getMessage());
		} 
		return rawLoginResponse;
	}
}
