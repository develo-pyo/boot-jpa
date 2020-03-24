package com.jpp.webservice.web.domain.user;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class UserRepositoryDSL {
   
   private JPAQueryFactory queryFactory;
   
   @PersistenceContext
   EntityManager em;
   
   public UserRepositoryDSL(JPAQueryFactory queryFactory) {
      this.queryFactory = queryFactory;
   }
   
   //https://www.baeldung.com/querydsl-with-jpa-tutorial
   public List<User> selectByNm(){ 
      
//      User u = new User();
      QUser user = QUser.user;
//      JPAQueryFactory jqf = new JPAQueryFactory(em);
      
      return queryFactory.selectFrom(user).where(user.name.eq("pyo")).fetch();
   }
   
}
