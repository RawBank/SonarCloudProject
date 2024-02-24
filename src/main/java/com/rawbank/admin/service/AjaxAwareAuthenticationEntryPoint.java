/**
* Copyright 2022 Rawbank. All Rights Reserved.
* 
 * Change Section:
* Programmer               Date
*
* 
 * 
 * Created By:
* @author jacquesk
* @since 3 nov. 2022
* 
 */

package com.rawbank.admin.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

/**
 * @author jacquesk
 * 
 *         this class is created to prevent an ajax call when the session is
 *         timed out and redirection to the login page 
 *
 */
public class AjaxAwareAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

	public AjaxAwareAuthenticationEntryPoint(String loginFormUrl) {
		super(loginFormUrl);

	}

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		String ajaxHeader = (request).getHeader("X-Requested-With");
		if ("XMLHttpRequest".equals(ajaxHeader)) {
			 //SC_PRECONDITION_FAILED 412
			response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "Ajax Request Denied (Session Expired)");
		} else {
			super.commence(request, response, authException);
		}
	}

	/**
	 * 
	 */

}
