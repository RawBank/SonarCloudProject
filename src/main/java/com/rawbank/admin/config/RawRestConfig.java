/**
 * © Copyright 2021 Rawbank. All Rights Reserved.
 * 
 * Change Section:
 * Programmer			Date
 * Krishna				Sep 15, 2022
 * Fixed critical SonarQube Issues.
 * 
 * Krishna				6/28/2022
 * CSC admin portal Project.
 * Initial Coding.
 * 
 * 
 * Created By:
 * 
 * @author krishna
 * @since Jun 28, 2022
 * 
 */
package com.rawbank.admin.config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;




@Configuration
@ComponentScan(basePackages = {"com.rawbank.admin.config"})
@PropertySource(value = {"file:${user.home}/rawb-appconfig/config.csc_admin/params.properties"}, ignoreResourceNotFound = false)
public class RawRestConfig {
  private static final Logger LOG = LoggerFactory.getLogger(com.rawbank.admin.config.RawRestConfig.class);
  
  private static final String ERROR_MSG = " ERROR {}";
  
  @Value("${rest.read.timeout}")
  Integer readTimeoutInMilliSeconds;
  
  @Value("${rest.connection.timeout}")
  Integer connectTimeoutInMilliSeconds;
}

