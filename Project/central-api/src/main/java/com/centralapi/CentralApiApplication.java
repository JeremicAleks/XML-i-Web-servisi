package com.centralapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@SpringBootApplication
@EnableAutoConfiguration
public class CentralApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CentralApiApplication.class, args);
	}
	
	@Bean
	public UserDetailsService userDetailsService () {

	    return new UserDetailsService() {

	        @Override
	        public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
	        	System.out.println("Fsafsafa" + username);
	            if (username.equals("Agent Mega Tarvel")) {
	                final User user = new User(username, "", AuthorityUtils.createAuthorityList("ROLE_AGENT_APP"));
	                return user;
	            }
	            return null;
	        }
	    };

}
}
