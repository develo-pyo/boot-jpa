package com.jpp.webservice.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jpp.webservice.web.domain.user.User;
import com.jpp.webservice.web.service.UserService;

@RestController
public class UserController {
   
   @Autowired
   private UserService userService;
   
   private static final Logger logger = LoggerFactory.getLogger(UserController.class);
   
   @PostMapping("/user")
   public ResponseEntity<?> userPost() throws Exception {
      logger.info("userPost");

      Map<String, String> rs = new HashMap<>();
      
      int i = 0;
//      i = userService.insertUser(new HashMap<String, String>());
      return new ResponseEntity<>(HttpStatus.OK);
   }
   
   @GetMapping("/user/{id}")
   public ResponseEntity<List<User>> userGet(Model model) throws Exception {
      logger.info("userGet");
      
      List<User> rs = userService.selectByNm("pyo");
      for(User u : rs) {
         logger.info("u : " + u.getName());
      }
      return new ResponseEntity<List<User>>(new ArrayList<User>(), HttpStatus.OK);
   }
   
   @PutMapping("/user/{id}")
   public ResponseEntity<Map<String, String>> userPut(@RequestBody Map<String, String> param) throws Exception {
      logger.info("userPut");

      Map<String, String> rs = new HashMap<>();
      
      
      return new ResponseEntity<Map<String,String>>(rs, HttpStatus.OK);
   }
   
   @DeleteMapping("/user/{id}")
   public ResponseEntity<Map<String, String>> userDelete(Model model) throws Exception {
      logger.info("userDelete");

      Map<String, String> rs = new HashMap<>();
      
      
      return new ResponseEntity<Map<String,String>>(rs, HttpStatus.OK);
   }
   
   
   
}
