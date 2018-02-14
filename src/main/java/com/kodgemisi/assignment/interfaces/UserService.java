package com.kodgemisi.assignment.interfaces;

import java.util.Set;

import com.kodgemisi.assignment.domains.Job;
import com.kodgemisi.assignment.domains.JobListing;

public interface UserService {

	public JobListing getJobListingByEmail(String email);
	
	public Set<Job> getJobByJobListingId(Long id);

}
