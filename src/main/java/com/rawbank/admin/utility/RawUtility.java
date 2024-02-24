/**
 * © Copyright 2021 Rawbank. All Rights Reserved.
 * 
 * Change Section:
 * Programmer			Date
 * Krishna				7/1/2022
 * CSC admin portal Project.
 * Initial Coding.
 * 
 * 
 * Created By:
 * @author krishna
 * @since Jul 1, 2022
 * 
 */
package com.rawbank.admin.utility;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Collection;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.neovisionaries.i18n.CountryCode;
import com.rawbank.admin.exception.RawErrorApiException;
import com.rawbank.admin.model.RawTokenResponseModel;
import com.rawbank.admin.service.RawTokenManagement;

/**
 * @author krishna
 *
 */
public class RawUtility {

	@Autowired
	private RawTokenManagement rawTokenManagement;
	
	static  SecureRandom random = new SecureRandom();	

	private static final Logger LOGGER = LoggerFactory.getLogger(RawUtility.class);

	private RawUtility() {
		throw new IllegalStateException("Utility class");
	}

	public static String getLoggedinUserName() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}

		return principal.toString();
	}

	public static GrantedAuthority getLoggedinUserRole() {
		return getRoleofUser(SecurityContextHolder.getContext().getAuthentication().getAuthorities()).get(0);
	}

	public static String maskString(String strText, int start, int end, char maskChar) {

		if (strText == null || strText.equals(""))
			return "";

		if (start < 0)
			start = 0;

		if (end > strText.length())
			end = strText.length();

		if (start > end)
			throw new RawErrorApiException("End index cannot be greater than start index");

		int maskLength = end - start;

		if (maskLength == 0)
			return strText;

		StringBuilder sbMaskString = new StringBuilder(maskLength);

		for (int i = 0; i < maskLength; i++) {
			sbMaskString.append(maskChar);
		}

		return strText.substring(0, start) + sbMaskString.toString() + strText.substring(start + maskLength);
	}

	public static String decodeBase64(String password) {
		byte[] decodeValuebyte = Base64.getDecoder().decode(password);
		return new String(decodeValuebyte);
	}

	public static Currency getCurrencyInstance(int numericCode) {
		Set<Currency> currencies = Currency.getAvailableCurrencies();
		for (Currency currency : currencies) {
			if (currency.getNumericCode() == numericCode) {
				return currency;
			}
		}
		return null;

	}

	public static String getTransactionId(String prefix) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		
		String formatted = String.format("%02d", random.nextInt(100));
		return prefix + sdf.format(new Date()) + formatted;

	}

	public static Currency getCurrencyInstance(String currencyCode) {
		Set<Currency> currencies = Currency.getAvailableCurrencies();
		for (Currency currency : currencies) {
			if (currency.getCurrencyCode().equalsIgnoreCase(currencyCode)) {
				return currency;
			}
		}
		return null;

	}

	public static List<GrantedAuthority> getRoleofUser(Collection<? extends GrantedAuthority> authorities) {
		return authorities.stream().filter(c -> c.getAuthority().contains("csc")).collect(Collectors.toList());
	}

	// generate the a new token if the recent is invalid

	public RawTokenResponseModel getGoodToken(RawTokenResponseModel rawTokenResponseModel) {
		try {
			rawTokenResponseModel = rawTokenResponseModel.getExpiresAt()
					.isBefore(LocalDateTime.now(ZoneId.systemDefault())) ? rawTokenManagement.generateCscToken()
							: rawTokenResponseModel;
			return rawTokenResponseModel;
		} catch (Exception e) {
			  LOGGER.error("context", e);
			
		}
		return null;

	}

	public static String getAgenceCompte(String string, String pos) {
		if (pos.equals("begin")) {
			return string.substring(0, 5);
		} else if (pos.equals("end")) {
			return string.substring(5, string.length());
		}
		return null;
	}
	// list of countries

	public static List<String> getCountries(String language) {
		List<String> countries = new ArrayList<>();

		countries.clear();
		LOGGER.info(" get Countries");

		String[] isoCountries = Locale.getISOCountries();
		for (String country : isoCountries) {
			Locale locale = new Locale(language, country);

			String code = locale.getCountry();
			String name = locale.getDisplayCountry();

			CountryCode cc = CountryCode.getByCode(code);
			countries.add(cc.getNumeric() + ", " + name);

		}
		return countries;
	}

	public static String generateRandomSequence(int size) {
		char[] chars = "0123456789".toCharArray();
		StringBuilder sb = new StringBuilder(size);
		for (int i = 0; i < 12; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		return sb.toString();

	}

	public static Boolean checkCardNotExpired(String expiryDate) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");

		final LocalDate ld = LocalDate.parse(expiryDate, format);
		LOGGER.info("date expiration  card  {} ", ld);
		LOGGER.info("current date  {} ", LocalDate.now());
		if (ld.isAfter(LocalDate.now()) || ld.equals(LocalDate.now())) {
			LOGGER.info("card is not expired ");
			return true;
		}
		LOGGER.error("card is  expired  Thanks");
		return false;
	}
	
	
	public static String getFormattedCurrentTimeStamp() {

		//Calendar calendar = Calendar.getInstance();
		//java.util.Date currentTime = calendar.getTime();
		//LOGGER.info("  =======Date time stamp  ====== " +  currentTime); // Thu Dec 14 14:10:06 WAT 2023
		
		
		// LocalDate myObj = LocalDate.now(); // 2023-12-14 
		//LocalTime myObj = LocalTime.now(); // 14:05:39.612109 
		
		  LocalDateTime myObj = LocalDateTime.now(); // 2023-12-14T14:05:39.612400 
		  DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  //14-12-2023 14:05:39 
		  String formattedDateTime = myObj.format(myFormatObj);
		  
		  LOGGER.info("  ======= Formatted Date time stamp  ====== " +  formattedDateTime);
		  
		  return formattedDateTime;

	}
	
	
	public static String generateRandomIdForCancelReq() {
		
		LocalDateTime myObj = LocalDateTime.now(); // 2023-12-14T14:05:39.612400 DDDZZ
		  DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyMMdd.HHmmss");  //14-12-2023 14:05:39 
		  String formattedDateTime = myObj.format(myFormatObj);
		  String randomNum = "";
		  
		  
		  char[] chars = "0123456789ABCDEFGHIJK".toCharArray();
			StringBuilder sb = new StringBuilder(10);
			for (int i = 0; i < 10; i++) {
				char c = chars[random.nextInt(chars.length)];
				sb.append(c);
			}
			
			randomNum = sb.toString();
			
			
			formattedDateTime = formattedDateTime + "."+randomNum;
			
		  
		  LOGGER.info("  ======= Formatted Date time stamp  ====== " +  formattedDateTime);
		  
		  return formattedDateTime;
		
	}


}
