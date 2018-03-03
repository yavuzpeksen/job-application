package com.kodgemisi.assignment.services.interfaces;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.kodgemisi.assignment.domains.Job;
import com.kodgemisi.assignment.domains.JobListing;

public interface UserService {
		
	public Long getUserIdByEmail(String email);
			
	public List<Job> getAllJobs();
	
	public Job getJobById(long id);

}
