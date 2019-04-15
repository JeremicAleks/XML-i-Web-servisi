package com.authorizationapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.authorizationapi.domain.SecurityUserFactory;
import com.authorizationapi.domain.User;
import com.authorizationapi.repo.UserRepository;

@Service
public class UserDetailsServiceCon implements UserDetailsService {
	

	@Autowired

	private UserRepository userRepository;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = this.userRepository.findByUsername(username);

		if (user == null) {

			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));

		} else {

			return SecurityUserFactory.create(user);

		}

	}

}
