package com.jpp.webservice.web.domain.user;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.jpp.webservice.web.domain.team.Team;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	
//	@Column(name="usernm", length=10)
	private String name;
	
	private String mobileNum;
	
	@ManyToOne
	@JoinColumn
	private Team team;
	
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
   public Team getTeam() {
      return team;
   }
   public void setTeam(Team team) {
      this.team = team;
   }
	
}
