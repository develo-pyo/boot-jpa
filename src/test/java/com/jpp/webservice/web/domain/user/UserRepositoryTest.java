package com.jpp.webservice.web.domain.user;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.jpp.webservice.web.domain.team.TeamRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserRepositoryTest {

   protected static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryTest.class);
   
   @Autowired
   UserRepository userRepository;
   
   @Autowired
   TeamRepository teamRepository;
   
   @After
   public void cleanup() {
      //이후 테스트 코드에 영향을 끼치지 않기 위해
      //테스트 메소드가 끝날 때 마다 repository 전체 비우는 코드
//      userRepository.deleteAll();
//      groupRepository.deleteAll();
   }
   
   @Ignore
   @Test
   public void userRepositoryTest() {
      
      User user = new User();
      user.setName("pyo");
      userRepository.save(user);
      List<User> userList = userRepository.findAll();

      for(User u : userList) {
         LOGGER.info("nm : "+u.getName());
      }
      
      assertThat(userList.get(0).getName(), is("testor100"));
   }
   
   @Test
   public void jpqlTest() {
      User u = new User();
      u.setName("pyo");
      userRepository.save(u);
      
      User fu = userRepository.customFind2("pyo");
      LOGGER.info("found nm : " + fu.getId());
      
//      ttt();
//      jpqlTest2();
   }
   
   @Transactional
   public void ttt() {
      User u = new User();
      u.setName("pyo");
      userRepository.save(u);
   }

   public void jpqlTest2() {
      User fu = userRepository.customFind2("pyo");
      LOGGER.info("found nm : " + fu.getId());
   }
}
