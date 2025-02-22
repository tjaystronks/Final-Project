package firefighter.roster.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import firefighter.roster.entity.Call_Sign;

public interface CallSignDao extends JpaRepository<Call_Sign, Long> {
} 