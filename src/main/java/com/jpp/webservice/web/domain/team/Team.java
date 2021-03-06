package com.jpp.webservice.web.domain.team;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.jpp.webservice.web.domain.user.User;

@Entity
public class Team {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="TEAM_ID")
	private Long tid;
	
	private String teamNm;

	@OneToMany(mappedBy = "team", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<User> users = new ArrayList<>();
	
   public Long getTid() {
      return tid;
   }

   public void setTid(Long tid) {
      this.tid = tid;
   }

   public String getTeamNm() {
      return teamNm;
   }

   public void setTeamNm(String teamNm) {
      this.teamNm = teamNm;
   }

   public List<User> getUsers() {
      return users;
   }

   public void setUsers(List<User> users) {
      this.users = users;
   }

   @Override
   public String toString() {
      return "Team [tid=" + tid + ", teamNm=" + teamNm + ", users=" + users + "]";
   }
   
}
