package com.csu.expense_tracker.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Component
public class MailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	public void send(String to, String subject, String body) throws MessagingException {
		
	MimeMessage message = javaMailSender.createMimeMessage();
	
	MimeMessageHelper helper;
	
	helper = new MimeMessageHelper(message, true);
	
	helper.setSubject(subject);
	helper.setTo(to);
	helper.setText(body);
	
	javaMailSender.send(message);
	
	}
	
	
}
