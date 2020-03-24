package com.jpp.webservice.web.domain.team;

import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.jpp.webservice.web.domain.user.User;
import com.jpp.webservice.web.domain.user.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TeamRepositoryTest {
   
   protected static final Logger LOGGER = LoggerFactory.getLogger(TeamRepositoryTest.class);
   
   @Autowired
   TeamRepository teamRepository;
   
   @Autowired
   UserRepository userRepository;
   
//   @Before
//   public void bef() {
//      userRepository.deleteAll();
//      teamRepository.deleteAll();
//   }
   
//   @Test
//   public void test() {
//      Team team = new Team();
//      team.setTeamNm("vip");
//      LOGGER.info("tid before save >>> " + team.getTid());
//      teamRepository.save(team);
//      
//      Long tid = team.getTid();
//      LOGGER.info("tid after save >>> " + tid);
//      
//      
//      List<Team> teams = teamRepository.findAll();
//      for(Team t : teams) {
//         LOGGER.info("t nm:"+t.getTeamNm());
//         LOGGER.info("id:"+t.getTid());
//      }
//      
//      User user = new User();
//      user.setMobileNum("01012345678");
//      user.setName("pyo");
//      user.setTid(tid);
//      
//      userRepository.save(user);
//      List<User> users = userRepository.findByName("pyo");
//      for(User u : users) {
//         LOGGER.info("u nm:"+u.getName());
//         LOGGER.info("u tid:"+u.getTid());
//         List<User> users2 = userRepository.findByTid(u.getTid());
//         for(User us : users2) {
//            LOGGER.info("us : " + us.getName());
//         }
//      }
//   }
   
//   @Test
//   public void test2() {
//      Team team = new Team();
//      team.setTeamNm("vip");
//      
//      User user = new User();
//      user.setMobileNum("01012345678");
//      user.setName("pyo");
//      
//      team.getUsers().add(user);
//      teamRepository.save(team);
//      
//      List<Team> listTeam = teamRepository.findAll();
//      for(Team t : listTeam) {
//         for(User u : t.getUsers()) {
//            LOGGER.info("user name : "+u.getName());
//            LOGGER.info("user team : "+u.getTeam());
//         }
//      }
//   }
   
   @Transactional
   @Test
   public void test1() {
      Team team = new Team();
      team.setTeamNm("vip");
      teamRepository.save(team);
      
      User user = new User();
      user.setMobileNum("01012345678");
      user.setName("pyo");
      
      team.getUsers().add(user);
      user.setTeam(team);
      
      User user2 = new User();
      user2.setName("soo");
      user2.setId(2L);
      
      userRepository.save(user);
      userRepository.save(user2);
      
      User us = userRepository.findById(2L);
      us.setName("sue");
      
      User us2 = userRepository.findById(2L);
      System.out.println("after set : "+us2.getName());
      LOGGER.info("found nm : " + us2.getName());
   }
   
   
   @Test
   public void test2() {
      List<Team> teamList = teamRepository.findAll();
      for(Team t : teamList) {
         for(User u : t.getUsers()) {
            LOGGER.info("user name : "+u.getName());
            LOGGER.info("user team : "+u.getTeam());
         }
      }
   }
   
}
