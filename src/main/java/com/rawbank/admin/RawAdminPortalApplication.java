/**
 * Copyright 2022 Rawbank. All Rights Reserved.
 * 
 * Change Section:
 * Programmer			Date
 * Krishna				6/20/2022
 * CSC admin portal Project.
 * Initial Coding.
 * 
 * Created By:
 * @author krishna
 * @since Jun 20, 2022
 * 
 */
package com.rawbank.admin;



import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rawbank.admin.service.RawChangeStatusCancelServiceImpl;
import com.rawbank.admin.sqlserver.entity.RawClientCards;
import com.rawbank.admin.sqlserver.repository.RawClientRepository;
import com.rawbank.admin.utility.RawUtility;
/**
 * @author krishna
 *
 */

@SpringBootApplication
public class RawAdminPortalApplication implements CommandLineRunner {

   	
	private static final Logger logger = LoggerFactory.getLogger(RawAdminPortalApplication.class);
	
	@Autowired
	private RawClientRepository rawClientRepository;
	
	@Autowired
	RawChangeStatusCancelServiceImpl rawChangeStatusCancelServiceImpl;
	
	public static void main(String[] args) {
		SpringApplication.run(RawAdminPortalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


		logger.info("\n==========================================================================\n"
				+ "             csc admin portal app"
				+ "\n==========================================================================\n");
		
		/*
		logger.info("==========="+ RawUtility.getFormattedCurrentTimeStamp());
		
		logger.info("=========== All records ======= "+ rawChangeStatusCancelServiceImpl.findAllCancelRecords());
		
		logger.info("===========   Airtel ===========  "+ RawUtility.generateRandomIdForCancelReq());
		
		//Failure =>403
		
		String s = "Failure =>";
	    String[] result = s.split("=>");
		
	    logger.info("===========   Airtel ===========  "+ result[0]);
		*/
		
		//import org.apache.commons.lang3.StringUtils;
		/*
		String myString = "example";

		if (StringUtils.isNotEmpty(myString)) {
		    // String exists and is not empty
			 logger.info("===========  String exists and is not empty===========  ");
		} else {
		    // String is either null or empty
			logger.info("===========  String is either null or empty ===========  ");
		}
		*/
		//One of my favorite tools is StringUtils.isNotBlank() which can be found in Apache Commons Lang. 
		//StringUtils.isNotBlank(myString);
		


	}

}
