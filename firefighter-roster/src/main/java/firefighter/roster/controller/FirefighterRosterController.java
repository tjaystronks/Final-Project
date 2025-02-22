package firefighter.roster.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import firefighter.roster.controller.model.FirefighterRosterCallSign;
import firefighter.roster.controller.model.FirefighterRosterLocation;
import firefighter.roster.controller.model.FirefighterRosterPersonnell;
import firefighter.roster.entity.Location;
import firefighter.roster.service.FireFighterRosterService;


@RestController
@RequestMapping("/firefighter_roster")
@Slf4j
public class FirefighterRosterController {
	@Autowired
	  private FireFighterRosterService firefighterRosterService;
	  
	  @PostMapping
	  @ResponseStatus(code = HttpStatus.CREATED)
	 public FirefighterRosterLocation insertFirefighterRoster(@RequestBody FirefighterRosterLocation firefighterRosterData) {
	   log.info("Creating firefighter roster {}", firefighterRosterData);
	   return firefighterRosterService.saveFirefighterRoster(firefighterRosterData);
	  }
	  
	  @PutMapping("/{locationId}")
	  public FirefighterRosterLocation updateFirefighterRoster(@PathVariable Long locationId,
	      @RequestBody FirefighterRosterLocation locationData) {
	    locationData.setLocationId(locationId);
	    log.info("Updating firefighter roster {}", locationId);
	    return firefighterRosterService.saveFirefighterRoster(locationData);
	  }
	 
	@PostMapping("/{locationId}/personnell")
	@ResponseStatus(code = HttpStatus.CREATED)
	public FirefighterRosterPersonnell addPersonnellToFirefighterRoster(@PathVariable Long locationId,
	    @RequestBody FirefighterRosterPersonnell firefighterRosterPersonnell) {
	  log.info("Adding personnell {} to firefighter roster with ID={}", firefighterRosterPersonnell,
		  locationId);
	  
	  return firefighterRosterService.savePersonnell(locationId, firefighterRosterPersonnell);
	}
	@PostMapping("/{locationId}/call sign")
	@ResponseStatus(code = HttpStatus.CREATED)
	public FirefighterRosterCallSign addCustomerToFirefighterRoster(@PathVariable Long locationId,
	    @RequestBody FirefighterRosterCallSign firefighterRosterCallSign) {
	 log.info("Adding call sign {} to firefighter roster with ID={}", firefighterRosterCallSign,
		locationId);
	 
	 return firefighterRosterService.saveCallSign(locationId, firefighterRosterCallSign);
	}
	    
	@GetMapping("/{locationId}")
	public Location findlocationById(@PathVariable Long locationId) {
	  log.info("Retrieving firefighter roster with ID={}", locationId);
	  return firefighterRosterService.findLocationById(locationId);
	}

	@DeleteMapping("/{locationId}")
	public Map<String, String> deleteFirefighterRosterById(@PathVariable Long locationId) {
	  log.info("Deleting firefighter roster with ID={}", locationId);

	  firefighterRosterService.findFirefighterRosterById(locationId);
	  
	  return Map.of("message", "Firefighter Roster with ID=" + locationId + "deleted.");
	}
	}


