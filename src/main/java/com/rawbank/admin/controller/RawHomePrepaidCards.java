package com.rawbank.admin.controller;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.rawbank.admin.config.RawAppConfig;
import com.rawbank.admin.utility.RawUtility;

@Controller
public class RawHomePrepaidCards {
	
	private static final Logger logger = LoggerFactory.getLogger(RawHomePrepaidCards.class);
	
	
	@GetMapping(value = { "/homePrepaidCard"})
	public ModelAndView homePrepaidCardView(ModelMap model, HttpServletRequest request) {
		/*
		 This piece of code reads files from a folder and loop thru it
		 */
		Set<String> fileSet = new HashSet<>();
		Path path = Paths.get(RawAppConfig.getUploadFilePath2());
		File directoryPath = new File(""+path);
	    File filesList[] = directoryPath.listFiles();
	    
	    logger.info(" ====Available files=== ");
	     for(File file : filesList) {
	         logger.info(" "+file.getName());
	         fileSet.add(file.getName());
	      }
	    
	     
	    model.put("name", RawUtility.getLoggedinUserName() + " [" + RawUtility.getLoggedinUserRole() + "]");
		model.put("role", RawUtility.getLoggedinUserRole());
	     
		return new ModelAndView("home_prepaid_cards","fichiers",fileSet);
	}
	
	
	
	

}
