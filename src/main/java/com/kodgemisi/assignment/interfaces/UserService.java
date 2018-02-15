package com.kodgemisi.assignment.interfaces;

import java.util.Date;
import java.util.Set;

import com.kodgemisi.assignment.domains.Job;
import com.kodgemisi.assignment.domains.JobListing;

public interface UserService {

	public JobListing getJobListingByEmail(String email);
	
	public Set<Job> getJobByJobListingId(Long id);
	
	public void addJob(int id, String title, String description, int numOfPerson, Date lastDate);

}
