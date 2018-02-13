package com.kodgemisi.assignment.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/accesspoint")
public class LoginController {
	
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String loginGetPage(Model model, String error, String logout, HttpServletRequest request) {
		
      if (error != null)
          model.addAttribute("error", "Your username and password is invalid.");

      if (logout != null)
          model.addAttribute("message", "You have been logged out successfully.");

      return "login";
  }
  

}
