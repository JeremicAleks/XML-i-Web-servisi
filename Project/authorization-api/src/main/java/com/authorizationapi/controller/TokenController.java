package com.authorizationapi.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
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

import com.authorizationapi.domain.PrivilegesEnum;
import com.authorizationapi.domain.RegistredUser;
import com.authorizationapi.domain.Role;
import com.authorizationapi.domain.SecurityUser;
import com.authorizationapi.domain.UserStatusEnum;
import com.authorizationapi.domain.dto.ChangePasswordDTO;
import com.authorizationapi.domain.dto.ForgottenPasswordDTO;
import com.authorizationapi.domain.dto.LoginDTO;
import com.authorizationapi.domain.dto.RegisterUserDTO;
import com.authorizationapi.domain.dto.TokenDTO;
import com.authorizationapi.domain.dto.UserLoginDTO;
import com.authorizationapi.exception.ResponseMessage;
import com.authorizationapi.repo.BlackListTokenRepository;
import com.authorizationapi.repo.RegUserRepository;
import com.authorizationapi.repo.RoleRepository;
import com.authorizationapi.repo.UserRepository;
import com.authorizationapi.service.MailService;
import com.authorizationapi.service.UserService;
import com.authorizationapi.utils.TokenUtils;

@Controller
@CrossOrigin
@RequestMapping("/api")
public class TokenController {
	
	public static Logger logger = Logger.getLogger(UserService.class);

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
	RegUserRepository regRepo;
	
	@Autowired
	MailService mailService;


	@Autowired
	BCryptPasswordEncoder bcript;

	@RequestMapping(method = RequestMethod.POST, value = "/token")
	public ResponseEntity<?> loginUser(@Validated @RequestBody LoginDTO user) {

		UserLoginDTO userLoginDTO = userService.userLogin(user.getUsername(), user.getPassword());
		System.out.println(user.getUsername());
		
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

		List<PrivilegesEnum> p1 = new ArrayList<>();
		p1.add(PrivilegesEnum.DELETE_PRODUCT);
		p1.add(PrivilegesEnum.READ_PRODUCT);
		p1.add(PrivilegesEnum.WRITE_PRODUCT);

		roleRepo.save(new Role("DefaultRole",p1));
		String salt = KeyGenerators.secureRandom().toString();
		String fullPass = "maki" + salt + pepper;
		userRepo.save(new com.authorizationapi.domain.User("Maki", new Role("SYSADMIN", p1), "dsjjjja", "maki",bcript.encode(fullPass), "dsla===",salt,UserStatusEnum.ACTIVE));
		userRepo.save(new com.authorizationapi.domain.User("Kiriyaga", new Role("SECADMIN", p1.subList(0, 1)), "Nesto","kiriyaga", bcript.encode(fullPass), "dsdsla",salt,UserStatusEnum.ACTIVE));

		return new ResponseEntity<String>("Uspesno su dodati Useri", HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/revoke")
	public ResponseEntity<?> revokeToken(@RequestHeader("Token-Authority") String token) {

		Date date = tokenUtils.getExpirationDateFromToken(token);
		logger.debug("Date from token: " + date);
		if (blackList.findByToken(token) == null || date != null) {
			logger.debug("Token is not present in blacklsit!");
			//blackList.save(new BlackListToken(token, date));
			
		}
		
		MDC.put("user",tokenUtils.getUsernameFromToken(token));
		MDC.put("logut","success");
		logger.info("Successfully logout!");
		return new ResponseEntity<ResponseMessage>(new ResponseMessage("Successfully logout!"), HttpStatus.OK);

	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/forgottenPassword")
	public ResponseEntity<?> forgottenPassword(@RequestBody ForgottenPasswordDTO user) {

		RegistredUser reguser = regRepo.findByUsername(user.getUsername());
		SecurityUser sec =new SecurityUser(reguser.getId(), reguser.getUsername(), reguser.getPassword(), reguser.getEmail(), null,null);
		String token = tokenUtils.generateTokenForgottenPassword(sec);
		mailService.sendRegistrationActivation(reguser, token);
		logger.info("Successfully sended mail");
		return new ResponseEntity<ResponseMessage>(new ResponseMessage("Check your mail!"), HttpStatus.OK);

	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/verifyForgottenPasswordToken")
	public ResponseEntity<?> forgottenPassword(@RequestBody TokenDTO token) {

		Date date = tokenUtils.getExpirationDateFromToken(token.getToken());
		logger.debug("Date from token: " + date);
		if (date != null) {
			logger.warn("Token is valid!");
			return new ResponseEntity<>(true, HttpStatus.OK);
	
		}
		logger.warn("Token has been expired!");
		return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/changePassword")
	public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDTO changePassword) {

		Date date = tokenUtils.getExpirationDateFromToken(changePassword.getToken());
		logger.debug("Date from token: " + date);
		if (date != null) {
			logger.warn("Token is valid!");
			return new ResponseEntity<>(userService.changePassword(changePassword), HttpStatus.OK);
	
		}
		return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/test/log")
	public ResponseEntity<?> test() {
		
		logger.warn("Warn logging Test!");
		logger.info("Info logging Test!");
		logger.error("Error logging Test!");
		logger.trace("Trace logging Test!");
		logger.debug("Debug logging Test!");
		
		return new ResponseEntity<String>("Test Logging!", HttpStatus.OK);

	}
}
