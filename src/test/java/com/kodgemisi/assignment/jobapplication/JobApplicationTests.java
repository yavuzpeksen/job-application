package com.kodgemisi.assignment.jobapplication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


import com.kodgemisi.assignment.JobApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=JobApplication.class)
public class JobApplicationTests {

	@Autowired
	private JavaMailSender mailSender;
	
	@Test
	public void contextLoads() throws MailException{
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo("asdsa@zcvd.com");
		email.setSubject("Web App Test email");
		email.setText("Test Message\n");
		email.setFrom("noreply@domain.com");
		mailSender.send(email);
	}

}
