package com.app.employee.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import com.app.employee.security.jwt.AuthEntryPointJwt;
import com.app.employee.security.jwt.AuthTokenFilter;
import com.app.employee.security.services.UserDetailsServiceImpl;

@Configuration

@EnableGlobalMethodSecurity(
		prePostEnabled = true)

public class WebSecurityConfig{
	@Autowired
	UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;
	
	@Bean
	/* Method to return the authorization token filter */
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}
	
	/*
	 * Method that uses userDetailsService and PasswordEncoder to authenticate
	 * username and password
	 */
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}
	
	/* AuthenticationManager calls the authConfig method */ 
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig)throws Exception{
		return authConfig.getAuthenticationManager();
	}
	
	/* Method to encrypt the password which is stored in the database */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/* Will intercept the request by hitting the servlet or throw exception */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
		http.cors().and().csrf().disable()
			.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeRequests().antMatchers("/api/**").permitAll()
			.antMatchers("/api/**").permitAll()
			.anyRequest().authenticated();
		
		http.authenticationProvider(authenticationProvider());
		
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
		
	}
	
	
}
