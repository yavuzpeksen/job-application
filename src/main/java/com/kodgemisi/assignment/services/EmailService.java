package com.kodgemisi.assignment.services;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;
	
	public void sendEmail(SimpleMailMessage email){
		
		mailSender.send(email);
	}
	@Async
	public boolean sendConfirmationEmail(HttpServletRequest request,String emailAddress, String token){
		
		String appUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
		
		boolean status = true;		
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(emailAddress);
		email.setSubject("Registration Confirmation");
		email.setText("To confirm your email address, please click the link below:\n" + appUrl + "/accesspoint/confirm?token=" + token);
		email.setFrom("noreply@domain.com");
		
		try {
			mailSender.send(email);
		} catch (MailException e) {
			System.out.println("Authentication Error");
			status = false;
		}
		return status;
	}
}
