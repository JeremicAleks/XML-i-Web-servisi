package com.authorizationapi.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.authorizationapi.domain.BlackListToken;
import com.authorizationapi.domain.PrivilegeEnum;
import com.authorizationapi.domain.Role;
import com.authorizationapi.domain.dto.LoginDTO;
import com.authorizationapi.repo.BlackListTokenRepository;
import com.authorizationapi.repo.UserRepository;
import com.authorizationapi.service.UserService;
import com.authorizationapi.utils.TokenUtils;

@Controller
@CrossOrigin
@RequestMapping("/api")
public class TokenController {

	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepo;

	@Autowired
	BlackListTokenRepository blackList;

	@Autowired
	TokenUtils tokenUtils;

	@Autowired
	BCryptPasswordEncoder bcript;

	@RequestMapping(method = RequestMethod.POST, value = "/token", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> loginUser(@Validated @RequestBody LoginDTO user) {

		String token = userService.userLogin(user.getUsername(), user.getPassword());

		return new ResponseEntity<String>(token, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/user")
	public ResponseEntity<?> addUser() {

		List<PrivilegeEnum> p1 = new ArrayList<>();
		p1.add(PrivilegeEnum.DELETE_PRODUCT);
		p1.add(PrivilegeEnum.READ_PRODUCT);
		p1.add(PrivilegeEnum.WRITE_PRODUCT);

		userRepo.save(new com.authorizationapi.domain.User("Maki", new Role("SYSADMIN", p1), "dsjjjja", "maki",
				bcript.encode("maki"), "dsla===", "421", "421"));
		userRepo.save(new com.authorizationapi.domain.User("Kiriyaga", new Role("SECADMIN", p1.subList(0, 1)), "Nesto",
				"kiriyaga", bcript.encode("maki"), "dsdsla", "421", "421"));

		return new ResponseEntity<String>("Uspesno su dodati Useri", HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/revoke")
	public ResponseEntity<?> revokeToken(@RequestHeader("Token-Authority") String token) {

		Date date = tokenUtils.getExpirationDateFromToken(token);
		if (blackList.findByToken(token) != null || date != null) {
			blackList.save(new BlackListToken(token, date));
		}
		return new ResponseEntity<String>("Successfully logout!", HttpStatus.OK);

	}
}
