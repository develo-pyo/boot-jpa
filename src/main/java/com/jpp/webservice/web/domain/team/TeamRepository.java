package com.jpp.webservice.web.domain.team;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpp.webservice.web.domain.user.User;

public interface TeamRepository extends JpaRepository<Team, Long> {
   Team findByTid(Long tid);
}
