package com.kodgemisi.assignment.services.interfaces;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.kodgemisi.assignment.domains.Job;
import com.kodgemisi.assignment.domains.JobListing;

public interface HRService extends UserService{

	public JobListing getJobListingByEmail(String email);
	
	public Set<Job> getJobByJobListingId(Long id);
		
	public void createJobListing(Long id);
	
	public void deleteJobListing(int id);
		
	public void createJobPost(int id, String title, String description, int numOfPerson, Date lastDate);
	
	public void deleteJob(int postId);

}
