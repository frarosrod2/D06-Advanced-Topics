
package acme.entities.investmentRounds;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.URL;

import acme.entities.accountingRecords.AccountingRecord;
import acme.entities.activities.Activity;
import acme.entities.applications.Application;
import acme.entities.forums.Forum;
import acme.entities.roles.Entrepreneur;
import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "creationMoment")
})
public class InvestmentRound extends DomainEntity {

	private static final long				serialVersionUID	= 1L;

	@Pattern(regexp = "^[A-Z]{3}-[0-9]{2}-[0-9]{6}")
	@NotBlank
	private String							ticker;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	private Date							creationMoment;

	@NotBlank
	@Pattern(regexp = "^(SEED)?(ANGEL)?(SERIES-A)?(SERIES-B)?(SERIES-C)?(BRIDGE)?$")
	private String							round;

	@NotBlank
	private String							title;

	@NotBlank
	private String							description;

	@NotNull
	@Valid
	private Money							money;

	@URL
	private String							link;

	@NotNull
	private boolean							finalMode;

	//Relations

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Entrepreneur					entrepreneur;

	@NotNull
	@Valid
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@Fetch(value = FetchMode.SUBSELECT)
	private Collection<Activity>			workProgramme;

	@NotNull
	@Valid
	@OneToMany(mappedBy = "investmentRound", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private Collection<Application>			applications;

	@NotNull
	@Valid
	@OneToMany(mappedBy = "investmentRound", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@Fetch(value = FetchMode.SUBSELECT)
	private Collection<AccountingRecord>	accountingRecords;

	@NotNull
	@Valid
	@OneToOne(optional = false)
	private Forum							forum;
}
