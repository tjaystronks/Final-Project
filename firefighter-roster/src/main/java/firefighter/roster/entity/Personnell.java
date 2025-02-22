package firefighter.roster.entity;

import java.util.HashSet;
import java.util.Set;

import firefighter.roster.controller.model.FirefighterRosterLocation;
import firefighter.roster.controller.model.FirefighterRosterPersonnell;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Personnell {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long personnellId;
	  
	  private String personnellFistName;
	  private String personnellLastName;
	  private String personnellAge;
	  private String personnellStatus;
	  
	  @EqualsAndHashCode.Exclude
	  @ToString.Exclude
	  @ManyToOne(cascade = CascadeType.ALL)
	  @JoinColumn(name = "personnell_id")
	  private Location location;
	  
	  @EqualsAndHashCode.Exclude
	  @ToString.Exclude
	  @ManyToMany(cascade = CascadeType.PERSIST)
      @JoinTable(name = "personnell_call_sign",
      joinColumns = @JoinColumn(name = "personnell_id"),
      inverseJoinColumns = @JoinColumn(name = "call_sign_id"))
	  private Set <Call_Sign> call_signs = new HashSet <>()	;
	  }
	

