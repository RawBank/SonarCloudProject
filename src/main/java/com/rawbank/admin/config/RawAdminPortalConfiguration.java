/**
 * © Copyright 2021 Rawbank. All Rights Reserved.
 * 
 * Change Section:
 * Programmer			Date
 * Krishna				6/27/2022
 * CSC admin portal Project.
 * Initial Coding.
 * 
 * Created By:
 * 
 * @author krishna
 * @since Jun 27, 2022
 * 
 */
package com.rawbank.admin.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author krishna
 *
 */
@ConfigurationProperties(prefix="spring.admin.portal")
@Configuration
public class RawAdminPortalConfiguration {
	
	private String baseAuthRestURL;
	private String sopraURL;

	public String getBaseAuthRestURL() {
		return baseAuthRestURL;
	}

	public void setBaseAuthRestURL(String baseAuthRestURL) {
		this.baseAuthRestURL = baseAuthRestURL;
	}

	/**
	 * @return the sopraURL
	 */
	public String getSopraURL() {
		return sopraURL;
	}

	/**
	 * @param sopraURL the sopraURL to set
	 */
	public void setSopraURL(String sopraURL) {
		this.sopraURL = sopraURL;
	}

}
