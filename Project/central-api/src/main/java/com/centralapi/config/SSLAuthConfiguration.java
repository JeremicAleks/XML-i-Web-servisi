package com.centralapi.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableAutoConfiguration
@Order()
public class SSLAuthConfiguration extends WebSecurityConfigurerAdapter {
	/*

	@Autowired
	@Qualifier("udSSL")
	UserDetailsService userDetailService;
	
	    @Override
	    protected void configure (final HttpSecurity http) throws Exception {
	        http
	            .csrf().disable()
	            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	            .and()
	                .authorizeRequests()
	                .antMatchers("/api/module/**").authenticated().and().x509().userDetailsService(userDetailService);
	    }*/

}
