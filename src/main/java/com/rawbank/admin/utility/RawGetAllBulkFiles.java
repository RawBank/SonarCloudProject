package com.rawbank.admin.utility;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rawbank.admin.config.RawAppConfig;


public class RawGetAllBulkFiles {
	
	private static final Logger logger = LoggerFactory.getLogger(RawGetAllBulkFiles.class);
	
	public static Set<String> getAllBulkFiles() {
		
		Set<String> fileSet = new HashSet<>();
		
		Path pathLoop = Paths.get(RawAppConfig.getUploadFilePath2());
		File directoryPath = new File(""+pathLoop);
		File filesList[] = directoryPath.listFiles();
		for(File fileLocal : filesList) {
	         logger.info(" "+fileLocal.getName());
	         fileSet.add(fileLocal.getName());
	      }
		
		return (Set<String>) fileSet;
	}

}
