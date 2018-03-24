package com.kodgemisi.assignment.services.interfaces;

import com.kodgemisi.assignment.domains.ConfirmationToken;
import com.kodgemisi.assignment.domains.User;

public interface ConfirmationService {

	String save(User user);
	
	ConfirmationToken findByToken(String token);
}
