package com.rawbank.admin.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;

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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.rawbank.admin.config.RawAppConfig;
import com.rawbank.admin.model.RawTokenResponseModel;

@Service
public class RawTokenManagement {


	private static final Logger LOG = LoggerFactory.getLogger(RawTokenManagement.class);
	private static final String URL_TOKEN = "/oauth2/token";

	private String token01 = "";

	public RawTokenResponseModel generateCscToken() {
		RestTemplate restTemplate = new RestTemplate();

		LOG.info("Generation of Token  generateCscToken method");

		String url = RawAppConfig.getCscEndpointBase() + URL_TOKEN;

		String clientSecret = RawAppConfig.getCscClientKey() + ":" + RawAppConfig.getCscSecretKey();

		try {

			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			httpHeaders.add("Authorization", "Basic " + Base64.getEncoder().encodeToString(clientSecret.getBytes()));

			LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<>();
			map.add("grant_type", "client_credentials");

			HttpEntity<LinkedMultiValueMap<String, String>> requestEntity = new HttpEntity<>(map, httpHeaders);

			ResponseEntity<RawTokenResponseModel> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity,
					RawTokenResponseModel.class);

			RawTokenResponseModel rawTokenResponseModel = response.getBody();
			if (rawTokenResponseModel != null) {
				rawTokenResponseModel.setExpiresAt(LocalDateTime.now(ZoneId.systemDefault())
						.plusSeconds(Long.valueOf(rawTokenResponseModel.getExpiresIn())));
				LOG.info(" rawTokenResponseModel =  {} ", rawTokenResponseModel);
				LOG.info(" Token will  expire =  {} ", rawTokenResponseModel.getExpiresAt());
				token01 = rawTokenResponseModel.getRefreshToken();
			}

			return rawTokenResponseModel;

		} catch (RestClientException e) {
			LOG.error("context", e);
		}

		return null;

	}

	public RawTokenResponseModel generateCscTokenBasedonRefresh() {
		RestTemplate restTemplate = new RestTemplate();
		LOG.info("Generation of Token  generateCscTokenBasedonRefresh method");

		String url = RawAppConfig.getCscEndpointBase() + URL_TOKEN;

		String client = RawAppConfig.getCscClientKey();
		try {

			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			httpHeaders.add("Authorization", "Basic " + Base64.getEncoder().encodeToString(client.getBytes()));

			LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<>();

			map.add("grant_type", "refresh_token");
			map.add("refresh_token", token01);

			HttpEntity<LinkedMultiValueMap<String, String>> requestEntity = new HttpEntity<>(map, httpHeaders);

			ResponseEntity<RawTokenResponseModel> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity,
					RawTokenResponseModel.class);

			RawTokenResponseModel rawTokenResponseModel = response.getBody();
			if (rawTokenResponseModel != null) {
				rawTokenResponseModel.setExpiresAt(LocalDateTime.now(ZoneId.systemDefault())
						.plusSeconds(Long.valueOf(rawTokenResponseModel.getExpiresIn())));

				LOG.info(" rawTokenResponseModel = {} ", rawTokenResponseModel);
				LOG.info(" Token will  expire at :  {}", rawTokenResponseModel.getExpiresAt());

				return rawTokenResponseModel;
			}

		} catch (RestClientException e) {
			LOG.error("context", e);
		}

		return null;
	}

	public Boolean checkTokenExpiration(RawTokenResponseModel rawTokenResponseModel) {
		return rawTokenResponseModel.getExpiresAt().isAfter(LocalDateTime.now(ZoneId.systemDefault()));
	}

}
