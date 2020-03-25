package com.jpp.webservice.web.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpp.webservice.web.domain.team.Team;
import com.jpp.webservice.web.domain.team.TeamRepository;
import com.jpp.webservice.web.domain.user.User;
import com.jpp.webservice.web.domain.user.UserRepository;
import com.jpp.webservice.web.domain.user.UserRepositoryDSL;

@Service
public class UserService {
   
   private static final Logger logger = LoggerFactory.getLogger(UserService.class);
   
   @Autowired
   UserRepository userRepository;
   
   
   @Autowired
   TeamRepository teamRepository;

   @Autowired
   UserRepositoryDSL dsl;
   
   @Transactional
   public int insertUser() throws Exception {
      Team team = new Team();
      team.setTeamNm("vip");
      teamRepository.save(team);
      
      User user = new User();
      user.setName("soo");
      user.setTeam(team);
      userRepository.save(user);
      
      return 1;
   }
   
   public void selectUserByTeam() {
      Team foundTeam = teamRepository.findByTid(1L);
      List<User> listUser = foundTeam.getUsers();
      for(User u : listUser) {
         logger.info("user name:"+u.getName());
      }
   }
   
   public List<User> selectByNm(String nm) {
      return dsl.selectUserByNm(nm);
   }
   
}
