package com.jpp.webservice.web.domain.user;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
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
   
   @Autowired
   UserRepositoryDSL dsl;
   
   @PersistenceContext
   EntityManager em;
   
   @After
   public void cleanup() {
      //이후 테스트 코드에 영향을 끼치지 않기 위해
      //테스트 메소드가 끝날 때 마다 repository 전체 비우는 코드
//      userRepository.deleteAll();
//      groupRepository.deleteAll();
   }
   
   @Test
   @Order(1)
   public void insertTest() {
      User u = new User();
      u.setName("pyo");
      u.setMobileNum("01012341234");
      userRepository.save(u);
   }
   
   @Test
   @Order(2)
   public void queryDSL() {
//      QUser user = QUser.user;
      
//      JPAQueryFactory jqf = new JPAQueryFactory(em);
      
      List<User> r = dsl.selectUserByNm("pyo");
      
//      List<User> r = jqf.selectFrom(user).where(user.name.eq("pyo")).fetch();
      
      for(User u : r) {
         LOGGER.info("result : " + u.getMobileNum());
      }
   }
   
   @Ignore
   @Test
   public void emTest() {
      User user = new User();
      user.setName("pyo");
      userRepository.save(user);
      
      TypedQuery<User> tq = em.createQuery("select u from User u where u.name = :name", User.class);
      tq.setParameter("name", "pyo");
      
      List<User> rs = tq.getResultList();
      LOGGER.info("id :: " + rs.get(0).getId());
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
   
   @Ignore
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
