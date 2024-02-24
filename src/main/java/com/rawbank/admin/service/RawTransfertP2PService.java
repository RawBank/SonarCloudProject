package com.rawbank.admin.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.rawbank.admin.config.RawAppConfig;
import com.rawbank.admin.exception.RawErrorApiException;
import com.rawbank.admin.model.RawTransferP2PRequest;
import com.rawbank.admin.model.RawTransferP2PResponse;
import com.rawbank.admin.utility.RawDisablesslcheck;
import com.rawbank.admin.utility.RawUtility;

@Service
public class RawTransfertP2PService {


	private static final Logger LOG = LoggerFactory.getLogger(RawTransfertP2PService.class);
	

	public RawTransferP2PResponse postTransferP2P(RawTransferP2PRequest rawTransferP2PRequest) {
		//RawDisablesslcheck.disableSslVerification();

		RestTemplate restTemplate = new RestTemplate();
		if (!rawTransferP2PRequest.getDevise().equalsIgnoreCase("USD")
				&& !rawTransferP2PRequest.getDevise().equalsIgnoreCase("CDF")
				&& !rawTransferP2PRequest.getDevise().equalsIgnoreCase("EUR")) {
			throw new RawErrorApiException("Invalid currency!");
		}



		final String url = RawAppConfig.geturlTransfer();
		

		LOG.info("Transfert url: {} ", url);

		HttpHeaders header = new HttpHeaders();

		header.setContentType(MediaType.APPLICATION_XML);

		rawTransferP2PRequest.setChannel(RawAppConfig.getTransferChannel());
		rawTransferP2PRequest.setToken(RawAppConfig.getTransferToken());
		rawTransferP2PRequest.setReferenceId(RawUtility.getTransactionId("ADM"));
		rawTransferP2PRequest.setRemittance(rawTransferP2PRequest.getMotif());

		LOG.info("=>{}<=", rawTransferP2PRequest);
		try {
			XmlMapper xmlMapper = new XmlMapper();
			String xmlString = null;

				xmlString = xmlMapper.writeValueAsString(rawTransferP2PRequest);


			LOG.info("Tranfert Request Body : {} ", xmlString);
			HttpEntity<String> requestHttpxml = new HttpEntity<>(xmlString, header);

			String result =  restTemplate.postForObject(url, requestHttpxml, String.class);
			
			
			if (StringUtils.isNotEmpty(result)) {
			    // String exists and is not empty
				LOG.info("=========== Txn request was successfuly processed by  Create Transfer API  ===========  ");
				
				LOG.info("Tranfert Result here::  {}", result);

				 RawTransferP2PResponse virementResp;
					virementResp = xmlMapper.readValue(result, RawTransferP2PResponse.class);
					LOG.info("virementResp => {}", virementResp);
					 // 000 : suppose to be the success code of  P2P transfert rest api 
					final String scodeTransfer = RawAppConfig.getScodeTransfer();
					LOG.info("Success  transfer  P2P code : {}", scodeTransfer );
					if (!virementResp.getStatus().equals(scodeTransfer)) {
						LOG.error("  transfer is failling with the error : {}", virementResp.getStatus());
						return null;
					} // 
					
					return virementResp;
				
				
			} else {
			    // String is either null or empty
				
			
				return null;
			}


			


		} catch (JsonProcessingException ex) {
			LOG.error("Transfer P2P Failed:  {} ", ex.getMessage());
			return null;
		}
	}

}
