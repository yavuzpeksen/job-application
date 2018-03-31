package com.kodgemisi.assignment.services;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Scope(value="prototype", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class EmailService {
	
	private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

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
			//logger.error("Gmail Authentication on sending failed:", e);
			//System.out.println("Gmail Authentication Error");
			logger.info("Gmail Authentication on sending confirmation failed:");

			status = false;
		}
		return status;
	}
	
	@Async
	public boolean sendResetPasswordEmail(HttpServletRequest request,String emailAddress, String token){
		String appUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
		
		boolean status = true;		
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(emailAddress);
		email.setSubject("Reset Password");
		email.setText("To reset your password, please click the link below:\n" + appUrl + "/accesspoint/resetPassword?token=" + token);
		email.setFrom("noreply@domain.com");
		
		try {
			mailSender.send(email);
		} catch (MailException e) {
			//System.out.println("Gmail Authentication Error");
			logger.info("Gmail Authentication on sending reset password failed:");
			status = false;
		}
		return status;
	}
}
