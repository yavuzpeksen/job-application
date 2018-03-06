package com.kodgemisi.assignment.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

@Controller
@RequestMapping(value="/accesspoint")
public class LoginController {
	
  @Autowired
  private UserService userService;
  
  @Autowired
  private SecurityService securityService;
  
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String loginGetPage(Model model, String error, String logout, HttpServletRequest request) {

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
  public String registration(@ModelAttribute("userForm") com.kodgemisi.assignment.domains.User userForm, BindingResult bindingResult, Model model) {
      /*userValidator.validate(userForm, bindingResult);

      if (bindingResult.hasErrors()) {
          return "registration";
      }*/
  	
  	userForm.setCreationDate(new Timestamp(new Date().getTime()));

      userService.save(userForm);

      securityService.autoLogin(userForm.getEmail(), userForm.getPasswordConfirm());
      

      return "redirect:/homepage";
  }

}
