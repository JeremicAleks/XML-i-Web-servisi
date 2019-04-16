package com.centralapi.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.centralapi.domain.Role;
import com.centralapi.repo.RoleRepository;

@Service
public class SecurityServiceCon implements SecurityService {

	@Autowired
	RoleRepository roleRepo;

	@Override
	public Boolean hasProtectedAccess(String role) {

		SimpleGrantedAuthority sga = new SimpleGrantedAuthority(role);
		Collection<? extends GrantedAuthority> authorities = null;
		// show privileges
		String rolee = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		String sub = rolee.substring(1, rolee.length() - 1);

		Role rol = roleRepo.findByName(sub);
		if (rol != null) {
			String priveleges = "";
			for (int i = 0; i < rol.getPrivileges().size(); i++) {
				priveleges += rol.getPrivileges().get(i).toString();
				if (i != rol.getPrivileges().size() - 1) {
					priveleges += ",";
				}

			}

			authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(priveleges);

		}

		if (authorities == null) {
			return false;
		}
		return (authorities.contains(sga));
	}
}
