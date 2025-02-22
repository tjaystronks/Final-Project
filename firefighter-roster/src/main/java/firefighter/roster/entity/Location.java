package firefighter.roster.entity;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Entity
@Data
public class Location {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long locationId;
	  private String locationFireDepartment;
	  private String locationStreetAddress;
	  private String locationCity;
	  private String locationState;
	  private String locationZip;
	  private String locationPhone;
	  
	  	  
	  @OneToMany(mappedBy = "location", cascade = CascadeType.ALL,
			  orphanRemoval = true)
	  @EqualsAndHashCode.Exclude
	  @ToString.Exclude
	  private Set<Personnell> personnell = new HashSet<>();

	}


