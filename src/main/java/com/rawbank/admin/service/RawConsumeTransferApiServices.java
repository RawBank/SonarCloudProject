/**
* Copyright 2022 Rawbank. All Rights Reserved.
* 
* Change Section:
* Programmer            Date
* Krishna				Sep 15, 2022
* Fixed critical SonarQube Issues.
* 
* jacquesk				24 août 2022
* CSC admin portal Project.
* Initial Coding.
* 
* 
* Created By:
* @author jacquesk
* @since 24 août 2022
* 
*/
package com.rawbank.admin.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.rawbank.admin.config.RawAppConfig;
import com.rawbank.admin.model.RawA2APojoRequestModel;
import com.rawbank.admin.model.RawA2APojoResponseModel;

/**
 * @author jacquesk
 *
 */

public class RawConsumeTransferApiServices {
	
	private static final Logger LOG = LoggerFactory.getLogger(RawConsumeTransferApiServices.class);
	
	private static final String STATUS = "STATUS";

	public RawA2APojoResponseModel doTransferP2P(RawA2APojoRequestModel rawA2APojoRequestModel)  {
		RestTemplate restTemplate = new RestTemplate();

		XmlMapper mapper = new XmlMapper();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/xml");

		String a2aPojoRequestXmlString = null;
		try {
			a2aPojoRequestXmlString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rawA2APojoRequestModel);

			LOG.info(" Transfer A2A payload  request : \n {}",a2aPojoRequestXmlString);

			HttpEntity<String> entity = new HttpEntity<>(a2aPojoRequestXmlString, headers);
			ResponseEntity<String> responseEntity = restTemplate.exchange(RawAppConfig.geturlTransfer(), HttpMethod.POST,
					entity, String.class);

			JsonNode omapper = mapper.readTree(responseEntity.getBody());
//
			RawA2APojoResponseModel rawA2APojoResponseModel = null; // 000 : success code AppConfig.getScodeTransfer()
			if (omapper.get(STATUS).asText().equals(RawAppConfig.getScodeTransfer())
					&& responseEntity.getStatusCodeValue() == 200) {

				rawA2APojoResponseModel = mapper.readValue(responseEntity.getBody(), RawA2APojoResponseModel.class);
				LOG.info("Transfer A2A payload  response :  {}" , rawA2APojoResponseModel);

	

			} else if (responseEntity.getStatusCodeValue() == 200
					&& !omapper.get(STATUS).asText().equals(RawAppConfig.getScodeTransfer())) {

				LOG.error("Transfer A2A payload response : {}" , responseEntity.getBody());
				rawA2APojoResponseModel = new RawA2APojoResponseModel();
				rawA2APojoResponseModel.setStatus(omapper.get(STATUS).asText());
				rawA2APojoResponseModel.setMsgdesc(omapper.get("MSGDESC").asText());


			}

		} catch (Exception e ) {
               if (e.getCause() instanceof RestClientException)	{
            	  LOG.error("Error   on the rest communication ");
            	  
               } else if (e.getCause() instanceof JsonProcessingException ) {
            	   LOG.error("Error  parsing json");
               }
		}
		return null;

	}
}

