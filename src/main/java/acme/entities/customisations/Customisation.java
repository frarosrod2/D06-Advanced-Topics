
package acme.entities.customisations;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customisation extends DomainEntity {

	// Serialisation identifier ----------------------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes ------------------------------------------------------------------------

	//	@NotNull
	//	@ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
	//	private Collection<String> spamWords;

	@NotBlank
	private String				spamWords;

	@NotNull
	@Range(min = 0, max = 100)
	private Double				spamThreshold;

	@NotBlank
	private String				activitySectors;
	// Derived Attributes ----------------------------------------------------------------

	// Relationships ---------------------------------------------------------------------
}
