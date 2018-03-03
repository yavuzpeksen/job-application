package com.kodgemisi.assignment.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kodgemisi.assignment.domains.Job;
import com.kodgemisi.assignment.services.interfaces.UserService;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;


@Controller
public class UserController {
	


	//@Service class'inda hangi isim tanimlandiysa o isim kullanilmak zorunda "userService"
  @Autowired
  private UserService userService;
  
  @RequestMapping(value = "/homepage", method = RequestMethod.GET)
  public String homePage(Model model, Principal principal) {
       
      User loginedUser = (User) ((Authentication) principal).getPrincipal();
      boolean isAdmin = loginedUser.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
      if(isAdmin){
      	return "forward:/admin/homepage";
      }else{	
      	List<Job> jobList = userService.getAllJobs();
      	boolean hasJob = false;
      	if(jobList != null){
      		if(jobList.size() != 0){
        		hasJob = true;
      		}
      	}
      	model.addAttribute("jobList", jobList);
      	model.addAttribute("hasJob", hasJob);
      }
      String name = loginedUser.getUsername();
      model.addAttribute("username",name);
      model.addAttribute("isAdmin",isAdmin);
      
      //model.addAttribute("jobListingId",jobListingId);
      return "homepage";
  }
  @RequestMapping(value = "/getJobApplyPage", method = RequestMethod.GET)
  public String getJobDetailPage(Model model, Principal principal, @RequestParam("postid") int id) {
       
  	//First get the detail of job post
  	userService.getJobById(id);
    return "jobDetailPage";
  }
  
}
