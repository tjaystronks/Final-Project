package firefighter.roster.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import firefighter.roster.entity.Location;


public interface LocationDao extends JpaRepository<Location, Long>{

}
