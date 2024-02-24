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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.rawbank.admin.exception.RawSSLHandShakeException;
import com.rawbank.admin.exception.RawUserLockedException;
import com.rawbank.admin.model.RawLoginResponse;
import com.rawbank.admin.model.RawRoles;
import com.rawbank.admin.service.RawAuthenticateService;

/**
 * @author krishna
 *
 */
@Component
public class RawCustomAuthenticationProvider implements UserDetailsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RawCustomAuthenticationProvider.class);

	@Autowired
	private RawAuthenticateService rawAuthenticateService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails user = null;
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		String password = requestAttributes.getRequest().getParameter("password");
		if (username != null && password != null) {
			Map<Integer, RawLoginResponse> loginResponseMap = authenticateAgainstRestAPI(username, password);
			user = getUserDetails(loginResponseMap, username, password);
		}
		return user;
	}

	private UserDetails getUserDetails(Map<Integer, RawLoginResponse> loginResponseMap, String userName,
			String password) {
		UserDetails user = null;
		if (loginResponseMap.containsKey(404)) {
			LOGGER.error(userName, " user not found");

		} else if (loginResponseMap.containsKey(423)) {
			LOGGER.error(userName, " User is locked ");

			user = User.withUsername(userName).password("").disabled(false).accountExpired(false).accountLocked(true)
					.credentialsExpired(false).authorities(new ArrayList<>()).build();
		} else if (loginResponseMap.containsKey(422)) {
			// password incorrect
			LOGGER.error(userName, " password incorrect, retry until you're sure that your password is not wrong");
			user = User.withUsername(userName).password(new BCryptPasswordEncoder().encode(password)).disabled(false)
					.accountExpired(false).accountLocked(false).credentialsExpired(true).authorities(new ArrayList<>())
					.build();

		} else if (loginResponseMap.containsKey(200)) {
			LOGGER.info("user found and password correct");

			RawLoginResponse loginResponse = loginResponseMap.get(200);
			LOGGER.info("Login Response : {}", loginResponse);
			Collection<GrantedAuthority> authorities = new ArrayList<>();
			List<RawRoles> roless = loginResponse.getRoles();
			// cscRoleNumber is incremented if the customer has even one role containing csc
			// role
			int cscRoleNumber = 0;
			for (RawRoles rol : roless) {
				if (rol.getRoleName().contains("csc")) {
					cscRoleNumber++;
					authorities.add(new SimpleGrantedAuthority(rol.getRoleName()));

				}
			}
			if (cscRoleNumber == 0) {

				LOGGER.error(userName, " User  does not have a right  roles to get access ");
//				user = User.withUsername(userName).password(new BCryptPasswordEncoder().encode(password))
//						.disabled(false).accountExpired(false).accountLocked(false).credentialsExpired(false)
//						.authorities(new ArrayList<>()).build();
			
			} else {
				user = User.withUsername(loginResponse.getUserName())
						.password(new BCryptPasswordEncoder().encode(password)).disabled(!loginResponse.getStatus())
						.accountExpired(false).accountLocked(false).credentialsExpired(false).authorities(authorities)
						.build();
				LOGGER.info("user : {}", user);
			}

		} else if (loginResponseMap.containsKey(500)) {
			LOGGER.error("Internal Server Error");
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return user;
	}

	private Map<Integer, RawLoginResponse> authenticateAgainstRestAPI(final String userName, final String password)
			throws RawSSLHandShakeException, RawUserLockedException {
		return rawAuthenticateService.validateLoginCredentials(userName, password);
	}
}
