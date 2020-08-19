
package acme.entities.creditCards;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Range;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CreditCard extends DomainEntity {

	//Serialisation identifier ----------------

	private static final long	serialVersionUID	= 1L;

	//Serialisation identifier ----------------

	@NotBlank
	private String				holderName;

	@NotBlank
	@CreditCardNumber
	private String				number;

	@NotBlank
	private String				brand;

	@NotNull
	@Range(min = 100, max = 999)
	private Integer				cvv;

	@NotNull
	@Range(min = 1, max = 12)
	private Integer				expMonth;

	@NotNull
	@Range(min = 10, max = 99)
	private Integer				expYear;

	//Relationships

	//	@Valid
	//	@OneToOne(optional = false)
	//	private Banner				banner;
}
