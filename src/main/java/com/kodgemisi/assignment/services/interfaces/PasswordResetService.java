package com.kodgemisi.assignment.services.interfaces;

import com.kodgemisi.assignment.domains.PasswordResetToken;
import com.kodgemisi.assignment.domains.User;

public interface PasswordResetService {

	String save(User user);
	
	PasswordResetToken findByToken(String token);
		
}
