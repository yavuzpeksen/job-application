package com.kodgemisi.assignment.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kodgemisi.assignment.domains.Job;
import com.kodgemisi.assignment.domains.JobListing;
import com.kodgemisi.assignment.interfaces.UserService;

import java.security.Principal;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;


@Controller
public class MainController {
	

  @Autowired
  private UserService userService;
  
  @RequestMapping(value = "/homepage", method = RequestMethod.GET)
  public String homePage(Model model, Principal principal) {
       
      User loginedUser = (User) ((Authentication) principal).getPrincipal();
      
      boolean isAdmin = loginedUser.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
      Long jobListingId = -1L;
      if(isAdmin){
      	JobListing jl = userService.getJobListingByEmail(loginedUser.getUsername());
      	if(jl == null){
      		System.out.println("Veri bulunamadi.");
      	}else{
      		jobListingId = jl.getId();
      		//System.out.println("Is ilan olusturma sayfaniza gitmek icin tiklayin. JL:" +jl.getId());
      		
      	}
      	//USER id ye gore arama yap, tabloda varsa link ver. 
      	//
      }else{
      	
      }
      
      String name = loginedUser.getUsername();
      model.addAttribute("username",name);
      model.addAttribute("isAdmin",isAdmin);
      model.addAttribute("jobListingId",jobListingId);
      return "homepage";
  }
  @RequestMapping(value = "/getJobListing", method = RequestMethod.POST)
  public String getJobListing(Model model, Principal principal, @RequestParam("id") int id) {
       
    //User loginedUser = (User) ((Authentication) principal).getPrincipal();
  	//System.out.println("Getirilecek id:" + id);
  	Set<Job> jobSet = userService.getJobByJobListingId(Long.valueOf(id));
  	
    model.addAttribute("jobSet", jobSet);
       
    return "jobListingPage";
  }
  
  @RequestMapping(value = "/deleteJobListing", method = RequestMethod.POST)
  public String deleteJobListing(Model model, Principal principal, @RequestParam("id") int id) {
       
      //User loginedUser = (User) ((Authentication) principal).getPrincipal();
		System.out.println("Getirilecek id:" + id);

      //model.addAttribute("userInfo", userInfo);
       
      return "adminPage";
  }
  
  @RequestMapping(value = "/createJobListing", method = RequestMethod.GET)
  public String createJobListing(Model model, Principal principal) {
       
    //User loginedUser = (User) ((Authentication) principal).getPrincipal();
		//System.out.println("Getirilecek id:" + id);
  	System.out.println("Job Listing yaratilacak.");
      //model.addAttribute("userInfo", userInfo);
       
      return "adminPage";
  }
  
  @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
  public String logoutSuccessfulPage(Model model) {
      model.addAttribute("title", "Logout");
      return "logoutSuccessfulPage";
  }
  
}
