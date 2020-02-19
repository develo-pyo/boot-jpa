package com.jpp.webservice.web.domain.user;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.jpp.webservice.web.domain.posts.Posts;
import com.jpp.webservice.web.domain.posts.Posts.PostsBuilder;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	private String mobileNum;
	
	
   public Long getId() {
      return id;
   }
   public void setId(Long id) {
      this.id = id;
   }
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public String getMobileNum() {
      return mobileNum;
   }
   public void setMobileNum(String mobileNum) {
      this.mobileNum = mobileNum;
   }
	
}
