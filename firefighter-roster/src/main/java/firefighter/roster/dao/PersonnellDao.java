package firefighter.roster.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import firefighter.roster.entity.Personnell;

public interface PersonnellDao extends JpaRepository<Personnell, Long> {

}

