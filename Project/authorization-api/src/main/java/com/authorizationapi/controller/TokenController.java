package com.authorizationapi.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.keygen.KeyGenerators;
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
import com.authorizationapi.domain.dto.RegisterUserDTO;
import com.authorizationapi.domain.dto.UserLoginDTO;
import com.authorizationapi.exception.ResponseMessage;
import com.authorizationapi.repo.BlackListTokenRepository;
import com.authorizationapi.repo.RoleRepository;
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
	
	@Value("${security.pepper}")
	private String pepper;
	
	@Autowired
	private RoleRepository roleRepo;


	@Autowired
	BCryptPasswordEncoder bcript;

	@RequestMapping(method = RequestMethod.POST, value = "/token", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> loginUser(@Validated @RequestBody LoginDTO user) {

		UserLoginDTO userLoginDTO = userService.userLogin(user.getUsername(), user.getPassword());
		
		if (userLoginDTO != null) {
			return new ResponseEntity<Object>(userLoginDTO, HttpStatus.OK);
		}
		
		return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUserDTO user) {

		return new ResponseEntity<>(userService.userRegister(user),HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/user")
	public ResponseEntity<?> addUser() {

		List<PrivilegeEnum> p1 = new ArrayList<>();
		p1.add(PrivilegeEnum.DELETE_PRODUCT);
		p1.add(PrivilegeEnum.READ_PRODUCT);
		p1.add(PrivilegeEnum.WRITE_PRODUCT);

		roleRepo.save(new Role("DefaultRole",p1));
		String salt = KeyGenerators.secureRandom().toString();
		String fullPass = "maki" + salt + pepper;
		userRepo.save(new com.authorizationapi.domain.User("Maki", new Role("SYSADMIN", p1), "dsjjjja", "maki",bcript.encode(fullPass), "dsla===", "421", "421",salt));
		userRepo.save(new com.authorizationapi.domain.User("Kiriyaga", new Role("SECADMIN", p1.subList(0, 1)), "Nesto","kiriyaga", bcript.encode(fullPass), "dsdsla", "421", "421",salt));

		return new ResponseEntity<String>("Uspesno su dodati Useri", HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/revoke")
	public ResponseEntity<?> revokeToken(@RequestHeader("Token-Authority") String token) {

		Date date = tokenUtils.getExpirationDateFromToken(token);
		System.out.println(date);
		if (blackList.findByToken(token) == null || date != null) {
			System.out.println("fsafa");
			blackList.save(new BlackListToken(token, date));
			
		}
		return new ResponseEntity<ResponseMessage>(new ResponseMessage("Successfully logout!"), HttpStatus.OK);

	}
}
