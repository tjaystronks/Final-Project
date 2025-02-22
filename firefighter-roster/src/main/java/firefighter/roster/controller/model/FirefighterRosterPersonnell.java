package firefighter.roster.controller.model;

import java.util.HashSet;
import java.util.Set;

import firefighter.roster.entity.Call_Sign;
import firefighter.roster.entity.Personnell;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
public class FirefighterRosterPersonnell {
	private Long personnellId;
	  private String personnellFirstName;
	  private String personnellLastName;
	  private String personnellAge;
	  private String personnellStatus;
	  private Set <FirefighterRosterCallSign> call_signs = new HashSet<>();
	  
	  public FirefighterRosterPersonnell(Personnell personnell) {
		personnellId = personnell.getPersonnellId();
		personnellFirstName = personnell.getPersonnellFistName();
		personnellLastName = personnell.getPersonnellLastName();
		personnellAge = personnell.getPersonnellAge();
		personnellStatus = personnell.getPersonnellStatus();
		
		for(Call_Sign call_sign : personnell.getCall_signs()) {
			  call_signs.add(new FirefighterRosterCallSign(call_sign));
			}
	  }
}