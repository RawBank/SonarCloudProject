package com.rawbank.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan(basePackages = "com.rawbank.admin.config")
@PropertySource(value = "file:${user.home}/rawb-appconfig/config.csc_admin/params.properties", ignoreResourceNotFound = false)
public class RawAppConfig {

	private static Environment environment;
	
	public RawAppConfig(Environment environment) {
		RawAppConfig.environment = environment;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	public static String getCscEndpointBase() {
		return environment.getProperty("csc.endpoint.base");
	}

	public static String getCscClientKey() {
		return environment.getProperty("csc.client.key");
	}

	public static String getCscSecretKey() {
		return environment.getProperty("csc.secret.key");
	}
	
	public static String getCscinstitutionNumber() {
		return environment.getProperty("csc.institution.number");
	}
	
	public static String getUploadFilePath() {
		return environment.getProperty("upload.file.path");
	}
	
	public static String getUploadFilePath2() {
		return environment.getProperty("upload.file.path2");
	}
	
	public static String getUploadFilePathOutPutBulk() {
		return environment.getProperty("upload.file.pathoutputbulk");
	}

	public static String geturlTransfer() {
		return environment.getProperty("transfer.endpoint.url");
	}

	public static String getTransferToken() {
		return environment.getProperty("a2a.token");
	}

	public static String getTransferChannel() {
		return environment.getProperty("a2a.channel");
	}
	
	public static String getScodeTransfer() {
		return environment.getProperty("transfer.success.code");
	}
	public static String getAESsecretKey() {
		return environment.getProperty("aes.secret.key");
	}
	
	
	public static String getMcPrepaidIndividualVal() {
		return environment.getProperty("mc.prepaid.individual");
	}
	
	public static String getMcPrepaidBulkVal() {
		return environment.getProperty("mc.prepaid.bulk");
	}
	
	
	
	//
	
	
}

//