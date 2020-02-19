package com.jpp.webservice.web.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;


//@Repository 
public interface PostsRepository extends JpaRepository<Posts, Long>{
	
}
