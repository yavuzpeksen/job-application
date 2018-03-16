package com.kodgemisi.assignment.services.interfaces;

import java.util.List;

import com.kodgemisi.assignment.domains.Job;
import com.kodgemisi.assignment.domains.User;

public interface UserService {
		
	public Long getUserIdByEmail(String email);
			
	public List<Job> getAllJobs();
	
	public Job getJobById(long id);
	
	public void save(User user);
	
	public User getUserByEmail(String email);

}
