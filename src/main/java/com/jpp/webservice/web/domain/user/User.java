package com.jpp.webservice.web.domain.user;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Null;

import com.jpp.webservice.web.domain.team.Team;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	
	
	private String name;
	
	private String mobileNum;
	
//	private Long tid; 
	
	@ManyToOne
	@JoinColumn(name="TEAM_ID")
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
//   public Long getTid() {
//      return tid;
//   }
//   public void setTid(Long tid) {
//      this.tid = tid;
//   }
   public Team getTeam() {
      return team;
   }
   public void setTeam(Team team) {
      this.team = team;
   }
   @Override
   public String toString() {
      return "User [id=" + id + ", name=" + name + ", mobileNum=" + mobileNum + ", team=" + team.getTeamNm() + "]";
   }
	
}
