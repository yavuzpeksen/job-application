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
import org.springframework.web.bind.annotation.RequestParam;

import com.kodgemisi.assignment.components.RegisterValidator;
import com.kodgemisi.assignment.domains.ConfirmationToken;
import com.kodgemisi.assignment.domains.form.RegisterForm;
import com.kodgemisi.assignment.services.EmailService;
import com.kodgemisi.assignment.services.TestBean;
import com.kodgemisi.assignment.services.interfaces.ConfirmationService;
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
  private ConfirmationService confirmationService;
  
  @Autowired
  private EmailService emailService;
  
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
  public String registration(@ModelAttribute("userForm") RegisterForm registerForm, BindingResult bindingResult, Model model, HttpServletRequest request) {
		registerFormValidator.validate(registerForm, bindingResult);    
    if (bindingResult.hasErrors()) {
    	return "register";
        
    }

  	userService.save(registerForm);
  	com.kodgemisi.assignment.domains.User user = userService.getUserByEmail(registerForm.getEmail());
    String token = confirmationService.save(user);
  	boolean isEmailSent = emailService.sendConfirmationEmail(request, registerForm.getEmail(), token);
  	String cMessage = "Confirmation e-mail has been sent to " + user.getEmail();
  	if(!isEmailSent){
  		cMessage = "Server internal error, try again later";
  	}
  	model.addAttribute("isEmailSent", isEmailSent);
  	model.addAttribute("confirmationMessage", cMessage);   
  	return "register";
  }
  @RequestMapping(value = "/confirm", method = RequestMethod.GET)
  public String showConfirmStatus(Model model, HttpServletRequest request, @RequestParam("token") String token){
  	
  	ConfirmationToken ct = confirmationService.findByToken(token);
  	boolean status = false;
  	if(ct != null){
  		userService.confirmUser(ct.getUser());
  		status = true;
  	}
  	
  	model.addAttribute("confirmationStatus", status);
  	return "login";
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
