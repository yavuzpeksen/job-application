package com.kodgemisi.assignment.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kodgemisi.assignment.domains.Job;
import com.kodgemisi.assignment.domains.JobListing;
import com.kodgemisi.assignment.interfaces.UserService;
import com.kodgemisi.assignment.repositories.JobListingRepository;
import com.kodgemisi.assignment.repositories.JobRepository;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private JobListingRepository jlRepository;
  
  @Autowired
  private JobRepository jobRepository;

	public UserServiceImpl(JobListingRepository jlRepository) {
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

}
