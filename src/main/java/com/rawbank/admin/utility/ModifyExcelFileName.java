package com.rawbank.admin.utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModifyExcelFileName {
	
	private static final Logger logger = LoggerFactory.getLogger(ModifyExcelFileName.class);
	
	String connectedUser = "" ;
	String  excelFNameModifier ;
	int year =0;
	
	public String excelFileNameModifier() {
		
		
		LocalDateTime myDateObj = LocalDateTime.now();  
	    System.out.println("Before formatting: " + myDateObj);  
	    //DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm:ss");  
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyMMddHHmmss"); 
	    String formattedDate = myDateObj.format(myFormatObj);  

		
		excelFNameModifier = connectedUser + formattedDate;
		logger.info(" Current Time Stamp "+ excelFNameModifier);
		
		return excelFNameModifier;
	}

}
