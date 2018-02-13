package com.kodgemisi.assignment.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.security.Principal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;


@Controller
public class MainController {

  @RequestMapping(value = "/homepage", method = RequestMethod.GET)
  public String homePage(Model model, Principal principal) {
       
      User loginedUser = (User) ((Authentication) principal).getPrincipal();

      String name = loginedUser.getUsername();
      model.addAttribute("username",name);
       
      return "homepage";
  }
  @RequestMapping(value = "/admin", method = RequestMethod.GET)
  public String adminPage(Model model, Principal principal) {
       
      User loginedUser = (User) ((Authentication) principal).getPrincipal();

      //model.addAttribute("userInfo", userInfo);
       
      return "adminPage";
  }
  
  @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
  public String logoutSuccessfulPage(Model model) {
      model.addAttribute("title", "Logout");
      return "logoutSuccessfulPage";
  }
  
}
