package com.kodgemisi.assignment.services;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kodgemisi.assignment.domains.JobListing;
import com.kodgemisi.assignment.interfaces.UserService;
import com.kodgemisi.assignment.repositories.JobListingRepository;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private JobListingRepository jlRepository;

	public UserServiceImpl(JobListingRepository jlRepository) {
		this.jlRepository = jlRepository;
	}
	
	@Override
  public JobListing getJobListingByEmail(String email){
  	JobListing jl = jlRepository.getJobListingByEmail(email);
  	return jl;
  }

}
