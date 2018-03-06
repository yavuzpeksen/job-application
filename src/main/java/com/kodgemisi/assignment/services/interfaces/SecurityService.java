package com.kodgemisi.assignment.services.interfaces;

import java.util.List;

import com.kodgemisi.assignment.domains.Job;
import com.kodgemisi.assignment.domains.User;

public interface SecurityService {
	
	String findLoggedInUsername();
	
	void autoLogin(String username, String password);

}
