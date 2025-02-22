package firefighter.roster.controller.model;

import java.util.HashSet;
import java.util.HashSet;
import java.util.Set;

import firefighter.roster.entity.Call_Sign;
import firefighter.roster.entity.Personnell;
import lombok.Data;
import lombok.NoArgsConstructor;
import firefighter.roster.controller.model.FirefighterRosterCallSign;
import firefighter.roster.controller.model.FirefighterRosterPersonnell;
import firefighter.roster.entity.Location;

@Data
@NoArgsConstructor
public class FirefighterRosterLocation {
	private Long locationId;
	  private String locationFireDepartment;
	  private String locationStreetAddress;
	  private String locationCity;
	  private String locationState;
	  private String locationZip;
	  private String locationPhone;
	  private Set<FirefighterRosterPersonnell> personnells = new HashSet<>();
	  
	  public FirefighterRosterLocation(Location location) {
		locationId = location.getLocationId();
		locationFireDepartment = location.getLocationFireDepartment();
		locationStreetAddress = location.getLocationStreetAddress();
		locationCity = location.getLocationCity();
		locationState = location.getLocationState();
		locationZip = location.getLocationZip();
		locationPhone = location.getLocationPhone();
		
		
		
		for(Personnell personnell : location.getPersonnell()) {
		  personnells.add(new FirefighterRosterPersonnell(personnell));
		}
	  }

}