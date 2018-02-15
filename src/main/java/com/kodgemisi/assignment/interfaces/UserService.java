package com.kodgemisi.assignment.interfaces;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.kodgemisi.assignment.domains.Job;
import com.kodgemisi.assignment.domains.JobListing;
import com.kodgemisi.assignment.domains.User;

public interface UserService {

	public JobListing getJobListingByEmail(String email);
	
	public Set<Job> getJobByJobListingId(Long id);
	
	public Long getUserIdByEmail(String email);
	
	public void createJobListing(Long id);
	
	public void deleteJobListing();
	
	public List<Job> getAllJobs();
	
	public void addJob(int id, String title, String description, int numOfPerson, Date lastDate);
	
	public void deleteJob(int postId);

}
