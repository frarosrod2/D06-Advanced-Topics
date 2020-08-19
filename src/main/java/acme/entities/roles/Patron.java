
package acme.entities.roles;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import acme.entities.creditCards.CreditCard;
import acme.framework.entities.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Patron extends UserRole {

	private static final long	serialVersionUID	= 1L;

	//Organisation name
	@NotBlank
	private String				name;

	@Valid
	@OneToOne(optional = true)
	private CreditCard			creditCard;
}
