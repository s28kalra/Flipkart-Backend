package com.flipkart.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.okta.spring.boot.oauth.Okta;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		// protecting end points that matches with the pattern 
		// only authorized people can make request to the api with this pattern
		http.authorizeRequests().antMatchers("/transaction*/**").authenticated().and().oauth2ResourceServer().jwt();
		http.authorizeRequests().antMatchers("/address*/**").authenticated().and().oauth2ResourceServer().jwt();
		http.authorizeRequests().antMatchers("/addProduct*/**").authenticated().and().oauth2ResourceServer().jwt();
		
		// add CORS filters
		http.cors();
		
		// Adding response to the body
		Okta.configureResourceServer401ResponseBody(http);
		
		// disable CSRF since we are not using Cookies for session tracking
		http.csrf().disable();
	}
	
	
}
