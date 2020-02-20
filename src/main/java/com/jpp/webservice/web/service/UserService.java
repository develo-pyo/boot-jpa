package com.jpp.webservice.web.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpp.webservice.web.domain.user.UserRepository;

@Service
public class UserService {
   
   @Autowired
   UserRepository userRepository;
   
   @Transactional
   public int insertUser(Map<String, String> param) throws Exception {
      
      
      
      return 1;
   }
   
}
