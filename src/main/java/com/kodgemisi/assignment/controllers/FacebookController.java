package com.kodgemisi.assignment.controllers;


import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kodgemisi.assignment.components.RandomStringGenerator;
import com.kodgemisi.assignment.services.interfaces.SecurityService;
import com.kodgemisi.assignment.services.interfaces.UserService;

@Controller
@RequestMapping(value="facebook")
public class FacebookController {

  @Autowired
  private ConnectionRepository connectionRepository;
  
  @Autowired
  private SecurityService securityService;
  
  @Autowired
  private UserService userService;
  
  @Autowired
  private Facebook facebook;
  
  @Autowired
  private RandomStringGenerator passwordGenerator;
    
	@RequestMapping(value="/auth", method=RequestMethod.GET)
	public String findUser(Model model){
		
		ConnectionKey connectionKey = connectionRepository.findPrimaryConnection(Facebook.class).getKey();
    String userKey = connectionRepository.findPrimaryConnection(Facebook.class).getKey().getProviderUserId();
    String[] fields = {"id", "email"};
    User facebookUser = facebook.fetchObject(userKey, User.class, fields);

    com.kodgemisi.assignment.domains.User currentUser = userService.getUserByEmail(facebookUser.getEmail());
    String currentPassword = null;
    if(currentUser == null){
    	
    	com.kodgemisi.assignment.domains.User user = new com.kodgemisi.assignment.domains.User();
    	user.setCreationDate(new Timestamp(new Date().getTime()));
    	
    	currentPassword = passwordGenerator.generateRandomString(8);
    	userService.save(user);
    }else{
    	currentPassword = currentUser.getPassword();
    }

    securityService.autoLogin(facebookUser.getEmail(), currentPassword);
    connectionRepository.removeConnection(connectionKey);	
		return "redirect:/homepage";
		
	}
	
}
