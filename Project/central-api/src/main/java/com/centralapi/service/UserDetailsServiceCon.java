package com.centralapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.centralapi.domain.SecurityUserFactory;
import com.centralapi.domain.User;
import com.centralapi.repo.UserRepository;

@Service
@Primary
public class UserDetailsServiceCon implements UserDetailsService {
	

	@Autowired

	private UserRepository userRepository;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		User user = this.userRepository.findByUsername(username);

		if (user == null) {

			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));

		}
		else {

			return SecurityUserFactory.create(user);

		}

	}

}

