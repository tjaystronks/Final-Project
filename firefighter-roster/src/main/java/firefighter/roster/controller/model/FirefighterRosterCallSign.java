package firefighter.roster.controller.model;

import java.util.HashSet;
import java.util.Set;

import firefighter.roster.entity.Call_Sign;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
public class FirefighterRosterCallSign {
	private Long callsignId;
	  private String callSignName;
	 	  
	  public FirefighterRosterCallSign(Call_Sign call_sign) {
		callsignId = call_sign.getCallSignId();
		callSignName = call_sign.getCallSignName();
	  }
	}

