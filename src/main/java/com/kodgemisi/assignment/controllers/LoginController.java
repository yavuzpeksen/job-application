package com.kodgemisi.assignment.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kodgemisi.assignment.components.RegisterValidator;
import com.kodgemisi.assignment.domains.form.RegisterForm;
import com.kodgemisi.assignment.services.TestBean;
import com.kodgemisi.assignment.services.interfaces.SecurityService;
import com.kodgemisi.assignment.services.interfaces.UserService;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.Twitter;

@Controller
@RequestMapping(value="/accesspoint")
public class LoginController {
	
  @Autowired
  private UserService userService;
  
  @Autowired
  private SecurityService securityService;
  
  @Autowired
  private RegisterValidator registerFormValidator;
  
  @Autowired
  private TestBean beanA;
  
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String loginGetPage(Model model, String error, String logout, HttpServletRequest request) {

  	beanA.doNothing();
  	if (error != null)
  		model.addAttribute("error", "Login unsuccessful");

    return "login";
  }
  
  @RequestMapping(value = "/unauthorized", method = RequestMethod.GET)
  public String accessDenied(Model model, Principal principal) {

    if (principal != null) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String username = loginedUser.getUsername();
        model.addAttribute("username", username);
    }
    
    

    return "unauthorized";
  }

  @RequestMapping(value="/logout", method = RequestMethod.GET)
  public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      if (auth != null){    
          new SecurityContextLogoutHandler().logout(request, response, auth);
      }
      return "logoutSuccessfulPage";
  }
  
  @RequestMapping(value = "/register", method = RequestMethod.GET)
  public String getRegisterPage(Model model, HttpServletRequest request) {

  	model.addAttribute("userForm",  new com.kodgemisi.assignment.domains.User());
    return "register";
  }
  
  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public String registration(@ModelAttribute("userForm") RegisterForm registerForm, BindingResult bindingResult, Model model) {
		registerFormValidator.validate(registerForm, bindingResult);    
    if (bindingResult.hasErrors()) {
    	return "register";
        
    }
    
  	userService.save(registerForm);
  	securityService.autoLogin(registerForm.getEmail(), registerForm.getPasswordConfirm());
   
  	return "redirect:/homepage";
  }
  
  /*@RequestMapping(value = "/facebook", method = RequestMethod.GET)
  public String loginFacebook(Model model) {

  	Connection<Facebook> connection = connectionRepository.findPrimaryConnection(Facebook.class);
  	if(connection == null){
  		return "redirect:/connect/facebook";
  	}
  	org.springframework.social.facebook.api.User u = facebook.userOperations().getUserProfile();
  	String email = connection.fetchUserProfile().getEmail();
  	//connection.getApi().feedOperations().
    return "homepage";
  }*/

}
