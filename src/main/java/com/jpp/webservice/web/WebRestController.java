package com.jpp.webservice.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpp.webservice.web.domain.user.User;
import com.jpp.webservice.web.domain.user.UserRepository;

@RestController
public class WebRestController {
	
   protected static final Logger LOGGER = LoggerFactory.getLogger(WebRestController.class);
   
   @Autowired
   private UserRepository userRepository;
   
	@GetMapping("/hello")
	public String hello() {
	   
	   LOGGER.info("hello world!");
	   
	   List<User> user = userRepository.findByName("testor");
	   for(User u : user) {
	      LOGGER.info("user : " + u.getId());
	   }
	   
		return "HELLOWORLD!"; 
	}
}
