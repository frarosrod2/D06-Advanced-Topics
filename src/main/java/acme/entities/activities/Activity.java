
package acme.entities.activities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Activity extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				title;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date				start;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date				end;

	@NotNull
	@Valid
	private Money				budget;

	//Relations
	//	@NotNull
	//	@Valid
	//	@ManyToOne(optional = false)
	//	private InvestmentRound		investmentRound;

}
