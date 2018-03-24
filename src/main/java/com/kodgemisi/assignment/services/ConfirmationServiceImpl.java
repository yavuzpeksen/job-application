package com.kodgemisi.assignment.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodgemisi.assignment.domains.ConfirmationToken;
import com.kodgemisi.assignment.domains.User;
import com.kodgemisi.assignment.repositories.ConfirmationTokenRepository;
import com.kodgemisi.assignment.services.interfaces.ConfirmationService;

@Service
public class ConfirmationServiceImpl implements ConfirmationService{

	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;

	@Override
	public String save(User user) {
		
		ConfirmationToken cToken = new ConfirmationToken();
		String token = UUID.randomUUID().toString().replaceAll("-", "");
		cToken.setToken(token);
		cToken.setUser(user);		
		confirmationTokenRepository.save(cToken);
		return token;
	}
	
	
}
