package com.kodgemisi.assignment.controllers;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping(value="twitter")
public class TwitterController {

  @Autowired
  private ConnectionRepository connectionRepository;
  
  @Autowired
  private Twitter twitter;
  
	@RequestMapping(value="/auth", method=RequestMethod.GET)
	public String findUser(Model model){
		Connection<Twitter> connection = connectionRepository.findPrimaryConnection(Twitter.class);
		
		//TwitterTemplate twitterTemplate = (TwitterTemplate)connection.getApi();
		//RestTemplate restTemplate = twitterTemplate.getRestTemplate();
    //String response = restTemplate.getForObject("https://api.twitter.com/1.1/account/verify_credentials.json?include_email=true", String.class);
    //String email = extractEmailFromResponse(response);
    String username = connection.fetchUserProfile().getEmail();
    
    //Eger null ise email alma yetkisi yok. Web sitesini kullanma izni verme    
    if(username == null){
    	return "redirect:/accesspoint/unauthorized";
    }		
		model.addAttribute("profile", twitter.userOperations().getUserProfile());		
		return "homepage";
		
	}
	
	private String extractEmailFromResponse(String response){
    JSONObject jsonObject= null;
    String email =null;
    try {
        jsonObject = new JSONObject(response);
        email=(String)jsonObject.get("email");
    } catch (JSONException e) {
        e.printStackTrace();
    }
    return email;
}
}
