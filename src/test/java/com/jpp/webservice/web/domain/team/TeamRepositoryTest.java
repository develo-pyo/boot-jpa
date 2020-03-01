package com.jpp.webservice.web.domain.team;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

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
//      teamRepository.save(team);
//      
//      Long tid = team.getTid();
//      LOGGER.info(">>> " + tid);
//      
//      
//      List<Team> teams = teamRepository.findAll();
//      for(Team t : teams) {
//         LOGGER.info("t nm:"+t.getTeamNm());
//         LOGGER.info("id:"+t.getTid());
//      }
//      
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
//      }
//   }
   
   @Test
   public void test2() {
      Team team = new Team();
      team.setTeamNm("vip");
      teamRepository.save(team);
      
      Long tid = team.getTid();
      LOGGER.info(">>> " + tid);
      
      User user = new User();
      user.setMobileNum("01012345678");
      user.setName("pyo");
      
      team.getUsers().add(user);
      user.setTeam(team);
      userRepository.save(user);
      
      List<User> users = userRepository.findByName("pyo");
      for(User u : users) {
         LOGGER.info("u nm:"+u.getName());
         Team ttt = u.getTeam();
         LOGGER.info(">> " + ttt.getTeamNm());
      }
   }
}
