package com.kodgemisi.assignment.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodgemisi.assignment.domains.ConfirmationToken;
import com.kodgemisi.assignment.domains.PasswordResetToken;
import com.kodgemisi.assignment.domains.User;
import com.kodgemisi.assignment.repositories.ConfirmationTokenRepository;
import com.kodgemisi.assignment.repositories.PasswordResetTokenRepository;
import com.kodgemisi.assignment.services.interfaces.ConfirmationService;
import com.kodgemisi.assignment.services.interfaces.PasswordResetService;

@Service
public class PasswordResetServiceImpl implements PasswordResetService{
	
	@Autowired
	private PasswordResetTokenRepository prtRepository;

	@Override
	public String save(User user) {
		
		PasswordResetToken prt = new PasswordResetToken();
		String token = UUID.randomUUID().toString().replaceAll("-", "");
		prt.setToken(token);
		prt.setUser(user);
		prtRepository.save(prt);
		return token;
				
	}

	@Override
	public PasswordResetToken findByToken(String token) {
		
		return prtRepository.findByToken(token);
	}
	
}
