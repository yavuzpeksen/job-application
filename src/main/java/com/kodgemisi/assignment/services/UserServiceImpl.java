package com.kodgemisi.assignment.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kodgemisi.assignment.domains.Job;
import com.kodgemisi.assignment.domains.Role;
import com.kodgemisi.assignment.domains.User;
import com.kodgemisi.assignment.domains.form.RegisterForm;
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
  
  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;
	
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

	@Override
	public void save(RegisterForm registerForm) {
		
		User user = new User();
		Role role = new Role();	
		role.setId(2L);
		role.setName("ROLE_USER");
		List<Role> roleList = new ArrayList<>();
		roleList.add(role);
    
    user.setCreationDate(new Timestamp(new Date().getTime()));
		user.setEmail(registerForm.getEmail());
		user.setPassword(bCryptPasswordEncoder.encode(registerForm.getPassword()));
    user.setRoles(new HashSet<>(roleList));
    userRepository.save(user);
		
	}

	@Override
	public User getUserByEmail(String email) {
		
		return userRepository.findByEmail(email);
		
	}

}
