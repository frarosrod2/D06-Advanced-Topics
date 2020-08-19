
package acme.entities.roles;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import acme.framework.entities.Authenticated;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

public class BookkeeperRequest extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				name;

	@NotBlank
	private String				responsabilityStatement;

	@NotBlank
	@Pattern(regexp = "^(accepted)?(rejected)?(pending)?$")
	private String				state;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Authenticated		authenticated;

}
