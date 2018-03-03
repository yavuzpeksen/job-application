package com.kodgemisi.assignment.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.kodgemisi.assignment.domains.Job;
import com.kodgemisi.assignment.domains.User;
import com.kodgemisi.assignment.repositories.JobListingRepository;
import com.kodgemisi.assignment.repositories.JobRepository;
import com.kodgemisi.assignment.repositories.UserRepository;
import com.kodgemisi.assignment.services.interfaces.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

  
  @Autowired
  protected JobRepository jobRepository;
  
  @Autowired
  protected UserRepository userRepository;
	
	@Override
	public List<Job> getAllJobs() {
		List<Job> jobSet = jobRepository.findAll();
		return jobSet;
	}

	@Override
	public Long getUserIdByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user.getId();
	}
	
	@Override
	public Job getJobById(long id){
		Job job = jobRepository.findOne(id);
		return job;
	}

}
