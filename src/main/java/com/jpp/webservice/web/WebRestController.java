package com.jpp.webservice.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpp.webservice.web.comm.aop.logging.NoLogging;
import com.jpp.webservice.web.domain.team.Team;
import com.jpp.webservice.web.domain.team.TeamRepository;
import com.jpp.webservice.web.domain.user.User;
import com.jpp.webservice.web.domain.user.UserRepository;
import com.jpp.webservice.web.service.UserService;

@RestController
public class WebRestController {
	
   protected static final Logger LOGGER = LoggerFactory.getLogger(WebRestController.class);
   
   @Autowired
   private UserRepository userRepository;
   
   @Autowired
   private TeamRepository teamRepository;
   
   @Autowired
   private UserService userService;
   
	@GetMapping("/hello")
	public String hello() {
	   
	   LOGGER.info("hello world!");
	   
	   List<User> user = userRepository.findByName("testor");
	   for(User u : user) {
	      LOGGER.info("user : " + u.getId());
	   }
	   
		return "HELLOWORLD!"; 
	}
	
	@NoLogging
	@GetMapping("/insert")
   public String i() throws Exception {
	   userService.insertUser();
      return ""; 
   }
	
	@NoLogging
	@GetMapping("/select")
   public String s() throws Exception {
      userService.selectUserByTeam();
      return ""; 
   }
   
}
