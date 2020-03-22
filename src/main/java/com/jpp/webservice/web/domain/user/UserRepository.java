package com.jpp.webservice.web.domain.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

 
public interface UserRepository extends JpaRepository<User, Long>{
	
   List<User> findByName(String name);
   User findById(long id);
   
   @Query("select u from User u where u.name = ?1")
   User customFind1(String nm);
   
   
   @Query("select u from User u where u.name = :name")
   User customFind2(@Param("name") String nm);
   
}
