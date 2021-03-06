package com.kodgemisi.assignment.services;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodgemisi.assignment.domains.Job;
import com.kodgemisi.assignment.domains.JobListing;
import com.kodgemisi.assignment.domains.User;
import com.kodgemisi.assignment.repositories.JobListingRepository;
import com.kodgemisi.assignment.repositories.JobRepository;
import com.kodgemisi.assignment.repositories.UserRepository;
import com.kodgemisi.assignment.services.interfaces.HRService;
import com.kodgemisi.assignment.services.interfaces.UserService;

@Service("hrService")
public class HRServiceImpl extends UserServiceImpl implements HRService {

  @Autowired
  private JobListingRepository jlRepository;

	public HRServiceImpl(JobListingRepository jlRepository) {
		this.jlRepository = jlRepository;
	}
	
	@Override
  public JobListing getJobListingByEmail(String email){
  	JobListing jl = jlRepository.getJobListingByEmail(email);
  	return jl;
  }
		
	@Override
  public Set<Job> getJobByJobListingId(Long id){
		Set<Job> jobSet = jobRepository.getJobByJobListingId(id);
		return jobSet;
  }

	@Override
	public void createJobPost(int id, String title, String description, int numOfPerson, Date lastDate) {
		
		Job currentJob = new Job();
		JobListing currJobListing = new JobListing();
		
		currJobListing.setId(Long.valueOf(id));
		currentJob.setJobListing(currJobListing);
		currentJob.setTitle(title);
		currentJob.setDescription(description);
		currentJob.setHiringPersonNumber(numOfPerson);
		currentJob.setLastApplicationDate(lastDate);
		jobRepository.save(currentJob);
	}

	@Override
	public void deleteJob(int postId) {
		
		jobRepository.delete(Long.valueOf(postId));		
		
	}

	@Override
	public void createJobListing(Long id) {
		
		JobListing jobListing = new JobListing();
		User user = new User();
		
		user.setId(id);	
		jobListing.setUser(user);
		jlRepository.save(jobListing);
	}

	@Override
	public void deleteJobListing(int id) {
		jobRepository.deleteJobsByJobListingId(Long.valueOf(id));
	}

}
