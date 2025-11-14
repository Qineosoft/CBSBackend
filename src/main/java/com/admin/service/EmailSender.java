package com.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {

	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendMail(String mail, String otp, String message) {
	SimpleMailMessage simpleMessage = new SimpleMailMessage();
	simpleMessage.setTo(mail);
	simpleMessage.setText(message + otp);
	javaMailSender.send(simpleMessage);
	}
}
