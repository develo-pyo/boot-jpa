package com.jpp.webservice.web.domain.user;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.jpp.webservice.web.domain.user.QUser;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class UserRepositoryDSL {
   
   @PersistenceContext
   EntityManager em;
   
   public List<User> selectUserByNm(String nm){
      JPAQueryFactory jqf = new JPAQueryFactory(em);
      
      QUser user = QUser.user;
      return jqf.selectFrom(user).where(user.name.eq(nm)).fetch();
   }
   
}
