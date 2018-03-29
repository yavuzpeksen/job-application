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
import com.kodgemisi.assignment.components.ResetPasswordValidator;
import com.kodgemisi.assignment.domains.ConfirmationToken;
import com.kodgemisi.assignment.domains.PasswordResetToken;
import com.kodgemisi.assignment.domains.form.RegisterForm;
import com.kodgemisi.assignment.domains.form.ResetPasswordForm;
import com.kodgemisi.assignment.services.EmailService;
import com.kodgemisi.assignment.services.TestBean;
import com.kodgemisi.assignment.services.interfaces.ConfirmationService;
import com.kodgemisi.assignment.services.interfaces.PasswordResetService;
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
  private ResetPasswordValidator resetPasswordValidator;
  
  @Autowired
  private ConfirmationService confirmationService;
  
  @Autowired
  private PasswordResetService resetService;
  
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
  		confirmationService.deleteConfirmationToken(ct);
  	}

  	model.addAttribute("confirmationStatus", status);
  	return "login";
  }
  
  @RequestMapping(value = "/forgot-password", method = RequestMethod.GET)
  public String getForgotPasswordPage(Model model, HttpServletRequest request){
  	
  	return "forgotPassword";
  }
  
  @RequestMapping(value = "/forgot-password", method = RequestMethod.POST)
  public String sendPasswordResetToken(Model model, HttpServletRequest request, @ModelAttribute("email") String email){
  	
  	com.kodgemisi.assignment.domains.User user = userService.getUserByEmail(email);
  	boolean status = false;
  	if(user != null){
  		status = true;
  		String token = resetService.save(user);
  		boolean isEmailSent = emailService.sendResetPasswordEmail(request, email, token);
    	String cMessage = "Reset e-mail has been sent to " + email;
  		if(!isEmailSent){
    		cMessage = "Server internal error, try again later";
    	}
  		model.addAttribute("resetMessage", cMessage);
  		model.addAttribute("isEmailSent", isEmailSent);
  	}
  	model.addAttribute("resetEmailControl", status);
  	
  	return "forgotPassword";
  }
  
  //Map objesine token-kullanici id atilabilir.
  @RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
  public String checkResetToken(Model model, HttpServletRequest request, @RequestParam("token") String token){
  	
  	PasswordResetToken prt = resetService.findByToken(token);
  	boolean status = false;
  	if(prt != null){
  		//userService.confirmUser(ct.getUser());
  		status = true;
  		//confirmationService.deleteConfirmationToken(ct);
  	}
  	ResetPasswordForm resetPasswordForm = new ResetPasswordForm();
  	resetPasswordForm.setToken(token);
  	model.addAttribute("isFormAllowed", status);
  	model.addAttribute("resetForm", resetPasswordForm);
  	return "resetPassword";
  }
  
  @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
  public String resetPassword(@ModelAttribute("resetForm") ResetPasswordForm resetForm, BindingResult bindingResult, Model model, HttpServletRequest request) {
		resetPasswordValidator.validate(resetForm, bindingResult);    
    if (bindingResult.hasErrors()) {
    	return "resetPassword";
        
    }
    
    PasswordResetToken prt = resetService.findByToken(resetForm.getToken());
    boolean status = false;
    String resetResultMessage = "Given token is not valid";
  	if(prt != null){
  		//com.kodgemisi.assignment.domains.User user = resetService.findUserByToken(resetForm.getToken());
  		com.kodgemisi.assignment.domains.User user = userService.findUserByPasswordResetToken(resetForm.getToken());
  		userService.changePassword(user, resetForm.getPassword());
  		status = true;
  		resetResultMessage = "Your password has been successfully changed";
  		//confirmationService.deleteConfirmationToken(ct);
  	}

  	model.addAttribute("resetStatus", status);
  	model.addAttribute("resetResultMessage", resetResultMessage);
  
  	return "resetPassword";
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
