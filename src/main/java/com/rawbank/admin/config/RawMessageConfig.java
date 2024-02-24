/**
 * © Copyright 2021 Rawbank. All Rights Reserved.
 * 
 * Change Section:
 * Programmer			Date
 * Krishna				Jul/1/2022
 * CSC admin portal Project.
 * Initial Coding.
 * 
 * 
 * Created By:
 * @author krishna
 * @since Jul 1, 2022
 * 
 */
package com.rawbank.admin.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * @author krishna
 *
 */
@Configuration
public class RawMessageConfig implements WebMvcConfigurer {
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.FRENCH);	
		return slr;
	}
	
	@Bean
	  public LocaleChangeInterceptor localeChangeInterceptor() {
	      LocaleChangeInterceptor localeChangeInterceptor  = new LocaleChangeInterceptor();
	      localeChangeInterceptor.setParamName("lang");
	      return localeChangeInterceptor;
	  }
	  
	  @Override
	  public void addInterceptors(InterceptorRegistry registry) {
	      registry.addInterceptor(localeChangeInterceptor());
	  }
	   // adding to  specify the location of language  messages 
	   // Link  :https://stackoverflow.com/questions/42612808/javax-servlet-jsp-jsptagexception-no-message-found-under-code-message-for-loc
	  @Bean(name = "messageSource")
	    public ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource() {
	        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	        messageSource.setBasenames("classpath:messages");
	        messageSource.setDefaultEncoding("UTF-8");
	        return messageSource;
	    }
	 
}
