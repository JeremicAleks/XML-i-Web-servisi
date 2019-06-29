package com.authorizationapi.service;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.authorizationapi.domain.RegistredUser;

@Service
public class MailService {
	
	@Autowired
	private JavaMailSender mailSender;

	/**
	 * Metoda koja salje aktivacioni link(token) korisniku koji se registrovao
	 * 
	 * @param regUser
	 * @param token
	 * @throws MailException
	 */

	@Async
	public void sendRegistrationActivation(RegistredUser regUser, String token) throws MailException {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("majkemijevrosime@gmail.com");
		mail.setTo(regUser.getEmail());
		mail.setSubject("Password Reset:");
		mail.setText("To change password click here: " + "http://localhost:4200/forgottenPassword/" + token);
		mailSender.send(mail);
	}
	
	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);

		mailSender.setUsername("majkemijevrosime@gmail.com");
		mailSender.setPassword("teamrocketisa");

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "false");

		return mailSender;
	}

}
