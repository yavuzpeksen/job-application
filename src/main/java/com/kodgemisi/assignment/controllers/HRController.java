package com.kodgemisi.assignment.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kodgemisi.assignment.domains.Job;
import com.kodgemisi.assignment.domains.JobListing;
import com.kodgemisi.assignment.services.interfaces.HRService;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;


@Controller
@RequestMapping(value="/admin")
public class HRController {
	
	//@Service class'inda hangi isim tanimlandiysa o isim kullanilmak zorunda "hrService"
  @Autowired
  private HRService hrService;
  
  @RequestMapping(value = "/homepage", method = RequestMethod.GET)
  public String homePage(Model model, Principal principal) {
       
      User loginedUser = (User) ((Authentication) principal).getPrincipal();
      boolean isAdmin = loginedUser.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
      Long jobListingId = -1L;
      if(isAdmin){
      	JobListing jl = hrService.getJobListingByEmail(loginedUser.getUsername());
      	if(jl != null){
      		jobListingId = jl.getId();
      	}
      }else{	
      	List<Job> jobList = hrService.getAllJobs();
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
      model.addAttribute("jobListingId",jobListingId);
      return "homepage";
  }
  @RequestMapping(value = "/getJobDetailPage", method = RequestMethod.GET)
  public String getJobDetailPage(Model model, Principal principal, @RequestParam("postid") int id) {
       
    return "jobDetailPage";
  }
  
  @RequestMapping(value = "/getJobListing", method = RequestMethod.POST)
  public String getJobListing(Model model, Principal principal, @RequestParam("id") int id) {

  	Set<Job> jobSet = hrService.getJobByJobListingId(Long.valueOf(id));
  	boolean hasJob = false;
  	if(jobSet != null ){
  		if(jobSet.size() != 0){
    		hasJob = true;
  		}
  	}
    model.addAttribute("jobSet", jobSet);
    model.addAttribute("hasJob", hasJob);
    model.addAttribute("jobListingId",id);
       
    return "jobListingPage";
  }
  
  @RequestMapping(value = "/deleteJobListing", method = RequestMethod.POST)
  @ResponseBody
  public String deleteJobListing(Model model, Principal principal, @RequestParam("id") int id) {
       
  	hrService.deleteJobListing(id);
		String result = "{\"status\":1}";
		
		return result;
  }
  
  @RequestMapping(value = "/createJobListing", method = RequestMethod.GET)
  @ResponseBody
  public String createJobListing(Model model, Principal principal) {
       
  	User loginedUser = (User) ((Authentication) principal).getPrincipal();
  	Long userId = hrService.getUserIdByEmail(loginedUser.getUsername());
  	hrService.createJobListing(userId);  	
  	String result = "{\"status\":1}";
  	
  	return result;
  }
  
  @RequestMapping(value = "/createJobPost", method = RequestMethod.POST)
  @ResponseBody
  public String createJobPost(Model model, Principal principal, @RequestParam("title") String title, @RequestParam("description") String description, @RequestParam("numOfPerson") int numOfPerson, @RequestParam("lastDate") String lastDate, @RequestParam("id") int id) {
       
  	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
  	Date dateObj = null;  	
    String result = "{\"status\":1}";
		try {
			dateObj = format.parse(lastDate);
		} catch (ParseException e) {
			result = "{\"status\":0}";
			return result;
		}
		hrService.createJobPost(id, title, description, numOfPerson, dateObj);
          
    return result;
  }
  
  @RequestMapping(value = "/deleteJobPost", method = RequestMethod.POST)
  @ResponseBody
  public String deleteJobPost(Model model, Principal principal, @RequestParam("postid") int postId) {
       
    String result = "{\"status\":1}";
    hrService.deleteJob(postId);
  	
    return result;
  }
  
  @ModelAttribute("pageMessage")
  public String getPageMessage(){
  	return "This is an admin page.";
  }
  
}
