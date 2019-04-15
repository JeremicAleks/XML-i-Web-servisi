package com.authorizationapi.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

public class SecurityUserFactory {

	public static SecurityUser create(User user) {

		Collection<? extends GrantedAuthority> authorities;

		try {

			authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole().getName().toString());

		} catch (Exception e) {

			authorities = null;

		}

		return new SecurityUser(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(), null,
				authorities);

	}

}
