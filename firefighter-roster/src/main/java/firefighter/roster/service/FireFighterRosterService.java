package firefighter.roster.service;

import java.util.Objects;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import firefighter.roster.controller.model.FirefighterRosterCallSign;
import firefighter.roster.controller.model.FirefighterRosterLocation;
import firefighter.roster.controller.model.FirefighterRosterPersonnell;
import firefighter.roster.dao.CallSignDao;
import firefighter.roster.dao.LocationDao;
import firefighter.roster.dao.PersonnellDao;
import firefighter.roster.entity.Call_Sign;
import firefighter.roster.entity.Location;
import firefighter.roster.entity.Personnell;

@Service
public class FireFighterRosterService {
	@Autowired
	  private LocationDao locationDao;
	  
	  @Autowired
	  private PersonnellDao personnellDao;
	  
	  @Autowired
	  private CallSignDao callsignDao;
	  
	  @Transactional(readOnly = false)
	  public FirefighterRosterLocation saveFirefighterRoster(FirefighterRosterLocation firefighterRosterLocation) {
		Long locationId = firefighterRosterLocation.getLocationId();
		Location location = findOrCreateFirefighterRoster(locationId);
		
		copyFirefighterRosterFields(firefighterRosterLocation, firefighterRosterLocation);
		return new FirefighterRosterLocation(locationDao.save(location));
	  }
	  
	  private void copyFirefighterRosterFields(FirefighterRosterLocation firefighterRosterLocation,
			  FirefighterRosterLocation firefighterRosterData) {
	   firefighterRosterLocation.setLocationFireDepartment(firefighterRosterData.getLocationFireDepartment());
	   firefighterRosterLocation.setLocationStreetAddress(firefighterRosterData.getLocationStreetAddress());
	   firefighterRosterLocation.setLocationId(firefighterRosterData.getLocationId());
	   firefighterRosterLocation.setLocationCity(firefighterRosterData.getLocationCity());
	   firefighterRosterLocation.setLocationPhone(firefighterRosterData.getLocationPhone());
	   firefighterRosterLocation.setLocationState(firefighterRosterData.getLocationState());
	   firefighterRosterLocation.setLocationZip(firefighterRosterData.getLocationZip());
	  }
	  
	  private Location findOrCreateFirefighterRoster(Long locationId) {
		if(Objects.isNull(locationId)) {
		  return new Location();
		}
		else {
		  return findFirefighterRosterById(locationId);
		}
	  }
	  
	  public Location findFirefighterRosterById(Long locationId) {
		return locationDao.findById(locationId)
			.orElseThrow(() -> new NoSuchElementException(
				"Location with ID=" + locationId + "was not found."));
	  }
	  
	  private void copyPersonnellFields(Personnell personnell,
		  FirefighterRosterPersonnell firefighterRosterPersonnell) {
		personnell.setPersonnellFistName(firefighterRosterPersonnell.getPersonnellFirstName());
		personnell.setPersonnellId(firefighterRosterPersonnell.getPersonnellId());
		personnell.setPersonnellStatus(firefighterRosterPersonnell.getPersonnellStatus());
		personnell.setPersonnellLastName(firefighterRosterPersonnell.getPersonnellLastName());
		personnell.setPersonnellAge(firefighterRosterPersonnell.getPersonnellAge());
	  }
	  
	  private void copyCallSignFields(Call_Sign call_sign,
		  FirefighterRosterCallSign firefighterRosterCallSign) {
	    call_sign.setCallSignId(firefighterRosterCallSign.getCallsignId());
	    call_sign.setCallSignName(firefighterRosterCallSign.getCallSignName());
	  }
	  
	  private Personnell findOrCreatePersonnell(Long locationId, Long personnellId) {
		if(Objects.isNull(personnellId)) {
		  return new Personnell();	
		}
		return findPersonnellById(locationId,personnellId);
	    }
	  
	  private Call_Sign findOrCreateCallSign(Long callSignId) {
		if(Objects.isNull(callSignId)) {
		  return new Call_Sign();
		}
		
		return findCallSignById(callSignId, callSignId);
	  }
	  
	  private Personnell findPersonnellById(Long locationId, Long personnellId) {
		Personnell personnell = personnellDao.findById(personnellId)
			.orElseThrow(() -> new NoSuchElementException(
				"Personnell with ID=" + personnellId + "was not found."));
		
		if(personnell.getLocation().getLocationId() != locationId) {
		  throw new IllegalArgumentException("The personnell with ID=" + personnellId
			  + "is not personnell at the fire department with ID=" + locationId + ".");	  
		}
		
		return personnell;
	  }
	  
	  private Call_Sign findCallSignById(Long locationId, Long callSignId) {
		Call_Sign call_Sign = callsignDao.findById(callSignId)
			.orElseThrow(() -> new NoSuchElementException(
				"Call Sign with ID=" + callSignId + "was not found."));
		
		boolean found = false;
		
		for(Location location : Call_Sign.getLocation()) {
		  if(location.getLocationId() == locationId) {
			found = true;
			break;
		  }
		}
		
		if(!found) {
		  throw new IllegalArgumentException("The call sign with ID=" + callSignId
			  + "is not a member of the firefighter roster with ID=" + locationId); 	  
		}
		
		return call_Sign;
	  }
	  
	  @Transactional(readOnly = false)
	  public FirefighterRosterPersonnell savePersonnell(Long locationId,
		  FirefighterRosterPersonnell firefighterRosterPersonnell) {
	    Location location = findLocationById(locationId);
	    Long personnellId = firefighterRosterPersonnell.getPersonnellId();
	    Personnell personnell = findOrCreatePersonnell(locationId, personnellId);
	    
	    copyPersonnellFields(personnell, firefighterRosterPersonnell);
	    
	    personnell.setLocation(location);
	    location.getPersonnell().add(personnell);
	    
	    return new FirefighterRosterPersonnell(personnellDao.save(personnell));
	  }
	  
	  public Location findLocationById(Long locationId) {
		// TODO Auto-generated method stub
		return locationDao.findById(locationId)
		.orElseThrow(() -> new NoSuchElementException("location not found"));
	}

	@Transactional
	  public FirefighterRosterCallSign saveCallSign(Long personnellId,
		FirefighterRosterCallSign firefighterRosterCallSign) {
		Personnell personnell = findPersonnellById(personnellId, personnellId);
		Long callsignId = firefighterRosterCallSign.getCallsignId();
		Call_Sign callSign = findOrCreateCallSign(personnellId, callsignId);
		
		copyCallSignFields(callSign, firefighterRosterCallSign);
		
		callSign.getPersonnell().add(personnell);
		personnell.getCall_signs().add(callSign);
		
		Call_Sign dbCustomer = callsignDao.save(callSign);
		
		return new FirefighterRosterCallSign(dbCustomer);
	  }
	  
	  private Call_Sign findOrCreateCallSign(Long personnellId, Long callsignId) {
		if(Objects.isNull(callsignId)) {
			return new Call_Sign();
		}
	  return findCallSignById(personnellId, callsignId); 
		  
	}

	@Transactional(readOnly = true)
	  public List<FirefighterRosterLocation> retrieveAllFirefighterRoster() {
		List<Location> locations = locationDao.findAll();
		
		List<FirefighterRosterLocation> result = new LinkedList<>();
		
		for(Location location : locations) {
			FirefighterRosterLocation psd = new FirefighterRosterLocation(location);
		  
		  psd.getPersonnells().clear();
		 
		  
		  result.add(psd);
		}
		
		return result;
	  }
	  
	  @Transactional(readOnly = true)
	  public FirefighterRosterLocation retrieveLoctionById(Long locationId) {
		return new FirefighterRosterLocation(findLocationById(locationId));  
	  }
	  
	  @Transactional(readOnly = false)
	  public void deleteLocationById(Long locationId) {
		Location location = findLocationById(locationId);
		locationDao.delete(location);
	  }
	
	}
