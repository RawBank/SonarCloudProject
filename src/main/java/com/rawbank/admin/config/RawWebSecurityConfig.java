/**
 * Copyright 2022 Rawbank. All Rights Reserved.
 * 
 * Change Section: 
 * Programmer 			Date
 * Krishna				6/20/2022
 * CSC admin portal Project.
 * Initial Coding.
 * 
 * 
 * Created By:
 * 
 * @author krishna
 * @since Jun 20, 2022
 * 
 */

package com.rawbank.admin.config;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.rawbank.admin.service.AjaxAwareAuthenticationEntryPoint;
import com.rawbank.admin.service.RawCustomAuthenticationHandler;
import com.rawbank.admin.service.RawCustomLogoutSuccessHandler;

/**
 * @author krishna
 *
 */
@EnableWebSecurity
public class RawWebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Resource
	private UserDetailsService userDetailsService;

	@Autowired
	private RawCustomAuthenticationHandler failureHandler;

	@Autowired
	private RawCustomLogoutSuccessHandler successHandler;

	@Bean
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(new BCryptPasswordEncoder());
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider());
	}
  // .anyRequest().anonymous()
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/static/**", "/webjars/**", "/language/**", "/js/**", "/css/**", "/images/**",
						"/login-error**", "/login**")
				.permitAll().antMatchers("/searchCards").hasAnyAuthority("csc_admin_call_center", "csc_admin_initiator", "csc_admin_administrator")
				.antMatchers("/reloadMC").hasAuthority("csc_admin_initiator")
				.antMatchers("/loadpendingcancelmcInitiator").hasAuthority("csc_admin_initiator")
				.antMatchers("/homePrepaidCardIndividuel").hasAuthority("csc_admin_initiator")
				.antMatchers("/homePrepaidCard").hasAuthority("csc_admin_initiator")
				.antMatchers("/loadpendingcancelmc").hasAuthority("csc_admin_administrator")
				.antMatchers("/reloadPrepaidCard").hasAuthority("csc_admin_initiator")
				.antMatchers("/approveCards").hasAuthority("csc_admin_administrator")
				.antMatchers("/approveCardsMcPrepaidIndividual").hasAuthority("csc_admin_administrator")
				.antMatchers("/approveCardsMcPrepaidBulk").hasAuthority("csc_admin_administrator")
				.anyRequest().authenticated().and().formLogin()
				.loginPage("/login").defaultSuccessUrl("/home", true ).permitAll().failureHandler(failureHandler).and()
				.logout().permitAll().logoutSuccessHandler(successHandler).and().exceptionHandling()
				.accessDeniedPage("/login?errorCode=403")
				.authenticationEntryPoint(new AjaxAwareAuthenticationEntryPoint("/login"));
  //
		http.sessionManagement().maximumSessions(1).expiredUrl("/login?errorCode=412");
        //
		http.cors().disable();
		http.csrf().disable();
		 // revise this before production go live 
	}
}