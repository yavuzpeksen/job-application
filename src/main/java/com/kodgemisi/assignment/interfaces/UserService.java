package com.kodgemisi.assignment.interfaces;

import com.kodgemisi.assignment.domains.JobListing;

public interface UserService {

	public JobListing getJobListingByEmail(String email);
	
	

}
