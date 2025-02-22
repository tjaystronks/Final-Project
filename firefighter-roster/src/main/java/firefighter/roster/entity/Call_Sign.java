package firefighter.roster.entity;

import java.util.HashSet;
import java.util.Set;

import firefighter.roster.controller.model.FirefighterRosterCallSign;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode; 
import lombok.ToString;
 

@Entity
@Data
public class Call_Sign {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long callSignId;
	  
	  private String callSignName;
	  
	  @EqualsAndHashCode.Exclude
	  @ToString.Exclude
	  @ManyToMany(mappedBy = "call_sign", cascade = CascadeType.PERSIST)
	  private Set<Personnell> personnell = new HashSet<>();

	public static Location[] getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	}

