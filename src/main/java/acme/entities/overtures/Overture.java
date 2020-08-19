
package acme.entities.overtures;

import java.beans.Transient;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Overture extends DomainEntity {

	// Serialization identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	private String				title;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				creation;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Future
	private Date				deadline;

	@NotBlank
	private String				description;

	@NotNull
	@Valid
	private Money				minMoney;

	@NotNull
	@Valid
	private Money				maxMoney;

	@NotBlank
	@Email
	private String				email;

	@Transient
	public String getRange() {
		StringBuilder res = new StringBuilder();
		res.append(this.minMoney.getAmount());
		res.append(" --- ");
		res.append(this.maxMoney.getAmount());
		res.append(" ");
		res.append(this.maxMoney.getCurrency());

		return res.toString();
	}

}
