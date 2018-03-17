package com.kodgemisi.assignment.services.interfaces;

import java.util.List;

import com.kodgemisi.assignment.domains.Job;
import com.kodgemisi.assignment.domains.User;
import com.kodgemisi.assignment.domains.form.RegisterForm;

public interface UserService {
		
	public Long getUserIdByEmail(String email);
			
	public List<Job> getAllJobs();
	
	public Job getJobById(long id);
	
	public void save(RegisterForm registerForm);
	
	public User getUserByEmail(String email);

}
