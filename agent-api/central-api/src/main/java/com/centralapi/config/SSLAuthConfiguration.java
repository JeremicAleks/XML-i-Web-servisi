package com.centralapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SSLAuthConfiguration extends WebSecurityConfigurerAdapter {
	
	 @Autowired
	 private UserDetailsService userDetailsService;
	 
	    @Override
	    protected void configure (final HttpSecurity http) throws Exception {
	        http
	            .csrf().disable()
	            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	            .and()
	                .authorizeRequests()
	                .antMatchers("/api/protect/**").authenticated() //Specify the URL path(s) requiring authentication...
	            .and()
	                .x509() //... and that x509 authentication is enabled
	                    .userDetailsService(userDetailsService);
	    }

}
